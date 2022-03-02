package com.bananeexport.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bananeexport.dto.DataUtils;
import com.bananeexport.dto.DestinataireDto;
import com.bananeexport.service.DestinataireService;

@RepositoryRestController
public class DestinataireController {
	@Autowired
	DestinataireService destinataireService;

	@PostMapping("/destinataires")
	public ResponseEntity<DestinataireDto> create(@Valid @RequestBody DestinataireDto destinataire){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(DataUtils.createDestinataireDto(destinataireService.saveDestinataire(destinataire)));
	}
	
	
	/**
	 * 
	 * @param newProduct
	 * @return
	 */
	@PutMapping("/destinataires/{id}")
	public ResponseEntity<DestinataireDto> Update(@RequestBody DestinataireDto destinataire) {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(DataUtils.createDestinataireDto(destinataireService.saveDestinataire(destinataire)));	
	}
}
