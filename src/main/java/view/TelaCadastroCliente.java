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
	private JFormattedTextField fmtTelefone;
	protected int linhaSelecionada;
	private JFormattedTextField fmtCpf;

	public TelaCadastroCliente() {
		setBackground(SystemColor.activeCaption);
		setBounds(0, 0, 1000, 761);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		JButton btnExcluirCliente = new JButton("Excluir Cliente");
		btnExcluirCliente.setBounds(273, 681, 122, 47);
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
		scrollPane.setBounds(75, 425, 832, 230);
		add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		tblConsultaCliente = new JTable();
		tblConsultaCliente.setBounds(43, 5, 744, 16);
		panel.add(tblConsultaCliente);
		tblConsultaCliente.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblCpf = new JLabel("Cpf ");
		lblCpf.setBounds(433, 110, 46, 14);
		add(lblCpf);
		add(btnExcluirCliente);

		JLabel lblCadastroDeCliente = new JLabel("Cadastro De Cliente");
		lblCadastroDeCliente.setBounds(388, 11, 196, 14);
		lblCadastroDeCliente.setFont(new Font("Segoe Script", Font.BOLD, 13));
		this.add(lblCadastroDeCliente);

		JLabel lblNome = new JLabel("Nome Completo");
		lblNome.setBounds(415, 54, 106, 14);
		this.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(360, 79, 204, 20);
		this.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(425, 170, 66, 17);
		this.add(lblTelefone);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(360, 337, 83, 39);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraCliente controladora = new ControladoraCliente();

				String nomeCompletoCliente = txtNome.getText().toUpperCase();
				String telefoneCliente = fmtTelefone.getText();
				telefoneCliente.replaceAll("()", "");
				telefoneCliente.replaceAll("-", "");
				String observacaoCliente = ((JTextComponent) txtObservacao).getText();
				String cpfCliente = fmtCpf.getText();
				cpfCliente.replaceAll(".", "");
				cpfCliente.replaceAll("-", "");
				String mensagem = "";
				String msg = "";
				mensagem += controladora.validar(nomeCompletoCliente, telefoneCliente, cpfCliente,
						observacaoCliente);
				if (mensagem.isEmpty()) {
					Cliente cliente = new Cliente(nomeCompletoCliente, telefoneCliente, cpfCliente,
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
		btnLimpar.setBounds(477, 337, 86, 39);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				fmtTelefone.setText("");
				fmtCpf.setText("");
				((JTextComponent) txtObservacao).setText("");
			}
		});
		this.add(btnLimpar);

		txtObservacao = new JTextArea();
		txtObservacao.setBounds(360, 254, 204, 66);
		this.add(txtObservacao);

		JLabel lblObservacao = new JLabel("Observação");
		lblObservacao.setBounds(425, 229, 77, 14);
		this.add(lblObservacao);

		JLabel lblConsultaDeCliente = new JLabel("Consulta de Cliente");
		lblConsultaDeCliente.setBounds(388, 387, 196, 14);
		lblConsultaDeCliente.setForeground(Color.BLACK);
		lblConsultaDeCliente.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 13));
		this.add(lblConsultaDeCliente);
		construirTabelaClientes();

		try {
			MaskFormatter mascaraTelefone = new MaskFormatter("(##)#####-####");
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			fmtTelefone = new JFormattedTextField(mascaraTelefone);
			fmtTelefone.setBounds(360, 198, 204, 20);
			add(fmtTelefone);

			fmtCpf = new JFormattedTextField(mascaraCpf);
			fmtCpf.setBounds(360, 139, 204, 20);
			add(fmtCpf);
			
					
			

		} catch (ParseException e) {
		}
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 0, 0);
		lblNewLabel.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/icones/deboche.gif")));
		add(lblNewLabel);
		
		

	}

	protected void atualizarTabelaClientes() {
		ControladoraCliente controller = new ControladoraCliente();
		clientes = controller.consultarTodos();
		construirTabelaClientes();
		DefaultTableModel model = (DefaultTableModel) tblConsultaCliente.getModel();
		for (Cliente cliente : clientes) {
			String[] novaLinha = new String[4];
			novaLinha[0] = cliente.getNomeCompleto().toUpperCase();
			novaLinha[1] = cliente.getTelefone().toUpperCase();
			novaLinha[2] = cliente.getCpf().toUpperCase();
			novaLinha[3] = cliente.getObservacao().toUpperCase();
			// Adiciona a nova linha na tabela
			model.addRow(novaLinha);

		}
	}

	Dimension dimensoesTela = Toolkit.getDefaultToolkit().getScreenSize();
	int larguraDaTela = (int) dimensoesTela.getWidth();
	int alturaDaTela = (int) (dimensoesTela.getHeight() - 100);

	private void construirTabelaClientes() {

		tblConsultaCliente.setModel(
				new DefaultTableModel(
			new Object[][] {
				{"Nome Completo", "telefone", "CPF ", "Observacao"},
			},
			new String[] {
				"Nome Completo", "telefone", "CPF ", "Observacao"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblConsultaCliente.getColumnModel().getColumn(0).setPreferredWidth(250);
		tblConsultaCliente.getColumnModel().getColumn(1).setPreferredWidth(188);
		tblConsultaCliente.getColumnModel().getColumn(2).setPreferredWidth(156);
		tblConsultaCliente.getColumnModel().getColumn(3).setPreferredWidth(236);

	}
}
