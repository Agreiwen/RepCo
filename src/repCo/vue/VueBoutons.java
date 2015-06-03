package repCo.vue;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import repCo.modele.Modele;

@SuppressWarnings("serial")
public class VueBoutons extends JPanel implements Observer{
	
	protected Modele m;
	protected JLabel JLabelLargeur;
	protected JLabel JLabelHauteur;
	protected JLabel JLabelTemps;
	protected JLabel JLabelEspace;
	protected JLabel JLabelEtats;

	public VueBoutons(Modele mod) {
		// TODO Auto-generated constructor stub
		super();
		this.m = mod;
		m.addObserver(this);
		
		GridLayout grille = new GridLayout(2, 5);
		this.setLayout(grille);
		
		//Ligne 1
		JLabelEspace = new JLabel();
		this.add(JLabelEspace);
		
		JLabelHauteur = new JLabel();
		this.add(JLabelHauteur);
		
		JLabelEspace = new JLabel();
		this.add(JLabelEspace);
		
		JLabelTemps = new JLabel();
		this.add(JLabelTemps);
		
		JLabelEspace = new JLabel();
		this.add(JLabelEspace);
		
		//Ligne 2
		
		JLabelEspace = new JLabel();
		this.add(JLabelEspace);
		
		JLabelLargeur = new JLabel();
		this.add(JLabelLargeur);
		
		JLabelEspace = new JLabel();
		this.add(JLabelEspace);
		
		JLabelEtats = new JLabel();
		this.add(JLabelEtats);
		
		JLabelEspace = new JLabel();
		this.add(JLabelEspace);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		JLabelLargeur.setText("Largeur : "+m.getLargeur());
		JLabelHauteur.setText("Hauteur : "+m.getHauteur());
		JLabelTemps.setText("Temps : "+m.getTemps());
		JLabelEtats.setText("Etats : "+m.getTailleHistorique());
	}

}