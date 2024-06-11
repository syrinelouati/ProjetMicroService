package com.omaima.microcampplace.controllers;

import com.omaima.microcampplace.dto.CampPlaceDto;
import com.omaima.microcampplace.entities.CampPlace;
import com.omaima.microcampplace.entities.Image;
import com.omaima.microcampplace.enumeration.CampPlaceCategory;
import com.omaima.microcampplace.enumeration.State;
import com.omaima.microcampplace.services.ICampPlaceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/campPlace")

public class CampPlaceController {
    private final ModelMapper modelMapper;
    private final ICampPlaceService iCampPlaceService ;
    @GetMapping
    public List<CampPlace> getAll(){
        return iCampPlaceService.getAllCampPlaces();
    }

    @GetMapping("/{id}")
    public CampPlace get(@PathVariable int id){
        return iCampPlaceService.getCampPlaceById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        iCampPlaceService.deleteCampPlace(id);
    }
    @PutMapping
    public void update(@RequestBody CampPlace campPlace){
        iCampPlaceService.updateCampPlace(campPlace);
    }
    @PostMapping
    public CampPlace addCampPlace(@ModelAttribute CampPlaceDto campPlaceDto)  {
        CampPlace campPlace = modelMapper.map(campPlaceDto, CampPlace.class);
        List<Image> images = new ArrayList<Image>();
      /*  for(int i = 0; i<campPlaceDto.getImages().size(); i++){

            try {
                images.add(new Image(0, campPlaceDto.getImages().get(i).getBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        campPlace.setImages(images); */
        iCampPlaceService.addCampPlace(campPlace);
        return campPlace;
    }
    @GetMapping("/categories")
    public List<CampPlaceCategory> getCategories(){
        return  iCampPlaceService.getCategories();
    }
    @GetMapping("/state")
    public List<State> getState(){
        return  iCampPlaceService.getState();
    }
    @GetMapping("/getSimilaireCampPlace/{category}")
    public List<CampPlace> getCampPlaceByCategory(@PathVariable CampPlaceCategory category){
        return iCampPlaceService.getCampPlaceByCategory(category);
    }

    @GetMapping("/filteredCampPlaces")
    public Page<CampPlace> getFilteredCampPlaces(
            @RequestParam(required = false) List<CampPlaceCategory> categories,
            @RequestParam(required = false) List<State> states,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(required = false,defaultValue = "id,asc") String sort,
            @RequestParam(required = false,defaultValue = "") String search



    ) {



        if (categories == null) {
            categories = Arrays.asList(CampPlaceCategory.values());
        }
        if (states == null) {
            states = Arrays.asList(State.values());
        }



        Pageable pageable = PageRequest.of(page, size, getSort(sort));
        return iCampPlaceService.getFiltredCampPlace(categories,states,search ,pageable);

    }



    private Sort getSort(String sort) {
        String[] sortParams = sort.split(",");
        String property = sortParams[0];
        String direction = sortParams[1];
        return Sort.by(Sort.Direction.fromString(direction), property);
    }
}
