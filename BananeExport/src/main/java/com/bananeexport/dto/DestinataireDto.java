package com.bananeexport.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DestinataireDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id;  
	
	@NotBlank(message="le nom est obligatoire")
	private String nom;
	
	@NotBlank(message="l'adresse est obligatoire")
	private String adresse;
	
	@NotBlank(message="le code postal est obligatoire")
	private String codePostal;
	
	@NotBlank(message="La ville est obligatoire")
	private String ville;
	
	@NotBlank(message=" le pays est obligatoire")
	private String pays;

}
