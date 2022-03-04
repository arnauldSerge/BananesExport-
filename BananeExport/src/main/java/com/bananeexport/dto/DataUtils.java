package com.bananeexport.dto;

import java.util.stream.Collectors;

import com.bananeexport.entity.Article;
import com.bananeexport.entity.Commande;
import com.bananeexport.entity.Destinataire;

public class DataUtils {
	
	public static Destinataire  createDestinataireFromDto(DestinataireDto dto) {
		Destinataire destinataire = new Destinataire();
		destinataire.setId(dto.getId());
		destinataire.setAdresse(dto.getAdresse());
		destinataire.setCodePostal(dto.getCodePostal());
		destinataire.setVille(dto.getVille());
		destinataire.setPays(dto.getPays());
		destinataire.setNom(dto.getNom());
		destinataire.setEmail(dto.getEmail());
		return destinataire;
	}
	
	
	public static DestinataireDto  createDestinataireDto(Destinataire dest) {
		DestinataireDto destinataire = new DestinataireDto();
		destinataire.setId(dest.getId());
		destinataire.setAdresse(dest.getAdresse());
		destinataire.setCodePostal(dest.getCodePostal());
		destinataire.setVille(dest.getVille());
		destinataire.setPays(dest.getPays());
		destinataire.setNom(dest.getNom());
		destinataire.setEmail(dest.getEmail());
		return destinataire;
	}
	
	
	public static AchatDto createAchatFromDto(Commande commande) {
		AchatDto achat = new AchatDto();
		achat.setId(commande.getId());
		achat.setLivraisonSouhaitePour(commande.getDateLivraison());
		achat.setNumeroCommande(commande.getNumeroCommande());
		achat.setDestinataire(createDestinataireDto(commande.getDestinataire()));
		achat.setArticles(commande.getArticles().stream()
				.map(article ->  createArticleDto(article)).collect(Collectors.toSet()));
		return achat;
		
	}

	
	
	public static ArticleDto createArticleDto(Article article) {
		ArticleDto articleDto = new ArticleDto();
		articleDto.setProduitId(article.getProduit());
		articleDto.setQuantite(article.getQuantite());
		articleDto.setId(article.getId());
		articleDto.setPrix(article.getPrix());
		return articleDto;
	}
	
	
	public static Article createArticleFromDto(ArticleDto articleDto) {
		Article article = new Article();
		article.setProduit(articleDto.getProduitId());
		article.setQuantite(articleDto.getQuantite());
		article.setId(articleDto.getId());
		article.setPrix(articleDto.getPrix());
		return article;
	}
}
