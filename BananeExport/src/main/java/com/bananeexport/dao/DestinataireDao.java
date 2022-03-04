package com.bananeexport.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.bananeexport.entity.Destinataire;

@RepositoryRestResource
public interface DestinataireDao extends JpaRepository<Destinataire, Long>, QueryByExampleExecutor<Destinataire> {
	//Destinataire findByNom(String nom);
	
	@Override
	@RestResource(exported =false)
	<S extends Destinataire> S save(S entity);
	
	@RestResource(exported =false)
	Destinataire findOneByEmail(String email);
}
