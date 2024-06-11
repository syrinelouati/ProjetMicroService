package com.omaima.microcampplace.config;
import org.modelmapper.ModelMapper;
import com.omaima.microcampplace.dto.CampPlaceDto;
import com.omaima.microcampplace.entities.CampPlace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
   @Bean
   public ModelMapper modelMapper() {
      ModelMapper modelMapper = new ModelMapper();

      modelMapper.createTypeMap(CampPlaceDto.class, CampPlace.class).addMappings(
              mapper -> {
                 mapper.map(CampPlaceDto::getName, CampPlace::setName);
                 mapper.map(CampPlaceDto::getEmail, CampPlace::setEmail);
                 mapper.map(CampPlaceDto::getTel, CampPlace::setTel);
                 mapper.map(CampPlaceDto::getAddress, CampPlace::setAddress);
                 mapper.map(CampPlaceDto::getState, CampPlace::setState);
                 mapper.map(CampPlaceDto::getCategory, CampPlace::setCategory);
                 mapper.map(CampPlaceDto::getDescription, CampPlace::setDescription);
                 mapper.map(CampPlaceDto::getLongitude, CampPlace::setLongitude);
                 mapper.map(CampPlaceDto::getLatitude, CampPlace::setLatitude);
              });
      return modelMapper;
   }
}

