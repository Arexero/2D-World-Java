package modele;

import controleur.AbstractEcoutableCarte;

public class Camera extends AbstractEcoutableCarte{
	
	protected int x;
	protected int y;
	private int xInitial;
	private int yInitial;
	
	protected float zoom;
	protected int distance;
	protected int horizon;
	protected int hauteur;
	protected float orientation;
	
	protected Carte carte;
	protected boolean animated;	
	
	public Camera(int x, int y, float orientation, int zoom, int hauteur,int distance, int horizon, Carte carte) {
		/* Nous avons a la fois x, y et xInitial ainsi que yInitial, car x et y correspond a l'emplacement
		 * ou devrait ce trouver la camera. Cela permet de genere le bruit suivant quand on se deplace.
		 * Puis xInitial et yInitial afin que la vu du joueur ne se deplace pas.
		 * (Il faut imaginer que le joueur et sur un tapis roulant)
		 */
		this.x = x;
		this.y = y;
		this.xInitial = x;
		this.yInitial = y;
		
		this.zoom = zoom;
		this.hauteur = hauteur;
		this.distance = distance;
		this.horizon = horizon;
		this.orientation = orientation;
		
		this.carte = carte;
		this.animated = false;
	}

	//--X--//
	public int getX() {
		return this.x;
	}
	public void setX(int x) {
		this.x = x;
	}

	//--Y--//
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void setPos(double distanceX, double distanceY, String strategie) {
		this.x += distanceX;
		this.y += distanceY;
		//nous le faisons uniquement pour voxel car il n'y a pas d'interet pour la 2D
		if(strategie == "VueCarteVoxel") {
			for(int i = -5; i<5; i++) {
				for(int j = -5; j<5;j++) {
					if(this.hauteur<(int)(600*this.carte.getPoint((int)this.xInitial+i,(int)this.yInitial+j).getElevation())) {
						this.setHauteur((int)(600*this.carte.getPoint((int)this.xInitial+i,(int)this.yInitial+j).getElevation()));
					}
				}
			}
		}
		this.firechange();
	}

	//--Zoom--//
	public float getZoom() {
		return zoom;
	}
	public void setZoom(float zoom) {
		if(zoom<=1) {
			this.zoom = zoom;
		}
		this.firechange();
	}
	
	//--Carte--//
	public Carte getCarte() {
		return this.carte;
	}
	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	//--Hauteur--//
	public int getHauteur() {
		return hauteur;
	}
	public void setHauteur(int hauteur) {
		if(hauteur >= (int)(600*this.carte.getPoint((int)this.xInitial, (int)this.yInitial).getElevation())) {
			this.hauteur = hauteur;	
		}
		this.firechange();
	}

	//--Distance--//
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
		this.firechange();
	}

	//--Horizon--//
	public int getHorizon() {
		return horizon;
	}
	public void setHorizon(int horizon) {
		this.horizon = horizon;
		this.firechange();
	}

	//--Orientation--//
	public float getOrientation() {
		return this.orientation;
	}
	public void setOrientation(float orientation) {
		this.orientation = orientation;
		this.firechange();
	}
}
