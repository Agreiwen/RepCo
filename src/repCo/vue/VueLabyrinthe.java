package repCo.vue;

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

@SuppressWarnings("serial")
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
	
	protected JButton[][] tabButton;

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
			this.removeAll();
            this.setLayout(new GridLayout(m.getHauteur(), m.getLargeur(), 0, 0));
            tabButton = new JButton[m.getHauteur()][m.getLargeur()];
            for (int i = 0; i < tabButton.length; i++) {
				for (int j = 0; j < tabButton[0].length; j++) {
					tabButton[i][j] = new JButton();
					this.add(tabButton[i][j]);
					tabButton[i][j].addActionListener(new EcouteurBoutonLabyrinthe(m, i, j));
					tabButton[i][j].setContentAreaFilled(false);
            		tabButton[i][j].setFocusPainted(false);
				}
			}
            m.setInitialise(false);
        }
        for(int i = 0; i < m.getHauteur(); i++){
            for(int j = 0; j < m.getLargeur(); j++){
            	if(m.getTableauFiltre()[i][j] == Filtre.CHEMIN && (m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.ARRIVEE && m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.DEPART && m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.MUR )){
            		// Chemin
            		tabButton[i][j].setIcon(iconChemin);
            	}else if(m.getTableauFiltre()[i][j] == Filtre.HISTORIQUE && (m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.ARRIVEE && m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.DEPART && m.getLabyrinthe().getJeu()[i][j].getTypeMap() != TypeMap.MUR)){
            		tabButton[i][j].setIcon(iconHistorique);
            	}else{
            		if(m.getLabyrinthe().getJeu()[i][j].getTypeMap() == TypeMap.MUR){
            			tabButton[i][j].setIcon(iconMur);
                	}else if(m.getLabyrinthe().getJeu()[i][j].getTypeMap() == TypeMap.PASSAGE){
                		tabButton[i][j].setIcon(iconPassage);
                	}else if(m.getLabyrinthe().getJeu()[i][j].getTypeMap() == TypeMap.DEPART){
                		tabButton[i][j].setIcon(iconDepart);
                	}else if(m.getLabyrinthe().getJeu()[i][j].getTypeMap() == TypeMap.ARRIVEE){
                		tabButton[i][j].setIcon(iconArrivee);
                	}
            	}
            }
        }
        revalidate();
        //repaint(); Pour les PaintComponent
    }
}
