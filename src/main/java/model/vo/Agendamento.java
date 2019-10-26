package model.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Agendamento {
	private int idAgendamento;
	private Cliente cliente;
	private Profissional profissional;
	private LocalDateTime dataComHora;
	private String servico;
	private Double valor;

	public Agendamento(int idAgendamento, Cliente cliente, Profissional profissional, LocalDateTime dataComHora,
			 String servico, Double valor) {
		super();
		this.idAgendamento = idAgendamento;
		this.cliente = cliente;
		this.profissional = profissional;
		this.dataComHora = dataComHora;
		
		this.servico = servico;
		this.valor = valor;
	}

	public Agendamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Agendamento(Cliente clienteSelecionado, Profissional profissionalSelecionado, String servicoDigitado,
			String valorDigitado, LocalDateTime dataComHoraSelecionada) {

		this.cliente = clienteSelecionado;
		this.profissional = profissionalSelecionado;
		this.dataComHora = dataComHoraSelecionada;
		this.servico = servicoDigitado;
		this.valor = Double.parseDouble(valorDigitado);

	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(int idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public LocalDateTime getDataComHora() {
		return dataComHora;
	}

	public void setDataComHora(LocalDateTime dataComHora) {
		this.dataComHora = dataComHora;
	}


}
