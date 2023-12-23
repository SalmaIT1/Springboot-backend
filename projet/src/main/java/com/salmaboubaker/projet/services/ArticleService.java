package com.salmaboubaker.projet.services;

import com.salmaboubaker.projet.entities.Article;
import com.salmaboubaker.projet.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }



    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }







    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article getArticleById(String articleId) {
        try {
            long id = Long.parseLong(articleId);
            return articleRepository.findById(id).orElse(null);
        } catch (NumberFormatException e) {
            // Handle the case where articleId cannot be parsed as a long
            // For example, log an error, throw an exception, or return a default value
            return null;
        }
    }


    public Article updateArticle(String articleId, Article updatedArticle) {
        try {
            long id = Long.parseLong(articleId);
            Article existingArticle = articleRepository.findById(id).orElse(null);

            if (existingArticle != null) {
                // Update fields based on your requirements
                existingArticle.setDesignation(updatedArticle.getDesignation());
                // Update other fields as needed
                return articleRepository.save(existingArticle);
            }
        } catch (NumberFormatException e) {
            // Handle the case where articleId cannot be parsed as a long
            // For example, log an error, throw an exception, or return a default value
        }

        return null;
    }


    public void deleteArticle(String articleId) {
        // Check if the input is a valid number
        try {
            Long articleIdLong = Long.parseLong(articleId);

            // Use the Long value to delete the article
            articleRepository.deleteById(articleIdLong);
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid number
            // Log an error, throw an exception, or handle it in a way that makes sense for your application
            // For example, you can log the error and not proceed with the deletion
            System.err.println("Invalid articleId format. Cannot delete article. ArticleId: " + articleId);
        }
    }

}
