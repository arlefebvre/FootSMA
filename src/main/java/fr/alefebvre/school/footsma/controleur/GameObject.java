package fr.alefebvre.school.footsma.controleur;

import jade.core.Agent;

import java.awt.*;

public abstract class GameObject extends Agent {

    protected int x, y;

    protected boolean visible = false;
    protected String id;
    protected int velX, velY;

    public void setGameObject(int x, int y, String id) {
        //super();
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void setGameObject(int x, int y, String id, int velX, int velY) {
        setGameObject(x, y, id);
        this.velX = velX;
        this.velY = velY;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public abstract void render(Graphics g);
}
