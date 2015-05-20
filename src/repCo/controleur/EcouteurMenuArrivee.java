package repCo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import repCo.modele.Modele;
import repCo.modele.Modele.TypeSelection;

public class EcouteurMenuArrivee implements ActionListener {
	
	protected Modele m;

	public EcouteurMenuArrivee(Modele mod) {
		// TODO Auto-generated constructor stub
		this.m = mod;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		m.setTypeSelection(TypeSelection.ARRIVEE);
	}

}
