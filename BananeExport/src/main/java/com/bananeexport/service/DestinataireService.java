package com.bananeexport.service;

import com.bananeexport.dto.DestinataireDto;
import com.bananeexport.entity.Destinataire;

public interface DestinataireService {
	public boolean existsDestinataire(Destinataire dest);
	public Destinataire saveDestinataire(DestinataireDto dest);
	public Destinataire findByDestinataire(Destinataire dest);
	
}
