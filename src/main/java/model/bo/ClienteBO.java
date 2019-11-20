package model.bo;

import java.util.ArrayList;

import model.dao.ClienteDAO;
import model.vo.Cliente;

public class ClienteBO {
	ClienteDAO dao = new ClienteDAO();

	public Cliente salvar(Cliente cliente) {
		ClienteDAO dao = new ClienteDAO();
		
		return dao.salvar(cliente);
	}

	
	public ArrayList<Cliente> consultarTodos() {
		
		return dao.consultarTodos();
	}


	public boolean excluirCliente(int linhaSelecionada) {
		// TODO Auto-generated method stub
		return dao.excluirCliente(linhaSelecionada);
	}

}
