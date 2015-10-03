package br.com.lfpmobile.qualoestado.models;

public class Jogador {

	private int pontos;
	private int numErros;
	private int numAcertos;
	private int numPulosResposta;
	private int numUsosDicaBandeira;
	private int numUsosDicaDescricao;
	private int numUsosDicaLetra;
	private int maiorNumPontos;
	private int menorNumPontos;
    private String nome;
	private boolean appJaUsado;
	
	public Jogador() {
	}

	public Jogador(int pontos, int numErros, int numAcertos, int numPulosResposta,
				   int numUsosDicaBandeira, int numUsosDicaDescricao, int numUsosDicaLetra,
				   int maiorNumPontos, int menorNumPontos, String nome, boolean appJaUsado) {
		this.pontos = pontos;
		this.numErros = numErros;
		this.numAcertos = numAcertos;
		this.numPulosResposta = numPulosResposta;
		this.numUsosDicaBandeira = numUsosDicaBandeira;
		this.numUsosDicaDescricao = numUsosDicaDescricao;
		this.numUsosDicaLetra = numUsosDicaLetra;
		this.maiorNumPontos = maiorNumPontos;
		this.menorNumPontos = menorNumPontos;
        this.nome = nome;
        this.appJaUsado = appJaUsado;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getNumErros() {
		return numErros;
	}

	public void setNumErros(int numErros) {
		this.numErros = numErros;
	}

	public int getNumAcertos() {
		return numAcertos;
	}

	public void setNumAcertos(int numAcertos) {
		this.numAcertos = numAcertos;
	}

	public int getNumPulosResposta() {
		return numPulosResposta;
	}

	public void setNumPulosResposta(int numPulosResposta) {
		this.numPulosResposta = numPulosResposta;
	}

	public int getNumUsosDicaBandeira() {
		return numUsosDicaBandeira;
	}

	public void setNumUsosDicaBandeira(int numUsosDicaBandeira) {
		this.numUsosDicaBandeira = numUsosDicaBandeira;
	}

	public int getNumUsosDicaDescricao() {
		return numUsosDicaDescricao;
	}

	public void setNumUsosDicaDescricao(int numUsosDicaDescricao) {
		this.numUsosDicaDescricao = numUsosDicaDescricao;
	}

	public int getNumUsosDicaLetra() {
		return numUsosDicaLetra;
	}

	public void setNumUsosDicaLetra(int numUsosDicaLetra) {
		this.numUsosDicaLetra = numUsosDicaLetra;
	}

	public int getMaiorNumPontos() {
		return maiorNumPontos;
	}

	public void setMaiorNumPontos(int maiorNumPontos) {
		this.maiorNumPontos = maiorNumPontos;
	}

	public int getMenorNumPontos() {
		return menorNumPontos;
	}

	public void setMenorNumPontos(int menorNumPontos) {
		this.menorNumPontos = menorNumPontos;
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAppJaUsado() {
        return appJaUsado;
    }

    public void setAppJaUsado(boolean appJaUsado) {
        this.appJaUsado = appJaUsado;
    }
}
