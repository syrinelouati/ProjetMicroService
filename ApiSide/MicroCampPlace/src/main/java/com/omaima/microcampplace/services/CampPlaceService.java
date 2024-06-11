package com.omaima.microcampplace.services;

import com.omaima.microcampplace.dto.CampPlaceSelectDto;
import com.omaima.microcampplace.entities.CampPlace;
import com.omaima.microcampplace.enumeration.CampPlaceCategory;
import com.omaima.microcampplace.enumeration.State;
import com.omaima.microcampplace.repositories.CampPlaceRepository;
import com.omaima.microcampplace.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CampPlaceService implements ICampPlaceService {
    private final CampPlaceRepository campPlaceRepository;
    private final ImageRepository imageRepository;
    @Override
    public List<CampPlace> getAllCampPlaces() {
        return campPlaceRepository.findAll();
    }

    @Override
    public CampPlace getCampPlaceById(int id) {
        return campPlaceRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void addCampPlace(CampPlace campPlace) {

        imageRepository.saveAll(campPlace.getImages());
        campPlaceRepository.saveAndFlush(campPlace);

    }

    @Override
    public void updateCampPlace(CampPlace campPlace) {
        campPlaceRepository.save(campPlace);

    }

    @Override
    public void deleteCampPlace(int id) {
        campPlaceRepository.deleteById(id);

    }


    @Override
    public List<CampPlaceCategory> getCategories() {
        List<CampPlaceCategory> categories = Arrays.asList(CampPlaceCategory.values());
        return categories;
    }
    @Override
    public List<State> getState() {
        List<State> states = Arrays.asList(State.values());
        return states;
    }
    @Override
    public List<CampPlace> getCampPlaceByCategory(CampPlaceCategory category){
        return campPlaceRepository.findTop4ByCategory(category);
    }
    @Override
    public Page<CampPlace> getFiltredCampPlace(List <CampPlaceCategory> category, List <State> state, String search, Pageable pageable){
        return campPlaceRepository.findByCampPlaceCategoryInAndStateInWithSearch(category, state, search,pageable);
    }

    // for home screen
    @Override
    public List<CampPlace> findTop5CampPlaces() {
        return campPlaceRepository.findTop5ByOrderByIdCampPlaceAsc();
    }

    @Override
    public long campPlacesCount(){
        return this.campPlaceRepository.count();
    }
    @Override
    public List<CampPlaceSelectDto> getCampPlaceSelect(){
        return this.campPlaceRepository.findAllCampPlaces();
    }

}