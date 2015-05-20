package repCo.recherche;

import repCo.modele.Carte;

public interface IJeu extends Iterable<IJeu> {

    public boolean estFinal();
    public Carte[][] getTab();

}