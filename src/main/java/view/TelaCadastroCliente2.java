package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import controller.ControladoraCliente;
import model.vo.Cliente;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroCliente2 extends JDialog {

	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome2;
	private JTextField txtSobreNome2;
	private JTextField txtTelefone2;
	private AbstractButton btnSalvar2;
	private Component lblTelaDeCadastro;
	private Component separator;
	private JTextPane txtPaneObs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaCadastroCliente2 dialog = new TelaCadastroCliente2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaCadastroCliente2() {
		this.setModal(true);
		setBounds(203, 67, 445, 425);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(40, 62, 46, 14);
		contentPanel.add(lblNome);
		
		JLabel lblSobreNome = new JLabel("SobreNome:");
		lblSobreNome.setBounds(8, 108, 105, 14);
		contentPanel.add(lblSobreNome);
		
		JLabel lblbTelefone = new JLabel("Telefone:");
		lblbTelefone.setBounds(26, 156, 75, 14);
		contentPanel.add(lblbTelefone);
		
		JLabel lblObservacao = new JLabel("Observação:");
		lblObservacao.setBounds(6, 199, 98, 14);
		contentPanel.add(lblObservacao);
		
		txtNome2 = new JTextField();
		txtNome2.setBounds(83, 59, 158, 20);
		contentPanel.add(txtNome2);
		txtNome2.setColumns(10);
		
		txtSobreNome2 = new JTextField();
		txtSobreNome2.setColumns(10);
		txtSobreNome2.setBounds(83, 105, 158, 20);
		contentPanel.add(txtSobreNome2);
		
		txtTelefone2 = new JTextField();
		txtTelefone2.setColumns(10);
		txtTelefone2.setBounds(83, 153, 158, 20);
		contentPanel.add(txtTelefone2);
		
		txtPaneObs = new JTextPane();
		txtPaneObs.setBounds(83, 199, 256, 112);
		contentPanel.add(txtPaneObs);
		
		 separator = new JSeparator();
		separator.setBounds(0, 31, 429, 2);
		contentPanel.add(separator);
		
		 lblTelaDeCadastro = new JLabel("Tela De Cadastro de Clientes");
		lblTelaDeCadastro.setFont(new Font("Segoe Script", Font.ITALIC, 16));
		lblTelaDeCadastro.setBounds(83, 6, 289, 14);
		contentPanel.add(lblTelaDeCadastro);
		
		 btnSalvar2 = new JButton("Salvar");
		btnSalvar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraCliente controladora = new ControladoraCliente();

				String nomeCliente = txtNome2.getText();
				String sobreNomeCliente = txtSobreNome2.getText();
				String TelefoneCliente = txtTelefone2.getText();
				String observacaoCliente = ((JTextComponent) txtPaneObs).getText();
				String mensagem = "";
				mensagem += controladora.validar(nomeCliente,sobreNomeCliente, TelefoneCliente, observacaoCliente);
				if (mensagem.isEmpty()) {
					Cliente cliente = new Cliente(nomeCliente,sobreNomeCliente, TelefoneCliente, observacaoCliente);
					cliente = controladora.salvar(cliente);
					JOptionPane.showMessageDialog(null, "salvo com sucesso!");
					FrmPrimeiraTela tela = new FrmPrimeiraTela();
					tela.consultarProfissional();
				} else {
					JOptionPane.showMessageDialog(null, mensagem);
				}
			}
		});
		btnSalvar2.setBounds(250, 338, 89, 37);
		contentPanel.add(btnSalvar2);
		
		JButton btnLimpar2 = new JButton("Limpar\r\n");
		btnLimpar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			txtNome2.setText("");
			txtSobreNome2.setText("");
			txtTelefone2.setText("");
			txtPaneObs.setText("");
			}
		});
		btnLimpar2.setBounds(83, 338, 89, 37);
		contentPanel.add(btnLimpar2);
		
		JLabel lblStudio = new JLabel("Studio");
		lblStudio.setFont(new Font("Segoe Script", Font.PLAIN, 12));
		lblStudio.setForeground(Color.RED);
		lblStudio.setBackground(Color.RED);
		lblStudio.setBounds(268, 60, 46, 14);
		contentPanel.add(lblStudio);
		
		JLabel lblDu = new JLabel("Du'");
		lblDu.setForeground(Color.RED);
		lblDu.setFont(new Font("Segoe Script", Font.ITALIC, 12));
		lblDu.setBackground(Color.RED);
		lblDu.setBounds(289, 85, 25, 14);
		contentPanel.add(lblDu);
		
		JLabel lblArtes = new JLabel("Artes");
		lblArtes.setForeground(Color.RED);
		lblArtes.setFont(new Font("Segoe Script", Font.ITALIC, 11));
		lblArtes.setBackground(Color.RED);
		lblArtes.setBounds(268, 108, 46, 14);
		contentPanel.add(lblArtes);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaCadastroCliente2.class.getResource("/icones/foto.png")));
		lblLogo.setBounds(248, 38, 171, 150);
		contentPanel.add(lblLogo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 325, 429, 2);
		contentPanel.add(separator_1);
	}
}
