package repCo.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import repCo.modele.Modele;

public class EcouteurNouveau implements ActionListener {
	
	protected Modele m;

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
			try {
				if(retourHauteur != null){
					int hauteur = Integer.parseInt(retourHauteur.toString());
						m.setHauteur(hauteur);
						boolHauteur = true;
				}else{
					break;
				}
				
			}
			catch(Exception ex){
				retourHauteur = JOptionPane.showInputDialog(null, "Donnez une hauteur valide\nCeci n'est pas un nombre","Hauteur du labyrinthe", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/occurence.png"),null,"");
				boolHauteur = false;
			}
		}
		
		boolean boolLargeur = false;
		Object retourLargeur = JOptionPane.showInputDialog(null, "Donnez une largeur","Largeur du labyrinthe", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/occurence.png"),null,"");

		while(!boolLargeur){
			try {
				if(retourLargeur != null){
					int largeur = Integer.parseInt(retourLargeur.toString());
						m.setLargeur(largeur);
						boolLargeur = true;
				}else{
					break;
				}
				
			}
			catch(Exception ex){
				retourLargeur = JOptionPane.showInputDialog(null, "Donnez une largeur valide\nCeci n'est pas un nombre","Largeur du labyrinthe", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/occurence.png"),null,"");
				boolLargeur = false;
			}
		}
		if(boolLargeur && boolHauteur){
			m.creerLabyrinthe(m.getHauteur(), m.getLargeur());
			m.setInitialise(true);
			m.miseAJour();
		}
		
		
	}
	
}


