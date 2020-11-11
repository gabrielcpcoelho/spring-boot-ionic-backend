package com.gabrielcoelho.cursomc.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.gabrielcoelho.cursomc.domain.Cliente;
import com.gabrielcoelho.cursomc.services.validation.ClienteInsert;

@SuppressWarnings("deprecation")
@ClienteInsert
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "O Nome do Cliente é obrigatório")
	@Length(min = 5, max = 120, message = "O Nome do Cliente deve conter entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "O E-mail do Cliente é obrigatório")
	@Email(message = "E-mail inválido")
	private String email;

	@NotEmpty(message = "O Tipo de Documento do Cliente é obrigatório")
	private String cpfCnpj;
	
	private Integer tipoCliente;
	
	@NotEmpty(message = "O Endereço do Cliente é obrigatório")
	private String logradouro;
	
	@NotEmpty(message = "O Número do Endereço do Cliente é obrigatório")
	private String numero;
	
	private String complemento;
	
	@NotEmpty(message = "O Bairro do Endereço do Cliente é obrigatório")
	private String bairro;
	
	@NotEmpty(message = "O CEP do Endereço do Cliente é obrigatório")
	private String cep;
	
	@NotEmpty(message = "O Telefone do Cliente é obrigatório. Por favor informar o telefone principal")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;

	public ClienteDTO() {
	}
	
	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

}
