package modele.couleurs;

public class RGB {
	protected int red;
	protected int green;
	protected int blue;
	
	public RGB(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	//--Rouge--//
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}

	//--Vert--//
	public int getGreen() {
		return green;
	}
	public void setGreen(int green) {
		this.green = green;
	}

	//--Bleu--//
	public int getBlue() {
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	public boolean isSameColor(RGB couleur) {
		if(this.red == couleur.getRed() && this.green == couleur.getGreen() && this.blue == couleur.getBlue()) {
			return true;
		}
		return false;
	}
	

}
