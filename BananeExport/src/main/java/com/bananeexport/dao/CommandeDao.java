package com.bananeexport.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestParam;

import com.bananeexport.entity.Commande;
@RepositoryRestResource
public interface CommandeDao extends JpaRepository<Commande, Long> {
	
	List<Commande> findByDestinataireId(@RequestParam("destinataireId") Long destinataireId);
	
	@Override
	@RestResource(exported =false)
	<S extends Commande> S save(S entity);

}
