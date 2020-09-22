package com.gabrielcoelho.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielcoelho.cursomc.domain.Categoria;
import com.gabrielcoelho.cursomc.repositories.CategoriaRepositoy;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepositoy repository;

	public Optional<Categoria> buscar(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);

		return categoria;
	}

}
