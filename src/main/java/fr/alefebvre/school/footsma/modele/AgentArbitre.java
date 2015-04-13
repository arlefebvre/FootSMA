package fr.alefebvre.school.footsma.modele;

import fr.alefebvre.school.footsma.vue.VueTerrain;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import javax.swing.*;

public class AgentArbitre extends Agent {
    private int scoreEquipe1;
    private int scoreEquipe2;
    private int tempsDeJeu = 0;
    private JLabel temps;
    private AID[] joueurs = {new AID("joueur1", AID.ISLOCALNAME),
            new AID("joueur2", AID.ISLOCALNAME),
            new AID("joueur3", AID.ISLOCALNAME),
            new AID("joueur4", AID.ISLOCALNAME),
            new AID("joueur5", AID.ISLOCALNAME),
            new AID("joueur6", AID.ISLOCALNAME),
            new AID("joueur7", AID.ISLOCALNAME),
            new AID("joueur8", AID.ISLOCALNAME)};

    private AID[] terrain;
    private VueTerrain vueTerrain;
    private Position pos;

    //private MessageTemplate template = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),MessageTemplate.MatchOntology("temps"));


    public void sifflerCoupDEnvoi() {
        // TODO Auto-generated method stub
        ACLMessage msg;
        msg = new ACLMessage(ACLMessage.REQUEST);
        for (int i = 0; i < 8; i++) {
            msg.addReceiver(joueurs[i]);
        }
        msg.setContent("Jouez");
        send(msg);
    }

    @Override
    protected void setup() {
        // TODO Auto-generated method stub
        Object[] args = getArguments();
        temps = (JLabel) args[0];
        vueTerrain = (VueTerrain) args[1];

        ContainerController cc = getContainerController();
        AgentController equipe1 = null;
        AgentController equipe2 = null;
        Object[] arg1 = new Object[2];
        arg1[0] = 1;
        arg1[1] = vueTerrain;
        Object[] arg2 = new Object[2];
        arg2[0] = 2;
        arg1[1] = vueTerrain;
        try {
            equipe1 = cc.createNewAgent("equipe1", AgentEquipe.class.getName(), arg1);
            equipe2 = cc.createNewAgent("equipe2", AgentEquipe.class.getName(), arg2);
        } catch (StaleProxyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            equipe1.start();
            equipe2.start();
        } catch (StaleProxyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        sifflerCoupDEnvoi();

        addBehaviour(new TickerBehaviour(this, 5000) {
            protected void onTick() {
                // perform operation Y
                System.out.println(tempsDeJeu + " minutes jouées");
                if (temps != null) {
                    //fen.setTemps(new JLabel(String.valueOf(tempsDeJeu)+" min"));
                    temps.setText(String.valueOf(tempsDeJeu) + " min");
                }
                if (tempsDeJeu >= 90) {
                    sifflerFinDuMatch();
                    myAgent.doDelete();
                } else tempsDeJeu++;
            }
        });

    }

    public void sifflerFinDuMatch() {
        // TODO Auto-generated method stub
        ACLMessage msg;
        msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setOntology("temps");
        for (int i = 0; i < 8; i++) {
            msg.addReceiver(joueurs[i]);
        }
        msg.setContent("STOP");
        send(msg);
    }

    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + ": terminating");
    }

}
