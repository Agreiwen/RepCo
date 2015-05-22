package repCo.recherche;

import java.util.Iterator;

import repCo.modele.Modele;

public class ProfondeurDAbord implements IRecherche {
	
	protected Modele m;

    public ProfondeurDAbord(Modele mod){
    	this.m = mod;
    }
    

    public IJeu existeChemin(IJeu i, Historique h){
    	h.ajouterHistorique(i);
    	m.creerFiltreHistorique();
    	try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean fini = false;
		IJeu res = null;
		if(i.estFinal()){
			res = i;
		}else{
			Iterator<IJeu> lesFils = i.iterator();
			while(lesFils.hasNext() && !fini){
				IJeu unFils = lesFils.next();
				if(!h.contain(unFils) && !fini){
					res = existeChemin(unFils, h);
					fini = res != null;
				}
			}
		}
		return res;
    }


    

}