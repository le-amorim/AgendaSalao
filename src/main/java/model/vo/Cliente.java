package model.vo;

public class Cliente {
	private int idCliente;
	private String nomeCompleto;
	private String telefone;
	private String observacao;
	private String cpf;
	


	public Cliente(int idCliente, String nomeCompleto, String telefone, String observacao, String cpf) {
		super();
		this.idCliente = idCliente;
		this.nomeCompleto = nomeCompleto;
		this.telefone = telefone;
		this.observacao = observacao;
		this.cpf = cpf;
	}


	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nomeCompletoCliente, String telefoneCliente,String cpfCliente ,String observacaoCliente) {
		this.nomeCompleto = nomeCompletoCliente;
		this.telefone = telefoneCliente;
		this.observacao = observacaoCliente;
		this.cpf = cpfCliente;
	}

	

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	
	@Override
	public String toString() {
		return " " + nomeCompleto + " ";
	}



	
}
