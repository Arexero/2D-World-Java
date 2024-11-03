package modele;

import modele.bruit.Bruit;
import modele.couleurs.Filtre;

public interface GenerationCarte {
	public Carte createCarteOrigine(Bruit bruit, Filtre filtre);
	public Carte carteNouvelleGeneration(Carte carteOrigine, int xCam, int yCam);
}
