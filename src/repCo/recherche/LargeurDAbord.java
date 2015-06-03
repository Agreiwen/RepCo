package repCo.recherche;

import java.util.ArrayList;
import java.util.Iterator;

import repCo.modele.Modele;

public class LargeurDAbord implements IRecherche{
	
	protected Modele m;

	public LargeurDAbord(Modele mod){
		this.m = mod;
	}

	public IJeu existeChemin(IJeu i, Historique h) {
		IJeu res = null;
		ArrayList<IJeu> noeuds = new ArrayList<IJeu>();
		noeuds.add(i);
		boolean isFinal = false;
		for (int j2 = 0; j2 < noeuds.size() && !isFinal; j2++) {
			IJeu courant = noeuds.get(j2);
			Iterator<IJeu> j = courant.iterator();
			if(courant.estFinal()){
				res = courant;
				isFinal = true;
			}
			if(!isFinal){
				while(j.hasNext()){
					IJeu tmp = j.next();
					if(!noeuds.contains(tmp)){
						noeuds.add(tmp);
						h.ajouterHistorique(tmp);
						m.creerFiltreHistorique();
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		return res;
	}
				
}
