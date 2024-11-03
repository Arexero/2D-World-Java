package vue;

import java.awt.Color;
import java.awt.Graphics;

import modele.Carte;

public class VueCarteVoxel implements VueCarteStrat{
	
	public String getName() {
		return "VueCarteVoxel";
	}

	@Override
	public void paintComponent(float zoom,int x, int y,float phi, int hauteur,int distance, int horizon, Carte carte, Graphics g) {
		float tailleLongueurEcran = 900.0f;
		float tailleLargeurEcran = 900.0f;// valeur par défault temporairement 
		int echelleHauteur = (int) (52/zoom); // valeur par défault temporairement 
		double dx,dy;
		int hauteur_sur_ecran;
		double sinphi = Math.sin(phi);
		double cosphi = Math.cos(phi);
		Couple pleft = new Couple(0,0);
		Couple pright = new Couple(0,0);
		g.setColor(new Color(176/255f, 196/255f, 222/255f));
		g.fillRect(0,0,(int) tailleLargeurEcran, (int) tailleLongueurEcran);
		//System.out.println("phi" + phi);
		distance = (int)(distance * zoom);
		for(int i = distance ; i>=1; i--) {
			pleft.setX(((-cosphi*i-sinphi*i) + x)*zoom);
			pleft.setY(((sinphi*i - cosphi*i) + y)*zoom);
			pright.setX(((cosphi*i - sinphi*i) + x)*zoom);
			pright.setY(((-sinphi*i - cosphi*i) + y)*zoom);
			dx = (pright.getX() - pleft.getX()) / tailleLargeurEcran;
			dy = (pright.getY() - pleft.getY()) / tailleLargeurEcran;
			for(int j = 0; j<tailleLargeurEcran ;j++ ) {
				hauteur_sur_ecran = (int)((hauteur- 600*carte.getPoint((int)(pleft.getX()),(int)(pleft.getY())).getElevation()) / i *echelleHauteur + (horizon*zoom));
				g.setColor(carte.getPoint((int)(pleft.getX()),(int)(pleft.getY())).getColor());
				g.drawLine(j,hauteur_sur_ecran,j,(int)tailleLongueurEcran);
				pleft.setX((pleft.getX()+dx)%carte.getLongueur());
				pleft.setY((pleft.getY()+dy)%carte.getLongueur());
			}
		}
		
	}

}
