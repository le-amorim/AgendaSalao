package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class EstruturaAgenda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
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
		
		textField = new JTextField();
		textField.setBounds(85, 62, 179, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(346, 62, 179, 20);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(85, 102, 179, 20);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(346, 102, 179, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
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
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		panel.setBounds(106, 130, 397, 197);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBackground(Color.ORANGE);
		table.setBorder(UIManager.getBorder("FormattedTextField.border"));
		table.setBounds(10, 11, 376, 173);
		panel.add(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Horario", "Cliente", "Telefone", "Servi\u00E7o", "Valor"},
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
				"Horario", "Cliente", "Telefone", "Servi\u00E7o", "Valor"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(38);
		table.getColumnModel().getColumn(4).setPreferredWidth(45);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(499, 133, 17, 188);
		getContentPane().add(scrollBar);
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
	}
}
