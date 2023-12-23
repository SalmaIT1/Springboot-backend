package com.salmaboubaker.projet.controller;

import com.salmaboubaker.projet.entities.Article;
import com.salmaboubaker.projet.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping("/")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }



    @PostMapping("/")
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }
    @GetMapping("/{articleId}")
    public Article getArticleById(@PathVariable String articleId) {
        return articleService.getArticleById(articleId);
    }
    @PutMapping("/{articleId}")
    public Article updateArticle(@PathVariable String articleId, @RequestBody Article updatedArticle) {
        return articleService.updateArticle(articleId, updatedArticle);
    }
    @DeleteMapping("/{articleId}")
    public void deleteArticle(@PathVariable String articleId) {
        articleService.deleteArticle(articleId);
    }
}
