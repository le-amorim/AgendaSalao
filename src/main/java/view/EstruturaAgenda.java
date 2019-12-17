package view;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import controller.ControladoraAgendamento;
import controller.ControladoraCliente;
import controller.ControladoraServico;
import model.vo.Agendamento;
import model.vo.Cliente;
import model.vo.Profissional;
import model.vo.Servico;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class EstruturaAgenda extends JDialog {
	protected JTable tblAgendamento;
	private JTextField txtValor;
	private Profissional profissionalSelecionado;
	private JLabel lblNomeProfissional;
	private JComboBox<Cliente> cbClientes;
	private ArrayList<Cliente> clientes;
	private ArrayList<Agendamento> agendamentosConsultado;
	private JComboBox<Servico> cbServico;
	private ArrayList<Servico> servicos;
	private DatePicker dataConsulta;
	@SuppressWarnings("unused")
	private ArrayList<Agendamento> agendamentosDodia;
	private LocalDate dataHoje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EstruturaAgenda dialog = new EstruturaAgenda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EstruturaAgenda() {

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		final DateTimePicker dataComHora = new DateTimePicker(dateSettings, null);
		dataComHora.setBounds(95, 60, 300, 30);
		getContentPane().add(dataComHora);

		JButton btnPegarData = new JButton("Criar data");
		btnPegarData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDate dataSelecionada = dataComHora.getDatePicker().getDate();
				LocalTime horaSelecionada = dataComHora.getTimePicker().getTime();
				LocalDateTime dataComHora = LocalDateTime.of(dataSelecionada, horaSelecionada);
				JOptionPane.showMessageDialog(null, "Data selecionada: " + dataSelecionada.toString());
				JOptionPane.showMessageDialog(null, "Horário selecionado: " + horaSelecionada.toString());
				JOptionPane.showMessageDialog(null, "Data e hora selecionada: " + dataComHora.toString());

			}
		});

		this.setModal(true);
		setBounds(203, 67, 680, 631);
		getContentPane().setLayout(null);

		JLabel lblAgendamento = new JLabel("Agendamento de ");
		lblAgendamento.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
		lblAgendamento.setBounds(231, 12, 173, 22);
		getContentPane().add(lblAgendamento);

		JLabel lblCliente = new JLabel("Cliente: ");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCliente.setBounds(36, 105, 46, 14);
		getContentPane().add(lblCliente);

		JLabel lblServico = new JLabel("Serviço\r\n:");
		lblServico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblServico.setBounds(33, 142, 46, 14);
		getContentPane().add(lblServico);

		JLabel lblHorario = new JLabel("Data/hora:");
		lblHorario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHorario.setBounds(19, 66, 59, 14);
		getContentPane().add(lblHorario);
		{
			JButton btnSalvar = new JButton("");
			btnSalvar.addActionListener(new ActionListener() {
				 LocalDateTime dataComHoraSelecionado;

				public void actionPerformed(ActionEvent e) {
					ControladoraAgendamento controladora = new ControladoraAgendamento();
					Servico servicoSelecionado = (Servico) cbServico.getSelectedItem();
					String valorDigitado = txtValor.getText();
					valorDigitado = valorDigitado.replaceAll(",", ".");

					LocalDate dataSelecionada = dataComHora.getDatePicker().getDate();
					LocalTime horaSelecionada = dataComHora.getTimePicker().getTime();
					Profissional profissionalSelecionado = getProfissionalSelecionado();
					Cliente clienteSelecionado = (Cliente) cbClientes.getSelectedItem();
					
					try {						
						 dataComHoraSelecionado = LocalDateTime.of(dataSelecionada, horaSelecionada);
						 String mensagem = "";
						 String msg ="";
						 mensagem += ControladoraAgendamento.validar(servicoSelecionado, valorDigitado,
								 profissionalSelecionado, dataComHoraSelecionado);
					
						 if (mensagem.isEmpty() || (dataComHora == null )) {
							 Agendamento agendamento = new Agendamento(clienteSelecionado, profissionalSelecionado,
									 servicoSelecionado, valorDigitado, dataComHoraSelecionado);
							  msg = controladora.salvar(agendamento);
							ArrayList<Agendamento> atualizarAgendamentos = consultarAgendamentos(dataSelecionada, profissionalSelecionado);
							 preencherTabela(atualizarAgendamentos);
							 JOptionPane.showMessageDialog(null, msg);
						 } else {
							 JOptionPane.showMessageDialog(null, mensagem);
						 }
					
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "É preciso preencher a data e hora para agendar");								
				
					}
					
					}
			});
			btnSalvar.setBounds(271, 180, 46, 41);
			getContentPane().add(btnSalvar);
			btnSalvar.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/Save.png")));
			btnSalvar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnSalvar);
		}
		{
			JButton btnLimpar = new JButton("");
			btnLimpar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtValor.setText("");
					cbClientes.setSelectedIndex(-1);
					cbServico.setSelectedIndex(-1);

				}
			});
			btnLimpar.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/limpar.png")));
			btnLimpar.setBounds(327, 180, 53, 41);
			getContentPane().add(btnLimpar);
			btnLimpar.setActionCommand("Cancel");
		}

		JButton btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tblAgendamento.getModel();
				int valorBotao = JOptionPane.YES_NO_OPTION;
				valorBotao = JOptionPane.showConfirmDialog(null, "deseja realmente excluir este Agendamento?",
						"CONFIRMAÇÃO", valorBotao);
				if (valorBotao == JOptionPane.YES_OPTION) {
					if (tblAgendamento.getSelectedRow() >= 0) {
						int linhaSelecionada = tblAgendamento.getSelectedRow();
						Agendamento agendamentoSelecionado = agendamentosConsultado.get(linhaSelecionada - 1);
						model.removeRow(tblAgendamento.getSelectedRow());
						ControladoraAgendamento controladoraAgendamento = new ControladoraAgendamento();
						String mensagem = controladoraAgendamento.excluir(agendamentoSelecionado);
						JOptionPane.showMessageDialog(null, mensagem);
					} else {
						JOptionPane.showMessageDialog(null, "Favor selecionar uma linha");

					}
				} else {
					JOptionPane.showMessageDialog(null, "Cancelado");
				}

			}
		});
		btnCancelar.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/delete.png")));
		btnCancelar.setActionCommand("Delete");
		btnCancelar.setBounds(369, 555, 30, 30);
		getContentPane().add(btnCancelar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 313, 565, 238);
		getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlShadow);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(panel);

		tblAgendamento = new JTable();
		panel.add(tblAgendamento);
		tblAgendamento.setBackground(UIManager.getColor("Button.highlight"));
		construirTabelaAgendamento();

		txtValor = new JNumberFormatField(2);
		txtValor.setBounds(346, 136, 49, 29);
		getContentPane().add(txtValor);
		// txtValor.setColumns(10);

		JLabel lblValor = new JLabel("");
		lblValor.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/money.png")));
		lblValor.setBounds(346, 93, 53, 35);

		getContentPane().add(lblValor);

		lblNomeProfissional = new JLabel("");
		lblNomeProfissional.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
		lblNomeProfissional.setBounds(400, 12, 173, 22);
		getContentPane().add(lblNomeProfissional);

		consultarClientes();
		cbClientes = new JComboBox(clientes.toArray());
		cbClientes.setSelectedIndex(-1);
		cbClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		cbClientes.setBounds(95, 98, 204, 30);
		getContentPane().add(cbClientes);

		JButton btnAddCliente = new JButton("");
		btnAddCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroCliente2 tela = new TelaCadastroCliente2();
				tela.setVisible(true);

			}
		});
		btnAddCliente.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/plus.png")));
		btnAddCliente.setBounds(5, 104, 24, 23);
		getContentPane().add(btnAddCliente);

		consultarServicos();
		cbServico = new JComboBox(servicos.toArray());
		cbServico.setBounds(95, 139, 204, 30);
		cbServico.setSelectedIndex(-1);
		getContentPane().add(cbServico);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroServico tela = new TelaCadastroServico();
				tela.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/plus.png")));
		button.setBounds(5, 141, 24, 23);
		getContentPane().add(button);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 225, 664, 14);
		getContentPane().add(separator);

		JLabel lblSelecioneDataPara = new JLabel("Selecione data para consulta:");
		lblSelecioneDataPara.setBounds(62, 282, 181, 14);
		getContentPane().add(lblSelecioneDataPara);

		dataConsulta = new DatePicker();
		dataConsulta.setBounds(250, 275, 288, 30);
		getContentPane().add(dataConsulta);

		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/lupa.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profissional profissionalSelecionado = getProfissionalSelecionado();
				LocalDate dataEscolhida = dataConsulta.getDate();
				agendamentosConsultado = consultarAgendamentos(dataEscolhida, profissionalSelecionado);
				preencherTabela(agendamentosConsultado);
				String mensagemDoBuscar = "";
				mensagemDoBuscar += ControladoraAgendamento.validarData(dataEscolhida);
				
				 if(mensagemDoBuscar.isEmpty())  {
				
					JOptionPane.showMessageDialog(null, "Consulta realizada!!");
				} else {
					JOptionPane.showMessageDialog(null, mensagemDoBuscar);
				}

				if (agendamentosConsultado.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não possui agendamentos para esse dia");
				} else {

					System.out.println("possui agendamentos");
				}

			}
		});
		btnBuscar.setBounds(310, 555, 30, 30);
		getContentPane().add(btnBuscar);

		JLabel lblConsultarAgenda = new JLabel("Consultar Agendamentos");
		lblConsultarAgenda.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
		lblConsultarAgenda.setBounds(246, 239, 245, 25);
		getContentPane().add(lblConsultarAgenda);
		dataConsulta.getComponentToggleCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	private void construirTabelaAgendamento() {
		tblAgendamento.setModel(
				new DefaultTableModel(new Object[][] { { "Horario", "Cliente", "Telefone", "Servico", "Valor" }, },
						new String[] { "Horario", "Cliente", "Telefone", "Servico", "Valor" }));
		tblAgendamento.getColumnModel().getColumn(0).setPreferredWidth(120);
		tblAgendamento.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblAgendamento.getColumnModel().getColumn(2).setPreferredWidth(110);
		tblAgendamento.getColumnModel().getColumn(3).setPreferredWidth(110);
		tblAgendamento.getColumnModel().getColumn(4).setPreferredWidth(90);

	}

	protected void preencherTabela(ArrayList<Agendamento> agendamentosConsultado) {
		construirTabelaAgendamento();
		DefaultTableModel model = (DefaultTableModel) tblAgendamento.getModel();

		for (Agendamento agendamento : agendamentosConsultado) {
			String[] novaLinha = new String[5];
			novaLinha[0] = obterHora(agendamento.getDataComHora());
			novaLinha[1] = agendamento.getCliente().getNome();
			novaLinha[2] = agendamento.getCliente().getTelefone();
			novaLinha[3] = agendamento.getServico().getServico();
			novaLinha[4] = agendamento.getValor() + "";
			model.addRow(novaLinha);
		}
	}

	protected void preencherTabelaDoDia(ArrayList<Agendamento> agendamentosDodia) {
		construirTabelaAgendamento();
		DefaultTableModel model = (DefaultTableModel) tblAgendamento.getModel();

		for (Agendamento agendamento : agendamentosDodia) {
			String[] novaLinha = new String[5];
			novaLinha[0] = obterHora(agendamento.getDataComHora());
			novaLinha[1] = agendamento.getCliente().getNome();
			novaLinha[2] = agendamento.getCliente().getTelefone();
			novaLinha[3] = agendamento.getServico().getServico();
			novaLinha[4] = agendamento.getValor() + "";
			model.addRow(novaLinha);
		}
	}

	private String obterHora(LocalDateTime dataComHora) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.systemDefault());

		String horaEmString = dataComHora.format(formatter);
		return horaEmString;
	}

	protected ArrayList<Agendamento> consultarAgendamentos(LocalDate dataEscolhida,
			Profissional profissionalSelecionado) {
		ControladoraAgendamento controladoraAgendamento = new ControladoraAgendamento();
		return agendamentosConsultado = controladoraAgendamento.consultaPorDia(dataEscolhida, profissionalSelecionado);

	}

	public Profissional getProfissionalSelecionado() {
		return profissionalSelecionado;
	}

	public void setProfissionalSelecionado(Profissional profissionalSelecionado) {
		this.lblNomeProfissional.setText(profissionalSelecionado.getNome());
		this.profissionalSelecionado = profissionalSelecionado;
	}

	ArrayList<Cliente> consultarClientes() {
		ControladoraCliente controladora = new ControladoraCliente();
		return clientes = controladora.consultarTodos();
	}

	ControladoraServico controladoraServico = new ControladoraServico();

	ArrayList<Servico> consultarServicos() {
		ControladoraServico controladoraServico = new ControladoraServico();
		return servicos = controladoraServico.consultarTodos();

	}

	protected ArrayList<Agendamento> consultarAgendamentosDoDia() {
		ControladoraAgendamento controller = new ControladoraAgendamento();

		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		dataHoje = LocalDate.now();
		dataHoje.format(formatador);
		return agendamentosDodia = controller.consultarPeloDiaAtual(dataHoje, profissionalSelecionado);
	}

}
