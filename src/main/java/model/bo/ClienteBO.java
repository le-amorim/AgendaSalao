package model.bo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.dao.ClienteDAO;
import model.vo.Cliente;

public class ClienteBO {
	ClienteDAO dao = new ClienteDAO();

	public String salvar(Cliente cliente) {
		String msg = "";
		int resultado = 0;
		ClienteDAO dao = new ClienteDAO();
		if (dao.consultarCpfCliente(cliente.getCpf())) {
			msg += ("Este cpf já foi utilizado ");
		} else {
			resultado = dao.salvar(cliente);
		}
		if (resultado > -1) {
			msg += ("Cliente cadastrado com sucesso.");
		} else {

		}
		System.out.println(resultado);
		return msg;
	}

	public ArrayList<Cliente> consultarTodos() {

		return dao.consultarTodos();
	}

	public String excluir(Cliente clienteSelecionado) {
		String mensagem = "";

		if (dao.excluir(clienteSelecionado.getIdCliente())) {
			mensagem = "Cliente Excluido com sucesso";
		} else {
			mensagem = "Erro ao excluir Cliente,"
					+ " possui agendamento";
		}

		return mensagem;
	}

}
