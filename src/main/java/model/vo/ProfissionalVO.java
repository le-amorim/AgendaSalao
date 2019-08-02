package model.vo;

import view.EstruturaAgenda;
import view.FrmPrimeiraTela;

public class ProfissionalVO {
private String nome;
private FrmPrimeiraTela frmPrimeiraTela;
private EstruturaAgenda estruturaAgenda;

public ProfissionalVO(String nome, FrmPrimeiraTela frmPrimeiraTela, EstruturaAgenda estruturaAgenda) {
	super();
	this.nome = nome;
	this.frmPrimeiraTela = frmPrimeiraTela;
	this.estruturaAgenda = estruturaAgenda;
}

public ProfissionalVO() {
	super();
	// TODO Auto-generated constructor stub
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public FrmPrimeiraTela getFrmPrimeiraTela() {
	return frmPrimeiraTela;
}

public void setFrmPrimeiraTela(FrmPrimeiraTela frmPrimeiraTela) {
	this.frmPrimeiraTela = frmPrimeiraTela;
}

public EstruturaAgenda getEstruturaAgenda() {
	return estruturaAgenda;
}

public void setEstruturaAgenda(EstruturaAgenda estruturaAgenda) {
	this.estruturaAgenda = estruturaAgenda;
}




}
