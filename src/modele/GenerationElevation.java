package modele;

import java.awt.Color;

import modele.bruit.Bruit;
import modele.couleurs.CarteCouleurBiome;
import modele.couleurs.Filtre;
import modele.couleurs.RGB;

public class GenerationElevation implements GenerationCarte{
	
	protected int[] tailleCarte;
	protected double niveauEau;
	
	private long seedElevation;
	
	protected RGB[][] tableauCouleurCarte;
	protected Bruit bruit;
	
	//Utiliser uniquement pour la fonction carteNouvelleGeneration
	protected int sauvegardeCamX = 0;
	protected int sauvegardeCamY = 0;

	public GenerationElevation(int[] tailleCarte, double niveauEau) {
		this.tailleCarte = tailleCarte;
		this.niveauEau = niveauEau;
	}

	@Override
	public Carte createCarteOrigine(Bruit bruit, Filtre filtre){
		this.bruit = bruit;
		
		this.seedElevation = this.bruit.getSeed();
		
		Point[][] carte = new Point[this.tailleCarte[0]][this.tailleCarte[1]];
		
		//--Creation du tableau de Couleur--//
		int newNiveauEau = (int) (this.niveauEau * 100);
		CarteCouleurBiome carteBiome = new CarteCouleurBiome(newNiveauEau, filtre.getColorationBiome());
		carteBiome.setAllBiome(filtre.getColorationBiome());
		this.tableauCouleurCarte = carteBiome.getCarteCouleur();
		
		//--Remplissage de la carte avec le bruit--//
		for(int x =0 ; x<this.tailleCarte[0];x++) {
			for(int y =0 ; y<this.tailleCarte[1];y++) {
				this.bruit.setSeed(this.seedElevation);
				double elevation = this.bruit.getNoise(x, y);
				carte[x][y] = remplissageCarte(elevation);
			}
		}
		return new Carte(carte);
	}
	
	public Carte carteNouvelleGeneration(Carte carteOrigine, int xCam, int yCam) {
		/* Cette condition permet d'eviter le probleme ou on se deplace en meme temps
		 * que tourner la tete
		 */
		if((xCam - this.sauvegardeCamX) == 0 && (yCam - this.sauvegardeCamY) == 0) {
			return carteOrigine;
		}
		
		this.seedElevation = this.bruit.getSeed();
		
		Point[][] carteOrigineModifier = carteOrigine.getCarte();
		Point[][] nouvelleCarte = new Point[this.tailleCarte[0]][this.tailleCarte[1]]; //nouvelleCarte utiliser pour l'axe des X
		Point[][] nouvelleCarteDiagonale = new Point[this.tailleCarte[0]][this.tailleCarte[1]]; //nouvelleCarte utiliser pour l'axe des Y
		
		//Valeur pour l'initialisation de x et y dans la premiere boucle for
		int valeurX = 0;
		int valeurY = 0;
		//Valeur a ne pas depasser pour x et y dans la premiere boucle for
		int superieurX = this.tailleCarte[0];
		int superieurY = this.tailleCarte[1];
		//Que pour l'axe des X, permet de prendre les valeurs de la carte d'origine, de tel facon que ce soit decale
		int pourX = 0;
		//Pareil mais pour les Y
		int pourY = 0;
				
		//Valeur pour l'initialisation de x et y dans la deuxieme boucle for (celle qui genere)
		int valeurXgeneration = 0;
		int valeurYgeneration = 0;
		//Valeur a ne pas depasser pour x et y dans la deuxieme boucle for (celle qui genere)
		int superieurXgeneration = this.tailleCarte[0];
		int superieurYgeneration = this.tailleCarte[1];
				
		int choixUtilisateurX = xCam - this.sauvegardeCamX;
		this.sauvegardeCamX = xCam;
				
		/* Nous avons a la fois l'axe des X puis l'axe des Y, afin d'avoir la possibilite de ce deplacer en diagonale
		*/
		//---Axe des X---//
		if(choixUtilisateurX != 0) {
			//-Droite-//
			if(choixUtilisateurX > 0) {	
				valeurX = 0;
				superieurX = this.tailleCarte[0]-choixUtilisateurX;
				pourX = choixUtilisateurX;

				valeurXgeneration = this.tailleCarte[0]-choixUtilisateurX;
				superieurXgeneration = this.tailleCarte[0];
			}
			//-Gauche-//
			else if(choixUtilisateurX < -10) {
				valeurX = 0-choixUtilisateurX;
				superieurX = this.tailleCarte[0];
				pourX = choixUtilisateurX;

				valeurXgeneration = 0;
				superieurXgeneration = 0-choixUtilisateurX;
			}
			
			//--Remplisage de la nouvelle carte par une partie de l'ancienne--//
			for(int x = valeurX; x < superieurX; x++) {
				for(int y = 0; y < this.tailleCarte[1]; y++) {
					nouvelleCarte[x][y] = carteOrigineModifier[x+pourX][y+0];
				}
			}
			//--Remplisage de la nouvelle carte par la "suite" de l'ancienne carte (grace au bruit)--//
			for(int x = valeurXgeneration; x < superieurXgeneration; x++) {
				for(int y = 0; y < this.tailleCarte[1]; y++) {
					this.bruit.setSeed(this.seedElevation);
					double elevation = this.bruit.getNoise(xCam + x, this.sauvegardeCamY + y);
					nouvelleCarte[x][y] = remplissageCarte(elevation);
				}
			}
			carteOrigineModifier = nouvelleCarte; 
		}
		
		int choixUtilisateurY = yCam - this.sauvegardeCamY;
		this.sauvegardeCamY = yCam;
		
		//---Axe des Y---//
		if(choixUtilisateurY != 0) {
			//-Haut-//
			if(choixUtilisateurY > 0) {
				valeurY = 0;
				superieurY = this.tailleCarte[1]-choixUtilisateurY;
				pourY = choixUtilisateurY;

				valeurYgeneration = this.tailleCarte[1]-choixUtilisateurY;
				superieurYgeneration = this.tailleCarte[1];
			}
			//-Bas-//
			else if(choixUtilisateurY < 0) {
				valeurY = 0-choixUtilisateurY;
				superieurY = this.tailleCarte[1];
				pourY = choixUtilisateurY;

				valeurYgeneration = 0;
				superieurYgeneration = 0-choixUtilisateurY;
			}
			//--Remplisage de la nouvelle carte par une partie de l'ancienne--//
			for(int x = 0; x < this.tailleCarte[0]; x++) {
				for(int y = valeurY; y < superieurY; y++) {
					nouvelleCarteDiagonale[x][y] = carteOrigineModifier[x+0][y+pourY];
				}
			}
			
			//--Remplisage de la nouvelle carte par la "suite" de l'ancienne carte (grace au bruit)--//
			for(int x = 0; x < this.tailleCarte[0]; x++) {
				for(int y = valeurYgeneration; y < superieurYgeneration; y++) {
					this.bruit.setSeed(this.seedElevation);
					double elevation = this.bruit.getNoise(xCam + x, yCam + y);
					nouvelleCarteDiagonale[x][y] = remplissageCarte(elevation);
				}
			}
			nouvelleCarte = nouvelleCarteDiagonale;
		}
	return new Carte(nouvelleCarte);
	}
	
	public Point remplissageCarte(double elevation) {
		Point newPoint;
		
		/*
		 * On met l'elevation et l'humidite sur 100 car cela permet de
		 * parcourir le tableau des couleur.
		 */
		int newElevation = (int) (elevation*100);
		if(elevation <= this.niveauEau) {
			elevation = this.niveauEau;
		}
		
		int rouge = (int)(this.tableauCouleurCarte[newElevation][0].getRed()*elevation);
		int vert = (int)(this.tableauCouleurCarte[newElevation][0].getGreen()*elevation);
		int bleu = (int)(this.tableauCouleurCarte[newElevation][0].getBlue()*elevation);
		newPoint = new Point((new Color(rouge, vert, bleu)), elevation);
		return newPoint;
	}
}
