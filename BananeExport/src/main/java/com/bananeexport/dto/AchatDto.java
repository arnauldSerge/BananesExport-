package com.bananeexport.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AchatDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long id;
	
	@NotNull(message = "{Le destinataire est obligatoire}")
	private DestinataireDto destinataire;
	
	@NotNull(message = "Une date de livraison doit etre renseignée" )
	//@DateLivraisonConstraint(message = "{error.date.livraison.impossible}", minimum = 7)
	private LocalDate livraisonSouhaitePour;
	
	@NotEmpty(message = " il faut au moins un article" )
	private Set<@Valid ArticleDto> articles = new HashSet<>();
	
	private String numeroCommande;
}
