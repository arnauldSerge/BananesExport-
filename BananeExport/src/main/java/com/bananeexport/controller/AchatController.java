package com.bananeexport.controller;

import javax.validation.Valid;

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
import com.bananeexport.service.impl.AchatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/achats")
@Validated
@RequiredArgsConstructor
public class AchatController {
	
	private final AchatService achatService;	
	
	@PostMapping("/commander" )
	public ResponseEntity<AchatDto> commander(@Valid @RequestBody AchatDto achat) {
		AchatDto newAchat = DataUtils.createAchatFromDto(achatService.save(achat));
		return new ResponseEntity<AchatDto> (newAchat, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param newProduct
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<Commande> Update(@RequestBody AchatDto achat) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(achatService.save(achat));	
	}

}
