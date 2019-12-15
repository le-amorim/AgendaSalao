package model.vo.seletor;

import java.time.LocalDate;

import model.vo.Profissional;
import model.vo.Servico;

public class AgendamentoSeletor {

	private int idAgendamento;
	private Profissional ProfissionalSeletor;
	private Servico servico;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private int limite;
	private int pagina;

	public boolean temFiltro() {
		if (this.idAgendamento > 0) {
			return true;
		}
		if ((this.ProfissionalSeletor != null) && (this.ProfissionalSeletor.getNome().trim().length() > 0)) {
			return true;
		}
		if ((this.servico != null) && (this.servico.getServico().trim().length() > 0)) {
			return true;
		}

		if (this.dataInicio != null) {
			return true;
		}
		if (this.dataFim != null) {
			return true;
		}
		return false;
	}

	public boolean temPaginacao() {
		return ((this.limite > 0) && (this.pagina > -1));
	}

	/**
	 * Calcula deslocamento (offset) a partir da pagina e do limite
	 *
	 * @return offset
	 */
	public int getOffset() {
		return (this.limite * (this.pagina - 1));
	}

	public int getIdagendamento() {
		return idAgendamento;
	}

	public void setIdagendamento(int idagendamento) {
		idAgendamento = idagendamento;
	}

	
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public Profissional getProfissionalSeletor() {
		return ProfissionalSeletor;
	}

	public void setProfissionalSeletor(Profissional profissionalSeletor) {
		ProfissionalSeletor = profissionalSeletor;
	}

}