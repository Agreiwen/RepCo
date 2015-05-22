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
		
		if(m.getTypeSelection() == TypeSelection.MUR){
			m.getLabyrinthe().getCarte(positionX, positionY).setTypeMap(TypeMap.MUR);
			m.miseAJour();
		}
		else if(m.getTypeSelection() == TypeSelection.PASSAGE){
			if(m.getLabyrinthe().getCarte(positionX, positionY).getTypeMap() == TypeMap.DEPART){
				m.getLabyrinthe().setCarteDepart(null);
				m.getLabyrinthe().getCarte(positionX, positionY).setTypeMap(TypeMap.PASSAGE);
				m.miseAJour();
			}
			else if(m.getLabyrinthe().getCarte(positionX, positionY).getTypeMap() == TypeMap.ARRIVEE){
				m.getLabyrinthe().setCarteArrivee(null);
				m.getLabyrinthe().getCarte(positionX, positionY).setTypeMap(TypeMap.PASSAGE);
				m.miseAJour();
			}
			else{
				m.getLabyrinthe().getCarte(positionX, positionY).setTypeMap(TypeMap.PASSAGE);
				m.miseAJour();
			}
		}else if(m.getTypeSelection() == TypeSelection.ARRIVEE){
			if(m.getLabyrinthe().getCarteArrivee() != null){
				m.getLabyrinthe().getCarteArrivee().setTypeMap(TypeMap.PASSAGE);
				m.getLabyrinthe().getCarte(positionX, positionY).setTypeMap(TypeMap.ARRIVEE);
				m.getLabyrinthe().setCarteArrivee(m.getLabyrinthe().getCarte(positionX, positionY));
			}
			else{
				m.getLabyrinthe().getCarte(positionX, positionY).setTypeMap(TypeMap.ARRIVEE);
				m.getLabyrinthe().setCarteArrivee(m.getLabyrinthe().getCarte(positionX, positionY));
			}
			m.miseAJour();
		}else if(m.getTypeSelection() == TypeSelection.DEPART){
			if(m.getLabyrinthe().getCarteDepart() != null){
				m.getLabyrinthe().getCarteDepart().setTypeMap(TypeMap.PASSAGE);
				m.getLabyrinthe().getCarte(positionX, positionY).setTypeMap(TypeMap.DEPART);
				m.getLabyrinthe().setCarteDepart(m.getLabyrinthe().getCarte(positionX, positionY));
			}
			else{
				m.getLabyrinthe().getCarte(positionX, positionY).setTypeMap(TypeMap.DEPART);
				m.getLabyrinthe().setCarteDepart(m.getLabyrinthe().getCarte(positionX, positionY));
			}
			m.miseAJour();
		}
		
	}

}
