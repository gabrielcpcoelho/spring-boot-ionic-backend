package com.gabrielcoelho.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.gabrielcoelho.cursomc.domain.enums.TipoCliente;
import com.gabrielcoelho.cursomc.dtos.ClienteDTO;
import com.gabrielcoelho.cursomc.resources.exceptions.FieldErrorMessage;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		List<FieldErrorMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCpf(objDto.getCpfCnpj())) {
			list.add(new FieldErrorMessage("CpfCnpj", "CPF Inválido"));
		}

		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCnpj(objDto.getCpfCnpj())) {
			list.add(new FieldErrorMessage("CpfCnpj", "CNPJ Inválido"));
		}

		for (FieldErrorMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
