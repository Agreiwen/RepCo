package repCo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import repCo.modele.Modele;
import repCo.recherche.AStar;
import repCo.recherche.Historique;
import repCo.recherche.IJeu;
import repCo.recherche.LargeurDAbord;
import repCo.recherche.ProfondeurDAbord;

public class EcouteurAlgoAStar implements ActionListener {
	
	protected Modele m;

	public EcouteurAlgoAStar(Modele mod) {
		// TODO Auto-generated constructor stub
		this.m = mod;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AStar a = new AStar();
		m.affichageLabyrinthe();
		m.resetFiltre();
		m.setHistorique(new Historique());
		IJeu j = a.existeChemin(m.getLabyrinthe(), m.getHistorique());
		m.colorierChemin(j);
		m.creerFiltre();
		m.miseAJour();
	}

}
