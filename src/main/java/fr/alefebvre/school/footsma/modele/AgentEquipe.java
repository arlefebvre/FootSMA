package fr.alefebvre.school.footsma.modele;

import fr.alefebvre.school.footsma.vue.VueTerrain;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class AgentEquipe extends Agent {
    private AID[] joueurs;
    private int numero;
    private VueTerrain vueTerrain;

    protected void setup() {
        Object[] args = getArguments();
        try {
            numero = (Integer) args[0];
            vueTerrain = (VueTerrain) args[1];
            //vue.setJoueurs((ArrayList<VueJoueur>) args[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (numero == 1) {
            joueurs = new AID[]{new AID("joueur1", AID.ISLOCALNAME),
                    new AID("joueur3", AID.ISLOCALNAME),
                    new AID("joueur5", AID.ISLOCALNAME), new AID("joueur7", AID.ISLOCALNAME)};
        } else {
            joueurs = new AID[]{new AID("joueur2", AID.ISLOCALNAME),
                    new AID("joueur4", AID.ISLOCALNAME),
                    new AID("joueur6", AID.ISLOCALNAME), new AID("joueur8", AID.ISLOCALNAME)};
        }
        System.out.println("Agent" + getLocalName() + " est créé");
    }

    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + ": terminating");
    }

    public void signalerPossession() {
        vueTerrain.setPossession(numero);
        ACLMessage msg;
        msg = new ACLMessage(ACLMessage.REQUEST);
        for (int i = 0; i < 4; i++) {
            msg.addReceiver(joueurs[i]);
        }
        msg.setContent("POSSESSION");
        send(msg);
    }

    public void signalerPerte() {
        if (numero == 1) vueTerrain.setPossessionEquipe1(false);
        else vueTerrain.setPossessionEquipe2(false);
        ACLMessage msg;
        msg = new ACLMessage(ACLMessage.REQUEST);
        for (int i = 0; i < 4; i++) {
            msg.addReceiver(joueurs[i]);
        }
        msg.setContent("PERTE");
        send(msg);
    }
}
