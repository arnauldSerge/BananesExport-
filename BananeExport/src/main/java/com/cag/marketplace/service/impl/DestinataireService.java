package com.cag.marketplace.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cag.marketplace.dao.DestinataireDao;
import com.cag.marketplace.dto.DataUtils;
import com.cag.marketplace.dto.DestinataireDto;
import com.cag.marketplace.entity.Destinataire;
import com.cag.marketplace.exception.ResourceAlreadyExistException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DestinataireService  {
	@Autowired
	private final DestinataireDao destinataireDao;

	
	public boolean existsDestinataire(Destinataire dest) {
		Example<Destinataire> destinataireExample = Example.of(dest);
		return destinataireDao.exists(destinataireExample);
	}
	
	
	
	
	public Destinataire findByDestinataire(Destinataire dest) {
		Example<Destinataire> destinataireExample = Example.of(dest);
		Optional<Destinataire> opt_dest = destinataireDao.findOne(destinataireExample);
		return opt_dest.isPresent()? opt_dest.get() : null;
	}

	
	public Destinataire saveDestinataire(DestinataireDto dest) {
		Destinataire entity = DataUtils.createDestinataireFromDto(dest);
		if(!existsDestinataire(entity)) {
				//Creation ou mise à jour
			return   destinataireDao.save(entity);
		} else {
			//Pas d'update possible, si creation
			throw new ResourceAlreadyExistException("Destination");
		}
	}
	
	

}
