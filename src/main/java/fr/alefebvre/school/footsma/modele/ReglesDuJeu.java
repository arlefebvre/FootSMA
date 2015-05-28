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

import java.awt.*;

public abstract class ReglesDuJeu {
    public static final int NBJOUEURS = 4;
    public static final Color COULEUR_GARDIENS = Color.BLACK;
    public static final Color COULEUR_EQUIPE_1 = Color.RED;
    public static final Color COULEUR_EQUIPE_2 = Color.BLUE;
    public static final Color COULEUR_ARBITRE = Color.YELLOW;
    private static final int largeurTerrain = 290;
    private static final int longueurTerrain = 440;
    private static Position posMillieuTerrain = new Position(225, 145);
    private static Position posButEquipe1 = new Position(10, posMillieuTerrain.getY());
    private static Position posButEquipe2 = new Position(posMillieuTerrain.getX() * 2 - 20, posMillieuTerrain.getY());
    private static double seuilDeProximite = 10;

    public static Position getPosMillieuTerrain() {
        return posMillieuTerrain;
    }

    public static void setPosMillieuTerrain(Position posMillieuTerrain) {
        ReglesDuJeu.posMillieuTerrain = posMillieuTerrain;
    }

    public static int getLargeurTerrain() {
        return largeurTerrain;
    }

    public static int getLongueurTerrain() {
        return longueurTerrain;
    }

    public static double getSeuilDeProximite() {
        return seuilDeProximite;
    }

    public static void setSeuilDeProximite(double seuilDeProximite) {
        ReglesDuJeu.seuilDeProximite = seuilDeProximite;
    }

    public static Position getPosButEquipe1() {
        return posButEquipe1;
    }

    public static void setPosButEquipe1(Position posButEquipe1) {
        ReglesDuJeu.posButEquipe1 = posButEquipe1;
    }

    public static Position getPosButEquipe2() {
        return posButEquipe2;
    }

    public static void setPosButEquipe2(Position posButEquipe2) {
        ReglesDuJeu.posButEquipe2 = posButEquipe2;
    }


}
