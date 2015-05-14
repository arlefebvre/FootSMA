package fr.alefebvre.school.footsma.modele;

import fr.alefebvre.school.footsma.controleur.AgentHandler;
import fr.alefebvre.school.footsma.controleur.GameObject;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.awt.*;

public class AgentArbitre extends GameObject {
    private int scoreEquipe1;
    private int scoreEquipe2;
    private int tempsDeJeu = 0;
    private Position pos;
    //private AgentTerrain terrain;
    private AgentHandler handler;
    private boolean coupDEnvoiDonne;

    //private MessageTemplate template = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),MessageTemplate.MatchOntology("temps"));

    public void sifflerCoupDEnvoi() {
        ACLMessage msg;
        msg = new ACLMessage(ACLMessage.REQUEST);
        handler.getJoueursIds().forEach(aid ->msg.addReceiver(aid));
        //terrain.getJoueurs().forEach(joueur ->msg.addReceiver(joueur.getAID()));
        msg.setContent("Jouez");
        send(msg);
        coupDEnvoiDonne = true;
    }

    protected void setup() {
        Object[] args = getArguments();
        tempsDeJeu = (int) args[0];
        handler = (AgentHandler) args[1];
        handler.getObjects().add(this);
        handler.setArbitreId(this.getAID());
        //terrain = handler.getTerrain();

       /* ContainerController cc = getContainerController();
        AgentController equipe1 = null;
        AgentController equipe2 = null;
        Object[] arg1 = new Object[2];
        arg1[0] = 1;
        arg1[1] = terrain;
        Object[] arg2 = new Object[2];
        arg2[0] = 2;
        arg1[1] = terrain;
        try {
            equipe1 = cc.createNewAgent("equipe1", AgentEquipe.class.getName(), arg1);
            equipe2 = cc.createNewAgent("equipe2", AgentEquipe.class.getName(), arg2);
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
        try {
            if (equipe1 != null) {
                equipe1.start();
            }
            if (equipe2 != null) {
                equipe2.start();
            }
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }*/
        addBehaviour(new TickerBehaviour(this, 5000) {
            protected void onTick() {
                // perform operation Y
                System.out.println(tempsDeJeu + " minutes jouées");
                if (tempsDeJeu >= 90) {
                    sifflerFinDuMatch();
                    myAgent.doDelete();
                } else if(coupDEnvoiDonne)
                    tempsDeJeu++;
            }
        });

    }

    public void sifflerFinDuMatch() {
        ACLMessage msg;
        msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setOntology("temps");
        handler.getJoueursIds().forEach(aid->msg.addReceiver(aid));
        msg.setContent("STOP");
        send(msg);
    }

    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + ": terminating");
    }

    @Override
    public void render(Graphics g) {
        g.drawString(String.valueOf(tempsDeJeu),0,0);
    }
}
