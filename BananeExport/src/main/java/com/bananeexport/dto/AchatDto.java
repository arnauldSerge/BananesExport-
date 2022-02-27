package com.bananeexport.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AchatDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "{error.user.name.empty}")
	@NotEmpty(message = "{error.user.name.empty}" )
	private String userName;
	
	@NotNull(message = "{error.livraisonSouhaitePour.empty}" )
	private Date livraisonSouhaitePour;
	
	@NotEmpty(message = "{error.articles.empty}" )
	private Set<ArticleDto> articles = new HashSet<>();
}
