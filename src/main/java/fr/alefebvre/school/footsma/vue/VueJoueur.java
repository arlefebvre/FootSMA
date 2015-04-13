package fr.alefebvre.school.footsma.vue;

import fr.alefebvre.school.footsma.modele.Position;
import jade.core.AID;

import java.awt.*;

public class VueJoueur {//extends JPanel {
    private static final int TAILLEJOUEUR = 20;
    private AID agentJoueur;
    private VueTerrain vueTerrain;
    private Color couleurMaillot;
    private Position pos;
    private int numero;
    private int numeroEquipe;
    private int tacles;
    private int dribles;
    private int arrets;
    private int tirs;
    private boolean possession;

    public VueJoueur() {
        initialiser();
    }

    public AID getAgentJoueur() {
        return agentJoueur;
    }

    public void setAgentJoueur(AID agentJoueur) {
        this.agentJoueur = agentJoueur;
    }

    public VueTerrain getVueTerrain() {
        return vueTerrain;
    }

    public void setVueTerrain(VueTerrain vueTerrain) {
        this.vueTerrain = vueTerrain;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void initialiser() {
        pos = new Position(0, 0);
    }

    public Color getCouleurMaillot() {
        return couleurMaillot;
    }

    public void setCouleurMaillot(Color couleurMaillot) {
        this.couleurMaillot = couleurMaillot;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int i) {
        numero = i;
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

    public void setNumeroEquipe(int myNumeroEquipe) {
        // TODO Auto-generated method stub
        numeroEquipe = myNumeroEquipe;
    }

    public int getTirs() {
        return tirs;
    }

    public void setTirs(int mytirs) {
        tirs = mytirs;
    }

    public boolean getPossession() {
        return possession;
    }

    public void setPossession(boolean b) {
        possession = b;
    }
}
