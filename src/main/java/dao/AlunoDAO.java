package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Aluno;
import util.ConnectionFactory;

public class AlunoDAO {
	private Connection conn;   
	private PreparedStatement ps;  
	private ResultSet rs;
	
	//Instanciar Objeto para o Fluxo de Bytes
	private FileInputStream fis;
		
	//Variável Glopal para Armazenar o Tamanho da Imagem (em Bytes)
	private int tamanho;
	
	public AlunoDAO () throws Exception{
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("erro: \n" + e.getMessage());
		}
	}
	
	public void adicionar(Aluno aluno) throws Exception {
		try {
			String SQL = "INSERT INTO aluno (ra, nome, data, cpf, email, endereco, municipio, uf, celular, curso, campos, periodo, imagem) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(SQL);
			ps.setString(1, aluno.getRa());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getData());
			ps.setString(4, aluno.getCpf());
			ps.setString(5, aluno.getEmail());
			ps.setString(6, aluno.getEndereco());
			ps.setString(7, aluno.getMunicipio());
			ps.setString(8, aluno.getUf());
			ps.setString(9, aluno.getCelular());
			ps.setString(10, aluno.getCurso());
			ps.setString(11, aluno.getCampos());
			ps.setString(12, aluno.getPeriodo());
			ps.setString(13, aluno.getImagem());
			ps.setBlob(13, fis, tamanho);
			ps.executeUpdate();
		} catch (SQLException err) {
			throw new Exception("Erro ao atualizar dados " + err);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	public Aluno verificar(String ra) throws Exception{
		Aluno aluno = new Aluno();
		
		try {
			String SQL = "SELECT * FROM aluno WHERE ra = ?";
			
			ps = conn.prepareStatement(SQL);
			ps.setString(1, ra);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				aluno.setRa(ra);
				aluno.setNome(rs.getString("nome"));
				aluno.setData(rs.getString("data"));
				aluno.setCpf(rs.getString("cpf"));
				aluno.setEmail(rs.getString("email"));
				aluno.setEndereco(rs.getString("endereco"));
				aluno.setMunicipio(rs.getString("municipio"));
				aluno.setUf(rs.getString("uf"));
				aluno.setCelular(rs.getString("celular"));
				aluno.setCurso(rs.getString("Curso"));
				aluno.setCampos(rs.getString("campos"));
				aluno.setPeriodo(rs.getString("periodo"));
				aluno.setImagem(rs.getString("imagem"));
				
			} else {
				throw new Exception(": Aluno não encontrado");
			}
		} catch(SQLException err) {
			throw new Exception("Erro ao consultar dados " + err);
		}
		
		return aluno;
	}
	
	public void atualizar(Aluno aluno) throws Exception{
		try {
			String SQL = "UPDATE aluno SET nome = ?, data = ?, cpf = ?, email = ?, endereco = ?, municipio = ?, uf = ?, celu"
					+ "lar = ?, curso = ?, campos = ?, periodo = ?, imagem = ? WHERE ra = ?";
			
			ps = conn.prepareStatement(SQL);
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getData());
			ps.setString(3, aluno.getCpf());
			ps.setString(4, aluno.getEmail());
			ps.setString(5, aluno.getEndereco());
			ps.setString(6, aluno.getMunicipio());
			ps.setString(7, aluno.getUf());
			ps.setString(8, aluno.getCelular());
			ps.setString(9, aluno.getCurso());
			ps.setString(10, aluno.getCampos());
			ps.setString(11, aluno.getPeriodo());
			ps.setString(12, aluno.getImagem());
			ps.setString(13, aluno.getRa());
			ps.executeUpdate();
			
		} catch (SQLException err) {
			throw new Exception("Erro ao atualizar dados " + err);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}
	
	public void deletar(String ra) throws Exception{
		try {
			String SQL = "DELETE FROM aluno WHERE ra = ?";
			
			ps = conn.prepareStatement(SQL);
			ps.setString(1, ra);
			ps.execute();
		} catch (SQLException err) {
			throw new Exception("Erro ao deletar aluno " + err);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

}
