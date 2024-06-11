package org.event.config;

import org.event.Entity.Event;
import org.event.Entity.EventDto;
import org.event.Entity.RelevantEvent;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(EventDto.class, Event.class)
                .addMappings(mapper -> {
                    mapper.map(EventDto::getIdEvent, Event::setIdEvent);
                    mapper.map(EventDto::getName, Event::setName);
                    mapper.map(EventDto::getDescription, Event::setDescription);
                    mapper.map(EventDto::getStartDate, Event::setStartDate);
                    mapper.map(EventDto::getEndDate, Event::setEndDate);
                    mapper.map(EventDto::getPrice, Event::setPrice);
                    mapper.map(EventDto::getCategory, Event::setCategory);
                    mapper.map(EventDto::getVideoUrl, Event::setVideoUrl);
                    mapper.map(EventDto::getNbParticipant, Event::setNbParticipant);

                    mapper.map(src -> {
                        try {
                            return src.getImage().getBytes();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }, Event::setImage);
                });

        modelMapper.createTypeMap(Event.class, RelevantEvent.class)
                .addMappings(mapper ->{
                    mapper.map(Event::getIdEvent, RelevantEvent::setIdEvent);
                    mapper.map(Event::getName, RelevantEvent::setName);
                    //mapper.map(src->src.getCampPlace().getAddress(), RelevantEvent::setCampPlaceLocation);
                    mapper.map(Event::getImage, RelevantEvent::setImage);


                });

        return modelMapper;
    }

}
