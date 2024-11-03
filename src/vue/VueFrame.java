package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.ModelMiseAJour;
import modele.Generation;

public class VueFrame extends JFrame implements ModelMiseAJour,Action{
	
	private VueCarte vueCarte;
	private Generation generation;
	private VueCarteStrat vueCarteStrat;
	private VueButton vueButton;
	private JPanel vueHome;
	private boolean etatFrame = true; // Permet de savoir si nous sommes sur la vue d'accueil ou la vue de modélisation
	private GridBagConstraints constraint;

	public VueFrame(Generation generation, VueCarteStrat vueCarteStrat) {
		int[] tailleCarte = new int[]{900,900};
		this.vueCarte = new VueCarte(/*generation,this.vueCarteStrat*/);
		this.vueHome = new VueHome(this, generation, vueCarteStrat, tailleCarte, this.vueCarte);
		this.vueCarteStrat = vueCarteStrat;
		this.generation = generation;
		this.generation.ajoutEcouteur(this);
		/*Taille de la fenetre et ses dimenssion non modifiable*/
	    this.setSize(1700,710);
	    this.setResizable(false);

	    //this.pack();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	 // Initialisation d'un layout
	    this.setLayout(new GridBagLayout());
	    this.constraint =new GridBagConstraints();
	    this.constraint.fill = GridBagConstraints.BOTH;
	    this.constraint.weightx = 1;
	    this.constraint.weighty = 1;

	    // Positionnement du panel représentant le home dans le layout
	    this.constraint.gridwidth = 1;
	    this.constraint.gridx = 0;
	    this.constraint.gridy=0;
	    this.add(this.vueHome,this.constraint);

	    this.vueHome.addKeyListener((KeyListener) this.vueHome);
	    this.vueHome.setFocusable(true);
	    /*On rend la frame visible et on lui demande de s'eteindre lorsque l'on quitte*/
	    this.repaint();
	    this.setVisible(true);
	    
	}
	
	public void swap() {
		this.vueButton = new VueButton(generation, this.vueCarte, this.vueCarte.getStrategieName());
		if(this.etatFrame) {
			this.remove(this.vueHome);
			// Positionnement du panel représentant la carte dans le layout
		    this.constraint.gridwidth = 1;
		    this.constraint.gridx = 0;
		    this.constraint.gridy=0;
		    this.add(this.vueCarte,this.constraint);
		    
		 // Positionnement du panel représentant des boutons dans le layout
		    this.constraint.gridwidth = 3;
		    this.constraint.gridx = 3;
		    this.constraint.gridy=0;
		    this.add(this.vueButton,this.constraint);
		    this.vueButton.setFocusable(true);
		    this.etatFrame = false;
		    this.revalidate();
			this.repaint();
			this.vueButton.run();
		}else {
			this.remove(this.vueButton);
			this.remove(this.vueCarte);
			 // Positionnement du panel représentant le home dans le layout
		    this.constraint.gridwidth = 4;
		    this.constraint.gridx = 0;
		    this.constraint.gridy=0;
		    this.add(this.vueHome,constraint);
			this.etatFrame = true;
			this.revalidate();
			this.repaint();
		}
	}
	
	@Override
	public void modelMiseAJour(Object source) {
		this.repaint();
		
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
