package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.ControladoraFuncionario;
import model.vo.Profissional;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class FrmPrimeiraTela extends JFrame {

	private JPanel painelEsquerdo;
	private JPanel painelDireito;
	private ArrayList<Profissional> profissionais;
	@SuppressWarnings("rawtypes")
	protected JComboBox cbProfissional;
	protected TelaCadastroCliente telaCadastroCliente;
	private JLabel lblImg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrimeiraTela frame = new FrmPrimeiraTela();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrmPrimeiraTela() {
		
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 549);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setIcon(new ImageIcon(FrmPrimeiraTela.class.getResource("/icones/settings.png")));
		menuBar.add(mnNewMenu);

		JMenuItem mntmCaixa = new JMenuItem("Caixa Salão");
		mntmCaixa.setIcon(new ImageIcon(FrmPrimeiraTela.class.getResource("/icones/carteira.png")));
		mntmCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vendas venda = new Vendas();
				venda.setVisible(true);

			}
		});
		mnNewMenu.add(mntmCaixa);

		JMenuItem mntmCaixaProdutos = new JMenuItem("Caixa Produtos");
		mnNewMenu.add(mntmCaixaProdutos);

		JMenu mnNewMenu_1 = new JMenu("Cadastrar");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Cliente");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(telaCadastroCliente == null) {
					telaCadastroCliente = new TelaCadastroCliente();
					telaCadastroCliente.setBounds(0,0, painelDireito.getWidth(), painelDireito.getHeight());
					painelDireito.add(telaCadastroCliente);
					revalidate();
					repaint();
					lblImg.setVisible(false);
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);

		JMenuItem mntmFuncionario = new JMenuItem("Profissional");
		mntmFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroFuncionario tela = new TelaCadastroFuncionario();
				tela.setVisible(true);
				lblImg.setVisible(false);
			}
		});
		mnNewMenu_1.add(mntmFuncionario);

		JMenu mnReltorio = new JMenu("Relátorio");
		menuBar.add(mnReltorio);

		JMenuItem mntmMensal = new JMenuItem("Mensal");
		mnReltorio.add(mntmMensal);
		
		Dimension dimensoesTela = Toolkit.getDefaultToolkit().getScreenSize();
		int larguraDaTela = (int) dimensoesTela.getWidth();
		int alturaDaTela = (int) (dimensoesTela.getHeight() - 50);
		
		painelEsquerdo = new JPanel();
		painelEsquerdo.setBounds(0,0,200,alturaDaTela);
		painelEsquerdo.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(painelEsquerdo);
		painelEsquerdo.setLayout(null);
		
		painelDireito = new JPanel();
		painelDireito.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		painelDireito.setBounds(204, 0, larguraDaTela-200, alturaDaTela - 50);
		getContentPane().add(painelDireito);
		painelDireito.setLayout(null);
		
		lblImg = new JLabel("New label");
		lblImg.setIcon(new ImageIcon(FrmPrimeiraTela.class.getResource("/icones/deboche.gif")));
		lblImg.setBounds(10, 11, larguraDaTela, alturaDaTela);
		painelDireito.add(lblImg);

		consultarProfissional();
		cbProfissional = new JComboBox(profissionais.toArray());
		cbProfissional.setSelectedIndex(-1);
		cbProfissional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Profissional profissionalSelecionado = (Profissional) cbProfissional.getSelectedItem();
				EstruturaAgenda estrutura = new EstruturaAgenda();
				estrutura.setProfissionalSelecionado(profissionalSelecionado);
				estrutura.setVisible(true);
			
			}
		});
		cbProfissional.setBounds(56, 265, 118, 20);
		painelEsquerdo.add(cbProfissional);

		JLabel lblProfissional = new JLabel("Profissional");
		lblProfissional.setIcon(new ImageIcon(FrmPrimeiraTela.class.getResource("/icones/user.png")));
		lblProfissional.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		lblProfissional.setBounds(56, 236, 123, 18);
		painelEsquerdo.add(lblProfissional);

		JLabel lblStudio = new JLabel("Studio");
		lblStudio.setFont(new Font("Segoe Script", Font.ITALIC, 18));
		lblStudio.setBounds(30, 9, 77, 14);
		painelEsquerdo.add(lblStudio);

		JLabel lblDu = new JLabel("Du'");
		lblDu.setFont(new Font("Segoe Script", Font.ITALIC, 18));
		lblDu.setBounds(30, 33, 46, 14);
		painelEsquerdo.add(lblDu);

		JLabel lblArtes = new JLabel("Artes");
		lblArtes.setFont(new Font("Segoe Script", Font.ITALIC, 18));
		lblArtes.setBounds(40, 58, 78, 14);
		painelEsquerdo.add(lblArtes);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmPrimeiraTela.class.getResource("/icones/foto.png")));
		label.setBounds(0, 0, 219, 225);
		painelEsquerdo.add(label);
	}

		ArrayList<Profissional> consultarProfissional() {
		ControladoraFuncionario controladora = new ControladoraFuncionario();
		return profissionais = controladora.consultarTodos();
	}
}