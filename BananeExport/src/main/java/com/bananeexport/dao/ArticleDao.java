package com.bananeexport.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bananeexport.entity.Article;
@RepositoryRestResource
public interface ArticleDao extends JpaRepository<Article, Long> {

}
