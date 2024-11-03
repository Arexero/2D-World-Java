package modele.couleurs;

import java.util.HashMap;

import modele.Biome;

public class FiltreNoirBlanc implements Filtre {

	//Du plus fonce (noir) au plus clair (blanc)
	private RGB[] tableauCouleur = new RGB[]{new RGB(0,0,0),new RGB(38,38,38),new RGB(71,71,71),new RGB(90,90,90),new RGB(110,110,110),new RGB(128,128,128),new RGB(154,154,154),new RGB(172,172,172),new RGB(186,186,186),new RGB(216,216,216),new RGB(234,234,234),new RGB(255,255,255)};
	
	protected HashMap<String, Biome> tableauAllBiome;

	public FiltreNoirBlanc() {
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
