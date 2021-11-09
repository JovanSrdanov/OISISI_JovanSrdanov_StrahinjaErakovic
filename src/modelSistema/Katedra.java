package modelSistema;

import java.util.ArrayList;

public class Katedra {

	private int sifraKatedre;
	private String nazivKatedre;
	private Profesor sefKatedre;
	private ArrayList<Profesor> spisakProfesoraKojiSuNaKatedri;

	@Override
	public String toString() {
		return "Katedra [sifraKatedre=" + sifraKatedre + ", nazivKatedre=" + nazivKatedre + ", sefKatedre=" + sefKatedre
				+ ", spisakProfesoraKojiSuNaKatedri=" + spisakProfesoraKojiSuNaKatedri + "]";
	}

	public int getSifraKatedre() {
		return sifraKatedre;
	}

	public void setSifraKatedre(int sifraKatedre) {
		this.sifraKatedre = sifraKatedre;
	}

	public String getNazivKatedre() {
		return nazivKatedre;
	}

	public void setNazivKatedre(String nazivKatedre) {
		this.nazivKatedre = nazivKatedre;
	}

	public Profesor getSefKatedre() {
		return sefKatedre;
	}

	public void setSefKatedre(Profesor sefKatedre) {
		this.sefKatedre = sefKatedre;
	}

	public ArrayList<Profesor> getSpisakProfesoraKojiSuNaKatedri() {
		return spisakProfesoraKojiSuNaKatedri;
	}

	public void setSpisakProfesoraKojiSuNaKatedri(ArrayList<Profesor> spisakProfesoraKojiSuNaKatedri) {
		this.spisakProfesoraKojiSuNaKatedri = spisakProfesoraKojiSuNaKatedri;
	}

	public Katedra(int sifraKatedre, String nazivKatedre, Profesor sefKatedre,
			ArrayList<Profesor> spisakProfesoraKojiSuNaKatedri) {
		super();
		this.sifraKatedre = sifraKatedre;
		this.nazivKatedre = nazivKatedre;
		this.sefKatedre = sefKatedre;
		this.spisakProfesoraKojiSuNaKatedri = spisakProfesoraKojiSuNaKatedri;
	}

	public Katedra() {
		super();
		// TODO Auto-generated constructor stub
	}

}