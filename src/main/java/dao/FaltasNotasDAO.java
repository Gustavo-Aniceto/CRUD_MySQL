package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.FaltasNotas;
import util.ConnectionFactory;

public class FaltasNotasDAO {
	private String nomealuno;
	private String curso;
	
	
	

	public String getNomealuno() {
		return nomealuno;
	}

	public void setNomealuno(String nomealuno) {
		this.nomealuno = nomealuno;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	private Connection conn;   
	private PreparedStatement ps;  
	private ResultSet rs;
	
	//Instanciar Objeto para o Fluxo de Bytes
	private FileInputStream fis;
		
	//Variável Glopal para Armazenar o Tamanho da Imagem (em Bytes)
	private int tamanho;
	
	public FaltasNotasDAO () throws Exception{
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("erro: \n" + e.getMessage());
		}
	}
	
	public void adicionar(FaltasNotas faltasNotas) throws Exception {
		try {
			String SQL = "INSERT INTO faltasnotas (curso, ra_aluno, disciplina, semestre, nota, faltas) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(SQL);
			ps.setString(1, faltasNotas.getCod_curso());
			ps.setString(2, faltasNotas.getRa());
			ps.setString(3, faltasNotas.getDisciplina());
			ps.setString(4, faltasNotas.getSemestre());
			ps.setDouble(5, faltasNotas.getNota());
			ps.setInt(6, faltasNotas.getFaltas());
			ps.executeUpdate();
		} catch (SQLException err) {
			throw new Exception("Erro ao atualizar dados " + err);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	// Listar todos
	public List<FaltasNotas> listar(String ra) throws Exception {
	    try {
	        String SQL = "select faltasnotas.curso, aluno.ra, faltasnotas.disciplina, faltasnotas.semestre, faltasnotas.nota, faltasnotas.faltas from faltasnotas\r\n"
	                + "inner join aluno on faltasnotas.ra_aluno = aluno.ra where aluno.ra = ?;";
	        ps = conn.prepareStatement(SQL);
	        ps.setString(1, ra);
	        rs = ps.executeQuery();
	        List<FaltasNotas> lista = new ArrayList<>();
	        while (rs.next()) {
	            String cod_curso = rs.getString(1);
	            String ra_ = rs.getString(2);
	            String disciplina = rs.getString(3);
	            String semestre = rs.getString(4);
	            float nota = rs.getFloat(5);
	            int faltas = rs.getInt(6);

	            // Criar uma instância de FaltasNotas e adicioná-la à lista
	            FaltasNotas faltasNotas = new FaltasNotas(curso, ra_, disciplina, semestre, nota, faltas);
	            lista.add(faltasNotas);
	        }
	        return lista;
	    } catch (SQLException sqle) {
	        throw new Exception(sqle);
	    } finally {
	        ConnectionFactory.closeConnection(conn, ps, rs);
	    }
	}
	public List<FaltasNotas> listar() throws Exception {
		try {
			String SQL = "SELECT  * FROM aluno";
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
			List<FaltasNotas> lista = new ArrayList<FaltasNotas>();
			while (rs.next()) {
				int ra = rs.getInt(1);
				String nome = rs.getString(2);
				String curso = rs.getString(3);
				String diciplina = rs.getString(4);
				String nota = rs.getString(5);
				String falta = rs.getString(6);
				lista.add(new FaltasNotas());
			}
			return lista;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
}
