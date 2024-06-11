package com.omaima.microcampplace.repositories;

import com.omaima.microcampplace.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Integer> {
}