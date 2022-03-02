package com.bananeexport.service;

import com.bananeexport.dto.AchatDto;
import com.bananeexport.entity.Commande;
import com.bananeexport.entity.Destinataire;

public interface AchatService {
	Commande save(AchatDto achat, Destinataire dest);
}
