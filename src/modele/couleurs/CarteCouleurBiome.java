package modele.couleurs;

import java.util.HashMap;

import modele.Biome;

public class CarteCouleurBiome {
	
	protected Biome merProfonde = new Biome("Mer Profonde",new RGB(14,74,133) );
	protected Biome merBordPlage = new Biome("Bord de Plage", new RGB(22,93,163));
	protected Biome plage = new Biome("Plage", new RGB(255,234,160));
	
	protected HashMap<String, Biome> tableauAllBiome;
	
	protected RGB[][] carteCouleur = new RGB[101][101];
	protected int niveauEau;
	
	public CarteCouleurBiome(int niveauEau, HashMap<String, Biome> tableauAllBiome) {
		this.tableauAllBiome = tableauAllBiome;
		
		this.niveauEau = niveauEau;
		this.creeCarteCouleur();
	}
	
	public Biome getMerBordPlage() {
		return this.merBordPlage;
	}

	public void setAllBiome(HashMap<String, Biome> allBiome) {
		if(allBiome.get("Biome1") != null) {
			this.tableauAllBiome.replace("Biome1", allBiome.get("Biome1"));
		}
		if(allBiome.get("Biome2") != null) {
			this.tableauAllBiome.replace("Biome2", allBiome.get("Biome2"));
		}
		if(allBiome.get("Biome3") != null) {
			this.tableauAllBiome.replace("Biome3", allBiome.get("Biome3"));
		}
		if(allBiome.get("Biome4") != null) {
			this.tableauAllBiome.replace("Biome4", allBiome.get("Biome4"));
		}
		if(allBiome.get("Biome5") != null) {
			this.tableauAllBiome.replace("Biome5", allBiome.get("Biome5"));
		}
		if(allBiome.get("Biome6") != null) {
			this.tableauAllBiome.replace("Biome6", allBiome.get("Biome6"));
		}
		if(allBiome.get("Biome7") != null) {
			this.tableauAllBiome.replace("Biome7", allBiome.get("Biome7"));
		}
		if(allBiome.get("Biome8") != null) {
			this.tableauAllBiome.replace("Biome8", allBiome.get("Biome8"));
		}
		if(allBiome.get("Biome9") != null) {
			this.tableauAllBiome.replace("Biome9", allBiome.get("Biome9"));
		}
		if(allBiome.get("Biome10") != null) {
			this.tableauAllBiome.replace("Biome10", allBiome.get("Biome10"));
		}
		if(allBiome.get("Biome11") != null) {
			this.tableauAllBiome.replace("Biome11", allBiome.get("Biome11"));
		}
		if(allBiome.get("Biome12") != null) {
			this.tableauAllBiome.replace("Biome12", allBiome.get("Biome12"));
		}
	}
	public HashMap<String, Biome> getTableauAllBiome() {
		return this.tableauAllBiome;
	}
	
	public RGB[][] getCarteCouleur(){
		return this.carteCouleur;
	}
	
	public void creeCarteCouleur() {
		/* Cette fonction permet de remplir un tableau de differente couleur, biome par biome
		 */
		for(String keyBiome : this.tableauAllBiome.keySet()) {
			Biome biomeCaracteristique = this.tableauAllBiome.get(keyBiome);
			for(Integer[] elevation : biomeCaracteristique.getElevationHumidite().keySet()) {
				for(int x=elevation[0]; x<=elevation[1]; x++) {
					Integer[] humidite = biomeCaracteristique.getElevationHumidite().get(elevation);
					for(int y=humidite[0]; y<=humidite[1]; y++) {
						RGB couleur = biomeCaracteristique.getCouleur();
						if(this.niveauEau != 0 && !this.gestionNiveauEau(x).isSameColor(new RGB(0,0,0))) {
							couleur = this.gestionNiveauEau(x);
						}
						this.carteCouleur[x][y] = couleur;
					}
				}
			}
		}
	}
	
	private RGB gestionNiveauEau(int x) {
		RGB couleur = new RGB(0,0,0);
		if((this.niveauEau - 5) > x) {
			couleur = this.merProfonde.getCouleur();
		}
		else if(this.niveauEau > x) {
			couleur = this.merBordPlage.getCouleur();
		}
		else if((this.niveauEau + 5) > x) {
			couleur = this.plage.getCouleur();
		}
		return couleur;
	}
}