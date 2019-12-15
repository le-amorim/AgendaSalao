package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.Profissional;

public class ProfissionalDAO {

	public Profissional salvar(Profissional profissional) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO PROFISSIONAL (NOME, ESPECIALIDADE) VALUES (?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);

		try {
			stmt.setString(1, profissional.getNome());
			stmt.setString(2, profissional.getEspecialidade());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				profissional.setIdProfissional(idGerado);

			}
		} catch (SQLException e) {
			System.out.println("nao foi possivel salvar Profissional");
			System.out.println("Erro" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);

		}

		return profissional;
	}

	public Profissional consultarPorId(int idProfissional) { // IMPLEMENTAR ESSE METODO HOJE!!
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PROFISSIONAL WHERE IDPROFISSIONAL=?";
		ResultSet resultadoDaConsulta = null;
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		Profissional profissionalSelecionado = null;
		try {
			stmt.setInt(1, idProfissional);
			resultadoDaConsulta = stmt.executeQuery();

			if (resultadoDaConsulta.next()) {
				profissionalSelecionado = construirDoResultSet(resultadoDaConsulta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar profissional por id: " + idProfissional);
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultadoDaConsulta);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return profissionalSelecionado;

	}

	public ArrayList<Profissional> consultarTodos() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet result = null;
		String sql = "SELECT * FROM PROFISSIONAL";
		ArrayList<Profissional> profissionais = new ArrayList<Profissional>();

		try {
			result = stmt.executeQuery(sql);

			while (result.next()) {
				Profissional profissional = construirDoResultSet(result);
				profissionais.add(profissional);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao Consultar Funcionário");
			System.out.println("Erro " + e);
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return profissionais;
	}

	private Profissional construirDoResultSet(ResultSet result) {
		Profissional novoProfissional = new Profissional();
		try {
			novoProfissional.setIdProfissional(result.getInt("IDPROFISSIONAL"));
			novoProfissional.setNome(result.getString("NOME"));
		} catch (SQLException e) {
			System.out.println("Erro ao construir a partir Do ResultSet. Causa " + e.getMessage());
		}
		return novoProfissional;
	}

}
