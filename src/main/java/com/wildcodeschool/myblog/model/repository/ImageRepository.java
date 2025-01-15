package com.wildcodeschool.myblog.model.repository;

import com.wildcodeschool.myblog.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Long> {
}