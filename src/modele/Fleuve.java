package modele;

import java.awt.Color;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Random;

import modele.bruit.Bruit;

public class Fleuve {
	
	protected int[] tailleCarte;
	protected Point[][] carte;
	protected Bruit bruit;
	protected int maxSourceEau;
	protected double niveauEau;
	protected Color colorFleuve;
	
	protected HashMap<Integer, Integer> ensembleGlobalFleuve;

	public Fleuve(int[] tailleCarte, Point[][] carte, Bruit bruit, double niveauEau, int maxSourceEau, Color colorFleuve) {
		this.tailleCarte = tailleCarte;
		this.carte = carte;
		this.bruit = bruit;
		this.maxSourceEau = maxSourceEau;
		this.niveauEau = niveauEau;
		this.colorFleuve = colorFleuve;
		
		this.ensembleGlobalFleuve = new HashMap<Integer, Integer>();
	}
	
	public Point[][] ajoutDesFleuveCarte(){
		HashMap<Integer, Integer> ensembleSource = ajoutSourceCarte();
		this.ensembleGlobalFleuve = ensembleSource;
		
		gestionEcoulement(ensembleSource);

		//on rempli la carte avec les zone d'eau lier au fleuve
		for(Integer sourceCoordX : this.ensembleGlobalFleuve.keySet()) {
			carte[sourceCoordX][this.ensembleGlobalFleuve.get(sourceCoordX)].setColor(this.colorFleuve);
		}
		return this.carte;
	}
	
	public void gestionEcoulement(HashMap<Integer, Integer> ecoulementZone) {
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
			gestionEcoulement(newEcoulementZone);
		}
	}

	public HashMap<Integer, Integer> ajoutSourceCarte(){
		//--Random du nombre total de source--//
		//Random ramdomNombreSource = new Random();
		//int nombreSource = ramdomNombreSource.nextInt(this.maxSourceEau);
		
		HashMap<Integer, Integer> ensembleSource = new HashMap<Integer, Integer>();
		
		if(this.maxSourceEau > 0) {
			for(int i=0; i<=this.maxSourceEau; i++) {
				Random ramdomSourceEau = new Random();
				int x = ramdomSourceEau.nextInt(this.tailleCarte[0]);
				int y = ramdomSourceEau.nextInt(this.tailleCarte[1]);
				ensembleSource.put(x, y);
			}
		}
		return ensembleSource;
	}
	
	public HashMap<Integer, Integer> elevationPlusBasseAutour(Integer[] source, double elevation) {
		HashMap<Integer, Integer> plusBas = new HashMap<Integer, Integer>();
		//System.out.println(plusBas);
		for(int i = -1; i<2 ; i++) {
			for(int j = -1 ; j<2 ; j++) {
				if(VerifOutOfBounds(source[0]+i, source[1]+j) && bruit.getNoise(source[0]+i, source[1]+j) < elevation) {
					plusBas.put(source[0]+i, source[1]+j);
					//System.out.println(i + ", " + j);
					//System.out.println(plusBas[0] + ", " + plusBas[1]);
				}
				//System.out.println(i + ", " + j + "Bon");
			}
		}
		return plusBas;
	}
	
	public boolean VerifOutOfBounds(int x, int y) {
		boolean verif = false;
		if(x >= 0 && x < this.tailleCarte[0] && y >= 0 && y < this.tailleCarte[1]) {
			verif = true;
		}
		return verif;
	}
}
