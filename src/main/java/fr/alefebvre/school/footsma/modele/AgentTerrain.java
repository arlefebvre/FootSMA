package fr.alefebvre.school.footsma.modele;

import fr.alefebvre.school.footsma.vue.VueTerrain;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgentTerrain extends Agent {
    private VueTerrain vue;
    private Position ballonPos;
    private boolean ballonDisponible = true;
    private AID joueurAuBallon;

    private MessageTemplate template = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.QUERY_IF),
            MessageTemplate.MatchOntology("ballon"));

    @Override
    protected void setup() {
        // TODO Auto-generated method stub
        Object[] args = getArguments();
        try {
            vue = (VueTerrain) args[0];
            //vue.setJoueurs((ArrayList<VueJoueur>) args[1]);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Agent" + getLocalName() + " est créé");
        // Make this agent terminate
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = myAgent.receive(template);
                if (msg != null) {
                    System.out.println("Received QUERY_IF message from agent " + msg.getSender().getName());
                    ACLMessage reply = msg.createReply();
                    reply.setOntology("ballon");
                    if ("BALLONDISPO".equals(msg.getContent())) {
                        if (ballonDisponible) {
                            reply.setPerformative(ACLMessage.INFORM);
                            reply.setContent("dispo");
                            ballonDisponible = false;
                            //vue.setBallonDisponible(false);
                            //vue.setJoueurAuBallon(msg.getSender());
                            joueurAuBallon = msg.getSender();
                        } else {
                            reply.setPerformative(ACLMessage.INFORM);
                            reply.setContent(vue.getJoueurAuBallon().getLocalName());
                        }
                    } else {
                        reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                        reply.setContent("Unknown-content");
                    }
                    myAgent.send(reply);
                    //System.out.println(myAgent.getLocalName()+" a repondu "+reply.getContent()+" a "+msg.getSender().getLocalName());
                } else {
                    block();
                }

					
					
					
					/*ACLMessage msg = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
					//ACLMessage msg = myAgent.receive();
					if (msg != null) {
					// Process the message
						//System.out.println(myAgent.getLocalName()+" a reçu un message ");
						if(msg.getContent()=="BALLONDISPO"){
							System.out.println(myAgent.getLocalName()+" a reçu un message de "+msg.getSender().getLocalName());
							ACLMessage reply = new ACLMessage(ACLMessage.INFORM);
							reply.addReceiver(msg.getSender());
							
							if(ballonDisponible==true){
								reply.setContent("Personne");
								ballonDisponible=false;
								joueurAuBallon=msg.getSender();
							} else {
								reply.setContent(joueurAuBallon.getLocalName());
							}
							send(reply);
							System.out.println(myAgent.getLocalName()+" a repondu a "+msg.getSender().getLocalName());
						}
					}*/
            }

        });
        //doDelete();
    }


    @Override
    public void doDelete() {
        // TODO Auto-generated method stub
        super.doDelete();
        vue.removeAll();
    }

    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + ": terminating");
    }

}
