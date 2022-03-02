package com.bananeexport.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bananeexport.dao.CommandeDao;
import com.bananeexport.dao.DestinataireDao;
import com.bananeexport.dao.ProduitDao;
import com.bananeexport.dto.AchatDto;
import com.bananeexport.dto.ArticleDto;
import com.bananeexport.entity.Article;
import com.bananeexport.entity.Commande;
import com.bananeexport.entity.Destinataire;
import com.bananeexport.entity.Produit;
import com.bananeexport.exception.BusinessResourceException;
import com.bananeexport.exception.ResourceNotFoundException;
import com.bananeexport.service.AchatService;

@Service
public class AchatServiceImpl implements AchatService {
	@Autowired
	CommandeDao commandeDao;
	@Autowired
	DestinataireDao destinataireDao;
	@Autowired
	ProduitDao produitDao;
	
	

	@Override
	@Transactional
	public Commande save(AchatDto achat, Destinataire dest) {
		Commande commande;
		if(achat.getId()!=null ) { 		
		 commande =  commandeDao.findById(achat.getId()).map(cmd -> cmd)	
				. orElse( new Commande());
		} else {
			commande = new Commande();
		}

		if(achat.getId()==null) {
			String numero = generateNumeroDeCommande();
			commande.setNumeroCommande(numero);
		}
		
		commande.setDateLivraison(achat.getLivraisonSouhaitePour());
		commande.setPrix(new BigDecimal(0));
		//Remplir la liste d'acticles
		Set<ArticleDto> articleDtos = achat.getArticles();
		List<Integer> delais = new ArrayList<>();
		Set<Article> articles = new HashSet<>();
		articleDtos.forEach(item -> {
			Article article = new Article();
			article.setQuantite(item.getQuantite());
			article.setProduit(item.getProduitId());
			Produit produit = produitDao.findById(item.getProduitId())
					.orElseThrow(() -> new ResourceNotFoundException("Produit","ID",item.getProduitId()));
			BigDecimal prixArticle =BigDecimal.ZERO;
					
			prixArticle = produit.getPrix().multiply(BigDecimal.valueOf(item.getQuantite()));
			article.setPrix(prixArticle);
			article.setCommande(commande);
			articles.add(article);
			commande.setPrix(commande.getPrix().add(prixArticle));
			// pour determiner le delais de livraison valable
			delais.add(produit.getNombreJourLivraison());
		});
		commande.setArticles(articles);	
		
		
		commande.setDestinataire(dest);
		Date date = achat.getLivraisonSouhaitePour();
		//date.

		LocalDate today = LocalDate.now();
		LocalDate localDateToBeValidate = date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
		boolean isValid = (localDateToBeValidate.isAfter(today) 
				&& ChronoUnit.DAYS.between(today,localDateToBeValidate) >= Collections.max(delais));

		if(!isValid) {
			LocalDate minDate = LocalDate.now().plusDays(Collections.max(delais));
			String[] tab = {"Date Livraison impossible avant le " + minDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))};
			throw new BusinessResourceException("Commande", tab[0],tab) ;
		}
		commande.setDateLivraison(achat.getLivraisonSouhaitePour());
		
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
