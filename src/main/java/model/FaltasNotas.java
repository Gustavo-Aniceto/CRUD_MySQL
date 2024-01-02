package model;

public class FaltasNotas {
	
	private String cod_curso;
	private String ra;
	private String disciplina;
	private String semestre;
	private double nota;
	private int faltas;
	private String nomeAluno;
	private String curso;
	
	
	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public FaltasNotas() {
		
	}

	public FaltasNotas(String cod_curso, String ra, String disciplina, String semestre, float nota, int faltas) {
		this.cod_curso = cod_curso;
		this.ra = ra;
		this.disciplina = disciplina;
		this.semestre = semestre;
		this.nota = nota;
		this.faltas = faltas;
	}

	public String getCod_curso() {
		return cod_curso;
	}

	public void setCod_curso(String cod_curso) {
		this.cod_curso = cod_curso;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public int getFaltas() {
		return faltas;
	}

	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}
}
