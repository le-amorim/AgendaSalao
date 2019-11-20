package model.vo.seletor;

import java.time.LocalDate;

public class AgendamentoSeletor {

	private int Idagendamento;
	private String nomeProfissional;
	private String servico;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private int limite;
	private int pagina;

	public boolean temFiltro() {
		if (this.Idagendamento > 0) {
			return true;
		}
		if ((this.nomeProfissional != null) && (this.nomeProfissional.trim().length() > 0)) {
			return true;
		}
		if ((this.servico != null) && (this.servico.trim().length() > 0)) {
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
		return Idagendamento;
	}

	public void setIdagendamento(int idagendamento) {
		Idagendamento = idagendamento;
	}

	public String getNomeProfissional() {
		return nomeProfissional;
	}

	public void setNomeProfissional(String nomeProfissional) {
		this.nomeProfissional = nomeProfissional;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
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

}