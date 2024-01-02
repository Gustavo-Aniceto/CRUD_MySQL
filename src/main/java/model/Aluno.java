package model;

public class Aluno {
	
	//Atributos
	private String ra;
	private String nome;
	private String data;
	private String cpf;
	private String email;
	private String endereco;
	private String municipio;
	private String uf;
	private String celular;
	private String curso;
	private String campos;
	private String periodo;
	private String imagem;
	private String semestre;
	private String nota;
	private String falta;
	private String disciplina;
	

	
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getFalta() {
		return falta;
	}

	public void setFalta(String falta) {
		this.falta = falta;
	}

	public String getSemestre() {
		return semestre;
	}

	public String getDisciplina() {
		return disciplina;
	}

	//Construtor Vazio
	public Aluno() {
		
	}
	
	//Construtor
	public Aluno(String ra, String nome, String data, String cpf, String email, String endereco, String municipio,
			String uf, String celular, String curso, String campos, String periodo, String imagem) {
		super();
		this.ra = ra;
		this.nome = nome;
		this.data = data;
		this.cpf = cpf;
		this.email = email;
		this.endereco = endereco;
		this.municipio = municipio;
		this.uf = uf;
		this.celular = celular;
		this.curso = curso;
		this.campos = campos;
		this.periodo = periodo;
		this.imagem = imagem;
	}

	//getters e setters
	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getCampos() {
		return campos;
	}

	public void setCampos(String campos) {
		this.campos = campos;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getNota1() {
		return nota;
	}
}
