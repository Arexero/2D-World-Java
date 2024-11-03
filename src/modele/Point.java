package modele;

import java.awt.Color;

public class Point {
	
	protected Color color;
	protected double elevation;
	protected double humidite;

	public Point(Color color, double elevation) {
		this.color = color;
		this.elevation = elevation;
	}
	
	public Point(Color color, double elevation, double humidite) {
		this.color = color;
		this.elevation = elevation;
		this.humidite = humidite;
	}

	//--Couleur-//
	public Color getColor() {
		return this.color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	//--Elevation--//
	public double getElevation() {
		return this.elevation;
	}
	public void setElevation(double elevation) {
		this.elevation = elevation;
	}
	
}
