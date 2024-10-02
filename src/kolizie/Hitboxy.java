package kolizie;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * genericka trieda pre hitboxy
 * @param <E>
 * @author Ľubomír Bojda
 */
public class Hitboxy<E> {
    private ArrayList<E> hitboxy;

    /**
     * konstruktor
     */
    public Hitboxy() {
        this.hitboxy = new ArrayList<E>();
    }

    /**
     * vrati arraylist hitboxov
     * @return ArrayList
     */
    public ArrayList<E> getHitboxy() {
        return this.hitboxy;
    }

    /**
     * prida hitboxy do arraylistu
     * @param obdlznik
     */
    public void pridajHitboxy(E obdlznik) {
        if (obdlznik instanceof Rectangle) {
            this.hitboxy.add(obdlznik);
        }
    }

    /**
     * odstrani vsetko z arraylistu
     */
    public void odstranVsetkyHitboxy() {
        this.hitboxy.clear();
    }
}
