package com.gabrielcoelho.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielcoelho.cursomc.domain.Cidade;
import com.gabrielcoelho.cursomc.domain.Cliente;
import com.gabrielcoelho.cursomc.domain.Endereco;
import com.gabrielcoelho.cursomc.domain.enums.TipoCliente;
import com.gabrielcoelho.cursomc.dtos.ClienteDTO;
import com.gabrielcoelho.cursomc.repositories.CidadeRepository;
import com.gabrielcoelho.cursomc.repositories.ClienteRepository;
import com.gabrielcoelho.cursomc.repositories.EnderecoRepository;
import com.gabrielcoelho.cursomc.services.exceptions.DataIntegrityException;
import com.gabrielcoelho.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);

		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID : " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pages = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pages);
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente objNew = find(obj.getId());
		updateData(objNew, obj);
		return repository.save(objNew);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar um cliente que possui associações!");
		}
	}
	
	public Cliente fromDTO(ClienteDTO obj) {
		Cliente cli = new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), obj.getCpfCnpj(), TipoCliente.toEnum(obj.getTipoCliente()));
		Optional<Cidade> cid = cidadeRepository.findById(obj.getCidadeId());
		Endereco end = new Endereco(null, obj.getLogradouro(), obj.getNumero(), obj.getComplemento(), obj.getBairro(), obj.getCep(), cli, cid.get());
		cli.getEnderecos().add(end);
		cli.getTelefones().add(obj.getTelefone1());
		if(obj.getTelefone2() != null) {
			cli.getTelefones().add(obj.getTelefone2());
		}
		if(obj.getTelefone3() != null) {
			cli.getTelefones().add(obj.getTelefone3());
		}
		
		return cli;
	}

	private void updateData(Cliente objNew, Cliente obj) {
		objNew.setNome(obj.getNome());
		objNew.setEmail(obj.getEmail());
	}
}
