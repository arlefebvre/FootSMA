package fr.alefebvre.school.footsma.vue;

import fr.alefebvre.school.footsma.modele.Position;
import fr.alefebvre.school.footsma.modele.ReglesDuJeu;
import jade.core.AID;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VueTerrain extends JPanel {
    private Image imgTerrain = null;
    private Position ballonPos = new Position(ReglesDuJeu.getPosMillieuTerrain());
    private Position arbitrePos = new Position(ReglesDuJeu.getPosMillieuTerrain());
    private ArrayList<VueJoueur> joueurs;
    private boolean ballonDisponible = true;
    private AID joueurAuBallon;
    private Position posJoueurAuBallon;
    private boolean possessionEquipe1 = false;
    private boolean possessionEquipe2 = false;


    public VueTerrain(ArrayList<VueJoueur> myJoueurs) {
        joueurs = myJoueurs;
        initialiser();
        System.out.println("Vue du Terrain créée avec les vues des joueurs");
    }

    public VueTerrain() {
        initialiser();
    }

    public Position getBallonPos() {
        return ballonPos;
    }

    public void setBallonPos(Position ballonPos) {
        this.ballonPos = ballonPos;
    }

    public ArrayList<VueJoueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(ArrayList<VueJoueur> joueurs) {
        this.joueurs = joueurs;
    }

    public void initialiser() {
        //setSize(ReglesDuJeu.getLongueurTerrain(), ReglesDuJeu.getLargeurTerrain());
        //setBackground(Color.GREEN);
        Icon icon = new ImageIcon("images/terrain.jpg");
        //imgTerrain = new JLabel(icon);
        mettreEnPage();
        //imgTerrain = Toolkit.getDefaultToolkit().getImage("terrain.jpg");
        try {
            imgTerrain = ImageIO.read(new File("src/main/resources/images/terrain.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Pb de chargement d'image");
        }
    }


    public boolean isBallonDisponible() {
        return ballonDisponible;
    }

    public void setBallonDisponible(boolean ballonDisponible) {
        this.ballonDisponible = ballonDisponible;
    }

    public AID getJoueurAuBallon() {
        return joueurAuBallon;
    }

    public void setJoueurAuBallon(AID joueurAuBallon) {
        this.joueurAuBallon = joueurAuBallon;
    }


    public boolean isPossessionEquipe1() {
        return possessionEquipe1;
    }

    public void setPossessionEquipe1(boolean possessionEquipe1) {
        this.possessionEquipe1 = possessionEquipe1;
    }

    public boolean isPossessionEquipe2() {
        return possessionEquipe2;
    }

    public void setPossessionEquipe2(boolean possessionEquipe1) {
        this.possessionEquipe2 = possessionEquipe2;
    }

    public void mettreEnPage() {
        //this.setLayout(new BorderLayout());
        //this.add(imgTerrain,BorderLayout.CENTER);

    }

    public void paintComponent(Graphics g) {
        //System.out.println("PAINT TERRAIN");
        g.drawImage(imgTerrain, 0, 0, this);
        Position pos;
        if (joueurs != null) {

            //System.out.println("Nombre de vues de joueurs enregistrées : "+String.valueOf(joueurs.size()));
            for (int i = 0; i < joueurs.size(); i++) {
                pos = joueurs.get(i).getPos();
                if (pos != null) {
                    g.setColor(joueurs.get(i).getCouleurMaillot());
                    g.fillOval(pos.getX(), pos.getY(), 20, 20);
                    g.setColor(Color.WHITE);
                    if (joueurs.get(i).getNumero() < 10) {
                        g.drawString(String.valueOf(joueurs.get(i).getNumero()), pos.getX() + 7, pos.getY() + 15);
                    } else {
                        g.drawString(String.valueOf(joueurs.get(i).getNumero()), pos.getX() + 3, pos.getY() + 15);
                    }
                    g.drawOval(pos.getX(), pos.getY(), 20, 20);
                }


                g.fillOval(ballonPos.getX(), ballonPos.getY(), 12, 12);
                g.setColor(Color.BLACK);
                g.drawOval(ballonPos.getX(), ballonPos.getY(), 12, 12);
                g.fillOval(ballonPos.getX(), ballonPos.getY() - 40, 20, 20);
                g.setColor(Color.WHITE);
                g.drawOval(ballonPos.getX(), ballonPos.getY() - 40, 20, 20);
            }
        } else System.out.println("Problème : pas de vues de joueurs enregistrées");


    }

    public void setPossession(int numero) {
        // TODO Auto-generated method stub
        if (numero == 1) {
            setPossessionEquipe1(true);
            setPossessionEquipe2(false);
        } else if (numero == 2) {
            setPossessionEquipe1(true);
            setPossessionEquipe1(false);
        } else {
            setPossessionEquipe1(false);
            setPossessionEquipe1(false);
        }
    }

    public Position getPosJoueurAuBallon() {
        // TODO Auto-generated method stub
        return posJoueurAuBallon;
    }

    public void setPosJoueurAuBallon(Position posJoueurAuBallon) {
        this.posJoueurAuBallon = posJoueurAuBallon;
    }

}
