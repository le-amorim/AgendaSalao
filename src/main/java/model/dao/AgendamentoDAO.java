package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Agendamento;
import model.vo.seletor.AgendamentoSeletor;

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
		String sql = "INSERT INTO AGENDAMENTO (IDCLIENTE, IDPROFISSIONAL, IDSERVICO, DATACOMHORA, VALOR) VALUES (?,?,?,?,?,?)";
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);

		try {
		
			prepStmt.setInt(1, agendamento.getCliente().getIdCliente());
			prepStmt.setInt(2, agendamento.getProfissional().getIdProfissional());
			prepStmt.setInt(3, agendamento.getServico().getIdservico());
			prepStmt.setDate(4, java.sql.Date.valueOf(agendamento.getDataComHora().toLocalDate()));
			prepStmt.setDouble(5, agendamento.getValor());
			prepStmt.execute();
			ResultSet rs = prepStmt.getGeneratedKeys();

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				agendamento.setIdAgendamento(idGerado);
			}
		} catch (SQLException e) {
			System.out.println("nao foi possivel salvar agendamento no banco");
			System.out.println("Erro  " + e);
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);

		}

		return agendamento;
	}

	public List<Agendamento> listarAgendamento(AgendamentoSeletor seletor) {
		String sql = " SELECT * FROM AGENDAMENTO";

		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);

		}

		if (seletor.temPaginacao()) {
			
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		
		}
		
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList <Agendamento> agendamentos = new ArrayList <Agendamento>();
		
		try {
		ResultSet result = prepStmt.executeQuery();	
		while (result.next()) {
			// falta construir AgendamentoDoResultSet
			Agendamento agendamento = construirAgendamentoDoResultSet(result);
			agendamentos.add(agendamento);
		}

		}catch  (SQLException e) {
			System.out.println("erro ao listar Agendamento");
			System.out.println(" erro " + e);
			
		}finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
			
		}
		
		
		return agendamentos;
	}

	private Agendamento construirAgendamentoDoResultSet(ResultSet result) {
		Agendamento agendamento = new Agendamento();
		try {
			agendamento.setIdAgendamento(result.getInt("IDAGENDAMENTO"));
			agendamento.getProfissional().setIdProfissional(result.getInt("IDPROFISSIONAL"));
			agendamento.getCliente().setIdCliente(result.getInt("IDCLIENTE"));
			agendamento.getServico().setIdservico(result.getInt("IDSERVICO"));
			//agendamento.setDataComHora(result.getDate("DATACOMHORA"));
			agendamento.setValor(result.getDouble("VALOR"));
		} catch (SQLException e) {
			System.out.println("não foi possivel Construir Apartir do ResultSet");
			System.out.println("Erro " + e );
		}
		
		 
		
		
		return agendamento;
	}

	private String criarFiltros(AgendamentoSeletor seletor, String sql) {
		sql += " WHERE ";
		boolean primeiro = true;

		if (seletor.getIdagendamento() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "IDAGENDAMENTO = " + seletor.getIdagendamento();
			primeiro = false;
		}

		if ((seletor.getNomeProfissional() != null) && (seletor.getNomeProfissional().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "IDPROFISSIONAL '%" + seletor.getNomeProfissional() + "%'";
			primeiro = false;
		}

		if ((seletor.getServico() != null) && (seletor.getServico().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "IDSERVICO = '" + seletor.getServico() + "'";
			primeiro = false;
		}

		if ((seletor.getDataInicio() != null) && (seletor.getDataFim() != null)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "DATACOMHORA = '" + seletor.getDataInicio() + "'";
			primeiro = false;
		} else if (seletor.getDataInicio() != null) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "DATACOMHORA >= " + seletor.getDataInicio();
			primeiro = false;
		} else if (seletor.getDataFim() != null) {
			if (!primeiro) {
				sql += "AND";
			}
			sql += " DATACOMHORA <= " + seletor.getDataFim();
		}

		return sql;
	}

}
