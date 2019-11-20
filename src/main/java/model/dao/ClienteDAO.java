package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.Cliente;

@SuppressWarnings("rawtypes")
public class ClienteDAO implements BaseDAO {

	public Cliente salvar(Cliente cliente) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO CLIENTE (NOME, SOBRENOME, TELEFONE, OBSERVACAO) VALUES (?,?,?,?)";
		PreparedStatement Prepstmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);

		try {
			Prepstmt.setString(1, cliente.getNome());
			Prepstmt.setString(2, cliente.getSobreNome());
			Prepstmt.setString(3, cliente.getTelefone());
			Prepstmt.setString(4, cliente.getObservacao());
			Prepstmt.execute();
			ResultSet rs = Prepstmt.getGeneratedKeys();

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				cliente.setIdCliente(idGerado);
			}
		} catch (SQLException e) {
			System.out.println("nao foi possivel salvar cliente no banco");
			System.out.println("Erro  " + e);
		} finally {
			Banco.closePreparedStatement(Prepstmt);
			Banco.closeConnection(conn);

		}

		return cliente;
	}

	public ArrayList<Cliente> consultarTodos() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet result = null;
		String sql = "SELECT * FROM CLIENTE";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		try {
			result = stmt.executeQuery(sql);

			while (result.next()) {
				Cliente cliente = construirDoResultSet(result);
				clientes.add(cliente);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao Consultar Cliente");
			System.out.println("Erro " + e);
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return clientes;

	}

	private Cliente construirDoResultSet(ResultSet result) {
		Cliente novoCliente = new Cliente();
		try {
			novoCliente.setIdCliente(result.getInt("IDCLIENTE"));
			novoCliente.setNome(result.getString("NOME"));
			novoCliente.setSobreNome(result.getString("SOBRENOME"));
			novoCliente.setTelefone(result.getString("TELEFONE"));
			novoCliente.setObservacao(result.getString("OBSERVACAO"));
		} catch (SQLException e) {
			System.out.println("Erro ao construir Apartir Do ResultSet");
		}
		return novoCliente;
	}

	public boolean excluirCliente(int linhaSelecionada) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;

		String query = "DELETE FROM Cliente WHERE linhaSelecionada = " + linhaSelecionada;
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Exclusão do Usuário.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return (resultado > 0);
	}

	public Object consultarPorId(int id) {
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

	public Object salvar(Object novaEntidade) {
		// TODO Auto-generated method stub
		return null;
	}
}