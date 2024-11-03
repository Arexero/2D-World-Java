package modele;

import controleur.AbstractEcoutableCarte;
import modele.bruit.Bruit;
import modele.couleurs.Filtre;

public class Generation extends AbstractEcoutableCarte{
	
	protected int[] tailleCarte = new int[2];
	
	protected long seed;
	
	private Carte carte;
	private GenerationCarte genCarte;
	
	private Bruit bruit;
	protected Filtre filtre;
	
	public Generation(int[] tailleCarte, GenerationCarte genCarte, Bruit bruit, Filtre filtre) {
		this.tailleCarte = tailleCarte;
		
		this.genCarte = genCarte;
		
		this.bruit = bruit;
		this.filtre = filtre;
	}
	
	public void createCarteOrigine(float freq){
		 this.bruit.setFrequency(freq);
		 this.carte = this.genCarte.createCarteOrigine(this.bruit, this.filtre);
		 this.firechange();
	}
	
	public void carteNouvelleGeneration(Carte carteOrigine, int xCam, int yCam) {
		 this.carte = this.genCarte.carteNouvelleGeneration(carteOrigine, xCam, yCam);
		 this.firechange();
	}
	
	public Carte getCarte(){
		return this.carte;
	}
	public void setGenerationCarte(GenerationCarte genCarte) {
		this.genCarte = genCarte;
	}
	
	//--Bruit--//
	public Bruit getBruit() {
		return this.bruit;
	}
	public void setBruit(Bruit bruit) {
		this.bruit = bruit;
	}
	
	//--Filtre--//
	public Filtre getFiltre() {
		return this.filtre;
	}
	public void setFiltre(Filtre filtre) {
		this.filtre = filtre;
	}
}
