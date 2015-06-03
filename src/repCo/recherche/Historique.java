package repCo.recherche;

import java.util.ArrayList;

public class Historique {
	
	private ArrayList<IJeu> historique;
	
	public Historique(){
		historique = new ArrayList<IJeu>();
	}
	
	public Historique(Historique h){
		this.historique = h.historique;
	}
	
	public IJeu getValue(int a){
		return this.historique.get(a);
	}
	
	public boolean contain(IJeu a){
		boolean rep = false;
		for(int i = 0; i < historique.size() && !rep; i++){
			if(a.equals(historique.get(i)))
				rep = true;
		}
		return historique.contains(a);
	}
	
	public void ajouterHistorique(IJeu a){
		this.historique.add(a);
	}

	public ArrayList<IJeu> getHistorique() {
		return historique;
	}

	public void setHistorique(ArrayList<IJeu> historique) {
		this.historique = historique;
	}
	
}
