package com.bananeexport.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;

import lombok.Data;

@Entity
@Table(name="destinataire")
@Data
public class Destinataire implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	(strategy = GenerationType.IDENTITY)
	private Long id;  
	
	@NotNull
	@Column(name="nom")
	private String nom;
	
	@NotBlank
	@Column(name="adresse")
	private String adresse;
	
	@NotBlank
	@Column(name="code_postal")
	private String codePostal;
	
	@NotNull
	@NotBlank
	@Column(name="ville")
	private String ville;
	
	@Column(name="pays")
	private String pays;
	
	@Transient
	@Lazy
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user" )
	private Set<Commande> commandes = new HashSet<Commande>();
	
	public void AjouterCommande(Commande item) {
		if(item!=null) {
			if(commandes == null) {
				commandes = new HashSet<Commande>();
			}
			commandes.add(item);
			item.setDestinataire(this);
		}
	}
}
