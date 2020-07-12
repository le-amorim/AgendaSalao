package controller;

import java.util.ArrayList;

import model.bo.ClienteBO;
import model.dao.ClienteDAO;
import model.vo.Cliente;

public class ControladoraCliente {
	ClienteBO bo = new ClienteBO();
	ClienteDAO dao = new ClienteDAO();
	public String validar(String nomeCompletoCliente ,String telefoneCliente, String cpfCliente ,String observacao) {
		String mensagem ="";
		


		if((nomeCompletoCliente == null) || (nomeCompletoCliente.trim().length() < 3)) {
			mensagem +="Cliente deve ter no minímo 3 letras ";
		}

		
		if((telefoneCliente == null) || (telefoneCliente.trim().length() < 8)) {
			mensagem +="Telefone deve ter no minímo 8 digitos ";
		}

		if((cpfCliente == null) || (cpfCliente.trim().length() < 11)){
			mensagem +="Cliente deve ter no minímo 11 digitos";
			
		}
	

		return mensagem;
	}

	public String salvar(Cliente cliente) {
		
		
		return bo.salvar(cliente);
	}

	public ArrayList<Cliente> consultarTodos() {
		// TODO Auto-generated method stub
		return bo.consultarTodos();
	}

	
	public String excluir(Cliente clienteSelecionado) {
		
		return bo.excluir(clienteSelecionado);
		 
	}


}
