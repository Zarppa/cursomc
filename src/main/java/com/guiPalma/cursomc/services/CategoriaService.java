package com.guiPalma.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guiPalma.cursomc.domain.Categoria;
import com.guiPalma.cursomc.repositories.CategoriaRepository;
import com.guiPalma.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		
		Categoria obj = repo.findOne(id);
		
		if(obj == null ) {
			throw new ObjectNotFoundException("Objeto não enontrado! Id: " +id
					+", Tipo: " + Categoria.class.getName());
		} 
		return obj;
		
	}

}
