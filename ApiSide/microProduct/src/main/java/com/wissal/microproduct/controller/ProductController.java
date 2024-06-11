package com.wissal.microproduct.controller;

import com.wissal.microproduct.dto.ProductDto;
import com.wissal.microproduct.entity.Product;
import com.wissal.microproduct.entity.ProductFile;
import com.wissal.microproduct.enumeration.ProductCategory;
import com.wissal.microproduct.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
//@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final IProductService iProductService;

    private final ModelMapper modelMapper;

    @GetMapping
    public List<Product> getAll(){
        return iProductService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable int id){
        return iProductService.getProductById(id);
    }

    @PostMapping("/addProduct")
    public Product addProduct(@ModelAttribute ProductDto productDto)  {
        Product product = modelMapper.map(productDto, Product.class);
        List<ProductFile> images = new ArrayList<ProductFile>();
        for(int i = 0; i<productDto.getFiles().size(); i++){
            try {
                images.add(new ProductFile(0, productDto.getFiles().get(i).getBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        product.setFiles(images);
        iProductService.addProduct(product);
        return product;
    }
    @GetMapping("/categories")
    public List<ProductCategory> getCategories(){
        return iProductService.getCategories();
    }

    @GetMapping("/getSimilaireProduct/{category}")
    public List<Product> getProductByCategory(@PathVariable
                                              ProductCategory category){
        return iProductService.getProductByCategory(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        iProductService.deleteProduct(id);
    }

    @PutMapping
    public void update(@RequestBody Product product){
        iProductService.UpdateProduct(product);
    }

    //for home screen

    @GetMapping("/getNewestProduct")
    public List<Product> getProductByCategory(){
        return iProductService.getTop4Product();
    }
    @GetMapping("productCount")
    public long getProductsCount(){
        return this.iProductService.productCount();
    }
}

