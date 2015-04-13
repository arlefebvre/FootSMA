package fr.alefebvre.school.footsma.modele;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Position(Position p) {
        this.x = p.x;
        this.y = p.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int xx) {

        if (xx >= 10 && xx < ReglesDuJeu.getLongueurTerrain() - 10) this.x = xx;
        else if (xx < 10) this.x = 10;
        else this.x = ReglesDuJeu.getLongueurTerrain() - 10;
    }

    public int getY() {
        return y;
    }

    public void setY(int yy) {
        if (yy >= 10 && yy < ReglesDuJeu.getLargeurTerrain() - 10) this.y = yy;
        else if (yy < 10) this.y = 10;
        else this.y = ReglesDuJeu.getLargeurTerrain() - 10;
    }

    public double distance(Position a) {
        return Math.sqrt((a.getX() - getX()) * (a.getX() - getX()) + (a.getY() - getY()) * (a.getY() - getY()));
    }

    public void Approcher(Position p) {
        if (getX() >= p.getX()) {
            if (!(getX() == p.getX())) setX(getX() - 2);
        } else {
            setX(getX() + 2);
        }
        if (getY() >= p.getY()) {
            if (!(getY() == p.getY())) setY(getY() - 2);
        } else {
            setY(getY() + 2);
        }
    }

    public void Fuir(Position p) {
        if (getX() >= p.getX()) {
            if (!(getX() == p.getX())) setX(getX() + 2);
        } else {
            setX(getX() - 2);
        }
        if (getY() >= p.getY()) {
            if (!(getY() == p.getY())) setY(getY() + 2);
        } else {
            setY(getY() - 2);
        }
    }

}
