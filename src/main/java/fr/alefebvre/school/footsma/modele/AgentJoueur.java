package fr.alefebvre.school.footsma.modele;

import fr.alefebvre.school.footsma.controleur.AgentHandler;
import fr.alefebvre.school.footsma.controleur.GameObject;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class AgentJoueur extends GameObject {
    //public VueJoueur vue = new VueJoueur();
    private AgentHandler handler;
    public int count = 0;
    protected Position pos;
    private Color couleurMaillot;
    private int numero;
    private int numeroEquipe;
    private boolean matchTermine = false;
    private boolean gardien;
    private boolean possessionEquipe = false;
    private boolean possessionJoueur = false;
    private int vitesse;
    private int tacles;
    private int dribles;
    private int arrets;
    private int tirs;
    //private AgentTerrain terrain;
    private boolean possession;

    protected void setup() {
        Object[] args = getArguments();
        try {
            handler = (AgentHandler) args[0];
            handler.getObjects().add(this);
            //vue.setAgentJoueur(getAID());
            couleurMaillot = (Color) args[1];
            //vue.setCouleurMaillot(couleurMaillot);
            pos = (Position) args[2];
            //vue.setPos(pos);
            numero = (Integer) args[3];
            gardien = (Boolean) args[4];
            //vue.setNumero(numero);
            numeroEquipe = (Integer) args[5];
            handler.ajouteJoueur(this.getAID(),numeroEquipe);
            //vue.setNumeroEquipe(numeroEquipe);
            //vue.setVueTerrain((VueTerrain) args[6]);
            //terrain = (AgentTerrain) args[6];
            /*terrain = handler.getTerrain();
            terrain.addJoueur(this);*/

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("probleme de recuperation des parametres pour : " + getLocalName());
        }
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        vitesse = rand.nextInt(400) + 100;
        tacles = rand.nextInt(100);
        dribles = (rand.nextInt(80) + 20);
        arrets = (rand.nextInt(100));
        tirs = (rand.nextInt(80) + 20);
        //possession(possessionJoueur);
        System.out.println("Agent" + getLocalName() + " est créé");

		/*addBehaviour(new OneShotBehaviour(this){
            public void action(){
			    //pos.setX(pos.getX()+20);
			  // pos.setY(pos.getY()+50);
			   // vue.setPos(pos);
		  }
	  });
		System.out.println("end of behaviour oneshot");*/

        System.out.println("Agent " + getLocalName() + ": en attente du coup d'envoi...");
        ACLMessage msg = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
        System.out.println("Agent " + getLocalName() + ": a entendu l'arbitre siffler le coup d'envoi");
        ACLMessage reply = new ACLMessage(ACLMessage.INFORM);
        reply.addReceiver(msg.getSender());
        reply.setContent("OK");
        send(reply);
        //doDelete();

        ParallelBehaviour comportementparallele = new ParallelBehaviour(ParallelBehaviour.WHEN_ANY);
        comportementparallele.addSubBehaviour(new TickerBehaviour(this, vitesse) {
            @Override
            protected void onTick() {
                //System.out.println("Tick");
                //System.out.println(myAgent.getLocalName()+"cherche la prochaine action qu'il va effectuer");
                if (numeroEquipe == 1) {
                    possessionEquipe = handler.getTerrain().isPossessionEquipe1();

                } else possessionEquipe = handler.getTerrain().isPossessionEquipe2();
                //if(count>0) count--;
                //else
                if (!possessionEquipe && !gardien) { //Si mon equipe n'a pas le ballon
                    //System.out.println(getLocalName()+ ": mon equipe n'a pas le ballon");
                    if (ReglesDuJeu.getSeuilDeProximite() >= pos.distance(handler.getTerrain().getBallonPos())) {
                        //Si on est a proximité du ballon
                        System.out.println(myAgent.getLocalName() + " est a proximité du ballon");
                        //On demande d'abord au handler.getTerrain() si le ballon est disponible
                        ACLMessage demandeBallon = new ACLMessage(ACLMessage.QUERY_IF);
                        demandeBallon.setOntology("ballon");
                        demandeBallon.addReceiver(new AID("handler.getTerrain()", AID.ISLOCALNAME));
                        demandeBallon.setContent("BALLONDISPO");
                        send(demandeBallon);
                        //System.out.println(myAgent.getLocalName()+" a effectué une demande pour savoir si le ballon etait disponible");

                        if (!handler.getTerrain().isBallonDisponible()) {
                            //System.out.println("pas dispo pour "+getLocalName());

                            //Tenter un tacle
                            addBehaviour(new Behaviour() {
                                public void action() {
                                    /*boolean trouve = false;
                                    int j = 0;
                                    while (!trouve && j < handler.getTerrain().getJoueurs().size()) {
                                        if (handler.getTerrain().getJoueurs().get(j).getAID() == handler.getTerrain().getJoueurAuBallon()) {
                                            trouve = true;
                                        } else {
                                            j++;
                                        }
                                    }*/
                                    AgentJoueur joueurAuBallon = handler.getJoueur(handler.getTerrain().getJoueurAuBallon());
                                    if (joueurAuBallon!=null) {
                                        if (tacles > joueurAuBallon.getDribles()) {
                                            handler.getTerrain().setPossession(numeroEquipe);
                                            possessionJoueur = true;
                                            handler.getTerrain().setBallonPos(pos);
                                            joueurAuBallon.setPossessionJoueur(false);
                                            System.out.println(getLocalName() + " a reussi un tacle");

                                        } else {
                                            count += 1;
                                            System.out.println(joueurAuBallon.getLocalName() + " a reussi un dribble");
                                        }
                                    } else System.out.println("Cible du tacle non trouvée");
                                }

                                public boolean done() {
                                    return true;
                                }
                            });
                        } else {//prendre le ballon
                            handler.getTerrain().setBallonDisponible(false);
                            handler.getTerrain().setJoueurAuBallon(myAgent.getAID());
                            handler.getTerrain().setPosJoueurAuBallon(pos);
                            possessionJoueur = true;
                            //vue.setPossession(true);
                            handler.getTerrain().setBallonPos(new Position(pos));
                            // possessionEquipe
                            possessionEquipe = true;
                            handler.getTerrain().setPossession(numero);
                            if (numeroEquipe == 1) {
                                pos.approcher(ReglesDuJeu.getPosButEquipe2());
                            } else pos.approcher(ReglesDuJeu.getPosButEquipe1());
                            System.out.println(myAgent.getLocalName() + " a r�cup�r� le ballon");
                        }
                    }

                    // Je me rapproche du ballon
                    pos.approcher(handler.getTerrain().getBallonPos());
                } else if (!gardien) { // Si mon equipe a le ballon
                    System.out.println(getLocalName() + ": mon equipe a le ballon");
                    if (!possessionJoueur) {// Si ce n'est pas moi qui ait le ballon, mais un coequipier
                        if (numeroEquipe == 1) {
                            pos.Fuir(ReglesDuJeu.getPosButEquipe1());
                        } else pos.Fuir(ReglesDuJeu.getPosButEquipe2());
                    } else { // Si j'ai le ballon
                        System.out.println(getLocalName() + " a le ballon");
                        if (numeroEquipe == 1) {
                            if (ReglesDuJeu.getSeuilDeProximite() * 3 < pos.distance(ReglesDuJeu.getPosButEquipe2())) {//si je suis loin du but adverse
                                pos.approcher(ReglesDuJeu.getPosButEquipe2());
                                //vue.getVueTerrain().setBallonPos(new fr.alefebvre.school.footsma.modele.Position(pos));
                                System.out.println(getLocalName() + " va au but");
                            } else {
                                //tenter un tir
                                System.out.println(getLocalName() + " tente une frappe");
                            }
                        } else {
                            if (ReglesDuJeu.getSeuilDeProximite() * 3 < pos.distance(ReglesDuJeu.getPosButEquipe1())) {//si je suis loin du but adverse
                                pos.approcher(ReglesDuJeu.getPosButEquipe1());
                                //vue.getVueTerrain().setBallonPos(new fr.alefebvre.school.footsma.modele.Position(pos));
                                System.out.println(getLocalName() + " va au but");
                            } else {
                                //tenter un tir
                                System.out.println(getLocalName() + " tente une frappe");
                            }
                        }

                    }
                }

                //pos.setX(pos.getX()+5);
                //vue.setPos(pos);
                //vue.getVueTerrain().repaint();
                //Si je suis a proximite du ballon
                //myAgent.doDelete();
            }
        });
        comportementparallele.addSubBehaviour(new CyclicBehaviour(this) {

            public void action() {
                ACLMessage msg = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("temps")));
                if (msg != null) {
                    // Process the message
                    if (Objects.equals(msg.getContent(), "STOP")) matchTermine = true;
                    System.out.println(myAgent.getLocalName() + " a entendu le coup de sifflet final");
                    myAgent.doDelete();
                }
            }
        });

        addBehaviour(comportementparallele);


        // Make this agent terminate
        //doDelete();
    }

    public void doDelete() {
        super.doDelete();
        //vue.setPos(null);
        //vue.getVueTerrain().repaint();
    }

    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + ": terminating");
    }

    @Override
    public void render(Graphics g) {
        g.setColor(couleurMaillot);
        g.fillOval(pos.getX(), pos.getY(), 20, 20);
        g.setColor(Color.WHITE);
        if (numero < 10) {
            g.drawString(String.valueOf(numero), pos.getX() + 7, pos.getY() + 15);
        } else {
            g.drawString(String.valueOf(numero), pos.getX() + 3, pos.getY() + 15);
        }
        g.drawOval(pos.getX(), pos.getY(), 20, 20);
    }

    public int getDribles() {
        return dribles;
    }

    public void setPossessionJoueur(boolean possession) {
        this.possessionJoueur = possession;
    }
}
