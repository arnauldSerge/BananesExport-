package com.bananeexport.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bananeexport.entity.Destinataire;

public interface DestinataireDao extends JpaRepository<Destinataire, Long> {
	Destinataire findByNom(String nom);
}
