package modele.bruit;

public class PerlinNoise implements Bruit{
	
	protected long seed;
	protected FastNoiseLite noise;

	public PerlinNoise(long seed) {
		this.seed = seed;
		this.noise = new FastNoiseLite((int)this.seed);
		this.noise.SetNoiseType(FastNoiseLite.NoiseType.Perlin);
	}
	

	public double getNoise(float nx, float ny) {
		//this.noise.GetNoise(x,y) => retourne des valeur entre -1 et 1
		
		return (this.noise.GetNoise(nx, ny)+1)/2; // permet de renvoyer des valeur entre 0 et 1
	}
	
	
	
	@Override
	public long getSeed() {
		return this.seed;
	}

	@Override
	public void setSeed(long seed) {
		this.noise.SetSeed((int)seed);
	}

	@Override
	public void setFrequency(float freq) {
		this.noise.SetFrequency(freq);
	}

	@Override
	public float getFrequency() {
		return this.noise.getFrequency();
		
	}

}
