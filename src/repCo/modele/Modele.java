package repCo.modele;

import java.util.ArrayList;
import java.util.Observable;

import repCo.modele.Carte.TypeMap;
import repCo.modele.Labyrinthe.Filtre;
import repCo.recherche.AStar;
import repCo.recherche.Historique;
import repCo.recherche.IJeu;
import repCo.recherche.LargeurDAbord;
import repCo.recherche.ProfondeurDAbord;

public class Modele extends Observable implements Runnable{
	
	protected Labyrinthe labyrinthe;
	protected int hauteur = 0;
	protected int largeur = 0;
	protected boolean initialise = false;
	public enum TypeSelection {MUR, PASSAGE, DEPART, ARRIVEE}
	protected TypeSelection typeSelection;
	protected Filtre[][] tableauFiltre;
	protected Historique h;
	protected ArrayList<Carte> chemin;
	protected long TempsDepart;
	protected long TempsArrivee;
	protected int tailleHistorique;
	protected boolean run = false;
	protected boolean nouveau = false;
	protected int algo;
	protected float temps;
	
	public float getTemps() {
		return temps;
	}

	public void setTemps(float temps) {
		this.temps = temps;
		this.miseAJour();
	}

	public int getAlgo() {
		return algo;
	}

	public void setAlgo(int algo) {
		this.algo = algo;
	}

	public boolean isNouveau() {
		return nouveau;
	}

	public void setNouveau(boolean nouveau) {
		this.nouveau = nouveau;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public long getTempsDepart() {
		return TempsDepart;
	}

	public void setTempsDepart(long tempsDepart) {
		TempsDepart = tempsDepart;
	}

	public long getTempsArrivee() {
		return TempsArrivee;
	}

	public void setTempsArrivee(long tempsArrivee) {
		TempsArrivee = tempsArrivee;
	}
	
	public int getTailleHistorique(){
		return h.getHistorique().size();
	}
	
	public void resetTailleHistorique(){
		tailleHistorique = 0;
	}

	public Modele(){
    	h = new Historique();
     	labyrinthe = new Labyrinthe(h);
	}
	
	public void setTypeSelection(TypeSelection typeSelection){
		this.typeSelection = typeSelection;
	}
	
	public TypeSelection getTypeSelection(){
		return typeSelection;
	}
	
	public boolean isInitialise(){
		return initialise;
	}
	
	public void setInitialise(boolean j){
		initialise = j;
	}
	
	public Labyrinthe getLabyrinthe() {
		return labyrinthe;
	}

	public void setLabyrinthe(Labyrinthe labyrinthe) {
		this.labyrinthe = labyrinthe;
	}
	
	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public void miseAJour(){
		setChanged();
		notifyObservers();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		switch(algo){
		case 1:
			this.setTempsDepart(System.currentTimeMillis());
			this.setRun(true);
			AStar a = new AStar(this);
			this.affichageLabyrinthe();
			this.resetFiltre();
			this.resetTailleHistorique();
			this.setHistorique(new Historique());
			IJeu j = a.existeChemin(this.getLabyrinthe(), this.getHistorique());
			this.colorierChemin(j);
			//this.creerFiltre();
			this.getTailleHistorique();
			this.setRun(false);
			this.miseAJour();
			this.creerFiltreChemin();
			this.setTempsArrivee(System.currentTimeMillis());
			float temps1 = (float)(this.getTempsArrivee()-this.getTempsDepart())/1000;
			this.setTemps(temps1);
			break;
		case 2:
			this.setTempsDepart(System.currentTimeMillis());
			this.setRun(true);
			LargeurDAbord l = new LargeurDAbord(this);
			this.affichageLabyrinthe();
			this.resetFiltre();
			this.resetTailleHistorique();
			this.setHistorique(new Historique());
			IJeu k = l.existeChemin(this.getLabyrinthe(), this.getHistorique());
			this.colorierChemin(k);
			//this.creerFiltre();
			this.getTailleHistorique();
			this.setRun(false);
			this.miseAJour();
			this.creerFiltreChemin();
			this.setTempsArrivee(System.currentTimeMillis());
			float temps2 = (float)(this.getTempsArrivee()-this.getTempsDepart())/1000;
			this.setTemps(temps2);
			break;
		case 3:
			this.setTempsDepart(System.currentTimeMillis());
			this.setRun(true);
			ProfondeurDAbord p = new ProfondeurDAbord(this);
			this.affichageLabyrinthe();
			this.resetFiltre();
			this.resetTailleHistorique();
			this.setHistorique(new Historique());
			IJeu c = p.existeChemin(this.getLabyrinthe(), this.getHistorique());
			this.colorierChemin(c);
			//this.creerFiltre();
			this.getTailleHistorique();
			this.setRun(false);
			this.miseAJour();
			this.creerFiltreChemin();
			this.setTempsArrivee(System.currentTimeMillis());
			float temps3 = (float)(this.getTempsArrivee()-this.getTempsDepart())/1000;
			this.setTemps(temps3);
			break;
		}
	}
	
	public void affichageLabyrinthe(){
		System.out.println("-> Affichage du labyrinthe :\n");
		for(int i=0; i<labyrinthe.hauteurLabyrinthe(); i++){
			for(int j=0; j<labyrinthe.largeurLabyrinthe(i); j++){
				System.out.print(labyrinthe.getMap(i, j).toString());
			}System.out.println();
		}
	}
	
	public void resetLabyrinthe(){
		for(int i=0; i<hauteur; i++){
			for(int j=0; j<largeur; j++){
				labyrinthe.getJeu()[i][j].setTypeMap(TypeMap.PASSAGE);
			}
		}
	}
	
	public void resetFiltre(){
		for (int i = 0; i < tableauFiltre.length; i++) {
			for (int j = 0; j < tableauFiltre[0].length; j++) {
				if(tableauFiltre[i][j] == Filtre.HISTORIQUE || tableauFiltre[i][j] == Filtre.CHEMIN){
					tableauFiltre[i][j] = Filtre.NORMALE;
				}
			}
		}
	}
	
	public void colorierChemin(IJeu j){
		chemin = new ArrayList<Carte>();
		Labyrinthe laby = (Labyrinthe) j;
		while(laby != null){
			chemin.add(laby.carteDepart);
			laby = laby.papa;
		}
	}
	
	public void initTableauFiltre(){
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				tableauFiltre[i][j] = Filtre.NORMALE;
			}
		}
	}
	
	public void creerLabyrinthe(int x, int y){
		tableauFiltre = new Filtre[x][y];
		labyrinthe.CreationLabyrinthe(x,y);
		initTableauFiltre();
	}
	
	public void creerFiltreHistorique(){
		initTableauFiltre();
		for (IJeu i : h.getHistorique()){
			Labyrinthe l = (Labyrinthe)i;
			tableauFiltre[l.getCarteDepart().getPositionX()][l.getCarteDepart().getPositionY()] = Filtre.HISTORIQUE;
		}
		miseAJour();
	}
	
	public void creerFiltreChemin(){
		for (Carte c : chemin) {
			tableauFiltre[c.getPositionX()][c.getPositionY()] = Filtre.CHEMIN;
		}
		miseAJour();
	}

	public Filtre[][] getTableauFiltre() {
		// TODO Auto-generated method stub
		return tableauFiltre;
	}

	public Historique getHistorique() {
		// TODO Auto-generated method stub
		return h;
	}
	
	public void setHistorique(Historique h){
		this.h = h;
	}
	
	public void affichageFiltre(){
		for (int i = 0; i < tableauFiltre.length; i++) {
			for (int j = 0; j < tableauFiltre[0].length; j++) {
				System.out.println(tableauFiltre[i][j]);
			}
		}
	}
}
