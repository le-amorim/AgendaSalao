package model.vo;

public class AgendamentoVO {
private String nome;
private String hora;
private String servico;
private String telefone;
private Double Valor;


public AgendamentoVO(String nome, String hora, String servico, String telefone, Double valor) {
	super();
	this.nome = nome;
	this.hora = hora;
	this.servico = servico;
	this.telefone = telefone;
	Valor = valor;
}

public AgendamentoVO() {
	super();
	// TODO Auto-generated constructor stub
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getHora() {
	return hora;
}

public void setHora(String hora) {
	this.hora = hora;
}

public String getServico() {
	return servico;
}

public void setServico(String servico) {
	this.servico = servico;
}

public String getTelefone() {
	return telefone;
}

public void setTelefone(String telefone) {
	this.telefone = telefone;
}

public Double getValor() {
	return Valor;
}

public void setValor(Double valor) {
	Valor = valor;
}



	
}
