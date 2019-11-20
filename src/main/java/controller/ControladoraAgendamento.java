package controller;

import java.util.List;

import model.bo.AgendamentoBO;
import model.vo.Agendamento;
import model.vo.Profissional;
import model.vo.Servico;
import model.vo.seletor.AgendamentoSeletor;

public class ControladoraAgendamento {
AgendamentoBO bo = new AgendamentoBO();
	public static String validar(Servico servicoSelecionado, String valorDigitado, Profissional profissionalSelecionado) {
		String mensagem = "";

		

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

	public List<Agendamento> listarAgendamento(AgendamentoSeletor seletor) {
		// TODO Auto-generated method stub
		return bo.listarAgendamento(seletor);
	}


}
