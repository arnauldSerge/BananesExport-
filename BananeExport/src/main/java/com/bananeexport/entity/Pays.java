package com.bananeexport.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="pays")
@Setter
@Getter
public class Pays implements Serializable{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String code;
	@Column(name="libelle", unique = true)
	private String libelle;
	
	@Column(name="continent")
	private String continent;

}
