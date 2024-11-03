package modele;

import java.util.HashMap;

import modele.couleurs.RGB;

public class Biome {
	
	protected String name;
	protected RGB couleur;
	protected HashMap<Integer[], Integer[]> elevationHumidite;

	public Biome(String name, RGB couleur, HashMap<Integer[], Integer[]> elevationHumidite) {
		this.name = name;
		this.couleur = couleur;
		this.elevationHumidite = elevationHumidite;
	}
	
	public Biome(String name, RGB couleur) {
		this.name = name;
		this.couleur = couleur;
	}

	//--Nom--//
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	//--Couleur--//
	public RGB getCouleur() {
		return couleur;
	}
	public void setCouleur(RGB couleur) {
		this.couleur = couleur;
	}

	//--Elevation et Humidite--//
	public HashMap<Integer[], Integer[]> getElevationHumidite() {
		return elevationHumidite;
	}
	public void setElevationHumidite(HashMap<Integer[], Integer[]> elevationHumidite) {
		this.elevationHumidite = elevationHumidite;
	}
	
}
