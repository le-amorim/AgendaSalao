package controller;

import java.util.ArrayList;

import model.bo.ServicoBO;
import model.vo.Servico;

public class ControladoraServico {
	ServicoBO bo = new ServicoBO();
	private String mensagem ="";

	public ArrayList<Servico> consultarTodos() {
		// TODO Auto-generated method stub
		return bo.consultarTodos();
	}

	public String validar(String servicoDigitado) {

		if((servicoDigitado == null) || (servicoDigitado.trim().length() < 3)) {
			 mensagem  += "servico deve ter no minímo 3 letras ";
		}
		return mensagem;
	}

	public Servico salvar(Servico servico) {
		// TODO Auto-generated method stub
		return bo.salvar(servico);
	}


	public String excluir(Servico servicoDigitado) {
		// TODO Auto-generated method stub
		return bo.excluir(servicoDigitado);
	}

}
