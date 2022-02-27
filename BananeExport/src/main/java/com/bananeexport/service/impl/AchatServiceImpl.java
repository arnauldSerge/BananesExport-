package com.bananeexport.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.bananeexport.dto.AchatResponseDto;
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
	public AchatResponseDto commander(AchatDto achat) {

		Commande commande  = new Commande();
		String numero = generateNumeroDeCommande();
		commande.setNumeroCommande(numero);
		commande.setLivraisonSouhaitePour(achat.getLivraisonSouhaitePour());
		
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
			article.setPrixTotal(produit.getPrix().multiply(BigDecimal.valueOf(item.getQuantite())));
			article.setCommande(commande);
			articles.add(article);
			delais.add(produit.getNombreJourLivraison());
		});
		commande.setArticles(articles);	

		//recuperer le destinantaire s'il existe
		String userName = achat.getUserName();
		Destinataire destinataire = destinataireDao.findByNom(userName);
		if(destinataire ==null) {
			throw new ResourceNotFoundException("Destinataire", "Nom", userName);
		}
		commande.setDestinataire(destinataire);

		Date date = commande.getLivraisonSouhaitePour();
		//date.
		
		

		LocalDate today = LocalDate.now();
		LocalDate localDateToBeValidate = date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
		boolean isValid = (localDateToBeValidate.isAfter(today) 
				&& ChronoUnit.DAYS.between(today,localDateToBeValidate) >= Collections.max(delais));

		if(!isValid) {
			LocalDate minDate = LocalDate.now().plusDays(Collections.max(delais));
			String[] tab = {minDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))};
			throw new BusinessResourceException("error.date.livraison", tab);
			 // String message = messages.getMessage("auth.message.invalidToken", null, locale);
		}


		//calcul du prix total
		BigDecimal sum = articles.stream()
				.map(x -> x.getPrixTotal())    // map
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		commande.setPrixTotal(sum);

		commandeDao.save(commande);
		return new AchatResponseDto(numero);
	}
	/**
	 * 
	 * @return // genere un Random UUID  pour la commande
	 */
	private String generateNumeroDeCommande() {

		return UUID.randomUUID().toString();
	}

}
