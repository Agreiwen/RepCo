package repCo.recherche;

import java.util.Iterator;

public class ProfondeurDAbord implements IRecherche {

    public ProfondeurDAbord(){
	
	
    }
    

    public IJeu existeChemin(IJeu i, Historique h){
    	h.ajouterHistorique(i);
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