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
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

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
	private JFormattedTextField fmtCpf;

	public TelaCadastroCliente() {
		setBackground(SystemColor.activeCaption);
		setBounds(0, 0, 679, 630);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		JButton btnExcluirCliente = new JButton("Excluir Cliente");
		btnExcluirCliente.setBounds(328, 585, 122, 23);
		btnExcluirCliente.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) tblConsultaCliente.getModel();
				int valorBotao = JOptionPane.YES_NO_OPTION;
				valorBotao = JOptionPane.showConfirmDialog(null, "deseja realmente excluir este cliente?",
						"CONFIRMAÇÃO", valorBotao);
				if (valorBotao == JOptionPane.YES_OPTION) {

					if (tblConsultaCliente.getSelectedRow() >= 0) {
						linhaSelecionada = tblConsultaCliente.getSelectedRow();
						Cliente clienteSelecionado = clientes.get(linhaSelecionada - 1);
						model.removeRow(tblConsultaCliente.getSelectedRow());
						ControladoraCliente controladoraCliente = new ControladoraCliente();
						String mensagem = controladoraCliente.excluir(clienteSelecionado);
						JOptionPane.showMessageDialog(null, mensagem);

					} else {

						JOptionPane.showMessageDialog(null, "Favor selecionar uma linha");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Cancelado");
				}

			}
		});
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 344, 522, 230);
		add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);

		tblConsultaCliente = new JTable();
		panel.add(tblConsultaCliente);
		tblConsultaCliente.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblCpf = new JLabel("Cpf: ");
		lblCpf.setBounds(70, 115, 46, 14);
		add(lblCpf);
		add(btnExcluirCliente);

		JLabel lblCadastroDeCliente = new JLabel("Cadastro De Cliente");
		lblCadastroDeCliente.setBounds(149, 11, 196, 14);
		lblCadastroDeCliente.setFont(new Font("Segoe Script", Font.BOLD, 13));
		this.add(lblCadastroDeCliente);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(60, 54, 46, 14);
		this.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(110, 51, 179, 20);
		this.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(50, 151, 66, 17);
		this.add(lblTelefone);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(206, 257, 83, 39);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraCliente controladora = new ControladoraCliente();

				String nomeCliente = txtNome.getText();
				String sobreNomeCliente = txtSobreNome.getText();
				String telefoneCliente = fmtTelefone.getText();
				telefoneCliente.replaceAll("()", "");
				telefoneCliente.replaceAll("-", "");
				String observacaoCliente = ((JTextComponent) txtObservacao).getText();
				String cpfCliente = fmtCpf.getText();
				cpfCliente.replaceAll(".", "");
				cpfCliente.replaceAll("-", "");
				String mensagem = "";
				String msg = "";
				mensagem += controladora.validar(nomeCliente, sobreNomeCliente, telefoneCliente, cpfCliente,
						observacaoCliente);
				if (mensagem.isEmpty()) {
					Cliente cliente = new Cliente(nomeCliente, sobreNomeCliente, telefoneCliente, cpfCliente,
							observacaoCliente);
					msg = controladora.salvar(cliente);
					JOptionPane.showMessageDialog(null, msg);
					FrmPrimeiraTela tela = new FrmPrimeiraTela();
					tela.consultarProfissional();
					atualizarTabelaClientes();
				} else {
					JOptionPane.showMessageDialog(null, mensagem);
				}

			}
		});
		this.add(btnSalvar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(110, 257, 86, 39);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtSobreNome.setText("");
				fmtTelefone.setText("");
				fmtCpf.setText("");
				((JTextComponent) txtObservacao).setText("");
			}
		});
		this.add(btnLimpar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 609, 565, 10);
		add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(564, 309, 20, 299);
		add(separator_2);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 307, 565, 10);
		this.add(separator_1);

		txtObservacao = new JTextArea();
		txtObservacao.setBounds(110, 180, 179, 66);
		this.add(txtObservacao);

		JLabel lblObservacao = new JLabel("Observação:");
		lblObservacao.setBounds(34, 204, 77, 14);
		this.add(lblObservacao);

		JLabel lblConsultaDeCliente = new JLabel("Consulta de Cliente");
		lblConsultaDeCliente.setBounds(149, 317, 196, 14);
		lblConsultaDeCliente.setForeground(Color.BLACK);
		lblConsultaDeCliente.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 13));
		this.add(lblConsultaDeCliente);
		construirTabelaClientes();

		JLabel lblSobreNome = new JLabel("SobreNome");
		lblSobreNome.setBounds(35, 79, 77, 14);
		add(lblSobreNome);

		txtSobreNome = new JTextField();
		txtSobreNome.setBounds(110, 82, 179, 20);
		txtSobreNome.setColumns(10);
		add(txtSobreNome);

		try {
			MaskFormatter mascaraTelefone = new MaskFormatter("(##)#####-####");
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			fmtTelefone = new JFormattedTextField(mascaraTelefone);
			fmtTelefone.setBounds(110, 149, 179, 20);
			add(fmtTelefone);

			fmtCpf = new JFormattedTextField(mascaraCpf);
			fmtCpf.setBounds(110, 113, 179, 20);
			add(fmtCpf);
			
					
			

		} catch (ParseException e) {
		}
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, larguraDaTela, alturaDaTela);
		lblNewLabel.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/icones/deboche.gif")));
		add(lblNewLabel);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(0, 307, 20, 299);
		add(separator_3);
		
		

	}

	protected void atualizarTabelaClientes() {
		ControladoraCliente controller = new ControladoraCliente();
		clientes = controller.consultarTodos();
		construirTabelaClientes();
		DefaultTableModel model = (DefaultTableModel) tblConsultaCliente.getModel();
		for (Cliente cliente : clientes) {
			String[] novaLinha = new String[5];
			novaLinha[0] = cliente.getNome().toUpperCase();
			novaLinha[1] = cliente.getSobreNome().toUpperCase();
			novaLinha[2] = cliente.getTelefone().toUpperCase();
			novaLinha[3] = cliente.getCpf().toUpperCase();
			novaLinha[4] = cliente.getObservacao().toUpperCase();
			// Adiciona a nova linha na tabela
			model.addRow(novaLinha);

		}
	}

	Dimension dimensoesTela = Toolkit.getDefaultToolkit().getScreenSize();
	int larguraDaTela = (int) dimensoesTela.getWidth();
	int alturaDaTela = (int) (dimensoesTela.getHeight() - 100);

	private void construirTabelaClientes() {

		tblConsultaCliente.setModel(
				new DefaultTableModel(new Object[][] { { "Nome", "Sobrenome", "telefone", "CPF ", "Observacao" }, },
						new String[] { "Nome", "Sobrenome", "telefone", "CPF ", "Observacao" }));
		tblConsultaCliente.getColumnModel().getColumn(0).setPreferredWidth(73);
		tblConsultaCliente.getColumnModel().getColumn(1).setPreferredWidth(101);
		tblConsultaCliente.getColumnModel().getColumn(2).setPreferredWidth(92);
		tblConsultaCliente.getColumnModel().getColumn(3).setPreferredWidth(102);
		tblConsultaCliente.getColumnModel().getColumn(4).setPreferredWidth(90);

	}
}
