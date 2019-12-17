package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.Servico;

public class ServicoDAO {

	public Servico consultarPorId(int idServico) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM SERVICO WHERE IDSERVICO=?";
		ResultSet resultadoDaConsulta = null;
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		Servico servicoSelecionado = null;
		try {
			stmt.setInt(1, idServico);
			resultadoDaConsulta = stmt.executeQuery();

			if (resultadoDaConsulta.next()) {
				servicoSelecionado = construirDoResultSet(resultadoDaConsulta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar profissional por id: " + idServico);
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultadoDaConsulta);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return servicoSelecionado;

	}

	public ArrayList<Servico> consultarTodos() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet result = null;
		String sql = "SELECT * FROM SERVICO ORDER BY SERVICO ASC";
		ArrayList<Servico> servicos = new ArrayList<Servico>();

		try {
			result = stmt.executeQuery(sql);

			while (result.next()) {
				Servico servico = construirDoResultSet(result);
				servicos.add(servico);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao Consultar servicos");
			System.out.println("Erro " + e);
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return servicos;
	}

	private Servico construirDoResultSet(ResultSet result) {
		Servico novoServico = new Servico();
		try {
			novoServico.setIdservico((result.getInt("IDSERVICO")));
			novoServico.setServico(result.getString("SERVICO"));
		} catch (SQLException e) {
			System.out.println("Erro ao construir Apartir Do ResultSet. Causa " + e.getMessage());
		}
		return novoServico;

	}

	public Servico salvar(Servico servico) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO SERVICO (SERVICO) VALUES (?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);

		try {
			stmt.setString(1, servico.getServico());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				servico.setIdservico(idGerado);

			}
		} catch (SQLException e) {
			System.out.println("nao foi possivel salvar Servico");
			System.out.println("Erro" + e);
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);

		}

		return servico;
	}

	public boolean excluir(int Idservico) {
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM SERVICO WHERE IDSERVICO = " + Idservico;
		Statement stmt = Banco.getStatement(conn);
		int quantidadeRegistrosExcluidos = 0;

		try {
			quantidadeRegistrosExcluidos = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Não foi possivel Excluir Servico");
			System.out.println("Erro" + e);
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return quantidadeRegistrosExcluidos > 0;
	}
}