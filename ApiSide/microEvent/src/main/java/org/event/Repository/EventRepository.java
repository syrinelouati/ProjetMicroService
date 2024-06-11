package org.event.Repository;

import org.event.Entity.Event;
import org.event.Entity.EventCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EventRepository  extends JpaRepository<Event,Integer> {
    List<Event> findTop4ByCategory(EventCategory category);
    List<Event> findTop10ByOrderByStartDateDesc();


    @Query("SELECT e FROM Event e WHERE " +
            "(:search IS NULL OR e.name LIKE %:search% OR e.description LIKE %:search%) AND " +
            "e.category IN :categories AND " +
            "e.price BETWEEN :minPrice AND :maxPrice AND " +
            "e.startDate BETWEEN :startDate AND :endDate")
    Page<Event> findByCategoryInAndPriceBetweenAndStartDateBetweenWithSearch(
            @Param("categories") List<EventCategory> categories,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("search") String search,
            Pageable pageable);

    long count();
}