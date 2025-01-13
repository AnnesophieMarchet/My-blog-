package com.wildcodeschool.myblog.model.repository;

import com.wildcodeschool.myblog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article,Long> {


}
