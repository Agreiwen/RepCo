package repCo.modele;
import java.util.ArrayList;
import java.util.Iterator;

import repCo.modele.Carte.TypeMap;
import repCo.recherche.Historique;
import repCo.recherche.IJeu;

public class Labyrinthe implements IJeu{
	
	private Carte[][] jeu;
	protected Carte carteDepart;
	protected Carte carteArrivee;
	protected Labyrinthe papa;
	public enum Filtre {NORMALE, HISTORIQUE, CHEMIN};
	protected Filtre filtre;
	private Historique historique;
	private int g;
	
	public Labyrinthe(Historique his){
		papa = null;
		historique= his;
		g=0;
	}
	
	public Labyrinthe(Labyrinthe l){
		this.jeu = new Carte[l.jeu.length][l.jeu[0].length];
		for (int i = 0; i < l.jeu.length; i++) {
			for (int j = 0; j < l.jeu[i].length; j++) {
				this.jeu[i][j] = new Carte (l.jeu[i][j]);
				if(this.jeu[i][j].getTypeMap() == TypeMap.ARRIVEE)
					this.carteArrivee = this.jeu[i][j];
				if(this.jeu[i][j].getTypeMap() == TypeMap.DEPART)
					this.carteDepart = this.jeu[i][j];
			}
		}
		this.historique = l.historique;
		this.papa = l;
		g=papa.g+1;
	}
	
	public void setFiltre(Filtre f){
		this.filtre = f;
	}
	
	public Filtre getFiltre(){
		return filtre;
	}
	
	public void CreationLabyrinthe(int x, int y){
		System.out.print("-> Création du labyrinthe ... ");
		jeu = new Carte[x][y];
		for(int i=0; i<hauteurLabyrinthe(); i++){
			for(int j=0; j<largeurLabyrinthe(i); j++){
				jeu[i][j] = new Carte(i,j);
				jeu[i][j].setTypeMap(TypeMap.PASSAGE);
			}
		}
		System.out.println("Effectué\n");
	}
	
	public void ajouterMur(int x, int y){
		jeu[x][y].setTypeMap(TypeMap.MUR);
	}
	
	public Carte getCarteDepart(){
		return this.carteDepart;
	}
	
	public void setCarteDepart(Carte carteDepart){
		this.carteDepart = carteDepart;
	}
	
	public Carte getCarteArrivee(){
		return this.carteArrivee;
	}
	
	public void setCarteArrivee(Carte carteArrivee){
		this.carteArrivee = carteArrivee;
	}
	
	public Carte[][] getJeu() {
		return jeu;
	}

	public void ajouterPassage(int x, int y){
		jeu[x][y].setTypeMap(TypeMap.PASSAGE);
	}
	
	public Carte getMap(int x, int y){
		return jeu[x][y];
	}
	
	public void setMap(int x, int y, TypeMap typeMap){
		jeu[x][y].setTypeMap(typeMap);
	}
	
	public int hauteurLabyrinthe(){
		return jeu.length;
	}
	
	public int largeurLabyrinthe(int ligne){
		return jeu[ligne].length;
	}

	public boolean droitePossible(Carte c){
		//System.out.println(c.positionY);
		if(c.positionY == largeurLabyrinthe(c.positionY)-1){
			return false;
		}
		else{
			if(carteDroite(c).getTypeMap()==TypeMap.MUR){
				return false;
			}
			return true;
		}
	}
	
	public boolean gauchePossible(Carte c){
		if(c.positionY == 0){
			return false;
		}else{
			if(carteGauche(c).getTypeMap()==TypeMap.MUR){
				return false;
			}
			return true;
		}
	}
	
	public boolean hautPossible(Carte c){
		if(c.positionX == 0){
			return false;
		}else{
			if(carteHaut(c).getTypeMap()==TypeMap.MUR){
				return false;
			}
			return true;
		}
	}
	
	public boolean basPossible(Carte c){
		if(c.positionX == hauteurLabyrinthe()-1){
			return false;
		}else{
			if(carteBas(c).getTypeMap()==TypeMap.MUR){
				return false;
			}
			return true;
		}
	}
	
	public Carte carteGauche(Carte c){
		//System.out.println(jeu[c.positionX][c.positionY-1]);
		return jeu[c.positionX][c.positionY-1];
	}
	
	public Carte carteDroite(Carte c){
		//System.out.println(jeu[c.positionX][c.positionY+1]);
		return jeu[c.positionX][c.positionY+1];
	}
	
	public Carte carteHaut(Carte c){
		//System.out.println(jeu[c.positionX-1][c.positionY]);
		return jeu[c.positionX-1][c.positionY];
	}
	
	public Carte carteBas(Carte c){
		//System.out.println(jeu[c.positionX+1][c.positionY]);
		return jeu[c.positionX+1][c.positionY];
	}
	
	public void deplacerDroite(){
		jeu[carteDepart.getPositionX()][carteDepart.getPositionY()] = new Carte(carteDepart.getPositionX(),carteDepart.getPositionY());
		jeu[carteDepart.getPositionX()][carteDepart.getPositionY()].setTypeMap(TypeMap.PASSAGE);
		jeu[carteDepart.getPositionX()][carteDepart.getPositionY()+1] = carteDepart;
		carteDepart.setPositionY(carteDepart.getPositionY()+1);
		System.out.println("Deplacer droit -> "+this.getCarteDepart().toString());
	}
	
	public void deplacerGauche(){
		jeu[carteDepart.getPositionX()][carteDepart.getPositionY()] = new Carte(carteDepart.getPositionX(),carteDepart.getPositionY());
		jeu[carteDepart.getPositionX()][carteDepart.getPositionY()].setTypeMap(TypeMap.PASSAGE);
		jeu[carteDepart.getPositionX()][carteDepart.getPositionY()-1] = carteDepart;
		carteDepart.setPositionY(carteDepart.getPositionY()-1);
	}
	
	public void deplacerHaut(){
		jeu[carteDepart.getPositionX()][carteDepart.getPositionY()] = new Carte(carteDepart.getPositionX(),carteDepart.getPositionY());
		jeu[carteDepart.getPositionX()][carteDepart.getPositionY()].setTypeMap(TypeMap.PASSAGE);
		jeu[carteDepart.getPositionX()-1][carteDepart.getPositionY()] = carteDepart;
		carteDepart.setPositionX(carteDepart.getPositionX()-1);
	}
	
	public void deplacerBas(){
		jeu[carteDepart.getPositionX()][carteDepart.getPositionY()] = new Carte(carteDepart.getPositionX(),carteDepart.getPositionY());
		jeu[carteDepart.getPositionX()][carteDepart.getPositionY()].setTypeMap(TypeMap.PASSAGE);
		jeu[carteDepart.getPositionX()+1][carteDepart.getPositionY()] = carteDepart;
		carteDepart.setPositionX(carteDepart.getPositionX()+1);
	}
	
	public void setJeu(Carte[][] c){
		this.jeu = c;
	}

	@Override
	public Iterator<IJeu> iterator() {
		ArrayList<IJeu> a = new ArrayList<IJeu>();
		System.out.println("----------------- "+this.getCarteDepart().toString());
		if(droitePossible(this.getCarteDepart()) && !estFinal()){
			//System.out.println("DROIT POSSIBLE");
			
			Labyrinthe newLaby = new Labyrinthe(this);
			//System.out.println(newLaby.getCarteArrivee().toString());
			newLaby.deplacerDroite();
			//System.out.println(newLaby.getCarteArrivee().toString());
			//System.out.println("Historique "+h.getHistorique().size());
			if(!historique.contain(newLaby)){
				System.out.println("Ajouter ********************************");
				a.add(newLaby);
				System.out.println("NewLaby "+newLaby.getCarteDepart().toString());
			}
			//System.out.println("Historique "+h.getHistorique().size());

		}
		if(gauchePossible(this.getCarteDepart()) && !estFinal()){
			//System.out.println("GAUCHE POSSIBLE");
			Labyrinthe newLaby = new Labyrinthe(this);
			newLaby.deplacerGauche();
			if(!historique.contain(newLaby)){
				a.add(newLaby);
			}
		}
		if(hautPossible(this.getCarteDepart()) && !estFinal()){
			//System.out.println("HAUT POSSIBLE");
			Labyrinthe newLaby = new Labyrinthe(this);
			newLaby.deplacerHaut();
			if(!historique.contain(newLaby)){
				a.add(newLaby);
			}
		}
		if(basPossible(this.getCarteDepart()) && !estFinal()){
			//System.out.println("BAS POSSIBLE");
			Labyrinthe newLaby = new Labyrinthe(this);
			newLaby.deplacerBas();
			if(!historique.contain(newLaby)){
				a.add(newLaby);
			}
		}
		return a.iterator();
	}

	public Historique getHistorique() {
		// TODO Auto-generated method stub
		return this.historique;
	}
	
//	public void setHistorique(Historique hist){
//		this.historique = hist;
//	}

	@Override
	public boolean estFinal() {
		// TODO Auto-generated method stub
		boolean x = this.getCarteDepart().getPositionX() == this.getCarteArrivee().getPositionX();
		boolean y = this.getCarteDepart().getPositionY() == this.getCarteArrivee().getPositionY();
		return x&&y;
	}

	@Override
	public Carte[][] getTab() {
		// TODO Auto-generated method stub
		return jeu;
	}
	
	public Carte getCarte(int i, int j){
		return jeu[i][j];
	}
	
	@Override
	public boolean equals(Object o){
		Labyrinthe tmp = (Labyrinthe) o;
		boolean b = true;
		if(!this.carteArrivee.equals(tmp.carteArrivee) || !this.carteDepart.equals(tmp.carteDepart)){
			b = false;
		}
//		this.jeu = new Carte[l.jeu.length][l.jeu[0].length];
		for (int i = 0; i < tmp.jeu.length; i++) {
			for (int j = 0; j < tmp.jeu[i].length; j++) {
				if(!this.jeu[i][j].equals(tmp.jeu[i][j]))
					b = false;
			}
		}
		return b;
	}
	
	public String toString(){
		return "CarteDepart ="+this.carteDepart.toString();
	}

	public void setPere(Labyrinthe fMinimum) {
		papa = fMinimum;
	}

	public int getF() {
		return g+Math.abs(carteDepart.getPositionX()-carteArrivee.getPositionX())+Math.abs(carteDepart.getPositionY()-carteArrivee.getPositionY());
	}
	
	public void setHistorique(Historique h){
		this.historique = h;
	}
}
