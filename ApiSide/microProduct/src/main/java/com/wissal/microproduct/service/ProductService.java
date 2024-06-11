package com.wissal.microproduct.service;

import com.wissal.microproduct.entity.Product;
import com.wissal.microproduct.enumeration.ProductCategory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.wissal.microproduct.repository.ProductFileRepository;
import com.wissal.microproduct.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final ProductFileRepository productFileRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }
    @Override
    public List<ProductCategory> getCategories() {
        List<ProductCategory> categories = Arrays.asList(ProductCategory.values());
        return categories;
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        productFileRepository.saveAll(product.getFiles());
        productRepository.saveAndFlush(product);

    }
    @Override
    public List<Product> getProductByCategory(ProductCategory category){
        return productRepository.findTop4ByCategory(category);
    }
    @Override

    @Transactional
    public void UpdateProduct(Product product) {
        productFileRepository.saveAll(product.getFiles());
        productRepository.saveAndFlush(product);

    }
    @Override
    public Page<Product> getFilteredProducts(
            List<ProductCategory> categories,
            Double minPrice,
            Double maxPrice,
            String search,
            Pageable pageable){
        return productRepository.findByCategoryInAndPriceBetweenWithSearch(
                categories,
                minPrice,
                maxPrice,
                search,
                pageable);
    }
    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);

    }

    // for home screen
    @Override
    public List<Product> getTop4Product(){
        return this.productRepository.findTop4ByOrderByIdProductDesc();
    }
    @Override
    public long productCount(){
        return  this.productRepository.count();
    }
}
