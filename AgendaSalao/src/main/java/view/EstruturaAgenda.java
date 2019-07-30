package view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class EstruturaAgenda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtHora;
	private JTextField txtNome;
	private JTextField txtTel;
	private JTextField txtServico;
	private JTable table;

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
		setBounds(100, 100, 665, 415);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 649, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblAgendamento = new JLabel("Agendamento");
		lblAgendamento.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
		lblAgendamento.setBounds(245, 12, 140, 22);
		getContentPane().add(lblAgendamento);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(290, 65, 46, 14);
		getContentPane().add(lblNome);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(20, 105, 65, 14);
		getContentPane().add(lblTelefone);
		
		JLabel lblServio = new JLabel("Serviço\r\n");
		lblServio.setBounds(290, 105, 46, 14);
		getContentPane().add(lblServio);
		
		txtHora = new JTextField();
		txtHora.setBounds(85, 62, 179, 20);
		getContentPane().add(txtHora);
		txtHora.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(346, 62, 179, 20);
		getContentPane().add(txtNome);
		
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(85, 102, 179, 20);
		getContentPane().add(txtTel);
		
		txtServico = new JTextField();
		txtServico.setBounds(346, 102, 179, 20);
		getContentPane().add(txtServico);
		txtServico.setColumns(10);
		
		JLabel lblHorario = new JLabel("Horario");
		lblHorario.setBounds(22, 65, 53, 14);
		getContentPane().add(lblHorario);
		{
			JButton okButton = new JButton("");
			okButton.setBounds(530, 335, 46, 41);
			getContentPane().add(okButton);
			okButton.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/Save.png")));
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("");
			cancelButton.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/limpar.png")));
			cancelButton.setBounds(586, 335, 53, 41);
			getContentPane().add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/delete.png")));
		button.setActionCommand("OK");
		button.setBounds(474, 335, 51, 41);
		getContentPane().add(button);
		
		JButton btnAlterar = new JButton("");
		btnAlterar.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/relogio.png")));
		btnAlterar.setActionCommand("OK");
		btnAlterar.setBounds(418, 335, 46, 41);
		getContentPane().add(btnAlterar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 130, 442, 196);
		getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Hor\u00E1rio", "Cliente", "Telefone", "Servi\u00E7o", "Valor"},
				{"9:00", null, null, null, null},
				{"9:30", null, null, null, null},
				{"10:00", null, null, null, null},
				{"10:30", null, null, null, null},
				{"11:00", null, null, null, null},
				{"11:30", null, null, null, null},
				{"12:00", null, null, null, null},
				{"12:30", null, null, null, null},
				{"13:00", null, null, null, null},
				{"13:30", null, null, null, null},
				{"14:00", null, null, null, null},
				{"14:30", null, null, null, null},
				{"15:00", null, null, null, null},
				{"15:30", null, null, null, null},
				{"16:00", null, null, null, null},
				{"16:30", null, null, null, null},
				{"17:00", null, null, null, null},
				{"17:30", null, null, null, null},
				{"18:00", null, null, null, null},
				{"18:30", null, null, null, null},
				{"19:00", null, null, null, null},
			},
			new String[] {
				"Horario", "Cliente", "Telefone", "Servico", "Valor"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		panel.add(table);
	}
}
