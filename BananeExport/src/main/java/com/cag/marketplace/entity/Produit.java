package com.cag.marketplace.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "produit")
@Setter
@Getter
public class Produit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name="description", nullable = false)
	private String description;
	
	@Column(name="prix", nullable = false)
	private BigDecimal prix;
	
	@Column(name="nom", nullable = false)
	private String nom;
	/**
	 * Nombre minimum de jour pour la livraison
	 */
	@Column(name="nombre_jour_livraison")
	private Integer nombreJourLivraison;
}
