package com.wissal.microproduct.config;

import com.wissal.microproduct.dto.ProductDto;
import com.wissal.microproduct.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        //product auto mapper  (ProductDto => Product
        modelMapper.createTypeMap(ProductDto.class, Product.class)
                .addMappings(mapper -> {
                    mapper.map(ProductDto::getName, Product::setName);
                    mapper.map(ProductDto::getDescription, Product::setDescription);
                    mapper.map(ProductDto::getPrice, Product::setPrice);
                    mapper.map(ProductDto::getCategory, Product::setCategory);
                    mapper.map(ProductDto::getColor, Product::setColor);
                    mapper.map(ProductDto::getWeight, Product::setWeight);
                    mapper.map(ProductDto::getState, Product::setState);
                });

        return modelMapper;
    }

}
