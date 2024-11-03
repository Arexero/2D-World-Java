package modele.bruit;

import java.util.Random;

public class Noise implements Bruit{
	
	protected Random random;
	protected long seed;

	public Noise(long seed) {
		this.seed = seed;
		this.random = new Random();
	}
	
	public Noise() {
		this.seed = System.currentTimeMillis();
		this.random = new Random();
	}

	public double getNoise(float nx, float ny) {
		double randomDouble = random.nextDouble();
		return randomDouble;
	}
	
	@Override
	public void setSeed(long seed) {
		this.random.setSeed(seed);
	}
	
	

	@Override
	public long getSeed() {
		return this.seed;
	}

	@Override
	public void setFrequency(float freq) {
	
	}

	@Override
	public float getFrequency() {
		return 0;	
	}
}
