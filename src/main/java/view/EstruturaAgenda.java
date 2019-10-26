package view;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;

import controller.ControladoraAgendamento;
import controller.ControladoraCliente;
import controller.ControladoraFuncionario;
import model.vo.Agendamento;
import model.vo.Cliente;
import model.vo.Profissional;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class EstruturaAgenda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtServico;
	private JTable table;
	private JTextField txtValor;
	private ArrayList<Profissional> profissionais;
	private Profissional profissionalSelecionado;
	private Cliente clienteSelecionado;
	private JLabel lblNomeProfissional;
	private JComboBox <Cliente> cbClientes;
	private ArrayList<Cliente> clientes;

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
	public EstruturaAgenda() {

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		final DateTimePicker dataTeste = new DateTimePicker(dateSettings, null);
		dataTeste.setBounds(95, 60, 300, 30);
		getContentPane().add(dataTeste);

		JButton btnPegarData = new JButton("Criar data");
		btnPegarData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Atributos próprios do componente datePicker (date e time)
				LocalDate dataSelecionada = dataTeste.getDatePicker().getDate();
				LocalTime horaSelecionada = dataTeste.getTimePicker().getTime();
				LocalDateTime dataComHora = LocalDateTime.of(dataSelecionada, horaSelecionada);

				JOptionPane.showMessageDialog(null, "Data selecionada: " + dataSelecionada.toString());
				JOptionPane.showMessageDialog(null, "Horário selecionado: " + horaSelecionada.toString());
				JOptionPane.showMessageDialog(null, "Data e hora selecionada: " + dataComHora.toString());

			}
		});

		this.setModal(true);
		setBounds(203, 67, 689, 468);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 649, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblAgendamento = new JLabel("Agendamento de ");
		lblAgendamento.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
		lblAgendamento.setBounds(231, 12, 173, 22);
		getContentPane().add(lblAgendamento);

		JLabel lblCliente = new JLabel("Cliente: ");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCliente.setBounds(33, 108, 46, 14);
		getContentPane().add(lblCliente);

		JLabel lblServico = new JLabel("Serviço\r\n");
		lblServico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblServico.setBounds(36, 142, 46, 14);
		getContentPane().add(lblServico);

		txtServico = new JTextField();
		txtServico.setBounds(96, 135, 206, 29);
		getContentPane().add(txtServico);
		txtServico.setColumns(10);

		JLabel lblHorario = new JLabel("Data/hora");
		lblHorario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHorario.setBounds(15, 66, 70, 14);
		getContentPane().add(lblHorario);
		{
			JButton okButton = new JButton("");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String servicoDigitado = txtServico.getText();
					String valorDigitado = txtValor.getText();
					
					LocalDate dataSelecionada = dataTeste.getDatePicker().getDate();
					LocalTime horaSelecionada = dataTeste.getTimePicker().getTime();
					Profissional profissionalSelecionado = getProfissionalSelecionado();
					Cliente clienteSelecionado = getClienteSelecionado();
					LocalDateTime dataComHoraSelecionado = LocalDateTime.of(dataSelecionada, horaSelecionada);
					String mensagem = "";
					mensagem += ControladoraAgendamento.validar(servicoDigitado, valorDigitado,
							profissionalSelecionado);
					if (mensagem.isEmpty()) {
						 Agendamento agendamento = new Agendamento(clienteSelecionado,profissionalSelecionado,
						 servicoDigitado,
						 valorDigitado, dataComHoraSelecionado);

					}
				}
			});
			okButton.setBounds(603, 377, 46, 41);
			getContentPane().add(okButton);
			okButton.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/Save.png")));
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtServico.setText("");
					txtValor.setText("");
				}
			});
			cancelButton.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/limpar.png")));
			cancelButton.setBounds(479, 377, 53, 41);
			getContentPane().add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}

		JButton button = new JButton("");
		button.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/delete.png")));
		button.setActionCommand("Delete");
		button.setBounds(542, 377, 51, 41);
		getContentPane().add(button);

		JButton btnAlterar = new JButton("");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAlterar.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/relogio.png")));
		btnAlterar.setActionCommand("OK");
		btnAlterar.setBounds(423, 377, 46, 41);
		getContentPane().add(btnAlterar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(95, 167, 442, 196);
		getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlShadow);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(panel);

		table = new JTable();
		table.setBackground(UIManager.getColor("Button.highlight"));
		table.setModel(new DefaultTableModel(
				new Object[][] { { "Horario", "Cliente", "Telefone", "Servico", "Valor" },
						{ "9:00", null, null, null, null }, { "9:30", null, null, null, null },
						{ "10:00", null, null, null, null }, { "10:30", null, null, null, null },
						{ "11:00", null, null, null, null }, { "11:30", null, null, null, null },
						{ "12:00", null, null, null, null }, { "12:30", null, null, null, null },
						{ "13:00", null, null, null, null }, { "13:30", null, null, null, null },
						{ "14:00", null, null, null, null }, { "14:30", null, null, null, null },
						{ "15:00", null, null, null, null }, { "15:30", null, null, null, null },
						{ "16:00", null, null, null, null }, { "16:30", null, null, null, null },
						{ "17:00", null, null, null, null }, { "17:30", null, null, null, null },
						{ "18:00", null, null, null, null }, { "18:30", null, null, null, null },
						{ "19:00", null, null, null, null }, },
				new String[] { "Horario", "Cliente", "Telefone", "Servico", "Valor" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		panel.add(table);

		txtValor = new JTextField();
		txtValor.setBounds(346, 130, 43, 30);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);

		JLabel lblValor = new JLabel("");
		lblValor.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/money.png")));
		lblValor.setBounds(343, 92, 53, 35);
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
		cbClientes.setBounds(95, 98, 207, 30);
		getContentPane().add(cbClientes);
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

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	
	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = (Cliente) cbClientes.getSelectedItem();
	}
}