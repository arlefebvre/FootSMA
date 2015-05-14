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

    private static int limit(int n, int min, int max) {
        if (n < min)
            return min;
        else if (n > max)
            return max;
        else
            return n;
    }

    public static Position milieu(Position p1, Position p2) {
        return new Position((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    public int getX() {
        return x;
    }

    public void setX(int xx) {
        this.x = limit(xx, 10, ReglesDuJeu.getLongueurTerrain() - 10);
    }

    public int getY() {
        return y;
    }

    public void setY(int yy) {
        this.y = limit(yy, 10, ReglesDuJeu.getLargeurTerrain() - 10);
    }

    public double distance(Position a) {
        return Math.sqrt((a.getX() - getX()) * (a.getX() - getX()) + (a.getY() - getY()) * (a.getY() - getY()));
    }

    public void approcher(Position p) {
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
