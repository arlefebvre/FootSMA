package fr.alefebvre.school.footsma.modele;

/**
 * Created by alefebvre on 27/05/2015.
 */
public abstract class MathHelper {

    public static int limit(int n, int min, int max) {
        if (n < min)
            return min;
        else if (n > max)
            return max;
        else
            return n;
    }
}
