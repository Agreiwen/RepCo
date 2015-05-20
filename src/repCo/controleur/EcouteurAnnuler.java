package repCo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import repCo.modele.Modele;
import repCo.recherche.Historique;

public class EcouteurAnnuler implements ActionListener {
	
	protected Modele m;

	public EcouteurAnnuler(Modele mod) {
		// TODO Auto-generated constructor stub
		this.m = mod;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		m.resetLabyrinthe();
		m.resetFiltre();
		m.setHistorique(new Historique());
		m.miseAJour();
	}

}
