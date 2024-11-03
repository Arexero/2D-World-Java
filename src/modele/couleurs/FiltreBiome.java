package modele.couleurs;

import java.util.HashMap;

import modele.Biome;

public class FiltreBiome implements Filtre {
	
	protected RGB Biome1Couleur = new RGB(35,80,29); //Marais
	protected RGB Biome2Couleur = new RGB(251,234,118); //Desert
	protected RGB Biome3Couleur = new RGB(164,255,100); //Plaine
	protected RGB Biome4Couleur = new RGB(30,128,0); //Jungle
	protected RGB Biome5Couleur = new RGB(81,157,58); //Foret
	protected RGB Biome6Couleur = new RGB(102,201,72); //Prairie
	protected RGB Biome7Couleur = new RGB(74,131,57); //Toundra
	protected RGB Biome8Couleur = new RGB(208,181,92); //Desert Montagneux
	protected RGB Biome9Couleur = new RGB(117,110,67); //Montagne
	protected RGB Biome10Couleur = new RGB(91,86,47); //Haute Montagne
	protected RGB Biome11Couleur = new RGB(214,218,213); //Montagne Enneige
	protected RGB Biome12Couleur = new RGB(255,255,255); //Pic Enneige

	protected HashMap<String, Biome> tableauAllBiome;

	public FiltreBiome() {
		//--creation des valeurs d'elevations et d'humidites pour les biomes--//
		HashMap<Integer[], Integer[]> maraisValeur = new HashMap<Integer[], Integer[]>();
		maraisValeur.put(new Integer[]{0,25}, new Integer[]{50,100});
		
		HashMap<Integer[], Integer[]> desertValeur = new HashMap<Integer[], Integer[]>();
		desertValeur.put(new Integer[]{0,52}, new Integer[]{0,25});
		
		HashMap<Integer[], Integer[]> plaineValeur = new HashMap<Integer[], Integer[]>();
		plaineValeur.put(new Integer[]{0,25}, new Integer[]{25,50});
		plaineValeur.put(new Integer[]{25,50}, new Integer[]{25,45});
		
		HashMap<Integer[], Integer[]> jungleValeur = new HashMap<Integer[], Integer[]>();
		jungleValeur.put(new Integer[]{25,50}, new Integer[]{70,100});
		
		HashMap<Integer[], Integer[]> foretValeur = new HashMap<Integer[], Integer[]>();
		foretValeur.put(new Integer[]{25,50}, new Integer[]{60,100});
		foretValeur.put(new Integer[]{50,52}, new Integer[]{70,100});
		foretValeur.put(new Integer[]{52,70}, new Integer[]{35,70});
		
		HashMap<Integer[], Integer[]> prairieValeur = new HashMap<Integer[], Integer[]>();
		prairieValeur.put(new Integer[]{25,50}, new Integer[]{45,60});
		prairieValeur.put(new Integer[]{50,52}, new Integer[]{25,70});
		
		HashMap<Integer[], Integer[]> toundraValeur = new HashMap<Integer[], Integer[]>();
		toundraValeur.put(new Integer[]{52,70}, new Integer[]{60,100});
		toundraValeur.put(new Integer[]{70,85}, new Integer[]{50,100});
		
		HashMap<Integer[], Integer[]> desertMontagneuxValeur = new HashMap<Integer[], Integer[]>();
		desertMontagneuxValeur.put(new Integer[]{50,70}, new Integer[]{0,20});
		
		HashMap<Integer[], Integer[]> montagneValeur = new HashMap<Integer[], Integer[]>();
		montagneValeur.put(new Integer[]{50,70}, new Integer[]{20,35});
		
		HashMap<Integer[], Integer[]> hauteMontagneValeur = new HashMap<Integer[], Integer[]>();
		hauteMontagneValeur.put(new Integer[]{70,85}, new Integer[]{0,50});
		
		HashMap<Integer[], Integer[]> montagneEnneigeValeur = new HashMap<Integer[], Integer[]>();
		montagneEnneigeValeur.put(new Integer[]{70,85}, new Integer[]{70,100});
		
		HashMap<Integer[], Integer[]> picEnneigeValeur = new HashMap<Integer[], Integer[]>();
		picEnneigeValeur.put(new Integer[]{85,100}, new Integer[]{0,100});
		
		//--Ajout des Biomes dans le tableau--//
		this.tableauAllBiome = new HashMap<String, Biome>();
		this.tableauAllBiome.put("Biome1", new Biome("Marais", this.Biome1Couleur, maraisValeur));
		this.tableauAllBiome.put("Biome2", new Biome("Desert", this.Biome2Couleur, desertValeur));
		this.tableauAllBiome.put("Biome3", new Biome("Plaine", this.Biome3Couleur, plaineValeur));
		this.tableauAllBiome.put("Biome4", new Biome("Jungle", this.Biome4Couleur, jungleValeur));
		this.tableauAllBiome.put("Biome5", new Biome("Foret", this.Biome5Couleur, foretValeur));
		this.tableauAllBiome.put("Biome6", new Biome("Prairie", this.Biome6Couleur, prairieValeur));
		this.tableauAllBiome.put("Biome7", new Biome("Toundra", this.Biome7Couleur, toundraValeur));
		this.tableauAllBiome.put("Biome8", new Biome("Desert Montagneux", this.Biome8Couleur, desertMontagneuxValeur));
		this.tableauAllBiome.put("Biome9", new Biome("Montagne", this.Biome9Couleur, montagneValeur));
		this.tableauAllBiome.put("Biome10", new Biome("Haute Montagne", this.Biome10Couleur, hauteMontagneValeur));
		this.tableauAllBiome.put("Biome11", new Biome("Montagne Enneige", this.Biome11Couleur, montagneEnneigeValeur));
		this.tableauAllBiome.put("Biome12", new Biome("Pic Enneige", this.Biome12Couleur, picEnneigeValeur));
	}
	
	public HashMap<String, Biome> getColorationBiome(){
		return this.tableauAllBiome;
	}
}
