package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.vo.Servico;

public class ServicoDAO implements BaseDAO {

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
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet result = null;
		String sql = "SELECT * FROM SERVICO";
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
			System.out.println("Erro ao construir Apartir Do ResultSet");
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

	public String excluir(Servico servicoDigitado) {
		Connection conn = Banco.getConnection();
		String sql = "DELETE * FROM SERVICO WHERE SERVICO= "+ servicoDigitado;
		Statement stmt = Banco.getStatement(conn);
		
		try {
		stmt.execute(sql);
		ResultSet rs = stmt.getGeneratedKeys();
		}catch (SQLException e) {
			System.out.println("nao foi possivel salvar Servico");
			System.out.println("Erro" + e);
		}finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return null;
	}
}