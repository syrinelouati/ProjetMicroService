package org.event.Services;

import lombok.RequiredArgsConstructor;
import org.event.Entity.Event;
import org.event.Entity.EventCategory;
import org.event.Repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService{
    private final EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findTop10ByOrderByStartDateDesc();
    }

    public Page<Event> getFilteredEvents(
            List<EventCategory> categories,
            Double minPrice,
            Double maxPrice,
            Date startDate,
            Date endDate,
            String search,
            Pageable pageable) {
        return eventRepository.findByCategoryInAndPriceBetweenAndStartDateBetweenWithSearch(
                categories,
                minPrice,
                maxPrice,
                startDate,
                endDate,
                search,
                pageable);
    }


    @Override
    public Event getEventById(int id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void updateEvent(Event event) {

        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<EventCategory> getCategories() {
        List<EventCategory> categories = Arrays.asList(EventCategory.values());
        return categories;
    }

    @Override
    public long eventCount(){
        return  this.eventRepository.count();
    }
}
