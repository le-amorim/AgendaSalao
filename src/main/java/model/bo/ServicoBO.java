package model.bo;

import java.util.ArrayList;

import model.dao.ServicoDAO;
import model.vo.Servico;

public class ServicoBO {
ServicoDAO dao = new ServicoDAO();
	public ArrayList<Servico> consultarTodos() {
		// TODO Auto-generated method stub
		return dao.consultarTodos();

	}
	public Servico salvar(Servico servico) {
		// TODO Auto-generated method stub
		return dao.salvar(servico);
	}
	
	public String excluir(Servico servicoExcluir) {
		String mensagem = "";
		
		if(dao.excluir(servicoExcluir.getIdservico())) {
			mensagem += "Servico excluído com sucesso";
		
		}else {
			mensagem += "Erro ao excluir empregado";
		}
			
			
		return mensagem;
	}

}
