package com.bananeexport.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bananeexport.entity.Produit;
@RepositoryRestResource
public interface ProduitDao extends JpaRepository<Produit, Long> {

}
