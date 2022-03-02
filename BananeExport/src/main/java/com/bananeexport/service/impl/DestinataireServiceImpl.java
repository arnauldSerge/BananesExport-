package com.bananeexport.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.bananeexport.dao.DestinataireDao;
import com.bananeexport.dto.DataUtils;
import com.bananeexport.dto.DestinataireDto;
import com.bananeexport.entity.Destinataire;
import com.bananeexport.exception.ResourceAlreadyExistException;
import com.bananeexport.service.DestinataireService;

@Service
public class DestinataireServiceImpl implements DestinataireService {
	@Autowired
	private DestinataireDao destinataireDao;

	@Override
	public boolean existsDestinataire(Destinataire dest) {
		Example<Destinataire> destinataireExample = Example.of(dest);
		return destinataireDao.exists(destinataireExample);
	}
	
	
	
	@Override
	public Destinataire findByDestinataire(Destinataire dest) {
		Example<Destinataire> destinataireExample = Example.of(dest);
		Optional<Destinataire> opt_dest = destinataireDao.findOne(destinataireExample);
		return opt_dest.isPresent()? opt_dest.get() : null;
	}

	@Override
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
