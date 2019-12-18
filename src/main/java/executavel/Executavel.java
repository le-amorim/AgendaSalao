package executavel;

import javax.swing.JFrame;

import view.FrmPrimeiraTela;

public class Executavel {

	public static void main(String[] args) {
		

	FrmPrimeiraTela  frmPrimeiraTela = new FrmPrimeiraTela();
	frmPrimeiraTela.setVisible(true);
	frmPrimeiraTela.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}

}
