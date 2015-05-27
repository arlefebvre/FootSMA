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

    public static Position milieu(Position p1, Position p2) {
        return new Position((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    public int getX() {
        return x;
    }

    public void setX(int xx) {
        this.x = MathHelper.limit(xx, 10, ReglesDuJeu.getLongueurTerrain() - 10);
    }

    public int getY() {
        return y;
    }

    public void setY(int yy) {
        this.y = MathHelper.limit(yy, 10, ReglesDuJeu.getLargeurTerrain() - 10);
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
