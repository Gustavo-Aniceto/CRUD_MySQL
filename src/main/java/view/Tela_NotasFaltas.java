package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import dao.AlunoDAO;
import dao.FaltasNotasDAO;
import model.Aluno;
import model.FaltasNotas;
import java.awt.Dimension;

public class Tela_NotasFaltas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtRA;
	private JTextField txtNota;
	private JTextField txtFaltas;
	private JTextField txtCurso;

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private TextArea txtListarDisciplinas;
	private JButton btnListar;

	private String ra_alun;

	/**
	 * Create the panel.
	 */
	public Tela_NotasFaltas() {
		setSize(new Dimension(695, 550));
		setBackground(new Color(0, 128, 192));
		setLayout(null);

		JLabel lblNewLabel_3_4 = new JLabel("Dados do Aluno");
		lblNewLabel_3_4.setFont(new Font("Poppins Black", Font.PLAIN, 25));
		lblNewLabel_3_4.setBounds(239, 11, 215, 31);
		add(lblNewLabel_3_4);

		final JComboBox<String> comboBoxDisciplina = new JComboBox<>();
		comboBoxDisciplina.setModel(new DefaultComboBoxModel<>(
				new String[] { "", "Adiministração Geral", "Arquitetura e Organização de Computadores",
						"Algoritmos e Lógica de Programação", "Banco de Dados", "Cálculo", "Comunicação e Expressão",
						"Contabilidade", "Economia e Finanças", "Engenharia de Software I", "Engenharia de Software II",
						"Engenharia de Software III", "Empreendedorismo", "Estatística Aplicada", "Estruturas de Dados",
						"Ética e Responsabilidade Profissional", "Gestão e Governança de Tecnologia da Informação",
						"Interação Humano Computador", "Inglês I", "Inglês II", "Inglês III", "Inglês IV", "Inglês V",
						"Inglês VI", "Inteligência Artificial", "Laboratório de Engenharia de Software",
						"Laboratório de Hardware", "Laboratório de Redes", "Linguagem de Programação",
						"Matemática Discreta", "Metodologia da Pesquisa Científico-Tecnológica",
						"Programação em Microinformática", "Programação Linear e Aplicações",
						"Programação Orientada a Objetos", "Programação Para Dispositivos Móveis",
						"Redes de Computadores", "Segurança da Informação", "Sistemas Distribuídos" }));
		comboBoxDisciplina.setFont(new Font("Poppins", Font.PLAIN, 15));
		comboBoxDisciplina.setBounds(123, 221, 263, 22);
		add(comboBoxDisciplina);

		final JComboBox<String> comboBoxSemestre = new JComboBox<>();
		comboBoxSemestre.setModel(new DefaultComboBoxModel<>(new String[] { "", "1º Semestre", "2º Semestre",
				"3º Semestre", "4º Semestre", "5º Semestre", "6º Semestre", "7º Semestre", "8º Semestre" }));
		comboBoxSemestre.setFont(new Font("Poppins", Font.PLAIN, 15));
		comboBoxSemestre.setBounds(123, 261, 177, 22);
		add(comboBoxSemestre);

		final JLabel lblFoto = new JLabel("");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBackground(new Color(0, 128, 192));
		lblFoto.setIcon(new ImageIcon("src/main/java/img/user.png"));
		lblFoto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblFoto.setBounds(10, 67, 134, 135);
		add(lblFoto);

		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		txtNome.setColumns(10);
		txtNome.setBounds(387, 84, 242, 20);
		add(txtNome);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblNome.setBounds(329, 87, 67, 14);
		add(lblNome);

		txtRA = new JTextField();
		txtRA.setFont(new Font("Poppins", Font.PLAIN, 15));
		txtRA.setColumns(10);
		txtRA.setBounds(190, 84, 110, 20);
		add(txtRA);

		JLabel lblNewLabel = new JLabel("RA");
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblNewLabel.setBounds(154, 87, 67, 14);
		add(lblNewLabel);

		JLabel lblDiciplina = new JLabel("Disciplina");
		lblDiciplina.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblDiciplina.setBounds(48, 225, 75, 14);
		add(lblDiciplina);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblCurso.setBounds(154, 138, 75, 14);
		add(lblCurso);

		JButton btnSalvar = new JButton("");
		btnSalvar.setIcon(new ImageIcon("src/main/java/img/save.png"));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					FaltasNotas faltasnotas = new FaltasNotas();
					// Preenche o objeto aluno com os dados da tela

					faltasnotas.setDisciplina(comboBoxDisciplina.getSelectedItem().toString());
					faltasnotas.setNota(Double.parseDouble(txtNota.getText()));
					faltasnotas.setSemestre(comboBoxSemestre.getSelectedItem().toString());
					faltasnotas.setFaltas(Integer.parseInt(txtFaltas.getText()));
					faltasnotas.setRa(txtRA.getText());

					FaltasNotasDAO dao = new FaltasNotasDAO();
					dao.adicionar(faltasnotas);
					;
					JOptionPane.showMessageDialog(null, "Salvo com sucesso");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e1.getMessage());
				}

			}
		});
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		btnSalvar.setBackground(new Color(0, 153, 102));
		btnSalvar.setBounds(84, 462, 79, 62);
		add(btnSalvar);

		JLabel lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblNota.setBounds(415, 225, 75, 14);
		add(lblNota);

		JLabel lblFaltas = new JLabel("Faltas");
		lblFaltas.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblFaltas.setBounds(355, 265, 75, 14);
		add(lblFaltas);

		txtNota = new JTextField();
		txtNota.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		txtNota.setColumns(10);
		txtNota.setBounds(480, 222, 110, 20);
		add(txtNota);

		txtFaltas = new JTextField();
		txtFaltas.setFont(new Font("Poppins", Font.PLAIN, 15));
		txtFaltas.setColumns(10);
		txtFaltas.setBounds(410, 262, 110, 20);
		add(txtFaltas);

		txtCurso = new JTextField();
		txtCurso.setEditable(false);
		txtCurso.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		txtCurso.setColumns(10);
		txtCurso.setBounds(205, 135, 385, 20);
		add(txtCurso);

		// evento de click para gerar PDF
		JButton btnGerarBoletim = new JButton("");
		btnGerarBoletim.setHorizontalAlignment(SwingConstants.LEADING);
		btnGerarBoletim.setIcon(new ImageIcon("src/main/java/img/boletim.png"));
		btnGerarBoletim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*try {
					// Chamar a função listar
				    FaltasNotasDAO daoNotas = new FaltasNotasDAO();
		            java.util.List<FaltasNotas> listaFaltasNotas = daoNotas.listar(ra_alun);
	
		            // Gerar o PDF com os dados da lista
		            gerarPDF(listaFaltasNotas, "caminho/do/arquivo.pdf");
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Erro"+e1.getMessage());
					
				}*/
				
				try {
		            // Chamar a função listar
		            FaltasNotasDAO daoNotas = new FaltasNotasDAO();
		            java.util.List<FaltasNotas> listaFaltasNotas = daoNotas.listar(ra_alun);

		            // Gerar o PDF com os dados da lista
		            gerarPDF(listaFaltasNotas, "caminho/do/arquivo.pdf", ra_alun);
		        } catch (Exception e1) {
		            JOptionPane.showMessageDialog(null, "Erro" + e1.getMessage());
		        }
				
			}
		});
		btnGerarBoletim.setForeground(Color.WHITE);
		btnGerarBoletim.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		btnGerarBoletim.setBackground(Color.BLACK);
		btnGerarBoletim.setBounds(387, 462, 67, 62);
		add(btnGerarBoletim);

		JLabel lblSemestre = new JLabel("Semestre");
		lblSemestre.setFont(new Font("Poppins", Font.PLAIN, 15));
		lblSemestre.setBounds(48, 265, 75, 14);
		add(lblSemestre);

		JLabel lblNewLabel_3_4_1 = new JLabel("Notas e Faltas");
		lblNewLabel_3_4_1.setFont(new Font("Poppins Black", Font.PLAIN, 25));
		lblNewLabel_3_4_1.setBounds(245, 165, 196, 31);
		add(lblNewLabel_3_4_1);

		txtListarDisciplinas = new TextArea();
		txtListarDisciplinas.setFont(new Font("Poppins", Font.PLAIN, 15));
		txtListarDisciplinas.setBounds(36, 311, 628, 135);
		add(txtListarDisciplinas);

		JButton btnLimpar = new JButton("");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtRA.setText(null);
				txtNome.setText(null);
				txtCurso.setText(null);
				comboBoxDisciplina.setSelectedIndex(0);
				txtNota.setText(null);
				comboBoxSemestre.setSelectedIndex(0);
				txtFaltas.setText(null);
				lblFoto.setIcon(new ImageIcon("src/main/java/img/user.png"));
				txtListarDisciplinas.setText(null);
			}
		});
		btnLimpar.setIcon(new ImageIcon("src/main/java/img/clear.png"));
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		btnLimpar.setBackground(new Color(255, 215, 0));
		btnLimpar.setBounds(523, 462, 67, 65);
		add(btnLimpar);
		
		JButton btnConsultar = new JButton("");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				Aluno aluno = new Aluno();
				String ra = txtRA.getText();
				aluno.setRa(ra);
				try {
					AlunoDAO dao = new AlunoDAO();

					Aluno a = dao.verificar(ra);

					//String img_name = a.getImagem();
					
					// cria um ícone de imagem com o caminho da foto do usuário
					ImageIcon foto = new ImageIcon("imagens/" + a.getImagem());
					
					// redimenciona a imagem com base na largura e altura do componente
					foto.setImage(foto.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), 1));

					//File arquivo = new File("imagens/" + img_name);
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

					txtRA.setText(String.valueOf(a.getRa()));
					ra_alun = String.valueOf(a.getRa());
					txtNome.setText(a.getNome());
					txtCurso.setText(a.getCurso());

					FaltasNotasDAO daoNotas = new FaltasNotasDAO();
					java.util.List<FaltasNotas> listaFaltasNotas = daoNotas.listar(a.getRa());

					// Limpar o conteúdo atual do txtListarDisciplinas
					txtListarDisciplinas.setText("");

					// Exibir os resultados no txtListarDisciplinas
					for (FaltasNotas fn : listaFaltasNotas) {
						String linha = String.format("Disciplina: %s, Semestre: %s, Nota: %.2f, Faltas: %d%n",
								fn.getDisciplina(), fn.getSemestre(), fn.getNota(), fn.getFaltas());
						txtListarDisciplinas.append(linha);
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro" + e1.getMessage());
				}
			}
		});
		btnConsultar.setIcon(new ImageIcon("src/main/java/img/search.png"));
		btnConsultar.setForeground(Color.WHITE);
		btnConsultar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		btnConsultar.setBackground(Color.MAGENTA);
		btnConsultar.setBounds(245, 462, 67, 65);
		add(btnConsultar);

		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

				} catch (Exception ex) {
					// Tratar exceções, por exemplo, exibir uma mensagem de erro
					ex.printStackTrace();
				}

			}
		});
		btnListar.setForeground(Color.WHITE);
		btnListar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		btnListar.setBackground(new Color(153, 0, 255));
		btnListar.setBounds(236, 452, 132, 23);
		// add(btnListar);

	}

	// método para gerar PDF
	public static void gerarPDF(java.util.List<FaltasNotas> lista, String caminhoDoPDF, String ra_aluno) throws Exception {
		/*try {
			Document documento = new Document();

			// Usar um JFileChooser para permitir que o usuário escolha o diretório de
			// download
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Salvar PDF");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String diretorioEscolhido = fileChooser.getSelectedFile().getAbsolutePath();
				caminhoDoPDF = diretorioEscolhido + "/arquivo.pdf";

				// Continuar com a geração do PDF
				PdfWriter.getInstance(documento, new FileOutputStream(caminhoDoPDF));
				documento.open();
				
						
				for (FaltasNotas fn : lista) {
					String linha = String.format("Nome do Aluno: %s\nCurso: %s\nDisciplina: %s, Semestre: %s, Nota: %.2f, Faltas: %d%n", fn.getNomeAluno(), fn.getCurso(),
							fn.getDisciplina(), fn.getSemestre(), fn.getNota(), fn.getFaltas());
					documento.add(new Paragraph(linha));
				}
				

				documento.close();
				System.out.println("PDF gerado com sucesso!");
			} else {
				System.out.println("Operação de salvar PDF cancelada pelo usuário.");
			}
		} catch (DocumentException | java.io.IOException e) {
			e.printStackTrace();
		}*/
		
		
		try {
	        Document documento = new Document();

	        // Usar um JFileChooser para permitir que o usuário escolha o diretório de download
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Salvar PDF");
	        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

	        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	            String diretorioEscolhido = fileChooser.getSelectedFile().getAbsolutePath();
	            caminhoDoPDF = diretorioEscolhido + "/boletim.pdf";

	            // Continuar com a geração do PDF
	            PdfWriter.getInstance(documento, new FileOutputStream(caminhoDoPDF));
	            documento.open();
	            
	            Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
	            
	            // adiciona parágrafo com a data ao arquivo
				documento.add(new Paragraph(String.format(" Data de Requisição: %s", formatador.format(data))));
	            documento.add(new Paragraph("---------------------------------------------------------------------------------"));

	            // Adicionar informações do aluno
	            adicionarInformacoesAluno(documento, ra_aluno);

	            
	            documento.add(new Paragraph("\n\n\n BOLETIM"));
	            documento.add(new Paragraph("----------------"));
	            
	            // Adicionar notas e faltas
	            for (FaltasNotas fn : lista) {
	                String linha = String.format(" Disciplina: %s   Semestre: %s", fn.getDisciplina(), fn.getSemestre());
	                documento.add(new Paragraph(linha));
	                
	                linha = String.format(" Nota: %.2f   Faltas: %d\n\n", fn.getNota(), fn.getFaltas());
	                documento.add(new Paragraph(linha));
	            }

	            documento.close();
	            System.out.println("PDF gerado com sucesso!");
	        } else {
	            System.out.println("Operação de salvar PDF cancelada pelo usuário.");
	        }
	    } catch (DocumentException | java.io.IOException e) {
	        e.printStackTrace();
	    }
		
		// abrir PDF no leitor padrão do sistema
				try {
					Desktop.getDesktop().open(new File(caminhoDoPDF));
				} catch (Exception e2) {
					System.out.println(e2);
					
				}
		
	}
	
	private static void adicionarInformacoesAluno(Document document, String ra_aluno) throws Exception {
	    // Adicione aqui o código para obter e adicionar as informações do aluno no documento
	    // Exemplo:
		AlunoDAO dao = new AlunoDAO();
		
		
		//a = dao.verificar(ra_aluno);
		Aluno a = dao.verificar(ra_aluno);
		
		
	    document.add(new Paragraph("\n\n\n INFORMAÇÕES DO ALUNO"));
	    document.add(new Paragraph("------------------------------------------"));
	    document.add(new Paragraph(String.format("RA: %s   Nome: %s   CPF: %s", a.getRa(), a.getNome(), a.getCpf())));
	    document.add(new Paragraph(String.format("Data de Nascimento: %s   E-mail: %s", a.getData(), a.getEmail())));
	    
	    // Adicione outras informações conforme necessário
	    document.add(new Paragraph(" "));
	    
	    document.add(new Paragraph("CURSO: " + a.getCurso() + "   CAMPUS: " + a.getCampos() + "\nPeríodo: " + a.getPeriodo() + "   Município: " + a.getMunicipio()));
	    
	}
}
