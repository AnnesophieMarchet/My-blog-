package com.wildcodeschool.myblog.controller;

import com.wildcodeschool.myblog.model.Article;
import com.wildcodeschool.myblog.model.repository.ArticleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleRepository articleRepository;
     public ArticleController(ArticleRepository articleRepository){
         this.articleRepository= articleRepository;
     }


    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        Article savedArticle = articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {

        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        article.setTitle(articleDetails.getTitle());
        article.setContent(articleDetails.getContent());
        article.setUpdatedAt(LocalDateTime.now());

        Article updatedArticle = articleRepository.save(article);
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {

        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        articleRepository.delete(article);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Article>> getArticlesByTitle(@RequestParam("title") String title) {
        System.out.println("Searching for articles with title: " + title);
        List<Article> articles = articleRepository.findByTitle(title);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }
//    @GetMapping("/search/content")
//    public ResponseEntity<List<Article>> getArticlesByContent(@RequestParam("content") String content) {
//        List<Article> articles = articleRepository.findByContent(content);
//        if (articles.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(articles);
//    }
// Méthode pour liste d'articles dont le contenu contient une chaine de caractère fournie en paramètre
    @GetMapping("/search/content")
    public ResponseEntity<List<Article>> getArticlesByContent(@RequestParam("content") String content) {
        List<Article> articles = articleRepository.findByContentContaining(content);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }



    // Méthode pour  des articles créés après une certaine date
    @GetMapping("/search/createdAfter")
    public ResponseEntity<List<Article>> getArticlesCreatedAfter(@RequestParam("date") String date) {
        LocalDateTime createdAt = LocalDateTime.parse(date);
        List<Article> articles = articleRepository.findByCreatedAtAfter(createdAt);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    // Méthode  5 derniers articles créés
    @GetMapping("/latest")
    public ResponseEntity<List<Article>> getFiveLastArticles() {
        List<Article> articles = articleRepository.findTop5ByOrderByCreatedAtDesc();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }
}

