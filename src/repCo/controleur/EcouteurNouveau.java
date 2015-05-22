package repCo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import repCo.modele.Modele;

public class EcouteurNouveau implements ActionListener {
	
	protected Modele m;
	protected int hauteur;
	protected int largeur;

	public EcouteurNouveau(Modele mod) {
		// TODO Auto-generated constructor stub
		this.m = mod;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		boolean boolHauteur = false;
		Object retourHauteur = JOptionPane.showInputDialog(null, "Donnez une hauteur","Hauteur du labyrinthe", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/occurence.png"),null,"");

		while(!boolHauteur){
			if(retourHauteur == null){
				break;
			}
			try {
				System.out.println("try");
				hauteur = Integer.parseInt(retourHauteur.toString());
				if(hauteur >= 10){
					m.setHauteur(hauteur);
					boolHauteur = true;
				}else if(hauteur < 10){
					retourHauteur = JOptionPane.showInputDialog(null, "Donnez une hauteur valide\nLa hauteur doit être de 10 minimum","Hauteur du labyrinthe", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/occurence.png"),null,"");
					boolHauteur = false;
				}
			}catch(Exception ex){
				System.out.println("catch");
				retourHauteur = JOptionPane.showInputDialog(null, "Donnez une hauteur valide\nCeci n'est pas un nombre","Hauteur du labyrinthe", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/occurence.png"),null,"");
				boolHauteur = false;
			}
			
		}
		
		boolean boolLargeur = false;
		Object retourLargeur = JOptionPane.showInputDialog(null, "Donnez une largeur","Largeur du labyrinthe", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/occurence.png"),null,"");

		while(!boolLargeur){
			if(retourLargeur == null){
				break;
			}
			try {
				System.out.println("try");
				largeur = Integer.parseInt(retourLargeur.toString());
				if(largeur >= 10){
					m.setLargeur(largeur);
					boolLargeur = true;
				}else if(largeur < 10){
					retourLargeur = JOptionPane.showInputDialog(null, "Donnez une largeur valide\nLa largeur doit être de 10 minimum","Largeur du labyrinthe", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/occurence.png"),null,"");
					boolLargeur = false;
				}
			}catch(Exception ex){
				retourLargeur = JOptionPane.showInputDialog(null, "Donnez une largeur valide\nCeci n'est pas un nombre","Largeur du labyrinthe", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/occurence.png"),null,"");
				boolLargeur = false;
			}
			
		}
		
		if(boolLargeur && boolHauteur){
			m.creerLabyrinthe(m.getHauteur(), m.getLargeur());
			m.setInitialise(true);
			m.setNouveau(true);
			m.getLabyrinthe().setCarteArrivee(null);
			m.getLabyrinthe().setCarteDepart(null);
			m.miseAJour();
		}
		
		
	}
	
}


