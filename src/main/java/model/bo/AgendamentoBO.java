package model.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.dao.AgendamentoDAO;
import model.vo.Agendamento;
import model.vo.Profissional;
import model.vo.seletor.AgendamentoSeletor;

public class AgendamentoBO {
	AgendamentoDAO dao = new AgendamentoDAO();

	public String salvar(Agendamento agendamento) {
		String msg = "";
		
		if (dao.verificarSePossuiHorarioMarcado(agendamento.getProfissional(), agendamento.getDataComHora())) {
			msg += ("Já possui cliente marcado para esse horário");
		} else {
			dao.salvar(agendamento);
			msg += ("Agendamento Realizado");
		}
		
		return msg;
	}

	public List<Agendamento> listarAgendamento(AgendamentoSeletor seletor) {
		return dao.listarAgendamento(seletor);
	}

	public ArrayList<Agendamento> consultarPorDia(LocalDate dataEscolhida, Profissional profissionalSelecionado) {

		return dao.consultarPorDia(dataEscolhida, profissionalSelecionado);

	}

	public String excluir(Agendamento agendamentoSelecionado) {
		String mensagem = "";

		if (dao.excluir(agendamentoSelecionado.getIdAgendamento())) {
			mensagem += "Agendamento Excluido com sucesso!";

		} else {
			mensagem += "Não foi possivel Excluir Agendamento";
		}

		return mensagem;
	}

	public ArrayList<Agendamento> consultarPeloDiaAtual(LocalDate dataHoje, Profissional profissionalSelecionado) {

		return dao.consultarPeloDiaAtual(dataHoje, profissionalSelecionado);
	}

}
