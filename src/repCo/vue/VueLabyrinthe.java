package repCo.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

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

	public VueLabyrinthe(Modele mod) {
		// TODO Auto-generated constructor stub
		super();
		this.m = mod;
		m.addObserver(this);
		this.setPreferredSize(new Dimension(500,600));
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(m.isInitialise()){
            this.setLayout(new GridLayout(m.getHauteur(), m.getLargeur()));
            m.setInitialise(false);
        }
		this.removeAll();
        for(int i = 0; i < m.getHauteur(); i++){
            for(int j = 0; j < m.getLargeur(); j++){
            	if(m.getTableauFiltre()[i][j] == Filtre.CHEMIN && (m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.ARRIVEE && m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.DEPART && m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.MUR )){
            		JButton chemin = new JButton();
            		chemin.setBackground(Color.BLUE);
            		chemin.addActionListener(new EcouteurBoutonLabyrinthe(m, i, j));
            		this.add(chemin);
            	}else if(m.getTableauFiltre()[i][j] == Filtre.HISTORIQUE && (m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.ARRIVEE && m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.DEPART && m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.MUR)){
            		JButton historique = new JButton();
            		historique.setBackground(Color.magenta);
            		historique.addActionListener(new EcouteurBoutonLabyrinthe(m, i, j));
            		this.add(historique);
            	}else{
            		if(m.getLabyrinthe().getJeu()[i][j].getTypeMap() == TypeMap.MUR){
                		JButton mur = new JButton();
                		mur.setBackground(Color.BLACK);
                		mur.addActionListener(new EcouteurBoutonLabyrinthe(m, i, j));
                		this.add(mur);
                	}else if(m.getLabyrinthe().getJeu()[i][j].getTypeMap() == TypeMap.PASSAGE){
                		JButton passage = new JButton();
                		passage.setBackground(Color.WHITE);
                		passage.addActionListener(new EcouteurBoutonLabyrinthe(m, i, j));
                		this.add(passage);
                	}else if(m.getLabyrinthe().getJeu()[i][j].getTypeMap() == TypeMap.DEPART){
                		JButton passage = new JButton();
                		passage.setBackground(Color.RED);
                		passage.addActionListener(new EcouteurBoutonLabyrinthe(m, i, j));
                		this.add(passage);
                	}else if(m.getLabyrinthe().getJeu()[i][j].getTypeMap() == TypeMap.ARRIVEE){
                		JButton passage = new JButton();
                		passage.setBackground(Color.GREEN);
                		passage.addActionListener(new EcouteurBoutonLabyrinthe(m, i, j));
                		this.add(passage);
                	}
            	}
            	
            }
        }
        revalidate();
        //repaint(); Pour les PaintComponent
    }
}
