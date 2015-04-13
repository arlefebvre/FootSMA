package fr.alefebvre.school.footsma.vue;

import fr.alefebvre.school.footsma.modele.Position;
import jade.core.AID;

import java.awt.Color;

public class VueJoueur {//extends JPanel {
	private AID agentJoueur;
	public AID getAgentJoueur() {
		return agentJoueur;
	}

	public void setAgentJoueur(AID agentJoueur) {
		this.agentJoueur = agentJoueur;
	}

	private VueTerrain vueTerrain;
	public VueTerrain getVueTerrain() {
		return vueTerrain;
	}

	public void setVueTerrain(VueTerrain vueTerrain) {
		this.vueTerrain = vueTerrain;
	}

	private static final int TAILLEJOUEUR=20;
	private Color couleurMaillot;
	private Position pos;
	private int numero;
	private int numeroEquipe;
	
	private int tacles;
	private int dribles;
	private int arrets;
	private int tirs;
	private boolean possession;
	public VueJoueur(){
		initialiser();
	}
	
	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	public void setCouleurMaillot(Color couleurMaillot) {
		this.couleurMaillot = couleurMaillot;
	}

	public void initialiser(){
		pos=new Position(0,0);
	}

	public void setNumero(int i) {
		// TODO Auto-generated method stub
		numero=i;
	}

	public Color getCouleurMaillot() {
		return couleurMaillot;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumeroEquipe(int myNumeroEquipe) {
		// TODO Auto-generated method stub
		numeroEquipe=myNumeroEquipe;
	}

	public int getTacles() {
		return tacles;
	}

	public void setTacles(int tacles) {
		this.tacles = tacles;
	}

	public int getDribles() {
		return dribles;
	}

	public void setDribles(int dribles) {
		this.dribles = dribles;
	}

	public int getArrets() {
		return arrets;
	}

	public void setArrets(int arrets) {
		this.arrets = arrets;
	}

	public int getNumeroEquipe() {
		return numeroEquipe;
	}

	public void setTirs(int mytirs) {
		// TODO Auto-generated method stub
		tirs = mytirs;
	}

	public int getTirs() {
		return tirs;
	}

	public void setPossession(boolean b) {
		// TODO Auto-generated method stub
		possession =b;
	}

	public boolean getPossession() {
		// TODO Auto-generated method stub
		return possession;
	}

	/*@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(couleurMaillot);
		g.fillOval(3,3,this.getWidth()-6,this.getHeight()-6);
	}*/
	
	
	
	
}
