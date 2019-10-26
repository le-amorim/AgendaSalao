package view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.ControladoraFuncionario;
import model.vo.Profissional;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TelaCadastroFuncionario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtEspecialidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaCadastroFuncionario dialog = new TelaCadastroFuncionario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaCadastroFuncionario() {
		setBounds(203, 67, 319, 283);
		this.setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 25, 303, 10);
		contentPanel.add(separator);
		
		JLabel lblCadastroDeProfissional = new JLabel("Cadastro de Profissional");
		lblCadastroDeProfissional.setFont(new Font("Segoe Script", Font.BOLD, 13));
		lblCadastroDeProfissional.setBounds(52, 8, 196, 14);
		contentPanel.add(lblCadastroDeProfissional);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(34, 54, 46, 14);
		contentPanel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(114, 51, 134, 20);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblEspecialidades = new JLabel("Especialidades");
		lblEspecialidades.setBounds(4, 111, 100, 14);
		contentPanel.add(lblEspecialidades);
		
		txtEspecialidade = new JTextField();
		txtEspecialidade.setColumns(10);
		txtEspecialidade.setBounds(114, 108, 134, 20);
		contentPanel.add(txtEspecialidade);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ControladoraFuncionario controladora = new ControladoraFuncionario();
			String nomeDigitado = txtNome.getText();
			String EspecialidadeDigitado = txtEspecialidade.getText();
			
			String mensagem = controladora.validarCamposDigitado(nomeDigitado, EspecialidadeDigitado);
			if (mensagem.isEmpty()) {
			Profissional profissional = new Profissional(nomeDigitado, EspecialidadeDigitado);
			profissional = controladora.salvar(profissional);
			JOptionPane.showMessageDialog(null,"salvo com sucesso!");
				
			}else {
				JOptionPane.showMessageDialog(null, mensagem);
			}
			
			}
		});
		btnSalvar.setBounds(204, 210, 89, 23);
		contentPanel.add(btnSalvar);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 189, 303, 10);
		contentPanel.add(separator_1);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(105, 210, 89, 23);
		contentPanel.add(btnLimpar);
	}
}
