package com.cag.marketplace.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.cag.marketplace.entity.Produit;
@RepositoryRestResource
public interface ProduitDao extends JpaRepository<Produit, Long> {

}
