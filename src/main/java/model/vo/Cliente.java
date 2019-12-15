package model.vo;

public class Cliente {
	private int idCliente;
	private String nome;
	private String telefone;
	private String observacao;
	private String sobreNome;
	private String cpf;
	


	public Cliente(int idCliente, String nome, String telefone, String observacao, String sobreNome, String cpf) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.telefone = telefone;
		this.observacao = observacao;
		this.sobreNome = sobreNome;
		this.cpf = cpf;
	}

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nomeCliente,String sobreNomeCliente, String telefoneCliente,String cpfCliente ,String observacaoCliente) {
		this.nome = nomeCliente;
		this.telefone = telefoneCliente;
		this.observacao = observacaoCliente;
		this.sobreNome = sobreNomeCliente;
		this.cpf = cpfCliente;
	}

	

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	@Override
	public String toString() {
		return " " + nome + " " + sobreNome + "";
	}


	
}
