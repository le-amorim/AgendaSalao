package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

import controller.ControladoraCliente;
import model.vo.Cliente;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class TelaCadastroCliente extends JPanel {

	private JTextField txtNome;
	private Component txtObservacao;
	private JButton btnLimpar;
	private JTable tblConsultaCliente;
	private String[] colunasTabelaCliente = { "Nome", "Telefone", "Observação" };
	private ArrayList<Cliente> clientes;
	private JTextField txtSobreNome;
	private JFormattedTextField fmtTelefone;

	public TelaCadastroCliente() {
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 450, 600);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);

		JLabel lblCadastroDeCliente = new JLabel("Cadastro De Cliente");
		lblCadastroDeCliente.setFont(new Font("Segoe Script", Font.BOLD, 13));
		lblCadastroDeCliente.setBounds(110, 11, 196, 14);
		this.add(lblCadastroDeCliente);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(34, 54, 46, 14);
		this.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(110, 51, 179, 20);
		this.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(27, 116, 77, 14);
		this.add(lblTelefone);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraCliente controladora = new ControladoraCliente();

				String nomeCliente = txtNome.getText();
				String sobreNomeCliente = txtSobreNome.getText();
				String TelefoneCliente = fmtTelefone.getText();
				String observacaoCliente = ((JTextComponent) txtObservacao).getText();
				String mensagem = "";
				mensagem += controladora.validar(nomeCliente, sobreNomeCliente, TelefoneCliente, observacaoCliente);
				if (mensagem.isEmpty()) {
					Cliente cliente = new Cliente(nomeCliente, sobreNomeCliente, TelefoneCliente, observacaoCliente);
					cliente = controladora.salvar(cliente);
					JOptionPane.showMessageDialog(null, "salvo com sucesso!");
					FrmPrimeiraTela tela = new FrmPrimeiraTela();
					tela.consultarProfissional();
				} else {
					JOptionPane.showMessageDialog(null, mensagem);
				}

			}
		});
		btnSalvar.setBounds(206, 234, 83, 39);
		this.add(btnSalvar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtSobreNome.setText("");
				fmtTelefone.setText("");
				((JTextComponent) txtObservacao).setText("");
			}
		});
		btnLimpar.setBounds(110, 234, 86, 39);
		this.add(btnLimpar);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 284, 406, 10);
		this.add(separator_1);

		txtObservacao = new JTextArea();
		txtObservacao.setBounds(110, 144, 179, 66);
		this.add(txtObservacao);

		JLabel lblObservacao = new JLabel("Observação:");
		lblObservacao.setBounds(34, 162, 77, 14);
		this.add(lblObservacao);

		JLabel lblConsultaDeCliente = new JLabel("Consulta de Cliente");
		lblConsultaDeCliente.setForeground(Color.BLACK);
		lblConsultaDeCliente.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 13));
		lblConsultaDeCliente.setBounds(110, 321, 196, 14);
		this.add(lblConsultaDeCliente);

		tblConsultaCliente = new JTable();
		tblConsultaCliente.setBounds(10, 346, 396, 224);
		this.add(tblConsultaCliente);

		JLabel lblSobreNome = new JLabel("SobreNome");
		lblSobreNome.setBounds(27, 79, 77, 14);
		add(lblSobreNome);

		txtSobreNome = new JTextField();
		txtSobreNome.setColumns(10);
		txtSobreNome.setBounds(110, 82, 179, 20);
		add(txtSobreNome);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/icones/deboche.gif")));
		lblNewLabel.setBounds(0, 0, larguraDaTela, alturaDaTela);
		add(lblNewLabel);

		try {
			MaskFormatter mascaraTelefone = new MaskFormatter("(##)#####-####");
			fmtTelefone = new JFormattedTextField(mascaraTelefone);
			fmtTelefone.setBounds(110, 113, 179, 20);
			add(fmtTelefone);
		} catch (ParseException e) {
		}

	}

	protected void atualizarTabelaEmpregados() {
		ControladoraCliente controller = new ControladoraCliente();
		clientes = controller.consultarTodos();
		ConstruirTabela();
		DefaultTableModel model = (DefaultTableModel) tblConsultaCliente.getModel();

		for (Cliente cliente : clientes) {
			String[] novaLinha = new String[5];
			novaLinha[0] = cliente.getNome();
			novaLinha[1] = cliente.getNome();
			novaLinha[2] = cliente.getTelefone();
			novaLinha[3] = cliente.getObservacao();

			// Adiciona a nova linha na tabela
			model.addRow(novaLinha);

		}
	}

	private void ConstruirTabela() {
		tblConsultaCliente
				.setModel(new DefaultTableModel(new Object[][] { colunasTabelaCliente, }, colunasTabelaCliente));

	}

	Dimension dimensoesTela = Toolkit.getDefaultToolkit().getScreenSize();
	int larguraDaTela = (int) dimensoesTela.getWidth();
	int alturaDaTela = (int) (dimensoesTela.getHeight() - 100);
}
