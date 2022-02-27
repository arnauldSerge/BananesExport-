package com.bananeexport.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bananeexport.dto.AchatDto;
import com.bananeexport.dto.AchatResponseDto;
import com.bananeexport.service.AchatService;

@RestController
@RequestMapping("api/achats")
@Validated 
public class AchatController {
	@Autowired
	private AchatService achatService;
	
	
	@PostMapping("/commander" )
	public ResponseEntity<AchatResponseDto> commander(@Valid @RequestBody AchatDto achat) {
		return new ResponseEntity<AchatResponseDto> (achatService.commander(achat), HttpStatus.OK);
	}

}
