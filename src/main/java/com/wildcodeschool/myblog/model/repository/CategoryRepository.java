package com.wildcodeschool.myblog.model.repository;

import com.wildcodeschool.myblog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
