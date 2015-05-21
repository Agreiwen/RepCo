package repCo.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import repCo.controleur.EcouteurBoutonLabyrinthe;
import repCo.modele.Carte.TypeMap;
import repCo.modele.Labyrinthe.Filtre;
import repCo.modele.Modele;

public class VueLabyrinthe extends JPanel implements Observer{
	
	protected Modele m;
	protected JButton jButtonPassage;
	protected JButton jButtonMur;
	protected JButton jButtonDepart;
	protected JButton jButtonArrivee;
	
	protected ImageIcon iconPassage = new ImageIcon(VueMenu.class.getResource("/repCo/folder/passage.png"));
	protected ImageIcon iconMur = new ImageIcon(VueMenu.class.getResource("/repCo/folder/mur.png"));
	protected ImageIcon iconDepart = new ImageIcon(VueMenu.class.getResource("/repCo/folder/depart.png"));
	protected ImageIcon iconArrivee = new ImageIcon(VueMenu.class.getResource("/repCo/folder/arrivee.png"));
	protected ImageIcon iconHistorique = new ImageIcon(VueMenu.class.getResource("/repCo/folder/historique.png"));
	protected ImageIcon iconChemin = new ImageIcon(VueMenu.class.getResource("/repCo/folder/chemin.png"));

	public VueLabyrinthe(Modele mod) {
		// TODO Auto-generated constructor stub
		super();
		this.m = mod;
		m.addObserver(this);
		this.setPreferredSize(new Dimension(600,600));
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(m.isInitialise()){
            this.setLayout(new GridLayout(m.getHauteur(), m.getLargeur(), 0, 0));
            m.setInitialise(false);
        }
		this.removeAll();
        for(int i = 0; i < m.getHauteur(); i++){
            for(int j = 0; j < m.getLargeur(); j++){
            	if(m.getTableauFiltre()[i][j] == Filtre.CHEMIN && (m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.ARRIVEE && m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.DEPART && m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.MUR )){
            		JButton chemin = new JButton();
            		chemin.setIcon(iconChemin);
            		chemin.setContentAreaFilled(false);
            		chemin.setFocusPainted(false);
            		chemin.addActionListener(new EcouteurBoutonLabyrinthe(m, i, j));
            		this.add(chemin);
            	}else if(m.getTableauFiltre()[i][j] == Filtre.HISTORIQUE && (m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.ARRIVEE && m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.DEPART && m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.MUR)){
            		JButton historique = new JButton();
            		historique.setIcon(iconHistorique);
            		historique.setContentAreaFilled(false);
            		historique.setFocusPainted(false);
            		historique.addActionListener(new EcouteurBoutonLabyrinthe(m, i, j));
            		this.add(historique);
            	}else{
            		if(m.getLabyrinthe().getJeu()[i][j].getTypeMap() == TypeMap.MUR){
            			jButtonMur = new JButton();
            			jButtonMur.setIcon(iconMur);
            			jButtonMur.setContentAreaFilled(false);
            			jButtonMur.setFocusPainted(false);
            			jButtonMur.addActionListener(new EcouteurBoutonLabyrinthe(m, i, j));
                		this.add(jButtonMur);
                	}else if(m.getLabyrinthe().getJeu()[i][j].getTypeMap() == TypeMap.PASSAGE){
                		jButtonPassage = new JButton();
                		jButtonPassage.setIcon(iconPassage);
                		jButtonPassage.setContentAreaFilled(false);
                		jButtonPassage.setFocusPainted(false);
                		jButtonPassage.addActionListener(new EcouteurBoutonLabyrinthe(m, i, j));
                		this.add(jButtonPassage);
                	}else if(m.getLabyrinthe().getJeu()[i][j].getTypeMap() == TypeMap.DEPART){
                		jButtonDepart = new JButton();
                		jButtonDepart.setIcon(iconDepart);
                		jButtonDepart.setContentAreaFilled(false);
                		jButtonDepart.setFocusPainted(false);
                		jButtonDepart.addActionListener(new EcouteurBoutonLabyrinthe(m, i, j));
                		this.add(jButtonDepart);
                	}else if(m.getLabyrinthe().getJeu()[i][j].getTypeMap() == TypeMap.ARRIVEE){
                		jButtonArrivee = new JButton();
                		jButtonArrivee.setIcon(iconArrivee);
                		jButtonArrivee.setContentAreaFilled(false);
                		jButtonArrivee.setFocusPainted(false);
                		jButtonArrivee.addActionListener(new EcouteurBoutonLabyrinthe(m, i, j));
                		this.add(jButtonArrivee);
                	}
            	}
            	
            }
        }
        revalidate();
        //repaint(); Pour les PaintComponent
    }
}
