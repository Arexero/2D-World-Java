package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import modele.Generation;
import modele.GenerationBiome;
import modele.GenerationElevation;
import modele.bruit.OctavesNoise;
import modele.bruit.PerlinNoise;
import modele.bruit.SimplexNoise;
import modele.couleurs.FiltreBiome;
import modele.couleurs.FiltreCouleur;
import modele.couleurs.FiltreNoirBlanc;

public class VueHome extends JPanel implements Action, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private JTextField textSeedField=  new JTextField();
	private VueFrame frame;
	private JLabel labelSeed = new JLabel();
	private JComboBox<String> boxVueCarte;
	private JComboBox<String> boxBruit;
	private JComboBox<String> boxGeneration;
	private JLabel labelNiveauEau = new JLabel();
	private JTextField textNiveauEau =  new JTextField();
	private JLabel labelVueCarte;
	private JLabel labelBruit;
	private JLabel labelGeneration;
	private JButton valide = new JButton();
	
	protected Generation generation;
	protected VueCarteStrat vueCarteStrat;
	protected int[] tailleCarte;
	protected VueCarte vueCarte;
	protected String actionVueCarte = "Default";
	protected String actionBruit = "Default";
	protected String actionGeneration = "Default";

	public VueHome(VueFrame frame, Generation generation, VueCarteStrat vueCarteStrat, int[] tailleCarte, VueCarte vueCarte) {
		this.frame = frame;
		this.generation = generation;
		this.vueCarteStrat = vueCarteStrat;
		this.tailleCarte = tailleCarte;
		this.vueCarte = vueCarte;
		this.setLayout(null);
		
		this.valide.addActionListener(this);
		
		this.labelSeed = new JLabel("Entrer votre graine       ------>");
	    this.labelSeed.setBounds(100,250,250,20);
	    this.labelSeed.setHorizontalAlignment(SwingConstants.CENTER);
	    this.add(this.labelSeed);
	    
	    this.textSeedField.setBounds(350,250,400,20);
	    this.textSeedField.setHorizontalAlignment(SwingConstants.CENTER);
	    this.textSeedField.setBackground(new Color(32,30,32));
	    this.textSeedField.setForeground(new Color(255,255,255));
	    this.add(this.textSeedField);
	    
	    //----Mise en place d'une liste a choix----//
	    //--Vue de la Carte--//
	    //Titre
	    this.labelVueCarte = new JLabel("Visualisation de la carte");
	    this.labelVueCarte.setBounds(200, 100, 200, 30);
	    this.labelVueCarte.setOpaque(true);
	    this.labelVueCarte.setHorizontalAlignment(SwingConstants.CENTER);
	    this.labelVueCarte.setBackground(new Color(32,30,32));
	    this.labelVueCarte.setForeground(new Color(150,150,150));
	    this.labelVueCarte.setFont(new Font("Sans-serif", Font.BOLD, 16));
	    this.add(this.labelVueCarte);
	    //Liste
	    Object[] elementsVueCarte = new Object[] {"Default", "Vue de la carte en 2D", "VoxelSpace"};
	    this.boxVueCarte = new JComboBox(elementsVueCarte);
	    this.boxVueCarte.setBounds(200, 140, 200, 30);
	    this.boxVueCarte.addActionListener(this);
	    this.boxVueCarte.setBackground(new Color(32,30,32));
	    this.boxVueCarte.setForeground(new Color(150,150,150));
	    this.add(boxVueCarte);
	    
	    //--Bruit--//
	    //Titre
	    this.labelBruit = new JLabel("Type de bruit");
	    this.labelBruit.setBounds(500, 100, 200, 30);
	    this.labelBruit.setOpaque(true);
	    this.labelBruit.setHorizontalAlignment(SwingConstants.CENTER);
	    this.labelBruit.setBackground(new Color(32,30,32));
	    this.labelBruit.setForeground(new Color(150,150,150));
	    this.labelBruit.setFont(new Font("Sans-serif", Font.BOLD, 16));
	    this.add(this.labelBruit);
	    //Liste
	    Object[] elementsBruit = new Object[] {"Default", "Simplex", "Simplex (Avec Octaves)", "Perlin", "Perlin (Avec Octaves)"};
	    this.boxBruit = new JComboBox(elementsBruit);
	    this.boxBruit.setBounds(500, 140, 200, 30);
	    this.boxBruit.addActionListener(this);
	    this.boxBruit.setBackground(new Color(32,30,32));
	    this.boxBruit.setForeground(new Color(150,150,150));
	    this.add(boxBruit);
	    
	    //--Generation--//
	    //Titre
	    this.labelGeneration = new JLabel("Type de G\u00e9n\u00e9ration");
	    this.labelGeneration.setBounds(800, 100, 200, 30);
	    this.labelGeneration.setOpaque(true);
	    this.labelGeneration.setHorizontalAlignment(SwingConstants.CENTER);
	    this.labelGeneration.setBackground(new Color(32,30,32));
	    this.labelGeneration.setForeground(new Color(150,150,150));
	    this.labelGeneration.setFont(new Font("Sans-serif", Font.BOLD, 16));
	    this.add(this.labelGeneration);
	    //Liste
	    Object[] elementsGeneration = new Object[] {"Default", "Seulement l'\u00e9l\u00e9vation", "El\u00e9vation en couleur", "Plusieurs biomes"};
	    this.boxGeneration = new JComboBox(elementsGeneration);
	    this.boxGeneration.setBounds(800, 140, 200, 30);
	    this.boxGeneration.addActionListener(this);
	    this.boxGeneration.setBackground(new Color(32,30,32));
	    this.boxGeneration.setForeground(new Color(150,150,150));
	    this.add(boxGeneration);
	    
	    //----Choix niveau de l'eau----//
		this.labelNiveauEau = new JLabel("Niveau de l'eau :");
	    this.labelNiveauEau.setBounds(500,300,150,20);
	    this.labelNiveauEau.setHorizontalAlignment(SwingConstants.CENTER);
	    this.labelNiveauEau.setBackground(new Color(32,30,32));
	    this.labelNiveauEau.setForeground(new Color(150,150,150));
	    this.labelNiveauEau.setOpaque(true);
	    this.add(this.labelNiveauEau);
	    
	    this.textNiveauEau.setBounds(650,300,50,20);
	    this.textNiveauEau.setHorizontalAlignment(SwingConstants.CENTER);
	    this.textNiveauEau.setBackground(new Color(32,30,32));
	    this.textNiveauEau.setForeground(new Color(255,255,255));
	    this.add(this.textNiveauEau);
	    
	    this.valide.setBounds(1050,535,96,96);
		this.valide.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/valider.png")).getImage().getScaledInstance(96, 96, Image.SCALE_DEFAULT)));
		this.add(this.valide);
		this.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	  public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image back = new ImageIcon(getClass().getClassLoader().getResource("images/image_home.jpg")).getImage();
		g.drawImage(back,0,0,null);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		long seed = System.currentTimeMillis();
		double niveauEau = 0.2;
		
		if(e.getSource().equals(this.boxVueCarte)) {
			this.actionVueCarte = (String) this.boxVueCarte.getSelectedItem();
		}
		if(e.getSource().equals(this.boxBruit)) {
			this.actionBruit = (String) this.boxBruit.getSelectedItem();
		}
		if(e.getSource().equals(this.boxGeneration)) {
			this.actionGeneration = (String) this.boxGeneration.getSelectedItem();
		}
		if(e.getSource().equals(this.valide)) {
			//--Seed--//
			if(!this.textSeedField.getText().equals("")) {
				seed = Long.parseLong(this.textSeedField.getText());
			}
			if(this.textNiveauEau.getText().matches("[0-9]{1,13}(\\.[0-9]*)?") && Double.parseDouble(this.textNiveauEau.getText()) <= 1) {
				//[0-9]{1,13}(\\.[0-9]*)? => ne comprend que les chiffres a virgules ou int
				niveauEau = Double.parseDouble(this.textNiveauEau.getText());
			}
			
			//--Vue de la Carte--//
			switch(this.actionVueCarte) {
				case "Default":
					this.vueCarteStrat = new VueCarte2D();
					break;
				case "Vue de la carte en 2D":
					this.vueCarteStrat = new VueCarte2D();
					break;
				case "VoxelSpace":
					this.vueCarteStrat = new VueCarteVoxel();
					break;
			}
			
			//--Bruit--//
			switch(this.actionBruit) {
				case "Default":
					this.generation.setBruit(new OctavesNoise(new SimplexNoise(seed)));
					break;
				case "Simplex":
					this.generation.setBruit(new SimplexNoise(seed));
					break;
				case "Simplex (Avec Octaves)":
					this.generation.setBruit(new OctavesNoise(new SimplexNoise(seed)));
					break;
				case "Perlin":
					this.generation.setBruit(new PerlinNoise(seed));
					break;
				case "Perlin (Avec Octaves)":
					this.generation.setBruit(new OctavesNoise(new PerlinNoise(seed)));
					break;
			}
			
			//--Generation--//
			switch(this.actionGeneration) {
			case "Default":
				this.generation.setFiltre(new FiltreBiome());
				this.generation.setGenerationCarte(new GenerationBiome(this.tailleCarte, niveauEau));
				break;
			case "Seulement l'\u00e9l\u00e9vation":
				this.generation.setFiltre(new FiltreNoirBlanc());
				this.generation.setGenerationCarte(new GenerationElevation(this.tailleCarte, 0));
				break;
			case "El\u00e9vation en couleur":
				this.generation.setFiltre(new FiltreCouleur());
				this.generation.setGenerationCarte(new GenerationElevation(this.tailleCarte, niveauEau));
				break;
			case "Plusieurs biomes":
				this.generation.setFiltre(new FiltreBiome());
				this.generation.setGenerationCarte(new GenerationBiome(this.tailleCarte, niveauEau));
				break;
			}
		this.generation.createCarteOrigine(0.01f);
		this.vueCarte.setVueCarte(this.generation, this.vueCarteStrat);
		this.frame.swap();
		}
		/*switch(((JButton)e.getSource()).getName()) {
		case "valide":
			if(!this.textSeedField.getText().equals("")) {
				this.generation.getBruit().setSeed(Long.parseLong(this.textSeedField.getText()));
			}
			this.frame.swap();
			break;
		case "Random":
			this.frame.swap();
			break;
		}*/
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

}
