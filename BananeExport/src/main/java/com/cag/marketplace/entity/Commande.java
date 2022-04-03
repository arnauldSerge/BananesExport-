package com.cag.marketplace.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
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
import lombok.ToString;

@Entity
@Table(name="commande")
@Setter
@Getter
@ToString(exclude = {"articles", "id"})
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
	private LocalTime dateCommande; 
	
	
	@Column(name="date_livraison")
	private LocalDate dateLivraison;
	
	
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCommande == null) ? 0 : dateCommande.hashCode());
		result = prime * result + ((destinataire == null) ? 0 : destinataire.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		if (dateCommande == null) {
			if (other.dateCommande != null)
				return false;
		} else if (!dateCommande.equals(other.dateCommande))
			return false;
		if (destinataire == null) {
			if (other.destinataire != null)
				return false;
		} else if (!destinataire.equals(other.destinataire))
			return false;
		return true;
	}

	
	
	
	
	
	
}
