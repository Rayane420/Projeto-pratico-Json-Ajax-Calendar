package entidades;

public class Series {

	private Long id;
	private String nome;
	private String dataInicial;
	private String dataFinal;
	private Long projeto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Long getProjeto() {
		return projeto;
	}

	public void setProjeto(Long projeto) {
		this.projeto = projeto;
	}

}
