package vue;

import java.awt.Graphics;

import modele.Carte;


public class VueCarte2D implements VueCarteStrat{
	
	public String getName() {
		return "VueCarte2D";
	}

	@Override
	public void paintComponent(float zoom,int x,int y,float phi, int hauteur,int distance, int horizon,Carte carte, Graphics g) {
		int tailleLongueurPixel = 900/(carte.getLongueur()-horizon);
		int tailleLargeurPixel = 900/(carte.getLargeur()-horizon);
		for(int i = x; i<carte.getLongueur()-horizon; i++) {
			for(int j = y; j<carte.getLargeur()-horizon;j++) {
				g.setColor(carte.getPoint((int) (zoom*j+x), (int) (zoom*i+y)).getColor());
				g.fillRect(j*tailleLargeurPixel,i*tailleLongueurPixel,tailleLargeurPixel,tailleLongueurPixel);
			}
		}
	}

}
