package com.gabrielcoelho.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.gabrielcoelho.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto, Date dataPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagamentoComBoleto.setDataVencimento(cal.getTime());
	}
}
