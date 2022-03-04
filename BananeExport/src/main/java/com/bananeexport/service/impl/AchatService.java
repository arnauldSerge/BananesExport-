package com.bananeexport.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bananeexport.dao.CommandeDao;
import com.bananeexport.dao.DestinataireDao;
import com.bananeexport.dao.ProduitDao;
import com.bananeexport.dto.AchatDto;
import com.bananeexport.dto.ArticleDto;
import com.bananeexport.dto.DataUtils;
import com.bananeexport.entity.Article;
import com.bananeexport.entity.Commande;
import com.bananeexport.entity.Destinataire;
import com.bananeexport.entity.Produit;
import com.bananeexport.exception.BusinessResourceException;
import com.bananeexport.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AchatService  {
	
	private final CommandeDao commandeDao;
	private DestinataireDao destinataireDao;
	private final ProduitDao produitDao;
	
	@Transactional
	public Commande save(AchatDto achat) {
		
		
		Commande  commande;
		if(achat.getId()!=null ) { 		
		 commande =  commandeDao.findById(achat.getId()).map(cmd -> cmd)	
				. orElse( new Commande());
		} else {
			commande = new Commande();
		}
		
		if(achat.getId() == null) {
			String numero = generateNumeroDeCommande();
			commande.setNumeroCommande(numero);
		}
		
		commande.setDateLivraison(achat.getLivraisonSouhaitePour());
		commande.setPrix(new BigDecimal(0));
		
		
		//Remplir la liste d'acticles
		Set<ArticleDto> articleDtos = achat.getArticles();
		Set<Article> articles = articleDtos.stream()
		.map( a -> DataUtils.createArticleFromDto(a))
		.collect(Collectors.toSet());
		
		
		//Calculer le prix des articles, recuperer le delain 
		//et integrer la commande des produits
		Set<Integer> delais = new HashSet<Integer>();
		articles.forEach(article -> {
			Produit produit = produitDao.findById(article.getProduit())
					.orElseThrow(() -> new ResourceNotFoundException("Produit","ID",article.getProduit()));
			delais.add(produit.getNombreJourLivraison());
			BigDecimal prixArticle =BigDecimal.ZERO;		
			prixArticle = produit.getPrix().multiply(BigDecimal.valueOf(article.getQuantite()));
			article.setPrix(prixArticle);
			article.setCommande(commande);
		});
		commande.setArticles(articles);
		
		// Determiner le prix de la commande
		BigDecimal prixCommande = articles.stream().map(Article::getPrix).reduce(BigDecimal.ZERO, BigDecimal::add);
		commande.setPrix(prixCommande);
		
		LocalDate date = achat.getLivraisonSouhaitePour();
		

		boolean isValid = ChronoUnit.DAYS.between(LocalDate.now(),date) >= Collections.max(delais);

		if(!isValid) {
			LocalDate minDate = LocalDate.now().plusDays(Collections.max(delais));
			String[] tab = {"Date Livraison impossible avant le " + minDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))};
			throw new BusinessResourceException("Commande", tab[0],tab) ;
		}
		commande.setDateLivraison(achat.getLivraisonSouhaitePour());
		
		//Recupere ou creer le destinataire 
		Destinataire destinataire = destinataireDao.findOneByEmail(achat.getDestinataire().getEmail());
		commande.setDestinataire(destinataire);
		
		return  commandeDao.save(commande);
	}
	/**
	 * 
	 * @return // genere un Random UUID  pour la commande
	 */
	private String generateNumeroDeCommande() {

		return UUID.randomUUID().toString();
	}

}
