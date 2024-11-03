package modele.couleurs;

import java.util.HashMap;

import modele.Biome;

public class FiltreCouleur implements Filtre {
	
	//Du plus fonce (marron vert) au plus clair (vert clair, voir blanc tout en haut)
	private RGB[] tableauCouleur = new RGB[]{new  RGB(138,109,44),new RGB(138,112,45),new RGB(133,123,51),new RGB(132,128,54),new RGB(129,136,58),new RGB(123,150,65),new RGB(120,159,71),new RGB(118,165,74),new RGB(113,178,80),new RGB(118,181,90),new RGB(134,192,105),new RGB(255,255,255)};
	
	protected HashMap<String, Biome> tableauAllBiome;

	public FiltreCouleur() {
		this.tableauAllBiome = new HashMap<String, Biome>();
		/* altitudeValeur permet de savoir quel est la plage d'elevation ou ce biome peut apparaitre,
		 * vu que le total ne va pas jusqu'a 100 la dernier est a l'exterieur de la boucle
		 */
		int altitudeValeur = 8;
		for(int i=0; i<11; i++) {
			HashMap<Integer[], Integer[]> altitude = new HashMap<Integer[], Integer[]>();
			altitude.put(new Integer[]{altitudeValeur*i,altitudeValeur*(i+1)}, new Integer[]{0,100});
			Biome biome = new Biome(("Altitude "+i), this.tableauCouleur[i], altitude);
			this.tableauAllBiome.put(("Biome"+(i+1)), biome);
		}
		HashMap<Integer[], Integer[]> altitude12 = new HashMap<Integer[], Integer[]>();
		altitude12.put(new Integer[]{88,100}, new Integer[]{0,100});
		Biome biome = new Biome(("Altitude 12"), this.tableauCouleur[11], altitude12);
		this.tableauAllBiome.put(("Biome12"), biome);
	}
	
	public HashMap<String, Biome> getColorationBiome(){
		return this.tableauAllBiome;
	}
}
