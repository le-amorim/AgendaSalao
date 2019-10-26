package controller;

import model.bo.AgendamentoBO;
import model.vo.Agendamento;
import model.vo.Profissional;

public class ControladoraAgendamento {
AgendamentoBO bo = new AgendamentoBO();
	public static String validar(String servicoDigitado, String valorDigitado, Profissional profissionalSelecionado) {
		String mensagem = "";

		if ((servicoDigitado == null) || (servicoDigitado.isEmpty())) {
			mensagem += "serviço não pode estar vazio";
		}

		if ((valorDigitado == null) || (valorDigitado.isEmpty())) {
			mensagem += "O valor deve ser preenchido";
		}

		if ((profissionalSelecionado == null)) {
			mensagem += "profissional deve ser selecionado";
		}

		return mensagem;
	}

	public Agendamento salvar(Agendamento agendamento) {
		
		return bo.salvar(agendamento);
	}


}
