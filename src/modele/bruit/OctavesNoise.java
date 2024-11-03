package modele.bruit;

public class OctavesNoise implements Bruit{
	
	
	
	/*protected long seed;
	protected FastNoiseLite noise;*/
	protected Bruit noise;
	protected double emplitude;

	public OctavesNoise(long seed ,Bruit bruit) {
		this.noise = bruit;
		this.emplitude=3;
	}
	
	public OctavesNoise(Bruit bruit){
		this.noise = bruit;
		this.emplitude=10;
	}

	@Override
	public double getNoise(float nx, float ny) {
		//renvoie le bruit tout en additionnant plusieurs bruits pour en obtenir un plus reel
		
		double diviserPar = 0;
		double multiple = 1;
		double e=0;
		 
		//Debut de la fonction Octave
		
		// Initialisation de la fonction
		 double lastMultiple=1;
		 e+= multiple*this.noise.getNoise((float)lastMultiple*nx,(float)lastMultiple*ny);
		 diviserPar+=multiple;
		 multiple=multiple/2;
		// Deroulage de la fonction Octave
		 for(int i =1 ;i<this.emplitude;i++) {
		  	lastMultiple=lastMultiple*2;
		  	e+= multiple*this.noise.getNoise((float)lastMultiple*nx,(float)lastMultiple*ny);
			diviserPar+=multiple;
			multiple=multiple/2;
		 }
		//Fin de la fonction Octave
		 
		return e/diviserPar;//la division permet de remettre la valeur entre 0 et 1
	}
	
	
	@Override
	public long getSeed() {
		return this.noise.getSeed();
	}

	@Override
	public void setSeed(long seed) {
		this.noise.setSeed(seed);
	}

	@Override
	public void setFrequency(float freq) {
		this.noise.setFrequency(freq);
	}

	@Override
	public float getFrequency() {
		return this.noise.getFrequency();
	}

}
