package com.cag.marketplace.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cag.marketplace.entity.Article;
//@RepositoryRestResource
public interface ArticleDao extends JpaRepository<Article, Long> {

}
