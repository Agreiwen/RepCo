package repCo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import repCo.modele.Modele;
import repCo.recherche.Historique;
import repCo.recherche.IJeu;
import repCo.recherche.LargeurDAbord;
import repCo.recherche.ProfondeurDAbord;

public class EcouteurAlgoProfondeur implements ActionListener {
	
	protected Modele m;

	public EcouteurAlgoProfondeur(Modele mod) {
		// TODO Auto-generated constructor stub
		this.m = mod;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ProfondeurDAbord p = new ProfondeurDAbord();
		m.affichageLabyrinthe();
		m.resetFiltre();
		m.setHistorique(new Historique());
		//m.affichageFiltre();
		IJeu j = p.existeChemin(m.getLabyrinthe(), m.getHistorique());
		m.colorierChemin(j);
		m.creerFiltre();
		m.miseAJour();
	}

}
