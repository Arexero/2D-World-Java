package vue;
import java.awt.Graphics;

import modele.Carte;

public interface VueCarteStrat {
	public String getName();
	public void paintComponent(float zoom,int x, int y,float phi,int distance, int horizon, int hauteur,Carte carte,Graphics g) ;
}
