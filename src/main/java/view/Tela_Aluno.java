package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import org.springframework.util.FileCopyUtils;

import dao.AlunoDAO;
import model.Aluno;
import util.Validador;
import java.awt.Dimension;
import java.awt.ComponentOrientation;

public class Tela_Aluno extends JPanel {

	// Instanciar Objeto para o Fluxo de Bytes
	private FileInputStream fis;

	// Variável Glopal para Armazenar o Tamanho da Imagem (em Bytes)
	private int tamanho;

	private static final long serialVersionUID = 1L;
	private JTextField txtRA;
	private JLabel lblFoto;
	private JTextField txtNome;
	private JTextField txtData;
	private JTextField txtEmail;
	private JTextField txtCPF;
	private JTextField txtEndereco;
	private JTextField txtMunicipio;
	private JTextField txtCelular;
	private final ButtonGroup bgPeriodo = new ButtonGroup();
	private String img_name;

	/**
	 * Create the panel.
	 * 
	 * @throws ParseException
	 */
	public Tela_Aluno() throws ParseException {
		setSize(new Dimension(730, 565));
		setBackground(new Color(0, 128, 192));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("RA:");
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblNewLabel.setBounds(154, 69, 67, 14);
		add(lblNewLabel);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblNome.setBounds(301, 69, 67, 14);
		add(lblNome);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblCpf.setBounds(498, 110, 47, 14);
		add(lblCpf);

		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblCelular.setBounds(154, 193, 67, 14);
		add(lblCelular);

		JLabel lblDataDeNacimento = new JLabel("Data de Nacimento");
		lblDataDeNacimento.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblDataDeNacimento.setBounds(154, 110, 157, 14);
		add(lblDataDeNacimento);

		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblEndereo.setBounds(154, 232, 81, 14);
		add(lblEndereo);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblEmail.setBounds(154, 150, 67, 14);
		add(lblEmail);

		JLabel lblMunicpio = new JLabel("Município");
		lblMunicpio.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblMunicpio.setBounds(412, 185, 93, 31);
		add(lblMunicpio);

		JLabel lblUf = new JLabel("UF");
		lblUf.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblUf.setBounds(627, 232, 41, 14);
		add(lblUf);

		JLabel lblNewLabel_3_4 = new JLabel("Dados do Aluno");
		lblNewLabel_3_4.setFont(new Font("Poppins Black", Font.PLAIN, 25));
		lblNewLabel_3_4.setBounds(269, 28, 229, 31);
		add(lblNewLabel_3_4);

		lblFoto = new JLabel("");
		lblFoto.setAutoscrolls(true);
		lblFoto.setAlignmentY(0.0f);
		lblFoto.setIconTextGap(0);
		lblFoto.setBackground(SystemColor.activeCaption);
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblFoto.setIcon(new ImageIcon("/home/jarvis/Imagens/Sistema Academico - Projeto MVC/src/main/java/img/user.png"));
		lblFoto.setBounds(10, 67, 134, 134);
		add(lblFoto);

		txtRA = new JTextField();
		txtRA.setFont(new Font("Poppins", Font.PLAIN, 15));
		txtRA.setBounds(181, 67, 110, 23);
		// Uso do PlainDocument para limitar os campos
		txtRA.setDocument(new Validador(13));
		add(txtRA);
		txtRA.setColumns(10);

		JButton btnCarregar = new JButton("Carregar Foto");
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarFoto();
			}
		});
		btnCarregar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		btnCarregar.setForeground(Color.BLACK);
		btnCarregar.setBounds(10, 213, 132, 23);
		add(btnCarregar);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Poppins", Font.PLAIN, 15));
		txtNome.setColumns(10);
		txtNome.setBounds(348, 66, 242, 23);
		add(txtNome);
		// Uso do PlainDocument para limitar os campos
		txtNome.setDocument(new Validador(30));

		txtData = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtData.setFont(new Font("Poppins", Font.PLAIN, 15));
		txtData.setColumns(10);
		txtData.setBounds(321, 107, 146, 23);
		add(txtData);
		// Uso do PlainDocument para limitar os campos
		txtData.setDocument(new Validador(10));

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Poppins", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(215, 146, 343, 23);
		add(txtEmail);
		// Uso do PlainDocument para limitar os campos
		txtEmail.setDocument(new Validador(40));

		txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCPF.setFont(new Font("Poppins", Font.PLAIN, 15));
		txtCPF.setColumns(10);
		txtCPF.setBounds(538, 107, 167, 20);
		add(txtCPF);
		// Uso do PlainDocument para limitar os campos
		txtCPF.setDocument(new Validador(20));

		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(245, 229, 335, 20);
		add(txtEndereco);
		// Uso do PlainDocument para limitar os campos
		txtEndereco.setDocument(new Validador(80));

		txtMunicipio = new JTextField();
		txtMunicipio.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		txtMunicipio.setColumns(10);
		txtMunicipio.setBounds(498, 193, 180, 20);
		add(txtMunicipio);
		// Uso do PlainDocument para limitar os campos
		txtMunicipio.setDocument(new Validador(10));

		final JComboBox<String> comboBoxUF = new JComboBox<>();
		comboBoxUF.setModel(new DefaultComboBoxModel<>(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF" }));
		comboBoxUF.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		comboBoxUF.setBounds(652, 228, 53, 22);
		add(comboBoxUF);

		txtCelular = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		txtCelular.setFont(new Font("Poppins", Font.PLAIN, 15));
		txtCelular.setColumns(10);
		txtCelular.setBounds(215, 190, 134, 20);
		add(txtCelular);
		// Uso do PlainDocument para limitar os campos
		txtCelular.setDocument(new Validador(14));

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblCurso.setBounds(83, 322, 75, 14);
		add(lblCurso);

		JLabel lblCampus = new JLabel("Campus");
		lblCampus.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblCampus.setBounds(83, 367, 75, 14);
		add(lblCampus);

		JLabel lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblPeriodo.setBounds(83, 418, 75, 14);
		add(lblPeriodo);

		final JComboBox<String> comboBoxCurso = new JComboBox<>();
		comboBoxCurso.setModel(new DefaultComboBoxModel<>(new String[] { "", "Analise e Desenvolvimento de Sistemas",
				"Administrador de banco de dados", "Administrador de redes", "Ciência de dados", "Comércio Exterior",
				"Gestão Empresarial", "Gestão da Produção Industrial", "Logística", "Logística Aeroportuária",
				"Segurança de dados", "Qualidade de software" }));
		comboBoxCurso.setFont(new Font("Poppins", Font.PLAIN, 15));
		comboBoxCurso.setBounds(154, 314, 462, 31);
		add(comboBoxCurso);

		final JComboBox<String> comboBoxCampus = new JComboBox<>();
		comboBoxCampus.setModel(new DefaultComboBoxModel<>(
				new String[] { "", "Fatec Barueri", "Fatec Carapicuíba", "Fatec Cotia", "Fatec Diadema", "Fatec Ferraz",
						"Fatec Franco da Rocha", "Fatec Guarulhos", "Fatec  Itaquaquecetuba", "Fatec Mauá",
						"Fatec Mogi das Cruzes", "Fatec Osasco", "Fatec Santa de Paraíba", "Fatec Santo André",
						"Fatec São Bernardo do Campo", "Fatec São Caetano do Sul", "Fatec Esportes(Futura unidade)",
						"Fatec Ipiranga", "Fatec Itaquera", "Fatec São Paulo", "Fatec Sebrae" }));
		comboBoxCampus.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		comboBoxCampus.setBounds(154, 361, 462, 27);
		add(comboBoxCampus);

		final JRadioButton rdbMatutino = new JRadioButton("Matutino");
		bgPeriodo.add(rdbMatutino);
		rdbMatutino.setFont(new Font("Poppins", Font.PLAIN, 15));
		rdbMatutino.setBounds(164, 414, 109, 23);
		add(rdbMatutino);

		final JRadioButton rdbtnVespertino = new JRadioButton("Vespertino");
		bgPeriodo.add(rdbtnVespertino);
		rdbtnVespertino.setFont(new Font("Poppins", Font.PLAIN, 15));
		rdbtnVespertino.setBounds(313, 414, 109, 23);
		add(rdbtnVespertino);

		final JRadioButton rdbtnNoturno = new JRadioButton("Noturno");
		bgPeriodo.add(rdbtnNoturno);
		rdbtnNoturno.setFont(new Font("Poppins", Font.PLAIN, 15));
		rdbtnNoturno.setBounds(498, 414, 109, 23);
		add(rdbtnNoturno);

		JLabel lblNewLabel_3_4_1 = new JLabel("Dados do Curso");
		lblNewLabel_3_4_1.setFont(new Font("Poppins Black", Font.PLAIN, 25));
		lblNewLabel_3_4_1.setBounds(269, 284, 208, 31);
		add(lblNewLabel_3_4_1);

		JButton btnSalvar = new JButton("");
		btnSalvar.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalvar.setIcon(new ImageIcon(Tela_Aluno.class.getResource("/img/save.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Verifica se todas as opções estão preenchidas
					if (txtRA.getText().isEmpty() || txtNome.getText().isEmpty() || txtCPF.getText().isEmpty()
							|| txtData.getText().isEmpty() || txtEmail.getText().isEmpty()
							|| txtCelular.getText().isEmpty() || txtEndereco.getText().isEmpty()
							|| txtMunicipio.getText().isEmpty() || comboBoxUF.getSelectedItem() == null
							|| comboBoxCurso.getSelectedItem() == null || comboBoxCampus.getSelectedItem() == null
							|| bgPeriodo.getSelection() == null) {

						JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
						return; // Retorna sem salvar se alguma opção não estiver preenchida
					}

					Aluno aluno = new Aluno();
					// Preenche o objeto aluno com os dados da tela

					aluno.setRa(txtRA.getText());
					aluno.setNome(txtNome.getText());
					aluno.setCpf(txtCPF.getText());
					aluno.setData(txtData.getText());
					aluno.setEmail(txtEmail.getText());
					aluno.setCelular(txtCelular.getText());
					aluno.setEndereco(txtEndereco.getText());
					aluno.setMunicipio(txtMunicipio.getText());
					aluno.setUf(comboBoxUF.getSelectedItem().toString());
					aluno.setCurso(comboBoxCurso.getSelectedItem().toString());
					aluno.setCampos(comboBoxCampus.getSelectedItem().toString());
					aluno.setImagem(img_name);

					if (rdbtnNoturno.isSelected()) {
						aluno.setPeriodo("Noturno");
					} else if (rdbtnVespertino.isSelected()) {
						aluno.setPeriodo("Vespertino");
					} else if (rdbMatutino.isSelected()) {
						aluno.setPeriodo("Matutino");
					}

					// aluno.setImagem(new
					// javax.sql.rowset.serial.SerialBlob(lblFoto.getIcon().toString().getBytes()));
					// aluno.setImagem(new
					// javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(fis, tamanho)));

					AlunoDAO dao = new AlunoDAO();
					dao.adicionar(aluno);
					;
					JOptionPane.showMessageDialog(null, "Cadastro Salvo com Sucesso!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e1.getMessage());
				}

				txtRA.setText(null);
				txtNome.setText(null);
				txtCPF.setText(null);
				txtCelular.setText(null);
				txtEmail.setText(null);
				txtData.setText(null);
				txtEndereco.setText(null);
				txtMunicipio.setText(null);
				comboBoxCurso.setSelectedIndex(0);
				comboBoxCampus.setSelectedIndex(0);
				comboBoxUF.setSelectedIndex(0);
				bgPeriodo.clearSelection();
				lblFoto.setIcon(new ImageIcon("/home/jarvis/Imagens/Sistema Academico - Projeto MVC/src/main/java/img/user.png"));
				txtRA.requestFocus();

			}
		});
		btnSalvar.setBackground(new Color(34, 139, 34));
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		btnSalvar.setBounds(118, 465, 67, 65);
		add(btnSalvar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(Tela_Aluno.class.getResource("/img/delete.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Aluno aluno = new Aluno();
				String ra = txtRA.getText();
				aluno.setRa(ra);

				try {
					AlunoDAO dao = new AlunoDAO();
					dao.deletar(ra); // O método deletar não retorna um Aluno, por isso não precisa ser atribuído a
										// uma variável

					JOptionPane.showMessageDialog(null, "Excluído com Sucesso");

				} catch (Exception el) {
					JOptionPane.showMessageDialog(null, el.getMessage());
				}

				txtRA.setText(null);
				txtNome.setText(null);
				txtCPF.setText(null);
				txtCelular.setText(null);
				txtEmail.setText(null);
				txtData.setText(null);
				txtEndereco.setText(null);
				txtMunicipio.setText(null);
				comboBoxCurso.setSelectedIndex(0);
				comboBoxCampus.setSelectedIndex(0);
				comboBoxUF.setSelectedIndex(0);
				bgPeriodo.clearSelection();
				lblFoto.setIcon(new ImageIcon("/home/jarvis/Imagens/Sistema Academico - Projeto MVC/src/main/java/img/user.png"));
				txtRA.requestFocus();

			}
		});
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setBounds(355, 465, 67, 65);
		add(btnExcluir);

		JButton btnAlterar = new JButton("");
		btnAlterar.setIcon(new ImageIcon(Tela_Aluno.class.getResource("/img/update.png")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Aluno aluno = new Aluno();

					aluno.setRa(txtRA.getText());
					aluno.setNome(txtNome.getText());
					aluno.setCpf(txtCPF.getText());
					aluno.setData(txtData.getText());
					aluno.setEmail(txtEmail.getText());
					aluno.setCelular(txtCelular.getText());
					aluno.setEndereco(txtEndereco.getText());
					aluno.setMunicipio(txtMunicipio.getText());
					aluno.setUf(comboBoxUF.getSelectedItem().toString());
					aluno.setCurso(comboBoxCurso.getSelectedItem().toString());
					aluno.setCampos(comboBoxCampus.getSelectedItem().toString());
					aluno.setImagem(img_name);

					if (rdbtnNoturno.isSelected()) {
						aluno.setPeriodo("Noturno");
					} else if (rdbtnVespertino.isSelected()) {
						aluno.setPeriodo("Vespertino");
					} else if (rdbMatutino.isSelected()) {
						aluno.setPeriodo("Matutino");
					}

					// luno.setImagem(new
					// javax.sql.rowset.serial.SerialBlob(lblFoto.getIcon().toString().getBytes()));
					// aluno.setImagem(new
					// javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(fis, tamanho)));

					// Abre a conexão com o banco de Dados
					// DAO >> Data Access Object
					AlunoDAO dao = new AlunoDAO();
					dao.atualizar(aluno);
					JOptionPane.showMessageDialog(null, ("Cadastro Atualizado com Sucesso!"));

				} catch (Exception el) {
					JOptionPane.showMessageDialog(null, el.getMessage());
				}

				txtRA.setText(null);
				txtNome.setText(null);
				txtCPF.setText(null);
				txtCelular.setText(null);
				txtEmail.setText(null);
				txtData.setText(null);
				txtEndereco.setText(null);
				txtMunicipio.setText(null);
				comboBoxCurso.setSelectedIndex(0);
				comboBoxCampus.setSelectedIndex(0);
				comboBoxUF.setSelectedIndex(0);
				bgPeriodo.clearSelection();
				lblFoto.setIcon(new ImageIcon("/home/jarvis/Imagens/Sistema Academico - Projeto MVC/src/main/java/img/user.png"));
				txtRA.requestFocus();

			}
		});
		btnAlterar.setForeground(Color.WHITE);
		btnAlterar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		btnAlterar.setBackground(Color.BLUE);
		btnAlterar.setBounds(236, 465, 75, 65);
		add(btnAlterar);

		JButton btnConsultar = new JButton("");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtRA.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o RA que deseja consultar.");
					return;
				}

				Aluno aluno = new Aluno();
				String ra = txtRA.getText();
				aluno.setRa(ra);
				try {
					AlunoDAO dao = new AlunoDAO();

					Aluno a = dao.verificar(ra);

					txtRA.setText(String.valueOf(a.getRa()));
					txtNome.setText(a.getNome());
					txtCPF.setText(a.getCpf());
					txtCelular.setText(a.getCelular());
					txtEmail.setText(a.getEmail());
					txtData.setText(a.getData());
					txtEndereco.setText(a.getEndereco());
					txtMunicipio.setText(a.getMunicipio());
					comboBoxCurso.setSelectedItem(a.getCurso());
					comboBoxCampus.setSelectedItem(a.getCampos());
					comboBoxUF.setSelectedItem(a.getUf());
					//img_name = a.getImagem();
					
					// cria um ícone de imagem com o caminho da foto do usuário
					ImageIcon foto = new ImageIcon("/home/jarvis/Imagens/Sistema Academico - Projeto MVC/imagens/" + a.getImagem());
					
					// redimenciona a imagem com base na largura e altura do componente
					foto.setImage(foto.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), 1));
					
					// Blob imagemBlob = a.getImagem(); // Supondo que a.getImagem() retorna um
					// objeto Blob
					//File arquivo = new File("/home/jarvis/Imagens/Sistema Academico - Projeto MVC/imagens/" + img_name);
					//String caminho = arquivo.getAbsolutePath();
					if (foto != null) {
						try {

							lblFoto.setIcon(foto);
							
						} catch (Exception e1) {
							// Lidere com exceções, se necessário
							e1.printStackTrace();
						}
					} else {
						// Caso a imagem seja null, você pode exibir uma imagem padrão ou deixar o
						// JLabel vazio
						lblFoto.setIcon(null);
					}

					if (a.getPeriodo().equals("Matutino")) {
						bgPeriodo.setSelected(rdbMatutino.getModel(), true);
					} else if (a.getPeriodo().equals("Vespertino")) {
						bgPeriodo.setSelected(rdbtnVespertino.getModel(), true);
					} else if (a.getPeriodo().equals("Noturno")) {
						bgPeriodo.setSelected(rdbtnNoturno.getModel(), true);
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro" + e1.getMessage());
				}
			}
		});
		btnConsultar.setIcon(new ImageIcon(Tela_Aluno.class.getResource("/img/search.png")));
		btnConsultar.setForeground(Color.WHITE);
		btnConsultar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		btnConsultar.setBackground(new Color(255, 0, 255));
		btnConsultar.setBounds(478, 465, 67, 65);
		add(btnConsultar);

		// evento de click para limpar os campos da tela de aluno
		JButton btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRA.setText(null);
				txtNome.setText(null);
				txtData.setText(null);
				txtCPF.setText(null);
				txtEmail.setText(null);
				txtCelular.setText(null);
				txtMunicipio.setText(null);
				txtEndereco.setText(null);
				
				rdbMatutino.setSelected(false);
				rdbtnVespertino.setSelected(false);
				rdbtnNoturno.setSelected(false);
				comboBoxUF.setSelectedIndex(0);
				comboBoxCurso.setSelectedIndex(0);
				comboBoxCampus.setSelectedIndex(0);
				lblFoto.setIcon(new ImageIcon("/home/jarvis/Imagens/Sistema Academico - Projeto MVC/src/main/java/img/user.png"));
				
			}
		});
		btnLimpar.setIcon(new ImageIcon(Tela_Aluno.class.getResource("/img/clear.png")));
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		btnLimpar.setBackground(new Color(255, 255, 0));
		btnLimpar.setBounds(601, 465, 67, 65);
		add(btnLimpar);

	}

	private void carregarFoto() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Selecionar Foto");
		fileChooser.setFileFilter(
				new FileNameExtensionFilter("Arquivo de Imagens (*.PNG,*.JPG,*.JPEG)", "png", "jpg", "jpeg"));
		int resultado = fileChooser.showOpenDialog(this);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			try {
				fis = new FileInputStream(fileChooser.getSelectedFile());
				tamanho = (int) fileChooser.getSelectedFile().length();
				Image foto = ImageIO.read(fileChooser.getSelectedFile()).getScaledInstance(lblFoto.getWidth(),
						lblFoto.getHeight(), Image.SCALE_SMOOTH);
				lblFoto.setIcon(new ImageIcon(foto));
				lblFoto.updateUI();
				// Obtém o caminho do arquivo
				File arquivo = fileChooser.getSelectedFile();

				// Copia o arquivo para uma pasta no projeto
				String pastaProjeto = System.getProperty("user.dir");
				File pastaImagens = new File(pastaProjeto, "imagens");
				pastaImagens.mkdirs();

				String nomeArquivo = arquivo.getName();
				img_name = nomeArquivo;
				File arquivoCopiado = new File(pastaImagens, nomeArquivo);
				FileCopyUtils.copy(arquivo, arquivoCopiado);

				// Lista as imagens na pasta
				// labelImagem.setText("");
				// for (File imagem : pastaImagens.listFiles()) {
				// labelImagem.setText(labelImagem.getText() + "<br>" + imagem.getName());
				// }
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}
