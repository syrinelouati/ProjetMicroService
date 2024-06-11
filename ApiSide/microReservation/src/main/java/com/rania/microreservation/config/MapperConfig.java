package com.rania.microreservation.config;

import com.rania.microreservation.dto.ReservationDto;
import com.rania.microreservation.entity.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        //product auto mapper  (ProductDto => Product
        modelMapper.createTypeMap(ReservationDto.class, Reservation.class)
                .addMappings(mapper ->{
                    mapper.map(ReservationDto::getNotes, Reservation::setNotes);
                    mapper.map(ReservationDto::getNbredays, Reservation::setNbredays);
                    mapper.map(ReservationDto::getStartDate, Reservation::setStartDate);
                    mapper.map(ReservationDto::getEndDate, Reservation::setEndDate);
                });

        return modelMapper;
    }

}
