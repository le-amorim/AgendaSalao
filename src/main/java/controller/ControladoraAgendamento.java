package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.bo.AgendamentoBO;
import model.vo.Agendamento;
import model.vo.Profissional;
import model.vo.Servico;
import model.vo.seletor.AgendamentoSeletor;

public class ControladoraAgendamento {
	AgendamentoBO bo = new AgendamentoBO();

	public static String validar(Servico servicoSelecionado, String valorDigitado,
			Profissional profissionalSelecionado,LocalDateTime dataComHoraSelecionado) {
		String mensagem = "";

		if ((valorDigitado == null) || (valorDigitado.isEmpty())) {
			mensagem += "O valor deve ser preenchido";
		}

		if ((profissionalSelecionado == null)) {
			mensagem += "profissional deve ser selecionado";
		}
		

		if ((dataComHoraSelecionado == null)) {
			mensagem += "é preciso prencher a data e horario para agendar";
		}
		
		return mensagem;
	}

	public String salvar(Agendamento agendamento) {

		return bo.salvar(agendamento);
	}

	public List<Agendamento> listarAgendamento(AgendamentoSeletor seletor) {
		
		return bo.listarAgendamento(seletor);
	}

	public ArrayList<Agendamento> consultaPorDia(LocalDate dataEscolhida, Profissional profissionalSelecionado) {
		

		return bo.consultarPorDia(dataEscolhida, profissionalSelecionado);
	}

	public String excluir(Agendamento agendamentoSelecionado) {
		
		return bo.excluir(agendamentoSelecionado);
	}

	public ArrayList<Agendamento> consultarPeloDiaAtual(LocalDate dataHoje, Profissional profissionalSelecionado) {
	
		return bo.consultarPeloDiaAtual(dataHoje, profissionalSelecionado);
	}

	public static String validarData(LocalDate dataEscolhida) {
		String mensagemDoBuscar ="";
		if ((dataEscolhida == null)) {
			mensagemDoBuscar = "É preciso escolher uma data para consulta";
		}
			return mensagemDoBuscar;
	}

}
