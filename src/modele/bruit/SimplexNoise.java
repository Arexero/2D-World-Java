package modele.bruit;

public class SimplexNoise implements Bruit{
	
	protected long seed;
	protected FastNoiseLite noise;

	public SimplexNoise(long seed) {
		this.seed = seed;
		//instanciation de la lib qui nous permet de generer un bruit
		this.noise = new FastNoiseLite((int)this.seed);
		this.noise.SetFrequency(0.04f);
		this.noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
	}
	
	@Override
	public double getNoise(float nx, float ny) {

		float bruitObtenu = (float) (this.noise.GetNoise(nx, ny)+1)/2;
		
		return bruitObtenu;
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
