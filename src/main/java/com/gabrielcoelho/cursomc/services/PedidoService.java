package com.gabrielcoelho.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielcoelho.cursomc.domain.Pedido;
import com.gabrielcoelho.cursomc.repositories.PedidoRepository;
import com.gabrielcoelho.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido buscar(Integer id) {
		Optional<Pedido> pedido = repository.findById(id);

		return pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! ID : " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
