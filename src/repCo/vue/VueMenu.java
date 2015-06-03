package repCo.vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import repCo.controleur.EcouteurAlgoAStar;
import repCo.controleur.EcouteurAlgoLargeur;
import repCo.controleur.EcouteurAlgoProfondeur;
import repCo.controleur.EcouteurAnnuler;
import repCo.controleur.EcouteurMenuArrivee;
import repCo.controleur.EcouteurMenuDepart;
import repCo.controleur.EcouteurMenuMur;
import repCo.controleur.EcouteurMenuPassage;
import repCo.controleur.EcouteurNouveau;
import repCo.controleur.EcouteurQuitter;
import repCo.modele.Modele;

@SuppressWarnings("serial")
public class VueMenu extends JMenuBar implements Observer{
	
	protected Modele m;
	
	protected JMenu jMenuFichier;
	protected JMenu jMenuConstruire;
	protected JMenu jMenuAlgo;
	
	protected JMenuItem jMenuItemNouveau;
	protected JMenuItem jMenuItemQuitter;
	protected JMenuItem jMenuItemAnnuler;
	
	protected JMenuItem jMenuItemMur;
	protected JMenuItem jMenuItemPassage;
	
	protected JMenuItem jMenuItemDepart;
	protected JMenuItem jMenuItemArrivee;
	
	protected JMenuItem jMenuItemLargeur;
	protected JMenuItem jMenuItemProfondeur;
	protected JMenuItem jMenuItemAStar;

	public VueMenu(Modele mod) {
		// TODO Auto-generated constructor stub
		super();
		this.m = mod;
		m.addObserver(this);
		
		jMenuFichier = new JMenu("Fichier");
		jMenuConstruire = new JMenu("Construire");
		jMenuAlgo = new JMenu("Algo");
		
		jMenuItemNouveau = new JMenuItem("Nouveau");
		jMenuItemNouveau.addActionListener(new EcouteurNouveau(m));
		
		jMenuItemQuitter = new JMenuItem("Quitter");
		jMenuItemQuitter.addActionListener(new EcouteurQuitter(m));
		
		jMenuItemAnnuler = new JMenuItem("Annuler");
		jMenuItemAnnuler.addActionListener(new EcouteurAnnuler(m));
		
		jMenuItemMur = new JMenuItem("Mur");
		jMenuItemMur.addActionListener(new EcouteurMenuMur(m));
		
		jMenuItemPassage = new JMenuItem("Passage");
		jMenuItemPassage.addActionListener(new EcouteurMenuPassage(m));
		
		jMenuItemDepart = new JMenuItem("Depart");
		jMenuItemDepart.addActionListener(new EcouteurMenuDepart(m));
		
		jMenuItemArrivee = new JMenuItem("Arrivee");
		jMenuItemArrivee.addActionListener(new EcouteurMenuArrivee(m));
		
		jMenuItemLargeur = new JMenuItem("Largeur");
		jMenuItemLargeur.addActionListener(new EcouteurAlgoLargeur(m));
		jMenuItemProfondeur = new JMenuItem("Profondeur");
		jMenuItemProfondeur.addActionListener(new EcouteurAlgoProfondeur(m));
		jMenuItemAStar = new JMenuItem("A*");
		jMenuItemAStar.addActionListener(new EcouteurAlgoAStar(m));
		
		this.add(jMenuFichier);
		this.add(jMenuConstruire);
		this.add(jMenuAlgo);
		
		jMenuFichier.add(jMenuItemNouveau);
		jMenuFichier.add(jMenuItemQuitter);
		jMenuConstruire.add(jMenuItemMur);
		jMenuConstruire.add(jMenuItemPassage);
		jMenuConstruire.add(jMenuItemDepart);
		jMenuConstruire.add(jMenuItemArrivee);
		jMenuConstruire.add(jMenuItemAnnuler);
		jMenuAlgo.add(jMenuItemLargeur);
		jMenuAlgo.add(jMenuItemProfondeur);
		jMenuAlgo.add(jMenuItemAStar);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
			jMenuAlgo.show(!m.isRun());
			jMenuConstruire.show(!m.isRun());
			
			jMenuAlgo.show(m.isNouveau());
			jMenuConstruire.show(m.isNouveau());
			
			jMenuAlgo.show(m.getLabyrinthe().getCarteDepart() != null && m.getLabyrinthe().getCarteArrivee() != null);
			
	}

}
