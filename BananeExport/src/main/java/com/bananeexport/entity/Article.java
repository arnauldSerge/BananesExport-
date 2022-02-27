package com.bananeexport.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.context.annotation.Lazy;

import lombok.Data;
@Entity
@Table(name="article")
@Data
public class Article implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Min(value = 1)
	@Column(name = "quantite")
	private Long quantite;
	
	@Column(name = "prix_total")
	private BigDecimal prixTotal;
		
//	@ManyToOne
//	@JoinColumn(name="produit_id", nullable = false)
//	private Produit produit;
	
	@Column(name="produit_id", nullable = false)
	private Long produit;
	@Lazy
	@ManyToOne
	@JoinColumn(name="commande_id", nullable = false)
	private Commande commande;
}