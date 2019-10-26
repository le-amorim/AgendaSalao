package model.bo;

import model.dao.AgendamentoDAO;
import model.vo.Agendamento;

public class AgendamentoBO {
	AgendamentoDAO dao = new AgendamentoDAO();

	public Agendamento salvar(Agendamento agendamento) {
		
		return dao.salvar(agendamento);
	}

}
