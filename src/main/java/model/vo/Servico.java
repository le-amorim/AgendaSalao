package model.vo;

public class Servico {
	private int Idservico;
	private String servico;
	public Servico(int idservico, String servico) {
		super();
		Idservico = idservico;
		this.servico = servico;
	}
	public Servico() {
		// TODO Auto-generated constructor stub
	}
	public Servico(String servicoDigitado) {
		this.servico = servicoDigitado;
	}
	public int getIdservico() {
		return Idservico;
	}
	public void setIdservico(int idservico) {
		Idservico = idservico;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	@Override
	public String toString() {
		return "" + servico + "";
	}
	
	



}
