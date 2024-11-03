package modele;

public class Carte {

	protected Point[][] carte;

	public Carte(Point[][] carte) {
		this.carte = carte;
	}
	
	public Point getPoint(int x, int y) {
		return this.carte[x][y];
	}
	
	public Point[][] getCarte() {
		return this.carte;
	}
	
	public int getLongueur() {
		return this.carte.length;
	}
	
	public int getLargeur() {
		return this.carte[0].length;
	}
}
