package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import util.ConnectionFactory;
import util.MexerTelaComMouse;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.ComponentOrientation;


public class Tela_Principal extends JFrame {
	
	//Instanciar Objetos
	ConnectionFactory con = new ConnectionFactory();
	private Connection conn;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	Tela_Aluno cadastrarAlunos;
	Tela_NotasFaltas gerenciarNotas;
	private JLabel lblStatus;
	private JPanel Tela_Integrantes;
	private JLabel lblData;
	private JTextField txtTelaInicial;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Principal frame = new Tela_Principal();
					MexerTelaComMouse.add(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Tela_Principal() throws ParseException {
		setSize(new Dimension(7, 575));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
				setarData();
			}
		});
		
		setUndecorated(true);
		setTitle("PROJETO MVC");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/img/system.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		// Chamando Panels
		cadastrarAlunos = new Tela_Aluno();
		gerenciarNotas = new Tela_NotasFaltas();
		setSize(780, 636);

		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel MenuSuperior = new JPanel();
		MenuSuperior.setBackground(SystemColor.textHighlight);
		MenuSuperior.setBorder(null);
		contentPane.add(MenuSuperior, BorderLayout.NORTH);
		
		Tela_Integrantes = new JPanel();
		Tela_Integrantes.setBackground(new Color(0, 128, 192));
		contentPane.add(Tela_Integrantes, BorderLayout.CENTER);
		Tela_Integrantes.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(new Color(0, 128, 192));
		btnNewButton.setIcon(new ImageIcon("src/main/java/img/desktop.png"));
		btnNewButton.setBounds(29, 54, 324, 408);
		Tela_Integrantes.add(btnNewButton);
		
		txtTelaInicial = new JTextField();
		txtTelaInicial.setEditable(false);
		txtTelaInicial.setBackground(new Color(0, 0, 255));
		txtTelaInicial.setFont(new Font("Poppins Black", Font.PLAIN, 16));
		txtTelaInicial.setText("Realizado com Java e MySQL");
		txtTelaInicial.setBounds(415, 175, 249, 52);
		Tela_Integrantes.add(txtTelaInicial);
		txtTelaInicial.setColumns(10);
		
		textField = new JTextField();
		textField.setText("TELA INICIAL");
		textField.setFont(new Font("Poppins Black", Font.PLAIN, 23));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.BLUE);
		textField.setBounds(449, 54, 156, 78);
		Tela_Integrantes.add(textField);
		
		JLabel lblNewLabel = new JLabel("CADASTRO ALUNO");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// Desabilitando Panels
				cadastrarAlunos.setVisible(false);
				gerenciarNotas.setVisible(false);
				Tela_Integrantes.setVisible(false);

				// Limpando BorderLayout Center
				BorderLayout layout = (BorderLayout) contentPane.getLayout();
				remove(layout.getLayoutComponent(BorderLayout.CENTER));

				// PanelCadastroDeAluno
				getContentPane().add(cadastrarAlunos, BorderLayout.CENTER);
				cadastrarAlunos.setVisible(true);
				
			}
		});
		lblNewLabel.setFont(new Font("Poppins Black", Font.PLAIN, 15));
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setForeground(Color.WHITE);
		
		JLabel lblFaltasENotas = new JLabel("NOTAS E FALTAS");
		lblFaltasENotas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				cadastrarAlunos.setVisible(false);
				gerenciarNotas.setVisible(false);
				Tela_Integrantes.setVisible(false);

				BorderLayout layout = (BorderLayout) contentPane.getLayout();
				remove(layout.getLayoutComponent(BorderLayout.CENTER));

				getContentPane().add(gerenciarNotas, BorderLayout.CENTER);
				gerenciarNotas.setVisible(true);
				
			}
		});
		lblFaltasENotas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblFaltasENotas.setForeground(Color.WHITE);
		lblFaltasENotas.setFont(new Font("Poppins Black", Font.PLAIN, 15));
		
		JLabel lblIntegrantes = new JLabel("PAGINA INICIAL");
		lblIntegrantes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Desabilitando Panels
				cadastrarAlunos.setVisible(false);
				gerenciarNotas.setVisible(false);
				Tela_Integrantes.setVisible(false);

				// Limpando BorderLayout Center
				BorderLayout layout = (BorderLayout) contentPane.getLayout();
				remove(layout.getLayoutComponent(BorderLayout.CENTER));

				// PanelGerenciarNotas
				getContentPane().add(Tela_Integrantes, BorderLayout.CENTER);
				Tela_Integrantes.setVisible(true);
			}
		});
		lblIntegrantes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblIntegrantes.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntegrantes.setForeground(Color.WHITE);
		lblIntegrantes.setFont(new Font("Poppins Black", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(DISPOSE_ON_CLOSE);
			}
		});
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setIcon(new ImageIcon("src/main/java/img/MicrosoftTeams-image (6).png"));
		
		lblStatus = new JLabel("");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setIcon(new ImageIcon("src/main/java/img/list.png"));
		
		lblData = new JLabel("");
		lblData.setVerticalAlignment(SwingConstants.TOP);
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Poppins Black", Font.PLAIN, 15));
		GroupLayout gl_MenuSuperior = new GroupLayout(MenuSuperior);
		gl_MenuSuperior.setHorizontalGroup(
			gl_MenuSuperior.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_MenuSuperior.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_MenuSuperior.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_MenuSuperior.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(137)
							.addComponent(lblFaltasENotas)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblIntegrantes)
							.addGap(10))
						.addGroup(Alignment.TRAILING, gl_MenuSuperior.createSequentialGroup()
							.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE)
							.addGap(56)))
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_MenuSuperior.setVerticalGroup(
			gl_MenuSuperior.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_MenuSuperior.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_MenuSuperior.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblIntegrantes, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFaltasENotas, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(18))
				.addGroup(gl_MenuSuperior.createSequentialGroup()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(47, Short.MAX_VALUE))
				.addGroup(gl_MenuSuperior.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		MenuSuperior.setLayout(gl_MenuSuperior);
		
	}
	
	private void status() {
		try {
			conn = con.conectar();
			if (conn == null) {
				//System.out.println("Erro de Conexão");
				lblStatus.setIcon(new ImageIcon(Tela_Principal.class.getResource("src/img/database_fail.png")));
			} else {
				//System.out.println("Banco de Dados Conectado");
				lblStatus.setIcon(new ImageIcon(Tela_Principal.class.getResource("src/img/database_success.png")));
			}
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private void setarData() {
		Date data = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		lblData.setText(formatador.format(data));
	}
}
