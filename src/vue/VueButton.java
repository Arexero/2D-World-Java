package vue;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import modele.Biome;
import modele.Camera;
import modele.Generation;

public class VueButton extends JPanel implements Action,KeyListener{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  JToggleButton haut = new JToggleButton();
	 private  JToggleButton bas = new JToggleButton();
	 private  JToggleButton gauche = new JToggleButton();
	 private  JToggleButton droite = new JToggleButton();
	 private  JToggleButton orientation_G_D = new JToggleButton();
	 private  JToggleButton orientation_D_G = new JToggleButton();
	 private  JToggleButton regarder_haut = new JToggleButton();
	 private  JToggleButton regarder_bas = new JToggleButton();
	 
	 
	 private ArrayList<JToggleButton> listJToggleBouton = new ArrayList<JToggleButton> ();
	 
	 private  JButton plus = new JButton();
	 private  JButton moins = new JButton();
	 private  JButton distance_plus = new JButton();
	 private  JButton distance_moins = new JButton();
	 
	 private  JToggleButton lever = new JToggleButton();
	 private  JToggleButton baisser = new JToggleButton();
	 
	 private Generation generation;
	 private VueCarte vueCarte;
	 private int moveCamCombien = 5;
	 private Thread tache;
	
	
	public VueButton(Generation generation, VueCarte vueCarte, String vueCarteStratChoisi) {
	
		this.generation = generation;
		this.vueCarte = vueCarte;
		if(vueCarteStratChoisi == "VueCarte2D") {
			this.moveCamCombien = 10;
		}
		this.setLayout(null);
		this.setBackground(new Color(32,30,32));
		this.haut.setName("haut");
		this.bas.setName("bas");
		this.gauche.setName("gauche");
		this.droite.setName("droite");
		this.plus.setName("plus");
		this.moins.setName("moins");
		this.distance_plus.setName("distance_plus");
		this.distance_moins.setName("distance_moins");
		
		this.lever.setName("lever");
		this.baisser.setName("baisser");
		
		this.orientation_G_D.setName("orientation_G_D");
		this.orientation_D_G.setName("orientation_D_G");
		this.regarder_haut.setName("regarder_haut");
		this.regarder_bas.setName("regarder_bas");
		
		this.listJToggleBouton.add(this.haut);
		this.listJToggleBouton.add(this.bas);
		this.listJToggleBouton.add(this.gauche);
		this.listJToggleBouton.add(this.droite);
		this.listJToggleBouton.add(this.lever);
		this.listJToggleBouton.add(this.baisser);
		this.listJToggleBouton.add(this.orientation_G_D);
		this.listJToggleBouton.add(this.orientation_D_G);
		this.listJToggleBouton.add(this.regarder_haut);
		this.listJToggleBouton.add(this.regarder_bas);
		
		this.haut.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/haut.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));
		this.bas.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/bas.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));
	    this.gauche.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/gauche.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));
	    this.droite.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/droite.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));
	    
	    this.plus.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/zoom.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));
	    this.moins.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/dezoom.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));
	    
	    this.lever.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/monter.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));
	    this.baisser.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/descendre.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));
	    
	    this.regarder_haut.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/regardHaut.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));
		this.regarder_bas.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/regardBas.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));
	    this.orientation_D_G.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/regardGauche.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));
	    this.orientation_G_D.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/regardDroite.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));
	    
	    this.distance_plus.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/voirPlus.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));
	    this.distance_moins.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("images/voirMoins.png")).getImage().getScaledInstance(98, 98, Image.SCALE_DEFAULT)));	    
	    
		
		this.haut.addActionListener(this);
		this.bas.addActionListener(this);
		this.gauche.addActionListener(this);
		this.droite.addActionListener(this);
		this.plus.addActionListener(this);
		this.moins.addActionListener(this);
		this.lever.addActionListener(this);
		this.baisser.addActionListener(this);
		this.orientation_G_D.addActionListener(this);
		this.orientation_D_G.addActionListener(this);
		this.regarder_haut.addActionListener(this);
		this.regarder_bas.addActionListener(this);
		this.distance_plus.addActionListener(this);
		this.distance_moins.addActionListener(this);
		
		
		this.haut.setBounds(138,330,98,98);
		this.bas.setBounds(138,530,98,98);
		this.gauche.setBounds(40,430,98,98);
		this.droite.setBounds(236,430,98,98);
		
		this.regarder_haut.setBounds(532,330,98,98);
		this.regarder_bas.setBounds(532,530,98,98);
		this.orientation_D_G.setBounds(434,430,98,98);
		this.orientation_G_D.setBounds(630,430,98,98);
		
		this.lever.setBounds(335,50,98,98);
		this.baisser.setBounds(335,188,98,98);
		
		this.plus.setBounds(40,110,98,98);
		this.moins.setBounds(178,110,98,98);

		this.distance_plus.setBounds(492,110,98,98);
		this.distance_moins.setBounds(630,110,98,98);
		
		this.add(this.haut);
		this.add(this.bas);
		this.add(this.gauche);
		this.add(this.droite);
		this.add(this.plus);
		this.add(this.moins);
		this.add(this.lever);
		this.add(this.baisser);
		this.add(this.orientation_D_G);
		this.add(this.orientation_G_D);
		this.add(this.regarder_haut);
		this.add(this.regarder_bas);
		this.add(this.distance_plus);
		this.add(this.distance_moins);
		this.repaint();
		this.addKeyListener(this);
		
		Runnable runnable =() -> { 
					while(!this.tache.isInterrupted()) {
						try {
							for(int i = 0 ; i<this.listJToggleBouton.size();i++) {
								if(this.listJToggleBouton.get(i).isSelected()) {
									execute(this.listJToggleBouton.get(i).getName());
								}
							}
							this.requestFocusInWindow();
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			};
		this.tache = new Thread(runnable);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if((e.getSource()).getClass() == this.plus.getClass()){
			execute(((JButton) (e.getSource())).getName());
		} else if((e.getSource()).getClass() == this.haut.getClass()) {
			switch(((JToggleButton)(e.getSource())).getName()) {
			case "haut" :
				this.bas.setSelected(false);
				break;
			case "bas" :
				this.haut.setSelected(false);
				break;
			case "gauche" :
				this.droite.setSelected(false);
				break;
			case "droite" :
				this.gauche.setSelected(false);
				break;
			}
		}
	}
	
	public void execute(String evenement) {
		//Camera posCam = this.vueCarte.getCam();
		//float freq = this.generation.getBruit().getFrequency();
		switch (evenement){
		case "plus" :
			//if(this.generation.getBruit().getFrequency()-0.005f>0.0f) {
			//this.generation.createCarte(freq-0.005f,this.generation.getPosCam());
			//}
			this.vueCarte.zoomCam(0.8f);
			break;
		case "moins" :
			//this.generation.createCarte(freq+0.005f,this.generation.getPosCam());
			this.vueCarte.deZoomCam(0.8f);
			break;
		///Inverser car seule moyen trouver pour l'instant
		case "droite" :
			this.vueCarte.moveCam(this.moveCamCombien,"droite");
			break;
		case "gauche" :
			this.vueCarte.moveCam(this.moveCamCombien,"gauche");
			break;
		case "haut" :
			this.vueCarte.moveCam(this.moveCamCombien,"haut");
			break;
		case "bas" :
			this.vueCarte.moveCam(this.moveCamCombien,"bas");
			break;
		case "lever":
			this.vueCarte.heigthCam(10);
			break;
		case "baisser":
			this.vueCarte.heigthCam(-10);
			break;
		case "orientation_G_D":
			this.vueCarte.moveOrientationCam(-0.07f);
			break;
		case "orientation_D_G":
			this.vueCarte.moveOrientationCam(0.07f);
			break;	
		case "regarder_haut":
			this.vueCarte.changeHorizonCam(10);
			break;
		case "regarder_bas":
			this.vueCarte.changeHorizonCam(-10);
			break;
		case "distance_plus":
			this.vueCarte.changeDistanceCam(10);
			break;
		case "distance_moins":
			this.vueCarte.changeDistanceCam(-10);
			break;
		}
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		String evenement=null;
		switch(e.getKeyCode()) {
			case KeyEvent.VK_A:
				evenement = "plus";
				break;
			case KeyEvent.VK_Z:
				evenement = "moins";
				break;
			case KeyEvent.VK_RIGHT :
				evenement = "Droite";
				this.droite.doClick();
				System.out.print(this.droite.isEnabled());
				break;
			case KeyEvent.VK_LEFT :
				evenement = "Gauche";
				break;
			case KeyEvent.VK_UP :
				evenement = "Haut";
				break;
			case KeyEvent.VK_DOWN :
				evenement = "Bas";
				break;
		}
		execute(evenement);
	}
	
	public void run() {
		//System.out.println("avant");
			this.tache.start();
		//System.out.println("apres");
	}
	          
	
	

	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void putValue(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
