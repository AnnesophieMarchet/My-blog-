package com.wildcodeschool.myblog.model.repository;

import com.wildcodeschool.myblog.model.Article;
import com.wildcodeschool.myblog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTitle(String title);

    List<Article> findByContent(String content);

    List<Article> findByContentContaining(String content);

    List<Article> findByCreatedAtAfter(LocalDateTime createdAt);

    List<Article> findTop5ByOrderByCreatedAtDesc();

    List<Article> findByCategory(Category category);
}