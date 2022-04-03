package com.cag.marketplace.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotBlank(message="le prenom est obligatoire")
	private String prenom;
	
	@NotBlank(message="l'adresse est obligatoire")
	private String adresse;
	
	@NotBlank(message="le code postal est obligatoire")
	private String codePostal;
	
	@NotBlank(message="La ville est obligatoire")
	private String ville;
	
	@NotBlank(message=" le pays est obligatoire")
	private String pays;
	
	@Email
	@NotNull
	private String email;

}
