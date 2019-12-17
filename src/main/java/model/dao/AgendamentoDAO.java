package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.vo.Agendamento;
import model.vo.Cliente;
import model.vo.Profissional;
import model.vo.Servico;
import model.vo.seletor.AgendamentoSeletor;


public class AgendamentoDAO  {

	public int salvar(Agendamento agendamento) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO AGENDAMENTO (IDCLIENTE, IDPROFISSIONAL, IDSERVICO, DATACOMHORA, VALOR)"
				+ " VALUES (?,?,?,?,?)";
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		int resultado = 0;
		try {
			prepStmt.setInt(1, agendamento.getCliente().getIdCliente());
			prepStmt.setInt(2, agendamento.getProfissional().getIdProfissional());
			prepStmt.setInt(3, agendamento.getServico().getIdservico());

			Timestamp timestampSQL = Timestamp.valueOf(agendamento.getDataComHora());
			prepStmt.setTimestamp(4, timestampSQL);
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
		
		return resultado;
	}

	public List<Agendamento> listarAgendamento(AgendamentoSeletor seletor) {
		String sql = " SELECT * FROM AGENDAMENTO ";

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

		System.out.println(sql);
		return agendamentos;
	}

	private Agendamento construirAgendamentoDoResultSet(ResultSet result) {
		Agendamento agendamento = new Agendamento();
		try {
			agendamento.setIdAgendamento(result.getInt("IDAGENDAMENTO"));
			
			ClienteDAO clienteDAO = new ClienteDAO();
			int idCliente = result.getInt("IDCLIENTE");
			Cliente cliente = (Cliente) clienteDAO.consultarPorId(idCliente);
			agendamento.setCliente(cliente);
			
			
			ProfissionalDAO profissionalDAO = new ProfissionalDAO();
			int idProfissional = result.getInt("IDPROFISSIONAL");
			Profissional profissional = profissionalDAO.consultarPorId(idProfissional);
			agendamento.setProfissional(profissional);
			
			
			ServicoDAO servicoDAO = new ServicoDAO();
			int idServico = result.getInt("IDSERVICO");
			Servico servico = (Servico) servicoDAO.consultarPorId(idServico);
			agendamento.setServico(servico);
			
			LocalDateTime dataComHora = result.getTimestamp("DATACOMHORA").toLocalDateTime();
			
			agendamento.setDataComHora(dataComHora);
			agendamento.setValor(result.getDouble("VALOR"));
		} catch (SQLException e) {
			System.out.println("não foi possivel Construir Apartir do ResultSet");
			System.out.println("Erro " + e.getMessage());
		}




		return agendamento;
	}

	private String criarFiltros(AgendamentoSeletor seletor, String sql) {
		sql += "WHERE ";
		boolean primeiro = true;

		if (seletor.getIdagendamento() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "IDAGENDAMENTO = " + seletor.getIdagendamento();
			primeiro = false;
		}

		if ((seletor.getProfissionalSeletor() != null)&& seletor.getProfissionalSeletor().getIdProfissional() > -1) {
			if (!primeiro) {
				sql += " AND ";
			}		
			sql += " IDPROFISSIONAL = " + seletor.getProfissionalSeletor().getIdProfissional();
			primeiro = false;
		}

		if ((seletor.getServico() != null)&& seletor.getServico().getIdservico() > -1){
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "IDSERVICO = " + seletor.getServico().getIdservico();
			primeiro = false;
		}

		if ((seletor.getDataInicio() != null) && (seletor.getDataFim() != null)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "date(DATACOMHORA) BETWEEN "+"'" + seletor.getDataInicio()+"'" + " AND " +"'"+seletor.getDataFim()+"'";
			primeiro = false;
		} else if (seletor.getDataInicio() != null) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "DATACOMHORA >= " + "'"+ seletor.getDataInicio()+"'";
			primeiro = false;
		} else if (seletor.getDataFim() != null) {
			if (!primeiro) {
				sql += "AND";
			}
			sql += " DATACOMHORA <= " +"'"+ seletor.getDataFim()+"'";
		}

		return sql;
	}

	public ArrayList<Agendamento> consultarPorDia(LocalDate dataEscolhida, Profissional profissionalSelecionado) {
		Connection conexao = Banco.getConnection();
		String sql = "SELECT * FROM AGENDAMENTO WHERE DATE(DATACOMHORA) = '"+ dataEscolhida 
				+ "' AND IDPROFISSIONAL = " + profissionalSelecionado.getIdProfissional() + " ORDER BY DATACOMHORA ";
		Statement stmt = Banco.getPreparedStatement(conexao, sql);		
		ArrayList <Agendamento> agendamentosConsulta = new ArrayList <Agendamento>();

		try {
			ResultSet result = stmt.executeQuery(sql);	
			while (result.next()) {
				Agendamento novoAgendamento = construirAgendamentoDoResultSet(result);
				agendamentosConsulta.add(novoAgendamento);
			}

		}catch  (SQLException e) {
			System.out.println("erro ao listar Agendamento");
			System.out.println(" erro " + e);

		}finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);

		}

		System.out.println(sql);
		return agendamentosConsulta;



	}

	public boolean excluir(int idAgendamento) {
		Connection conexao = Banco.getConnection();
		Statement statement = Banco.getStatement(conexao);
		String sql = " DELETE FROM AGENDAMENTO WHERE IDAGENDAMENTO  = " + idAgendamento;

		int quantidadeRegistrosExcluidos = 0;
		try {
			quantidadeRegistrosExcluidos = statement.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir agendamento.");
			System.out.println("Erro: " + e.getMessage());
		}finally {
			Banco.closePreparedStatement(statement);
			Banco.closeConnection(conexao);
		}

		return quantidadeRegistrosExcluidos > 0;
	}

	public ArrayList<Agendamento> consultarPeloDiaAtual(LocalDate dataHoje, Profissional profissionalSelecionado) {
		Connection conexao = Banco.getConnection();
		String sql = "SELECT * FROM AGENDAMENTO WHERE DATE(DATACOMHORA) = '"+ dataHoje
		+ "' AND IDPROFISSIONAL = " + profissionalSelecionado.getIdProfissional() + " ORDER BY DATACOMHORA ";
		Statement stmt = Banco.getPreparedStatement(conexao, sql);		
		ArrayList <Agendamento> agendamentosDodia = new ArrayList <Agendamento>();

		try {
			ResultSet result = stmt.executeQuery(sql);	
			while (result.next()) {
				Agendamento novoAgendamento = construirAgendamentoDoResultSet(result);
				agendamentosDodia.add(novoAgendamento);
			}

		}catch  (SQLException e) {
			System.out.println("erro ao listar Agendamento");
			System.out.println(" erro " + e);

		}finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);

		}

		System.out.println(sql);
		return agendamentosDodia;
	}

	public boolean verificarSePossuiHorarioMarcado(Profissional profissionalSelecionado, LocalDateTime dataComHoraSelecionado) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet result = null;
		String sql = "SELECT * FROM AGENDAMENTO WHERE IDPROFISSIONAL = " + profissionalSelecionado.getIdProfissional() +" AND DATACOMHORA = " +"'"+ dataComHoraSelecionado +"'"; 
		try {
			result = stmt.executeQuery(sql);
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se Agendamento está disponivel");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(result);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}
		System.out.println(sql);
	
		return false;
	}
}
