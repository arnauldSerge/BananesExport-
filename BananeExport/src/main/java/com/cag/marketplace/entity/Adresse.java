package com.cag.marketplace.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="adresse")
@Setter
@Getter
@ToString(exclude = {"id"})
public class Adresse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6170261835308086437L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero_et_Rue")
	private String numeroEtRue;
	
	
	@ManyToOne
	@JoinColumn(name="ville_id", nullable = false)
	private Ville ville;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroEtRue == null) ? 0 : numeroEtRue.hashCode());
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
		Adresse other = (Adresse) obj;
		if (numeroEtRue == null) {
			if (other.numeroEtRue != null)
				return false;
		} else if (!numeroEtRue.equals(other.numeroEtRue))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}
	
	

}
