package com.bananeexport.entity;

import java.io.Serializable;

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
@Table(name="adresse")
@Setter
@Getter
public class Adresse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6170261835308086437L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero_et_Rue")
	private String numeroEtRue;
	
	
	@ManyToOne
	@JoinColumn(name="ville_id", nullable = false)
	private Ville ville;

}
