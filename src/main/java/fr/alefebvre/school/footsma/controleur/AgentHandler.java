package fr.alefebvre.school.footsma.controleur;

import java.awt.*;
import java.util.ArrayList;

public class AgentHandler {

    protected ArrayList<GameObject> objects = new ArrayList<>();

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

/*    public void tick() {
        objects.forEach(GameObject::tick);
    }*/

    public void render(Graphics g) {
        objects.forEach(agent -> agent.render(g));
    }
}
