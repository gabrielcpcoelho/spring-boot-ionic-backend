package com.gabrielcoelho.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielcoelho.cursomc.domain.Categoria;
import com.gabrielcoelho.cursomc.repositories.CategoriaRepositoy;
import com.gabrielcoelho.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepositoy repository;

	public Categoria buscar(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);

		return categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! ID : " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
}
