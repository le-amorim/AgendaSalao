package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class FrmPrimeiraTela extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrimeiraTela frame = new FrmPrimeiraTela();
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
	public FrmPrimeiraTela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 430);
		
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("3");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		btnNewButton.setBounds(314, 42, 58, 49);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button.setBounds(382, 42, 58, 49);
		contentPane.add(button);
		
		JButton button_1 = new JButton("5");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_1.setBounds(450, 42, 58, 49);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("6");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_2.setBounds(518, 42, 58, 49);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("7");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_3.setBounds(586, 42, 58, 49);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("2");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Fechado!!");
				
			}
		});
		button_4.setBounds(246, 42, 58, 49);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("1");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				JOptionPane.showMessageDialog(null,"Fechado!!");
			}
		});
		button_5.setBounds(178, 42, 58, 49);
		contentPane.add(button_5);
		
		JLabel lblDomingo = new JLabel("Domingo");
		lblDomingo.setBounds(178, 11, 58, 14);
		contentPane.add(lblDomingo);
		
		JLabel lblSegunda = new JLabel("Segunda");
		lblSegunda.setBounds(246, 11, 58, 14);
		contentPane.add(lblSegunda);
		
		JLabel lblTerca = new JLabel("Terca");
		lblTerca.setBounds(315, 11, 57, 14);
		contentPane.add(lblTerca);
		
		JLabel lblQuarta = new JLabel("Quarta");
		lblQuarta.setBounds(382, 11, 46, 14);
		contentPane.add(lblQuarta);
		
		JLabel lblQuinta = new JLabel("Quinta");
		lblQuinta.setBounds(450, 11, 46, 14);
		contentPane.add(lblQuinta);
		
		JLabel lblSegunda_1 = new JLabel("Sexta");
		lblSegunda_1.setBounds(518, 11, 46, 14);
		contentPane.add(lblSegunda_1);
		
		JLabel lblSabado = new JLabel("sabado");
		lblSabado.setBounds(586, 11, 46, 14);
		contentPane.add(lblSabado);
		
		JButton button_6 = new JButton("8");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Fechado!!");
			}
		});
		button_6.setBounds(178, 102, 58, 49);
		contentPane.add(button_6);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Selecione uma Opção>", "Miriam", "Valquiria", "Joice", "Sabrina"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		comboBox.setBounds(10, 71, 158, 20);
		contentPane.add(comboBox);
		
		JLabel lblProfissional = new JLabel("Profissional");
		lblProfissional.setIcon(new ImageIcon(FrmPrimeiraTela.class.getResource("/icones/user.png")));
		lblProfissional.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		lblProfissional.setBounds(25, 42, 143, 18);
		contentPane.add(lblProfissional);
		
		JButton button_7 = new JButton("9");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Fechado!!");
			}
		});
		button_7.setBounds(246, 102, 58, 49);
		contentPane.add(button_7);
		
		JButton button_8 = new JButton("10\r\n");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_8.setBounds(314, 102, 58, 49);
		contentPane.add(button_8);
		
		JButton button_9 = new JButton("11");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_9.setBounds(382, 102, 58, 49);
		contentPane.add(button_9);
		
		JButton button_10 = new JButton("12");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_10.setBounds(450, 102, 58, 49);
		contentPane.add(button_10);
		
		JButton button_11 = new JButton("13\r\n");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_11.setBounds(518, 102, 58, 49);
		contentPane.add(button_11);
		
		JButton button_12 = new JButton("14\r\n");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_12.setBounds(586, 102, 58, 49);
		contentPane.add(button_12);
		
		JButton button_13 = new JButton("15");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Fechado!!");
			}
		});
		button_13.setBounds(178, 162, 58, 49);
		contentPane.add(button_13);
		
		JButton button_14 = new JButton("16");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Fechado!!");
			}
		});
		button_14.setBounds(246, 162, 58, 49);
		contentPane.add(button_14);
		
		JButton button_15 = new JButton("17");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_15.setBounds(314, 162, 58, 49);
		contentPane.add(button_15);
		
		JButton button_16 = new JButton("18");
		button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_16.setBounds(382, 162, 58, 49);
		contentPane.add(button_16);
		
		JButton button_17 = new JButton("19");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_17.setBounds(450, 162, 58, 49);
		contentPane.add(button_17);
		
		JButton button_18 = new JButton("20\r\n");
		button_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_18.setBounds(518, 162, 58, 49);
		contentPane.add(button_18);
		
		JButton button_19 = new JButton("21");
		button_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_19.setBounds(586, 162, 58, 49);
		contentPane.add(button_19);
		
		JButton button_20 = new JButton("22");
		button_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Fechado!!");
			}
		});
		button_20.setBounds(178, 222, 58, 49);
		contentPane.add(button_20);
		
		JButton button_21 = new JButton("23");
		button_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Fechado!!");
			}
		});
		button_21.setBounds(246, 222, 58, 49);
		contentPane.add(button_21);
		
		JButton button_22 = new JButton("24");
		button_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_22.setBounds(314, 222, 58, 49);
		contentPane.add(button_22);
		
		JButton button_23 = new JButton("25");
		button_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_23.setBounds(382, 222, 58, 49);
		contentPane.add(button_23);
		
		JButton button_24 = new JButton("26");
		button_24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_24.setBounds(450, 222, 58, 49);
		contentPane.add(button_24);
		
		JButton button_25 = new JButton("27");
		button_25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_25.setBounds(518, 222, 58, 49);
		contentPane.add(button_25);
		
		JButton button_26 = new JButton("28");
		button_26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_26.setBounds(586, 222, 58, 49);
		contentPane.add(button_26);
		
		JButton button_27 = new JButton("29");
		button_27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Fechado!!");
			}
		});
		button_27.setBounds(178, 282, 58, 49);
		contentPane.add(button_27);
		
		JButton button_28 = new JButton("30");
		button_28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Fechado!!");
			}
		});
		button_28.setBounds(246, 282, 58, 49);
		contentPane.add(button_28);
		
		JButton button_29 = new JButton("31");
		button_29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstruturaAgenda agenda = new EstruturaAgenda();
				agenda.setVisible(true);
			}
		});
		button_29.setBounds(314, 282, 58, 49);
		contentPane.add(button_29);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(FrmPrimeiraTela.class.getResource("/icones/back.png")));
		btnNewButton_1.setBounds(382, 316, 58, 44);
		contentPane.add(btnNewButton_1);
		
		JButton button_30 = new JButton("");
		button_30.setIcon(new ImageIcon(FrmPrimeiraTela.class.getResource("/icones/next.png")));
		button_30.setBounds(576, 316, 63, 44);
		contentPane.add(button_30);
		
		JLabel lblMesAtual = new JLabel("Setembro");
		lblMesAtual.setBounds(481, 331, 63, 14);
		contentPane.add(lblMesAtual);
	}
}
