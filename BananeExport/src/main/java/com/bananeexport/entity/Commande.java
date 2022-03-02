package com.bananeexport.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Lazy;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="commande")
@Setter
@Getter
public class Commande implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Date effective de commande
	 */
	@Column(name="dateCommande", updatable = false)
	@CreationTimestamp
	private Date dateCommande; 
	
	
	@Column(name="date_livraison")
	private Date dateLivraison;
	
	
	
	@Column(name="numero_commande")
	private String numeroCommande;
	
//	/**
//	 * Quantite en nombre d'article(produits)
//	 */
//	@Column
//	private int quantiteTotal;
	
	@NotNull
	@Column(name="prix")
	private BigDecimal prix;
	
	
	@Lazy
	@ManyToOne
	@JoinColumn(name="destinataire_id")
	private Destinataire destinataire;
	

	@Lazy
	@Transient
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commande" )
	private Set<Article> articles = new HashSet<Article>();
	
	public void ajouterUnArticle(Article article) {
		if(article!=null) {
			if(articles == null) {
				articles = new HashSet<Article>();
			}
			articles.add(article);
			article.setCommande(this);
		}
	}
}
