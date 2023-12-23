package com.salmaboubaker.projet.repository;


import com.salmaboubaker.projet.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}

