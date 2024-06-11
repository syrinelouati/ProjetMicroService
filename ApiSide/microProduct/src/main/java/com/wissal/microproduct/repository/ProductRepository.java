package com.wissal.microproduct.repository;

import com.wissal.microproduct.entity.Product;
import com.wissal.microproduct.enumeration.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findTop4ByCategory(ProductCategory category);
    @Query("SELECT p FROM Product p WHERE " +
            "(:search IS NULL OR p.name LIKE %:search% OR p.description LIKE %:search%) AND " +
            "p.category IN :categories AND " +
            "p.price BETWEEN :minPrice AND :maxPrice ")
    Page<Product> findByCategoryInAndPriceBetweenWithSearch(
            @Param("categories") List<ProductCategory> categories,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("search") String search,
            Pageable pageable);

    //for home screen
    List<Product> findTop4ByOrderByIdProductDesc();

    long count();
}
