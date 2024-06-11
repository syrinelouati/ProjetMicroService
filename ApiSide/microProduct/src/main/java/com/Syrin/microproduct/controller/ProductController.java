package com.Syrin.microproduct.controller;

import com.Syrin.microproduct.enumeration.ProductCategory;
import com.Syrin.microproduct.service.IProductService;
import com.Syrin.microproduct.dto.ProductDto;
import com.Syrin.microproduct.entity.Product;
import com.Syrin.microproduct.entity.ProductFile;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.ArrayList;
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

