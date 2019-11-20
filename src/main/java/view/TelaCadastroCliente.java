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
	private ArrayList<Cliente> clientes;
	private JTextField txtSobreNome;
	private JFormattedTextField fmtTelefone;
	protected int linhaSelecionada;

	public TelaCadastroCliente() {
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 481, 616);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
					JButton btnExcluirCliente = new JButton("Excluir Cliente");
					btnExcluirCliente.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {

							DefaultTableModel model = (DefaultTableModel) tblConsultaCliente.getModel();
							if (tblConsultaCliente.getSelectedRow() >= 0) {
								model.removeRow(tblConsultaCliente.getSelectedRow());
								linhaSelecionada = tblConsultaCliente.getSelectedRow();
								tblConsultaCliente.getValueAt(linhaSelecionada,-1);
								tblConsultaCliente.setModel(model);
								excluirClienteSelecionado();
							} else {
								JOptionPane.showMessageDialog(null, "Favor selecionar uma linha");
							}

						}
					});
					btnExcluirCliente.setBounds(328, 582, 122, 23);
					add(btnExcluirCliente);

		JLabel lblCadastroDeCliente = new JLabel("Cadastro De Cliente");
		lblCadastroDeCliente.setFont(new Font("Segoe Script", Font.BOLD, 13));
		lblCadastroDeCliente.setBounds(110, 11, 196, 14);
		this.add(lblCadastroDeCliente);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(54, 54, 46, 14);
		this.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(110, 51, 179, 20);
		this.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(34, 115, 46, 17);
		this.add(lblTelefone);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraCliente controladora = new ControladoraCliente();

				String nomeCliente = txtNome.getText();
				String sobreNomeCliente = txtSobreNome.getText();
				String telefoneCliente = fmtTelefone.getText();
				telefoneCliente.replaceAll("()" ,  "");
				telefoneCliente.replaceAll("-"  ,  "");
				String observacaoCliente = ((JTextComponent) txtObservacao).getText();
				String mensagem = "";
				mensagem += controladora.validar(nomeCliente, sobreNomeCliente, telefoneCliente, observacaoCliente);
				if (mensagem.isEmpty()) {
					Cliente cliente = new Cliente(nomeCliente, sobreNomeCliente, telefoneCliente, observacaoCliente);
					cliente = controladora.salvar(cliente);
					JOptionPane.showMessageDialog(null, "salvo com sucesso!");
					FrmPrimeiraTela tela = new FrmPrimeiraTela();
					tela.consultarProfissional();
					atualizarTabelaClientes();
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
		separator_1.setBounds(0, 284, 450, 10);
		this.add(separator_1);

		txtObservacao = new JTextArea();
		txtObservacao.setBounds(110, 144, 179, 66);
		this.add(txtObservacao);

		JLabel lblObservacao = new JLabel("Observação:");
		lblObservacao.setBounds(27, 160, 62, 14);
		this.add(lblObservacao);

		JLabel lblConsultaDeCliente = new JLabel("Consulta de Cliente");
		lblConsultaDeCliente.setForeground(Color.BLACK);
		lblConsultaDeCliente.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 13));
		lblConsultaDeCliente.setBounds(150, 295, 196, 14);
		this.add(lblConsultaDeCliente);

		tblConsultaCliente = new JTable();
		tblConsultaCliente.setBounds(10, 320, 461, 246);
		construirTabelaClientes();
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

	protected void excluirClienteSelecionado() {
		ControladoraCliente controller = new ControladoraCliente();
		controller.excluirCliente(linhaSelecionada);

	}

	protected void atualizarTabelaClientes() {
		ControladoraCliente controller = new ControladoraCliente();
		clientes = controller.consultarTodos();
		construirTabelaClientes();
		DefaultTableModel model = (DefaultTableModel) tblConsultaCliente.getModel();
		for (Cliente cliente : clientes) {
			String[] novaLinha = new String[4];
			novaLinha[0] = cliente.getNome();
			novaLinha[1] = cliente.getSobreNome();
			novaLinha[2] = cliente.getTelefone();
			novaLinha[3] = cliente.getObservacao();

			// Adiciona a nova linha na tabela
			model.addRow(novaLinha);

		}
	}

	Dimension dimensoesTela = Toolkit.getDefaultToolkit().getScreenSize();
	int larguraDaTela = (int) dimensoesTela.getWidth();
	int alturaDaTela = (int) (dimensoesTela.getHeight() - 100);

	private void construirTabelaClientes() {

		tblConsultaCliente
				.setModel(new DefaultTableModel(new String[][] { { "Nome", "Sobrenome", "telefone", "Observação " }, },
						new String[] { "Nome", "Sobrenome", "telefone", "Observação " }));

	}
}
