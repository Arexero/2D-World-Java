package modele.bruit;

public interface Bruit {
	public double getNoise(float nx, float ny);
	public void setFrequency(float freq);
	public float getFrequency();
	public void setSeed(long seed);
	public long getSeed();
}
