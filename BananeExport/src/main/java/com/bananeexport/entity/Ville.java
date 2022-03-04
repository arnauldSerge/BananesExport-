package com.bananeexport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="ville")
@Setter
@Getter
public class Ville {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codePostal")
	private String codePostal;
	
	@Column(name = "departement")
	private String departement;
	
	@Column(name = "region")
	private String region;
	
	@Column(name = "nom")
	private String nom;
	
	@ManyToOne
	@JoinColumn(name="pays_id", nullable = false)
	private Pays pays;

}
