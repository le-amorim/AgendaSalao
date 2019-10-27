package model.vo;

public class Profissional {

	private int idProfissional;
	private String nome;
	private String especialidade;
	
	
	public Profissional(int idProfissional, String nome, String especialidade) {
		super();
		this.idProfissional = idProfissional;
		this.nome = nome;
		this.especialidade = especialidade;
	}

	public Profissional() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Profissional(String nomeDigitado, String especialidadeDigitado) {
		this.nome = nomeDigitado;
		this.especialidade = especialidadeDigitado;
	}

	public int getIdProfissional() {
		return idProfissional;
	}

	public void setIdProfissional(int idProfissional) {
		this.idProfissional = idProfissional;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public String toString() {
		return "" + nome + "";
	}

	

}
