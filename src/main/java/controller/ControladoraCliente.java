package controller;

import java.util.ArrayList;

import model.bo.ClienteBO;
import model.vo.Cliente;

public class ControladoraCliente {
	ClienteBO bo = new ClienteBO();
	public String validar(String nomeCliente,String sobreNomeCliente, String telefoneCliente, String observacao) {
		String mensagem ="";


		if((nomeCliente == null) || (nomeCliente.trim().length() < 3)) {
			mensagem +="Cliente deve ter no minímo 3 letras ";
		}

		if((sobreNomeCliente == null) || (sobreNomeCliente.trim().length() < 3)) {
			mensagem +="Cliente deve ter no minímo 3 letras ";
		}
		
		if((telefoneCliente == null) || (telefoneCliente.trim().length() < 8)) {
			mensagem +="Telefone deve ter no minímo 8 digitos ";
		}


		return mensagem;
	}

	public Cliente salvar(Cliente cliente) {

		return bo.salvar(cliente);
	}

	public ArrayList<Cliente> consultarTodos() {
		// TODO Auto-generated method stub
		return bo.consultarTodos();
	}

	public boolean excluirCliente(int linhaSelecionada) {
	
	
		return bo.excluirCliente(linhaSelecionada);	
	}


}
