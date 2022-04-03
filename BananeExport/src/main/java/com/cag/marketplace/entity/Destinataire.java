package com.cag.marketplace.entity;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Lazy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="destinataire")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"commandes","createDate"})
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
	@JoinColumn(name="adresse_id", nullable = false)
	private Adresse adresse;

	
	@Column(name="date_Creation", updatable = false)
	@CreationTimestamp
	private LocalTime createDate; 
	
	
	
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	
	
	
	
}
