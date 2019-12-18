package view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import com.github.lgooddatepicker.components.DatePicker;
import controller.ControladoraAgendamento;
import controller.ControladoraFuncionario;
import controller.ControladoraServico;
import model.vo.Agendamento;
import model.vo.Profissional;
import model.vo.Servico;
import model.vo.seletor.AgendamentoSeletor;
import javax.swing.UIManager;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class TelaConsultaAgendamento extends JDialog {
	
	private static final int TAMANHO_PAGINA = 0;

	private final JPanel contentPanel = new JPanel();
	private JTable tblAvacada;
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private JTable tblAgendamento;
	private ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();
	private DatePicker dataInicial;
	private DatePicker dataFinal;
	private ArrayList<Profissional> profissionais;
	private ArrayList<Servico> servicos;
	private JComboBox<Servico> cbServico;
	private int paginaAtual = 1;
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

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TelaConsultaAgendamento() {
		this.setModal(true);
		setBounds(100, 100, 662, 599);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(19, 55, 606, 2);
		contentPanel.add(separator_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 30, 646, 15);
		contentPanel.add(separator);

		JLabel lblConsultaAvanada = new JLabel("Consulta Avançada");
		lblConsultaAvanada.setBounds(176, 3, 217, 23);
		lblConsultaAvanada.setFont(new Font("Segoe Script", Font.PLAIN, 19));
		contentPanel.add(lblConsultaAvanada);

		JLabel lblLupa = new JLabel("");
		lblLupa.setBounds(190, 35, 27, 26);
		lblLupa.setIcon(new ImageIcon(TelaConsultaAgendamento.class.getResource("/icones/lupa.png")));
		contentPanel.add(lblLupa);

		JLabel lblFiltrosDeConsulta = new JLabel("Filtros de consulta");
		lblFiltrosDeConsulta.setBounds(227, 40, 125, 14);
		contentPanel.add(lblFiltrosDeConsulta);
		consultarProfissional();
		cbNomeProfissional = new JComboBox(profissionais.toArray());
		cbNomeProfissional.setBounds(150, 65, 378, 26);
		cbNomeProfissional.setSelectedIndex(-1);
		contentPanel.add(cbNomeProfissional);

		JLabel lblNomeDoProfissional = new JLabel("Nome do Profissional");
		lblNomeDoProfissional.setBounds(15, 68, 147, 16);
		contentPanel.add(lblNomeDoProfissional);

		JLabel lblDataInicial = new JLabel("Data inicial");
		lblDataInicial.setBounds(73, 140, 100, 14);
		contentPanel.add(lblDataInicial);

		JLabel lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setBounds(80, 180, 99, 15);
		contentPanel.add(lblDataFinal);

		dataInicial = new DatePicker();
		dataInicial.setBounds(150, 135, 378, 30);
		dataInicial.getComponentDateTextField().setText("<<SELECIONE UMA DATA INICIAL>>");
		dataInicial.getComponentToggleCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPanel.add(dataInicial);

		dataFinal = new DatePicker();
		dataFinal.setBounds(150, 175, 378, 30);
		dataFinal.getComponentDateTextField().setForeground(Color.BLACK);
		dataFinal.getComponentDateTextField().setText("<<SELECIONE UMA DATA FINAL>>");
		contentPanel.add(dataFinal);

		consultarServico();
		cbServico = new JComboBox(servicos.toArray());
		cbServico.setBounds(150, 100, 378, 26);
		cbServico.setSelectedIndex(-1);
		contentPanel.add(cbServico);

		JLabel lblServico = new JLabel("Servico");
		lblServico.setBounds(92, 105, 85, 14);
		contentPanel.add(lblServico);

		tblAvacada = new JTable();
		tblAvacada.setBounds(15, 30, 610, 180);
		tblAvacada.setBackground(Color.GRAY);
		tblAvacada.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		contentPanel.add(tblAvacada);

		JLabel lblTabelaDeAgendamento = new JLabel("Tabela de Agendamento");
		lblTabelaDeAgendamento.setBounds(176, 245, 256, 20);
		lblTabelaDeAgendamento.setFont(new Font("Segoe Script", Font.ITALIC, 19));
		contentPanel.add(lblTabelaDeAgendamento);

		JButton btnConsultar = new JButton("");
		btnConsultar.setBounds(559, 216, 66, 57);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarAgendamentos();
			}
		});

		btnConsultar.setIcon(new ImageIcon(TelaConsultaAgendamento.class.getResource("/icones/lupa2.png")));
		contentPanel.add(btnConsultar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 284, 615, 265);
		contentPanel.add(scrollPane);
		
				tblAgendamento = new JTable();
				scrollPane.setColumnHeaderView(tblAgendamento);
				tblAgendamento.setBorder(UIManager.getBorder("PopupMenu.border"));
		atualizarTabelaAgendamento(agendamentos);
	}

	private void consultarAgendamentos() {


		ControladoraAgendamento controller = new ControladoraAgendamento();
		AgendamentoSeletor seletor = new AgendamentoSeletor();

		seletor.setPagina(paginaAtual);
		seletor.setLimite(TAMANHO_PAGINA);

		if (cbNomeProfissional.getSelectedIndex() > -1) {
			seletor.setProfissionalSeletor((Profissional) cbNomeProfissional.getSelectedItem());
		}

		if (cbServico.getSelectedIndex() > -1) {
			seletor.setServico((Servico) cbServico.getSelectedItem());
		}

		seletor.setDataInicio(dataInicial.getDate());
		seletor.setDataFim(dataFinal.getDate());

		List<Agendamento> agendamentos = controller.listarAgendamento(seletor);
		atualizarTabelaAgendamento(agendamentos);

		if(agendamentos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não possui agendamentos para esse filtro");
		}else {
			JOptionPane.showMessageDialog(null, "Consulta Realizada");
		}
	}

	private void atualizarTabelaAgendamento(List<Agendamento> agendamentos) {

		tblAgendamento.setModel(new DefaultTableModel(
			new Object[][] {
				{"IDAgendamento", "Cliente", "Profissional", "Servi\u00E7o", "Valor", "Data"},
			},
			new String[] {
				"IDAgendamento", "Cliente", "Profissional", "Servi\u00E7o", "Valor", "Data"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		DefaultTableModel model = (DefaultTableModel) tblAgendamento.getModel();

		for (Agendamento agendamento : agendamentos) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String dataFormatada = agendamento.getDataComHora().format(formatter);

			String[] novaLinha = new String[] { agendamento.getIdAgendamento() + "",
					agendamento.getCliente().getNome() + "", agendamento.getProfissional().getNome(),
					agendamento.getServico().getServico() + "", agendamento.getValor() + "", dataFormatada };
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
}
