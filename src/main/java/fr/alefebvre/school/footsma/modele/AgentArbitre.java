/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Arthur Lefebvre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

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
    private AgentHandler handler;
    private boolean coupDEnvoiDonne;

    public void sifflerCoupDEnvoi() {
        ACLMessage msg;
        msg = new ACLMessage(ACLMessage.REQUEST);
        handler.getJoueursIds().forEach(msg::addReceiver);
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

        addBehaviour(new TickerBehaviour(this, 5000) {
            protected void onTick() {
                // perform operation Y
                System.out.println(tempsDeJeu + " minutes jouées");
                if (tempsDeJeu >= 90) {
                    sifflerFinDuMatch();
                    myAgent.doDelete();
                } else if (coupDEnvoiDonne)
                    tempsDeJeu++;
            }
        });
    }

    public void sifflerFinDuMatch() {
        ACLMessage msg;
        msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setOntology("temps");
        handler.getJoueursIds().forEach(msg::addReceiver);
        msg.setContent("STOP");
        send(msg);
    }

    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + ": terminating");
    }

    @Override
    public void render(Graphics g) {
        g.drawString(String.valueOf(tempsDeJeu), 0, 0);
    }
}
