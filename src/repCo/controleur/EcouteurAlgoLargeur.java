package repCo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import repCo.modele.Modele;

public class EcouteurAlgoLargeur implements ActionListener {
	
	protected Modele m;

	public EcouteurAlgoLargeur(Modele mod) {
		// TODO Auto-generated constructor stub
		this.m = mod;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		m.setAlgo(2);
		Thread t = new Thread((Runnable)m, "Traitement-labyrinthe");
		t.start() ;
	}

}
