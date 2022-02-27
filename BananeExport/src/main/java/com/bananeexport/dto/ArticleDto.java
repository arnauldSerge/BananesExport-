package com.bananeexport.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ArticleDto {
	
	@Min(value = 1, message = "{error.quatite.absent}")
	private Long quantite;
	@NotNull( message = "{error.produitid.absent}")
	private Long produitId;

}
