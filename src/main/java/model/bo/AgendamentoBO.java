package model.bo;

import java.util.List;

import model.dao.AgendamentoDAO;
import model.vo.Agendamento;
import model.vo.seletor.AgendamentoSeletor;

public class AgendamentoBO {
	AgendamentoDAO dao = new AgendamentoDAO();

	public Agendamento salvar(Agendamento agendamento) {
		
		return dao.salvar(agendamento);
	}

	public List<Agendamento> listarAgendamento(AgendamentoSeletor seletor) {
		// TODO Auto-generated method stub
		return dao.listarAgendamento(seletor);
	}

}
