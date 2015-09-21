package br.com.lfpmobile.qualoestado.dominio;

public class DicaBandeira extends Dica {

	private String bandeiraImagePath;
	
	public DicaBandeira() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DicaBandeira(int custoEmPontos, Estado estado) {
		super(custoEmPontos, estado);
		// TODO Auto-generated constructor stub
	}
	public String getBandeiraImagePath() {
		return bandeiraImagePath;
	}
	public void setBandeiraImagePath(String bandeiraImagePath) {
		this.bandeiraImagePath = bandeiraImagePath;
	}
}
