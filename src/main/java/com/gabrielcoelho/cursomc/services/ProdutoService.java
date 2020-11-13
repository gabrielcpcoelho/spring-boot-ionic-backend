package com.gabrielcoelho.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gabrielcoelho.cursomc.domain.Categoria;
import com.gabrielcoelho.cursomc.domain.Produto;
import com.gabrielcoelho.cursomc.repositories.CategoriaRepositoy;
import com.gabrielcoelho.cursomc.repositories.ProdutoRepository;
import com.gabrielcoelho.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepositoy categoriaRepository;

	public Produto find(Integer id) {
		Optional<Produto> pedido = repository.findById(id);

		return pedido.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! ID : " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> idsCategoria, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pages = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(idsCategoria);
		return repository.search(nome, categorias, pages);
	}
}
