package com.gabrielcoelho.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielcoelho.cursomc.domain.Cliente;
import com.gabrielcoelho.cursomc.repositories.ClienteRepository;
import com.gabrielcoelho.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);

		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID : " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
