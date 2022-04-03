package com.cag.marketplace.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="article")
@Setter
@Getter
@ToString(exclude = {"id"})
public class Article implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@ToString.Exclude
	private Long id;
	
	@Column(name = "reference", unique = true)
	private String reference; 
	
	@Min(value = 1)
	@Column(name = "quantite")
	private Long quantite;
	
	@Column(name = "prix")
	private BigDecimal prix;
		
	@ManyToOne
	//@Transient
	@JoinColumn(name="produit_id", nullable = false)
	private Produit produit;
	
//	@Column(name="produit_id", nullable = false)
//	private Long produit;
	
	@Transient
	//@Lazy
	@ManyToOne
	@JoinColumn(name="commande_id", nullable = false)
	private Commande commande;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
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
		Article other = (Article) obj;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}

	
	
	
	
}