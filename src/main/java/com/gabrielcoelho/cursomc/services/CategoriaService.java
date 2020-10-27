package com.gabrielcoelho.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gabrielcoelho.cursomc.domain.Categoria;
import com.gabrielcoelho.cursomc.repositories.CategoriaRepositoy;
import com.gabrielcoelho.cursomc.services.exceptions.DataIntegrityException;
import com.gabrielcoelho.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepositoy repository;
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Categoria find(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);

		return categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! ID : " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pages = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pages);
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar uma categoria que possui produtos!");
		}
	}
	
}
