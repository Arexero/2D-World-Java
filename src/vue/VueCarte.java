package vue;


import java.awt.Graphics;

import javax.swing.JPanel;

import controleur.ModelMiseAJour;
import modele.Camera;
import modele.Generation;

public class VueCarte extends JPanel implements ModelMiseAJour{
	
	private Generation generation;
	private VueCarteStrat vueCarteStrat;
	private Camera cam;
	
	private int x = 0;
	private int y = 0;
	
	private boolean mouvementJoueur = false;
	
	public VueCarte() {
		//Voir setVueCarte plus bas
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// Si le joueur ne bouge pas il n'y a pas d'interet a regenerer la carte.
		if(this.mouvementJoueur) {
			this.generation.carteNouvelleGeneration(this.cam.getCarte(), this.cam.getX(), this.cam.getY());
		}
		this.mouvementJoueur = false;
		this.cam.setCarte(this.generation.getCarte());
		this.vueCarteStrat.paintComponent(this.cam.getZoom(), this.x, this.y, this.cam.getOrientation(),this.cam.getHauteur(),this.cam.getDistance(), this.cam.getHorizon(), this.cam.getCarte(),g);
	}

	public Camera getCam() {
		return cam;
	}
	
	public void moveCam(int distance, String direction) {
		double distanceX = 0;
		double distanceY = 0;

		switch(direction) {
			case "droite":
				distanceX = (distance*Math.cos(-this.cam.getOrientation()));
				distanceY = (distance*Math.sin(-this.cam.getOrientation()));
				break;
			case "gauche":
				distanceX = (distance*Math.cos(Math.PI-this.cam.getOrientation()));
				distanceY = (distance*Math.sin(Math.PI-this.cam.getOrientation()));
				break;
			case "haut":
				distanceX = (distance*Math.cos(-Math.PI/2-this.cam.getOrientation()));
				distanceY = (distance*Math.sin(-Math.PI/2-this.cam.getOrientation()));
				break;
			case "bas":
				distanceX = (distance*Math.cos(Math.PI/2-this.cam.getOrientation()));
				distanceY = (distance*Math.sin(Math.PI/2-this.cam.getOrientation()));
				break;
		}
		this.mouvementJoueur = true;
		//Nous faisons cela car la VueCarteVoxel a besoin d'une gestion des collision
		if(vueCarteStrat.getName() == "VueCarte2D") {
			this.cam.setPos(distanceX, distanceY, "VueCarte2D");
		}
		else {
			this.cam.setPos(distanceX, distanceY, "VueCarteVoxel");
		}
	}
	
	public void zoomCam(float z) {
		this.cam.setZoom(this.cam.getZoom()*z);
	}
	
	public void deZoomCam(float z) {
		this.cam.setZoom(this.cam.getZoom()/z);
	}
	
	/* Pour ces trois fonction (changeHorizonCam, changeDistanceCam, heigthCam)
	 * nous ne voulons pas quel soit utilisable avec VueCarte2D
	 */
	public void changeHorizonCam(int z) {
		if(vueCarteStrat.getName() == "VueCarteVoxel") {
			this.cam.setHorizon(this.cam.getHorizon()+z);
		}
	}
	
	public void changeDistanceCam(int z) {
		if(vueCarteStrat.getName() == "VueCarteVoxel") {
			//On verifie que la distance d'affichage et ni trop grande ni trop petite pour evite les OutOfBounds
			//if(this.cam.getDistance()+z <= this.x-83 && this.cam.getDistance()+z <= this.y-83 && this.cam.getDistance()+z >= 20 && this.cam.getDistance()+z >= 20) {
				this.cam.setDistance(this.cam.getDistance()+z);
			//}
		}
	}

	public void heigthCam(int hauteur) {
		if(vueCarteStrat.getName() == "VueCarteVoxel") {
			this.cam.setHauteur(this.cam.getHauteur()+ hauteur);
		}
	}
	
	public void moveOrientationCam(float orientation) {
		this.cam.setOrientation(this.cam.getOrientation()+orientation);
	}
	
	public void setVueCarte(Generation generation, VueCarteStrat vueCarteStrat) {
		/* Nous creons la camera que maintenant car nous avons besoin d'attendre que le "joueur"
		 * choisisse la vision de la simulation dans VueHome. 
		 */
		if(vueCarteStrat.getName() == "VueCarte2D") {
			this.x = 0;
			this.y = 0;
		}
		else if(vueCarteStrat.getName() == "VueCarteVoxel") {
			this.x = 300;
			this.y = 300;
		}
		
		this.generation = generation;
		this.vueCarteStrat = vueCarteStrat;
		this.cam = new Camera(this.x,this.y,0,1,600,150,125,this.generation.getCarte());
		if(vueCarteStrat.getName() == "VueCarteVoxel") {
			/* On genere directement la nouvelle carte avec generation pour mettre a jour le fait que nous
			 * ne somme plus en x = 0 et y = 0 avec VueCarteVoxel
			 */
			this.generation.carteNouvelleGeneration(this.cam.getCarte(), this.cam.getX(), this.cam.getY());
		}
		this.cam.ajoutEcouteur(this);
		this.repaint();
	}
	
	public String getStrategieName() {
		return this.vueCarteStrat.getName();
	}

	@Override
	public void modelMiseAJour(Object source) {
		this.repaint();
	}
}
