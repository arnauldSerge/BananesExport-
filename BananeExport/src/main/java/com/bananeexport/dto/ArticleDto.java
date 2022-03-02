package com.bananeexport.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.bananeexport.validator.MultipleDeConstraint;

import lombok.Data;

@Data
public class ArticleDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Min(value = 1, message = "La quantité doit etre renseignée")
	@MultipleDeConstraint(facteur = 25, message = "La quantité doit être un multiple de 25")
	@Max(value = 10000, message = "La quantité maximale prise en charge est de 10000")
	private Long quantite;
	
	@NotNull( message = "Il faut selectionné un produit")
	private Long produitId;
	
	private Long id;
	
	private BigDecimal prix;

}
