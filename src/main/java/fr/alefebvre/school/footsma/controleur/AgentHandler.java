package fr.alefebvre.school.footsma.controleur;

import fr.alefebvre.school.footsma.modele.AgentArbitre;
import fr.alefebvre.school.footsma.modele.AgentTerrain;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class AgentHandler {

    protected CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<>();

    public AgentArbitre getArbitre() {
        return arbitre;
    }

    private AgentArbitre arbitre;

    public AgentTerrain getTerrain() {
        return terrain;
    }

    private AgentTerrain terrain;

    public CopyOnWriteArrayList<GameObject> getObjects() {
        return objects;
    }

/*    public void tick() {
        objects.forEach(GameObject::tick);
    }*/

    public void render(Graphics g) {
        //List liste = Collections.synchronizedList(objects);
        objects.forEach(agent -> agent.render(g));
    }

    public void setTerrain(AgentTerrain terrain) {
        this.terrain = terrain;
    }

    public void startMatch() {
        if(arbitre!= null){
            arbitre.sifflerCoupDEnvoi();
        }
    }

    public void setArbitre(AgentArbitre arbitre) {
        this.arbitre = arbitre;
    }
}
