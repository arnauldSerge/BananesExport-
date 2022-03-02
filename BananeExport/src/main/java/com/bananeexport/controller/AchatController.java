package com.bananeexport.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bananeexport.dto.AchatDto;
import com.bananeexport.dto.DataUtils;
import com.bananeexport.entity.Commande;
import com.bananeexport.entity.Destinataire;
import com.bananeexport.service.AchatService;
import com.bananeexport.service.DestinataireService;

@RestController
@RequestMapping("api/achats")
@Validated 
public class AchatController {
	@Autowired
	private AchatService achatService;
	@Autowired
	private DestinataireService destinataireService;
	
	
	@PostMapping("/commander" )
	public ResponseEntity<AchatDto> commander(@Valid @RequestBody AchatDto achat) {
		 Destinataire dest =destinataireService.
				findByDestinataire(DataUtils.createDestinataireFromDto(achat.getDestinataire()));
		if(dest == null) {
			dest = destinataireService.saveDestinataire(achat.getDestinataire());
		}
		AchatDto newAchat = DataUtils.createAchatFromDto(achatService.save(achat, dest));
		return new ResponseEntity<AchatDto> (newAchat, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param newProduct
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<Commande> Update(@RequestBody AchatDto achat) {
		Destinataire dest =destinataireService.findByDestinataire(DataUtils.createDestinataireFromDto(achat.getDestinataire()));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(achatService.save(achat, dest));	
	}

}
