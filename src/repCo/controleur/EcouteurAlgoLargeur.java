package repCo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import repCo.modele.Modele;
import repCo.recherche.Historique;
import repCo.recherche.IJeu;
import repCo.recherche.LargeurDAbord;

public class EcouteurAlgoLargeur implements ActionListener {
	
	protected Modele m;

	public EcouteurAlgoLargeur(Modele mod) {
		// TODO Auto-generated constructor stub
		this.m = mod;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		LargeurDAbord l = new LargeurDAbord();
		m.affichageLabyrinthe();
		m.resetFiltre();
		m.resetTailleHistorique();
		m.setHistorique(new Historique());
		m.setTempsDepart(System.currentTimeMillis());
		IJeu j = l.existeChemin(m.getLabyrinthe(), m.getHistorique());
		m.setTempsArrivee(System.currentTimeMillis());
		m.colorierChemin(j);
		m.creerFiltre();
		m.getTailleHistorique();
		m.miseAJour();
	}

}
