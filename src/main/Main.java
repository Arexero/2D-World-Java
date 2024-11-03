package main;

import modele.Generation;
import modele.GenerationCarte;
import modele.GenerationElevation;
import modele.bruit.Bruit;
import modele.bruit.OctavesNoise;
import modele.bruit.SimplexNoise;
import modele.couleurs.Filtre;
import modele.couleurs.FiltreNoirBlanc;
import vue.VueCarte2D;
import vue.VueCarteStrat;
import vue.VueFrame;

public class Main {

	public static void main(String[] args) {
		int[] tailleCarte = new int[]{900,900};
		Bruit bruit =new OctavesNoise(new SimplexNoise(0));
		Filtre filtre = new FiltreNoirBlanc();
		GenerationCarte genCarte = new GenerationElevation(tailleCarte, 0);
		Generation generation = new Generation(tailleCarte, genCarte, bruit, filtre);
		
		VueCarteStrat vueCarte2D = new VueCarte2D();
		new VueFrame(generation, vueCarte2D);
	}

}
