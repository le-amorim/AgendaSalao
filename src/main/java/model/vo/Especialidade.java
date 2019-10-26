package model.vo;

public class Especialidade {
	private int IdEspecialidade;
	private String especialidade;
	
	
	public Especialidade(int idEspecialidade, String especialidade) {
		super();
		IdEspecialidade = idEspecialidade;
		this.especialidade = especialidade;
	
	}

	public int getIdEspecialidade() {
		return IdEspecialidade;
	}

	public void setIdEspecialidade(int idEspecialidade) {
		IdEspecialidade = idEspecialidade;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

		
}
