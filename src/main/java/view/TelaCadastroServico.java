package view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.ControladoraServico;
import model.vo.Servico;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class TelaCadastroServico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtServico;
	private JComboBox<Servico> cbServico;
	private ArrayList<Servico> servicos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaCadastroServico dialog = new TelaCadastroServico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaCadastroServico() {
		setBounds(100, 100, 368, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setModal(true);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblServico = new JLabel("Servico");
		lblServico.setBounds(72, 88, 46, 14);
		contentPanel.add(lblServico);
		
		txtServico = new JTextField();
		txtServico.setBounds(139, 84, 169, 23);
		contentPanel.add(txtServico);
		txtServico.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 51, 434, 2);
		contentPanel.add(separator);
		
		JLabel lblCadastroDeServios = new JLabel("Cadastro de Serviços");
		lblCadastroDeServios.setFont(new Font("Segoe Script", Font.ITALIC, 16));
		lblCadastroDeServios.setBounds(71, 11, 237, 14);
		contentPanel.add(lblCadastroDeServios);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraServico controladora = new ControladoraServico();
				String servicoDigitado = txtServico.getText();
				servicoDigitado.toUpperCase();
				String mensagem = "";
				mensagem += controladora.validar(servicoDigitado);
			
				if (mensagem.isEmpty()) {
					Servico servico = new Servico(servicoDigitado);
					servico = controladora.salvar(servico);
					JOptionPane.showMessageDialog(null, "SALVO COM SUCESSO!!");
				} else {
					JOptionPane.showMessageDialog(null, mensagem);
				}
			
			}
		});
		btnSalvar.setBounds(200, 210, 89, 40);
		contentPanel.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraServico controladora = new ControladoraServico();
				int indiceServicoExcluir = cbServico.getSelectedIndex();
				Servico servicoExcluir = servicos.get(indiceServicoExcluir);
				   String mensagem = controladora.excluir(servicoExcluir);
				   consultarServicos();
					JOptionPane.showMessageDialog(null,mensagem);
				
			}
		});
		btnExcluir.setBounds(89, 210, 89, 40);
		contentPanel.add(btnExcluir);
			
		consultarServicos();
		 cbServico = new JComboBox(servicos.toArray());
		 cbServico.setBounds(139, 145, 169, 23);
		contentPanel.add(cbServico);
		
		JLabel lblSelecioneParaExcluir = new JLabel("Selecione para Excluir");
		lblSelecioneParaExcluir.setBounds(10, 149, 119, 14);
		contentPanel.add(lblSelecioneParaExcluir);
	}

	 ArrayList<Servico> consultarServicos() {
		 ControladoraServico controller = new ControladoraServico();
	
		 
		 return	servicos = controller.consultarTodos();
	}
}
