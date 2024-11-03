package modele.bruit;

public class RedistributionNoise implements Bruit{

	
	protected OctavesNoise noiseOctave;
	protected double valueRedistribution;//coef multiplicateur de 0.01 a 10.00

	public RedistributionNoise(long seed,Bruit bruit) {
		this.noiseOctave=new OctavesNoise(seed,bruit);
		this.valueRedistribution = 1;
	}
	
	public RedistributionNoise(Bruit bruit) {		
		this.noiseOctave=new OctavesNoise(bruit);

		this.valueRedistribution = 1;
	}

	@Override
	public double getNoise(float nx, float ny) {
		double e = this.noiseOctave.getNoise(nx, ny);
		// aplication de la methode Redistribution qui conciste a mettre a la puissance le coef multiplicateur choisit
		return Math.pow( e, this.valueRedistribution);
	}
	
	@Override
	public void setSeed(long seed) {
		this.noiseOctave.setSeed(seed);
	}
	
	

	@Override
	public long getSeed() {
		// TODO Auto-generated method stub
		return this.noiseOctave.getSeed();
	}

	@Override
	public void setFrequency(float freq) {
		this.noiseOctave.setFrequency(freq);
	}

	@Override
	public float getFrequency() {
		return this.noiseOctave.getFrequency();
	}

}
