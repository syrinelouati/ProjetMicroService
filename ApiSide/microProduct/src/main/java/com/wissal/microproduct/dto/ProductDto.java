package com.wissal.microproduct.dto;

import com.wissal.microproduct.enumeration.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer idProduct;
    private String name;

    private String description;
    private String state;

    private double price;
    private boolean available;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private  double size;
    private  double weight ;
    private String color;
    private List<MultipartFile> files;
}
