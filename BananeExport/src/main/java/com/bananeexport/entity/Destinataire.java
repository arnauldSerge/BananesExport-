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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Lazy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="destinataire")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
	
	@NotBlank(message="le nom est obligatoire")
	@Column(name="nom")
	private String nom;
	
	@NotBlank(message="le prenom est obligatoire")
	@Column(name="prenom")
	private String prenom;
	
	@NotBlank(message="l'email est obligatoire")
	@Email
	@Column(name="email" , unique = true)
	private String email;
	
	
	@NotBlank(message="l'adresse est obligatoire")
	@Column(name="adresse")
	private String adresse;
	
	@NotBlank(message="le code postal est obligatoire")
	@Column(name="code_postal")
	private String codePostal;
	
	
	@NotBlank(message="La ville est obligatoire")
	@Column(name="ville")
	private String ville;
	
	@NotBlank(message="Le pays est obligatoire")
	@Column(name="pays")
	private String pays;
	
	@Transient
	@Lazy
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "destinataire" )
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + ((commandes == null) ? 0 : commandes.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((pays == null) ? 0 : pays.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
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
		Destinataire other = (Destinataire) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equals(other.codePostal))
			return false;
		if (commandes == null) {
			if (other.commandes != null)
				return false;
		} else if (!commandes.equals(other.commandes))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (pays == null) {
			if (other.pays != null)
				return false;
		} else if (!pays.equals(other.pays))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}
	
	
	
}
