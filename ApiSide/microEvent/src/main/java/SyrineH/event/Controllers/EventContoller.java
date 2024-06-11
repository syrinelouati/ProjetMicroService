package SyrineH.event.Controllers;

import SyrineH.event.Services.IEventService;
import lombok.RequiredArgsConstructor;
import SyrineH.event.Entity.Event;
import SyrineH.event.Entity.EventDto;
import SyrineH.event.Entity.EventCategory;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
@RequestMapping("api/event")
public class EventContoller {

    private final IEventService iEventService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<Event> getAll() {
        return iEventService.getAllEvents();
    }

    @GetMapping("/categories")
    public List<EventCategory> getCategories() {
        return iEventService.getCategories();
    }

    @GetMapping("/{id}")
    public Event get(@PathVariable int id) {
        return iEventService.getEventById(id);
    }

    @PostMapping("/addEvent")
    public Event addEvent(@RequestPart("event") EventDto eventDto,
                          @RequestPart("image") MultipartFile image) throws IOException {
        Event event = modelMapper.map(eventDto, Event.class);
        if (!image.isEmpty()) {
            event.setImage(image.getBytes());
        }
        iEventService.addEvent(event);
        return event;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        iEventService.deleteEvent(id);
    }

    @PutMapping
    public void update(@RequestPart("event") EventDto eventDto,
                       @RequestPart("image") MultipartFile image) throws IOException {
        Event event = modelMapper.map(eventDto, Event.class);
        if (!image.isEmpty()) {
            event.setImage(image.getBytes());
        }
        iEventService.updateEvent(event);
    }

    @GetMapping("/filteredEvents")
    public Page<Event> getFilteredEvents(
            @RequestParam(required = false) List<EventCategory> categories,
            @RequestParam(required = false, defaultValue = "0.0") Double minPrice,
            @RequestParam(required = false, defaultValue = "100000.0") Double maxPrice,
            @RequestParam(required = false, defaultValue = "2020-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false, defaultValue = "2050-12-12") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(required = false, defaultValue = "id,asc") String sort,
            @RequestParam(required = false, defaultValue = "") String search
    ) {
        if (categories == null) {
            categories = Arrays.asList(EventCategory.values());
        }

        Pageable pageable = PageRequest.of(page, size, getSort(sort));

        return iEventService.getFilteredEvents(
                categories,
                minPrice,
                maxPrice,
                startDate,
                endDate,
                search,
                pageable
        );
    }

    private Sort getSort(String sort) {
        String[] sortParams = sort.split(",");
        String property = sortParams[0];
        String direction = sortParams[1];
        return Sort.by(Sort.Direction.fromString(direction), property);
    }

    @GetMapping("eventCount")
    public long getEventsCount() {
        return iEventService.eventCount();
    }
}
