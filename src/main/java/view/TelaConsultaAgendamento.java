package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.Box;
import java.awt.CardLayout;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import com.github.lgooddatepicker.components.DatePicker;
import controller.ControladoraAgendamento;
import controller.ControladoraFuncionario;
import controller.ControladoraServico;
import model.vo.Agendamento;
import model.vo.Profissional;
import model.vo.Servico;
import model.vo.seletor.AgendamentoSeletor;
import javax.swing.UIManager;
import javax.swing.JFormattedTextField;

public class TelaConsultaAgendamento extends JDialog {

	private static final int TAMANHO_PAGINA = 0;

	private final JPanel contentPanel = new JPanel();
	private JTable tblAvacada;
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private JTable tblCliente;
	private String[] colunasTabelaCliente = { "Data", "Nome", "Servico", "Telefone", "Valor" };
	private ArrayList<Agendamento> agendamentos;
	private DatePicker dataInicial;
	private DatePicker dataFinal;
	private ArrayList<Profissional> profissionais;
	private ArrayList<Servico> servicos;
	private JComboBox<Servico> cbServico;
	private Component lblPaginaAtual;
	private int paginaAtual = 1;
	private List<Agendamento> agendamentosConsultado;
	private JComboBox<Profissional> cbNomeProfissional;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaConsultaAgendamento dialog = new TelaConsultaAgendamento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaConsultaAgendamento() {
		setBounds(100, 100, 599, 599);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(27, 55, 538, 2);
		contentPanel.add(separator_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 30, 583, 15);
		contentPanel.add(separator);

		JLabel lblConsultaAvanada = new JLabel("Consulta Avançada");
		lblConsultaAvanada.setFont(new Font("Segoe Script", Font.PLAIN, 19));
		lblConsultaAvanada.setBounds(176, 11, 217, 23);
		contentPanel.add(lblConsultaAvanada);

		JLabel lblLupa = new JLabel("");
		lblLupa.setIcon(new ImageIcon(TelaConsultaAgendamento.class.getResource("/icones/lupa.png")));
		lblLupa.setBounds(190, 35, 27, 26);
		contentPanel.add(lblLupa);

		JLabel lblFiltrosDeConsulta = new JLabel("Filtros de consulta");
		lblFiltrosDeConsulta.setBounds(227, 40, 125, 14);
		contentPanel.add(lblFiltrosDeConsulta);
		consultarProfissional();
		cbNomeProfissional = new JComboBox(profissionais.toArray());
		cbNomeProfissional.setSelectedIndex(-1);
		cbNomeProfissional.setBounds(140, 65, 378, 26);
		contentPanel.add(cbNomeProfissional);

		JLabel lblNomeDoProfissional = new JLabel("Nome do Profissional");
		lblNomeDoProfissional.setBounds(30, 70, 125, 14);
		contentPanel.add(lblNomeDoProfissional);

		JLabel lblDataInicial = new JLabel("Data inicial");
		lblDataInicial.setBounds(73, 140, 77, 14);
		contentPanel.add(lblDataInicial);

		JLabel lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setBounds(78, 180, 53, 15);
		contentPanel.add(lblDataFinal);

		dataInicial = new DatePicker();
		dataInicial.getComponentDateTextField().setText("<<SELECIONE UMA DATA INICIAL>>");
		dataInicial.getComponentToggleCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dataInicial.setBounds(140, 135, 378, 30);
		contentPanel.add(dataInicial);

		dataFinal = new DatePicker();
		dataFinal.getComponentDateTextField().setForeground(Color.BLACK);
		dataFinal.getComponentDateTextField().setText("<<SELECIONE UMA DATA FINAL>>");
		dataFinal.setBounds(140, 175, 378, 30);
		contentPanel.add(dataFinal);

		consultarServico();
		cbServico = new JComboBox(servicos.toArray());
		cbServico.setSelectedIndex(-1);
		cbServico.setBounds(140, 100, 378, 26);
		contentPanel.add(cbServico);

		JLabel lblServico = new JLabel("Servico");
		lblServico.setBounds(85, 105, 46, 14);
		contentPanel.add(lblServico);

		tblAvacada = new JTable();
		tblAvacada.setBackground(Color.GRAY);
		tblAvacada.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		tblAvacada.setBounds(27, 30, 536, 180);
		contentPanel.add(tblAvacada);

		JLabel lblTabelaDeClientes = new JLabel("Tabela de Clientes");
		lblTabelaDeClientes.setFont(new Font("Segoe Script", Font.ITALIC, 19));
		lblTabelaDeClientes.setBounds(176, 253, 217, 20);
		contentPanel.add(lblTabelaDeClientes);
		
				lblPaginaAtual = new JLabel("1");
				lblPaginaAtual.setBounds(286, 537, 46, 14);
				((JLabel) lblPaginaAtual).setText(paginaAtual + "");
				contentPanel.add(lblPaginaAtual);

		tblCliente = new JTable();
		tblCliente.setBorder(UIManager.getBorder("PopupMenu.border"));
		tblCliente.setBounds(17, 284, 546, 242);
		contentPanel.add(tblCliente);

		JButton btnConsultar = new JButton("");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarAgendamentos();
			}
		});

		btnConsultar.setIcon(new ImageIcon(TelaConsultaAgendamento.class.getResource("/icones/lupa2.png")));
		btnConsultar.setBounds(499, 216, 66, 57);
		contentPanel.add(btnConsultar);

		JButton btnProximo = new JButton("Proximo");
		btnProximo.setBounds(392, 532, 89, 23);
		contentPanel.add(btnProximo);

		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(140, 532, 89, 23);
		contentPanel.add(btnAnterior);
		atualizarTabelaAgendamento(agendamentos);
	}

	private void consultarAgendamentos() {
		
		((JLabel) lblPaginaAtual).setText(paginaAtual + "");

		ControladoraAgendamento controller = new ControladoraAgendamento();
		AgendamentoSeletor seletor = new AgendamentoSeletor();

		seletor.setPagina(paginaAtual);
		seletor.setLimite(TAMANHO_PAGINA);

		if (cbNomeProfissional.getSelectedIndex() > 0) {
			seletor.setNomeProfissional(cbNomeProfissional.getSelectedItem().toString());
		}

		if (cbServico.getSelectedIndex() > 0) {
			seletor.setServico(cbServico.getSelectedItem().toString());
		}

		seletor.setDataInicio(dataInicial.getDate());
		seletor.setDataFim(dataFinal.getDate());

		List<Agendamento> agendamentos = controller.listarAgendamento(seletor);
		atualizarTabelaAgendamento(agendamentos);

	}

	private void atualizarTabelaAgendamento(List<Agendamento> agendamentos) {
		agendamentosConsultado = agendamentos;
		
		tblCliente.setModel(new DefaultTableModel(new String[][] { { "IDAgendamento", "Cliente", "Serviço", "Telefone", "Data" }, },
				new String[] { "IDAgendamento", "Cliente", "Serviço", "Telefone", "Data" }));

	
		DefaultTableModel model = (DefaultTableModel) tblCliente.getModel();
		for (Agendamento agendamento : agendamentos) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataFormatada = agendamento.getDataComHora().format(formatter);	

			String[] novaLinha = new String[] { agendamento.getIdAgendamento() + "",
					agendamento.getCliente().getNome() + "", agendamento.getServico().getServico(),
					agendamento.getCliente().getTelefone() + "", agendamento.getValor() + "", dataFormatada };
			model.addRow(novaLinha);
		}

	
	
	}

	

	
	private ArrayList<Servico> consultarServico() {
		ControladoraServico controller = new ControladoraServico();
		return servicos = controller.consultarTodos();
	}

	ArrayList<Profissional> consultarProfissional() {
		ControladoraFuncionario controladora = new ControladoraFuncionario();
		return profissionais = controladora.consultarTodos();

	}


	private void ConstruirTabela() {
		tblCliente.setModel(new DefaultTableModel(new Object[][] { colunasTabelaCliente, }, colunasTabelaCliente));
	}
}
