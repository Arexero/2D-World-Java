package modele;

import java.awt.Color;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import modele.Point;
import modele.bruit.Bruit;

public class FleuveB {
	
	protected int[] tailleCarte;
	protected Point[][] carte;
	protected Bruit bruit;
	protected int maxSourceEau;
	protected double niveauEau;
	protected Color colorFleuve;
	
	protected HashMap<Integer, Integer> ensembleGlobalFleuve;
	protected HashMap<Integer, Integer> ensembleSourceButFinal;

	public FleuveB(int[] tailleCarte, Point[][] carte, Bruit bruit, double niveauEau, int maxSourceEau, Color colorFleuve, HashMap<Integer, Integer> ensembleSourceButFinal) {
		this.tailleCarte = tailleCarte;
		this.carte = carte;
		this.bruit = bruit;
		this.maxSourceEau = maxSourceEau;
		this.niveauEau = niveauEau;
		this.colorFleuve = colorFleuve;
		
		this.ensembleGlobalFleuve = new HashMap<Integer, Integer>();
		this.ensembleSourceButFinal = ensembleSourceButFinal;
	}
	
	public Point[][] ajoutDesFleuveCarte(int[] posCam){
		sourcePourCarte();
		gestionEcoulement(this.ensembleSourceButFinal, posCam);

		//on rempli la carte avec les zone d'eau lier au fleuve
		for(Integer sourceCoordX : this.ensembleGlobalFleuve.keySet()) {
			//carte[sourceCoordX][this.ensembleGlobalFleuve.get(sourceCoordX)].setColor(this.colorFleuve);
			carte[sourceCoordX][this.ensembleGlobalFleuve.get(sourceCoordX)].setColor(new Color(0xD410E4));
		}
		//Test voir source
		for(Integer sourceCoordX : this.ensembleSourceButFinal.keySet()) {
			carte[sourceCoordX][this.ensembleSourceButFinal.get(sourceCoordX)].setColor(new Color(0xE41010));
		}
		return this.carte;
	}
	
	public void gestionEcoulement(HashMap<Integer, Integer> ecoulementZone, int[] posCam) {
		this.ensembleGlobalFleuve.putAll(ecoulementZone);
		HashMap<Integer, Integer> newEcoulementZone = new HashMap<Integer, Integer>();
		for(Integer sourceCoordX : ecoulementZone.keySet()) {
			Integer sourceCoordY = ecoulementZone.get(sourceCoordX);
			Integer[] source = new Integer[]{sourceCoordX, sourceCoordY};
			double elevation = bruit.getNoise(sourceCoordX, sourceCoordY);
			//System.out.println(elevation);
			newEcoulementZone.putAll(elevationPlusBasseAutour(source, elevation));
			//System.out.println(newEcoulementZone.size());
		}
		//System.out.println(newEcoulementZone.isEmpty());
		if(!newEcoulementZone.isEmpty()) {
			gestionEcoulement(newEcoulementZone, posCam);
		}
	}

	/////Modifier Normalement
	public void sourcePourCarte(){
		List<Integer> listeSource = new ArrayList<Integer>(this.ensembleSourceButFinal.keySet());
		
		if(this.maxSourceEau < this.ensembleSourceButFinal.size() && this.ensembleSourceButFinal.size() != 0) {
			int nombreNonSource = this.ensembleSourceButFinal.size() - this.maxSourceEau;
			for(int i=0; i<nombreNonSource; i++) {
				Random ramdomNonSource = new Random();
				int nonSource = ramdomNonSource.nextInt(this.ensembleSourceButFinal.size());
				Integer sourceASupprimerCle = listeSource.get(nonSource);
				listeSource.remove(nonSource);
				this.ensembleSourceButFinal.remove(sourceASupprimerCle);
			}
		}
		else if(this.maxSourceEau > this.ensembleSourceButFinal.size() && this.ensembleSourceButFinal.size() != 0){
			int nombreNonSource = this.maxSourceEau - this.ensembleSourceButFinal.size();
			for(int i=0; i<nombreNonSource; i++) {
				Random ramdomNonSource = new Random();
				int nonSource = ramdomNonSource.nextInt(this.ensembleSourceButFinal.size());
				Integer sourceASupprimerCle = listeSource.get(nonSource);
				listeSource.remove(nonSource);
				this.ensembleSourceButFinal.remove(sourceASupprimerCle);
			}
		}
	}
	
	public HashMap<Integer, Integer> elevationPlusBasseAutour(Integer[] source, double elevation) {
		HashMap<Integer, Integer> plusHaut = new HashMap<Integer, Integer>();
		//System.out.println(plusBas);
		for(int i = -1; i<2 ; i++) {
			for(int j = -1 ; j<2 ; j++) {
				if(!VerifOutOfBounds(source[0]+i, source[1]+j) && bruit.getNoise(source[0]+i, source[1]+j) > elevation && bruit.getNoise(source[0]+i, source[1]+j) <= 0.8) {
					plusHaut.put(source[0]+i, source[1]+j);
					//System.out.println(i + ", " + j);
					//System.out.println(plusBas[0] + ", " + plusBas[1]);
				}
				//System.out.println(i + ", " + j + "Bon");
			}
		}
		return plusHaut;
	}
	
	public boolean VerifOutOfBounds(int x, int y) {
		boolean verif = true;
		if(x >= 0 && x < this.tailleCarte[0] && y >= 0 && y < this.tailleCarte[1]) {
			verif = false;
		}
		return verif;
	}
}
