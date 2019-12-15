package controller;

import java.util.ArrayList;

import model.bo.ProfissionalBO;
import model.vo.Profissional;

public class ControladoraFuncionario {

	ProfissionalBO bo = new ProfissionalBO();
	public String validarCamposDigitado(String nomeDigitado, String especialidadeDigitado) {
		String mensagem = "";
		if (nomeDigitado.isEmpty() || nomeDigitado.trim().length() < 3) {
			mensagem += "Profissional deve ter no minimo 3 letras";
		}
		if (especialidadeDigitado.isEmpty() || especialidadeDigitado.trim().length() < 3) {
			mensagem += "especialidade deve ter no minimo 3 letras";
		}
		return mensagem;
	}

	public Profissional salvar(Profissional profissional) {
		
		return bo.salvar(profissional);
	}

	public ArrayList<Profissional> consultarTodos() {
		
		return bo.consultarTodos();
	}

	public ArrayList<Profissional> atualizarCombo(ArrayList<Profissional> profissionais) {
			
		if(profissionais.isEmpty()) {
			System.out.println("deu ruim");
			
		}else {
			
		System.out.println("deu bom");
		}
		
		return profissionais;
	}

}
