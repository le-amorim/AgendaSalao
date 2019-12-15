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

	public Agendamento salvar(Agendamento agendamento) {
		
		return dao.salvar(agendamento);
	}

	public List<Agendamento> listarAgendamento(AgendamentoSeletor seletor) {
		return dao.listarAgendamento(seletor);
	}


	public ArrayList<Agendamento> consultarPorDia(LocalDate dataEscolhida, Profissional profissionalSelecionado) {
		
		return dao.consultarPorDia(dataEscolhida, profissionalSelecionado);
	
	} 

	public String excluir(Agendamento agendamentoSelecionado) {
		String mensagem = "";
		
		if(dao.excluir(agendamentoSelecionado.getIdAgendamento())) {
			mensagem += "Agendamento Excluido com sucesso!";
		
		}else {
			mensagem += "Não foi possivel Excluir Agendamento";
		}
		
		
		return mensagem;
	}

	public ArrayList<Agendamento> consultarPeloDiaAtual(LocalDate dataHoje, Profissional profissionalSelecionado) {
	
		return dao.consultarPeloDiaAtual(dataHoje, profissionalSelecionado);
	}

}
