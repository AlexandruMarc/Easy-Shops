package com.easyshops.backend.repository;

import com.easyshops.backend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
  List<Image> findByProductId(Long id);
}
