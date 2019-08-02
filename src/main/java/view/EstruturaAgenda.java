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
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class EstruturaAgenda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtTel;
	private JTextField txtServico;
	private JTable table;
	private JTextField txtValor;

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
		lblNome.setBounds(245, 65, 46, 14);
		getContentPane().add(lblNome);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(20, 105, 65, 14);
		getContentPane().add(lblTelefone);
		
		JLabel lblServio = new JLabel("Serviço\r\n");
		lblServio.setBounds(245, 105, 46, 14);
		getContentPane().add(lblServio);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(301, 62, 118, 20);
		getContentPane().add(txtNome);
		
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(85, 102, 140, 20);
		getContentPane().add(txtTel);
		
		txtServico = new JTextField();
		txtServico.setBounds(301, 102, 118, 20);
		getContentPane().add(txtServico);
		txtServico.setColumns(10);
		
		JLabel lblHorario = new JLabel("Horario");
		lblHorario.setBounds(22, 65, 53, 14);
		getContentPane().add(lblHorario);
		{
			JButton okButton = new JButton("");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				}
			});
			okButton.setBounds(530, 335, 46, 41);
			getContentPane().add(okButton);
			okButton.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/Save.png")));
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtTel.setText("");
				txtServico.setText("");
				txtValor.setText("");
				}
			});
			cancelButton.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/limpar.png")));
			cancelButton.setBounds(586, 335, 53, 41);
			getContentPane().add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/delete.png")));
		button.setActionCommand("Delete");
		button.setBounds(474, 335, 51, 41);
		getContentPane().add(button);
		
		JButton btnAlterar = new JButton("");
		btnAlterar.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/relogio.png")));
		btnAlterar.setActionCommand("OK");
		btnAlterar.setBounds(418, 335, 46, 41);
		getContentPane().add(btnAlterar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 130, 442, 196);
		getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlShadow);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(panel);
		
		table = new JTable();
		table.setBackground(UIManager.getColor("Button.highlight"));
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
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		panel.add(table);
		
		JComboBox BoxHorario = new JComboBox();
		BoxHorario.setModel(new DefaultComboBoxModel(new String[] {"<Selecione um horário>", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00"}));
		BoxHorario.setBounds(85, 62, 140, 17);
		getContentPane().add(BoxHorario);
		
		txtValor = new JTextField();
		txtValor.setBounds(448, 101, 79, 22);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);
		
		JLabel lblValor = new JLabel("");
		lblValor.setIcon(new ImageIcon(EstruturaAgenda.class.getResource("/icones/money.png")));
		lblValor.setBounds(460, 48, 53, 42);
		getContentPane().add(lblValor);
	}
}
