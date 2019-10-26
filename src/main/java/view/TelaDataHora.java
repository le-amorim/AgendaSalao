package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class TelaDataHora {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtServico;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDataHora window = new TelaDataHora();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaDataHora() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(30, 64, 40, 20);
		frame.getContentPane().add(lblData);

		// Configurações da parte de DATAS do componente
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		final DateTimePicker dataTeste = new DateTimePicker(dateSettings, null);
		dataTeste.setBounds(80, 60, 300, 30);
		frame.getContentPane().add(dataTeste);

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

				// Preenche uma data utilizando os dois campos do componente
				Date dataCompleta = new Date(dataSelecionada.getYear(), dataSelecionada.getMonthValue(),
						dataSelecionada.getDayOfMonth(), horaSelecionada.getHour(), horaSelecionada.getMinute(),
						horaSelecionada.getSecond());
					EstruturaAgenda agendamento = new EstruturaAgenda();
					agendamento.setVisible(true);
			
			}
		});
		btnPegarData.setBounds(116, 265, 181, 23);
		frame.getContentPane().add(btnPegarData);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(24, 112, 46, 14);
		frame.getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(80, 110, 171, 17);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(24, 146, 46, 14);
		frame.getContentPane().add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(80, 144, 171, 17);
		frame.getContentPane().add(txtTelefone);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 44, 465, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblServico = new JLabel("Servico");
		lblServico.setBounds(24, 182, 46, 14);
		frame.getContentPane().add(lblServico);
		
		txtServico = new JTextField();
		txtServico.setColumns(10);
		txtServico.setBounds(80, 179, 171, 17);
		frame.getContentPane().add(txtServico);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(30, 223, 46, 14);
		frame.getContentPane().add(lblValor);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(80, 220, 171, 17);
		frame.getContentPane().add(textField);
	}
}
