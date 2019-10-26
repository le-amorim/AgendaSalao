package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.Agendamento;

@SuppressWarnings("rawtypes")
public class AgendamentoDAO implements BaseDAO {

	public Object salvar(Object novaEntidade) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean alterar(Object entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Agendamento salvar(Agendamento agendamento) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO AGENDAMENTO (DATACOMHORA,SERVICO, VALOR) VALUES (?,?,?)";
		PreparedStatement Prepstmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);

		try {
			//Prepstmt.setDate(1, agendamento.getDataComHora());
			Prepstmt.setString(2, agendamento.getServico());
			Prepstmt.setDouble(3, agendamento.getValor());
			Prepstmt.execute();
			ResultSet rs = Prepstmt.getGeneratedKeys();

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				agendamento.setIdAgendamento(idGerado);
			}
		} catch (SQLException e) {
			System.out.println("nao foi possivel salvar agendamento no banco");
			System.out.println("Erro  " + e);
		} finally {
			Banco.closePreparedStatement(Prepstmt);
			Banco.closeConnection(conn);

		}

		return agendamento;
	}

}
