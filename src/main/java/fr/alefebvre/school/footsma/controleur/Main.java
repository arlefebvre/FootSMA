package fr.alefebvre.school.footsma.controleur;

import fr.alefebvre.school.footsma.modele.AgentJoueur;
import fr.alefebvre.school.footsma.modele.AgentTerrain;
import fr.alefebvre.school.footsma.modele.Position;
import fr.alefebvre.school.footsma.modele.ReglesDuJeu;
import fr.alefebvre.school.footsma.vue.Fenetre;
import fr.alefebvre.school.footsma.vue.VueJoueur;
import fr.alefebvre.school.footsma.vue.VueTerrain;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] argss) {
        // Get a hold on JADE runtime
        Runtime rt = Runtime.instance();
        // Create a default profile
        Profile p = new ProfileImpl();
        // Create a new non-main container, connecting to the default
        // main container (i.e. on this host, port 1099)
        ContainerController cc = rt.createAgentContainer(p);
        // Create a new agent, a DummyAgent
        // and pass it a reference to an Object
        Color bleu = Color.BLUE;
        Color rouge = Color.RED;
        Color jaune = Color.YELLOW;

        // Creation d'un joueur
        Object[] args = new Object[7];
        VueJoueur vueJoueur1 = new VueJoueur();
        args[0] = vueJoueur1;
        args[1] = bleu;
        args[2] = new Position(ReglesDuJeu.getPosMillieuTerrain().getX() - 50, ReglesDuJeu.getPosMillieuTerrain().getY() + 50);
        args[3] = 10;
        args[4] = false;
        args[5] = 1;
        // Creation d'un joueur
        Object[] argsJoueur2 = new Object[7];
        VueJoueur vueJoueur2 = new VueJoueur();
        argsJoueur2[0] = vueJoueur2;
        argsJoueur2[1] = rouge;
        argsJoueur2[2] = new Position(ReglesDuJeu.getPosMillieuTerrain().getX() + 50, ReglesDuJeu.getPosMillieuTerrain().getY() - 50);
        argsJoueur2[3] = 7;
        argsJoueur2[4] = false;
        argsJoueur2[5] = 2;
        // Creation d'un joueur
        Object[] argsJoueur3 = new Object[7];
        VueJoueur vueJoueur3 = new VueJoueur();
        argsJoueur3[0] = vueJoueur3;
        argsJoueur3[1] = bleu;
        argsJoueur3[2] = new Position(ReglesDuJeu.getPosMillieuTerrain().getX() - 80, ReglesDuJeu.getPosMillieuTerrain().getY() - 80);
        argsJoueur3[3] = 22;
        argsJoueur3[4] = false;
        argsJoueur3[5] = 1;
        // Creation d'un joueur
        Object[] argsJoueur4 = new Object[7];
        VueJoueur vueJoueur4 = new VueJoueur();
        argsJoueur4[0] = vueJoueur4;
        argsJoueur4[1] = rouge;
        argsJoueur4[2] = new Position(ReglesDuJeu.getPosMillieuTerrain().getX() + 80, ReglesDuJeu.getPosMillieuTerrain().getY() + 80);
        argsJoueur4[3] = 6;
        argsJoueur4[4] = false;
        argsJoueur4[5] = 2;
        // Creation d'un joueur
        Object[] argsJoueur5 = new Object[7];
        VueJoueur vueJoueur5 = new VueJoueur();
        argsJoueur5[0] = vueJoueur5;
        argsJoueur5[1] = jaune;
        argsJoueur5[2] = new Position(ReglesDuJeu.getPosButEquipe1());
        argsJoueur5[3] = 1;
        argsJoueur5[4] = true;
        argsJoueur5[5] = 1;
        // Creation d'un joueur
        Object[] argsJoueur6 = new Object[7];
        VueJoueur vueJoueur6 = new VueJoueur();
        argsJoueur6[0] = vueJoueur6;
        argsJoueur6[1] = jaune;
        argsJoueur6[2] = new Position(ReglesDuJeu.getPosButEquipe2());
        argsJoueur6[3] = 1;
        argsJoueur6[4] = true;
        argsJoueur6[5] = 2;

        // Creation d'un joueur
        Object[] argsJoueur7 = new Object[7];
        VueJoueur vueJoueur7 = new VueJoueur();
        argsJoueur7[0] = vueJoueur7;
        argsJoueur7[1] = bleu;
        argsJoueur7[2] = new Position(60, 120);
        argsJoueur7[3] = 12;
        argsJoueur7[4] = false;
        argsJoueur7[5] = 1;

        // Creation d'un joueur
        Object[] argsJoueur8 = new Object[7];
        VueJoueur vueJoueur8 = new VueJoueur();
        argsJoueur8[0] = vueJoueur8;
        argsJoueur8[1] = rouge;
        argsJoueur8[2] = new Position(350, 60);
        argsJoueur8[3] = 14;
        argsJoueur8[4] = false;
        argsJoueur8[5] = 2;

        // On recupere toutes les vues créées pour les donner a la vueTerrain
        ArrayList<VueJoueur> joueurs = new ArrayList<>();
        joueurs.add(vueJoueur1);
        joueurs.add(vueJoueur2);
        joueurs.add(vueJoueur3);
        joueurs.add(vueJoueur4);
        joueurs.add(vueJoueur5);
        joueurs.add(vueJoueur6);
        joueurs.add(vueJoueur7);
        joueurs.add(vueJoueur8);
        // args2[1]=joueurs;
        VueTerrain vueTerrain = new VueTerrain(joueurs);
        for (VueJoueur joueur : joueurs) {
            joueur.setVueTerrain(vueTerrain);
        }
        Object[] args2 = new Object[1];
        args2[0] = vueTerrain;
        args[6] = vueTerrain;
        argsJoueur2[6] = vueTerrain;
        argsJoueur3[6] = vueTerrain;
        argsJoueur4[6] = vueTerrain;
        argsJoueur5[6] = vueTerrain;
        argsJoueur6[6] = vueTerrain;
        argsJoueur7[6] = vueTerrain;
        argsJoueur8[6] = vueTerrain;

        // AgentController dummy=null;
        AgentController joueur1 = null;
        AgentController joueur2 = null;
        AgentController joueur3 = null;
        AgentController joueur4 = null;
        AgentController joueur5 = null;
        AgentController joueur6 = null;
        AgentController joueur7 = null;
        AgentController joueur8 = null;
        AgentController terrain = null;
        //AgentController arbitre = null;
        try {
            // dummy =
            // cc.createNewAgent("inProcess","jade.tools.DummyAgent.DummyAgent",
            // args);
            joueur1 = cc.createNewAgent("joueur1", AgentJoueur.class.getName(),
                    args);
            joueur2 = cc.createNewAgent("joueur2",
                    AgentJoueur.class.getName(), argsJoueur2);
            joueur3 = cc.createNewAgent("joueur3",
                    AgentJoueur.class.getName(), argsJoueur3);
            joueur4 = cc.createNewAgent("joueur4",
                    AgentJoueur.class.getName(), argsJoueur4);
            joueur5 = cc.createNewAgent("joueur5",
                    AgentJoueur.class.getName(), argsJoueur5);
            joueur6 = cc.createNewAgent("joueur6",
                    AgentJoueur.class.getName(), argsJoueur6);
            joueur7 = cc.createNewAgent("joueur7",
                    AgentJoueur.class.getName(), argsJoueur7);
            joueur8 = cc.createNewAgent("joueur8",
                    AgentJoueur.class.getName(), argsJoueur8);
            // args2[1]=new AID("petit poney rouge",AID.ISLOCALNAME);
            terrain = cc.createNewAgent("terrain",
                    AgentTerrain.class.getName(), args2);
            //arbitre = cc.createNewAgent("arbitre","footsma.modele.fr.alefebvre.school.footsma.modele.AgentArbitre",null);
        } catch (StaleProxyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Fire up the agent
        try {
            // dummy.start();
            if (joueur1 != null) {
                joueur1.start();
            }
            if (joueur2 != null) {
                joueur2.start();
            }
            if (joueur3 != null) {
                joueur3.start();
            }
            if (joueur4 != null) {
                joueur4.start();
            }
            if (joueur5 != null) {
                joueur5.start();
            }
            if (joueur6 != null) {
                joueur6.start();
            }
            if (joueur7 != null) {
                joueur7.start();
            }
            if (joueur8 != null) {
                joueur8.start();
            }
            if (terrain != null) {
                terrain.start();
            }
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }

        Fenetre fen = new Fenetre(vueTerrain, cc);
        VueTerrain temp = fen.getVueTerrain();
        fen.setVueTerrain(temp);
        fen.getVueTerrain().repaint();
        fen.setSize(800, 600);
    }
}
