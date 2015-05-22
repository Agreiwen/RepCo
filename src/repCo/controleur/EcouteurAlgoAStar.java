package repCo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import repCo.modele.Modele;
import repCo.recherche.AStar;
import repCo.recherche.Historique;
import repCo.recherche.IJeu;

public class EcouteurAlgoAStar implements ActionListener {
	
	protected Modele m;

	public EcouteurAlgoAStar(Modele mod) {
		// TODO Auto-generated constructor stub
		this.m = mod;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		m.setAlgo(1);
		Thread t = new Thread((Runnable)m, "Traitement-labyrinthe");
		t.start() ;
	}

}
