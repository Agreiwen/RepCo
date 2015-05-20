package repCo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import repCo.modele.Modele;
import repCo.modele.Carte.TypeMap;
import repCo.modele.Modele.TypeSelection;

public class EcouteurBoutonLabyrinthe implements ActionListener {
	
	protected Modele m;
	protected int positionX;
	protected int positionY;

	public EcouteurBoutonLabyrinthe(Modele mod) {
		// TODO Auto-generated constructor stub
		this.m = mod;
	}

	public EcouteurBoutonLabyrinthe(Modele mod, int x, int y) {
		// TODO Auto-generated constructor stub
		this.m = mod;
		this.positionX = x;
		this.positionY = y;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println(m.getLabyrinthe().getCarte(positionX, positionY).toString()+"\n");
//		System.out.println(m.getLabyrinthe().getJeu()[positionX][positionY]);
//		System.out.println("Droite : "+m.getLabyrinthe().droitePossible(m.getLabyrinthe().getCarte(positionX, positionY)));
//		System.out.println("Gauche : "+m.getLabyrinthe().gauchePossible(m.getLabyrinthe().getCarte(positionX, positionY)));
//		System.out.println("Haut : "+m.getLabyrinthe().hautPossible(m.getLabyrinthe().getCarte(positionX, positionY)));
//		System.out.println("Bas : "+m.getLabyrinthe().basPossible(m.getLabyrinthe().getCarte(positionX, positionY)));
		
//		System.out.print("\nCarte de gauche :");
//		m.getLabyrinthe().carteGauche(m.getCarte(positionX, positionY));
//		
//		System.out.print("\nCarte de droite :");
//		m.getLabyrinthe().carteDroite(m.getCarte(positionX, positionY));
//		
//		System.out.print("\nCarte du bas :");
//		m.getLabyrinthe().carteBas(m.getCarte(positionX, positionY));
//		
//		System.out.print("\nCarte du haut :");
//		m.getLabyrinthe().carteHaut(m.getCarte(positionX, positionY));
		
		
		if(m.getTypeSelection() == TypeSelection.MUR){
			m.getLabyrinthe().getCarte(positionX, positionY).setTypeMap(TypeMap.MUR);
			m.miseAJour();
		}
		else if(m.getTypeSelection() == TypeSelection.PASSAGE){
			m.getLabyrinthe().getCarte(positionX, positionY).setTypeMap(TypeMap.PASSAGE);
			m.miseAJour();
		}else if(m.getTypeSelection() == TypeSelection.ARRIVEE){
			m.getLabyrinthe().getCarte(positionX, positionY).setTypeMap(TypeMap.ARRIVEE);
			m.getLabyrinthe().setCarteArrivee(m.getLabyrinthe().getCarte(positionX, positionY));
			System.out.println(m.getLabyrinthe().getCarteArrivee().toString());
			m.miseAJour();
		}else if(m.getTypeSelection() == TypeSelection.DEPART){
			m.getLabyrinthe().getCarte(positionX, positionY).setTypeMap(TypeMap.DEPART);
			m.getLabyrinthe().setCarteDepart(m.getLabyrinthe().getCarte(positionX, positionY));
			System.out.println(m.getLabyrinthe().getCarteDepart().toString());
			m.miseAJour();
		}
		
	}

}
