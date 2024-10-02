package strely;

import kolizie.Hitboxy;
import rozhranie.NacitavacObrazkov;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * trieda pre zaklad kazdej strely
 * @author Ľubomír Bojda
 */

public class StrelyZaklad {
    private BufferedImage strela;
    private int maximalnyPocetStriel;
    public static final int RYCHLOST_STRELY = 7;
    private ArrayList<Strela> praveStrely;
    private ArrayList<Strela> laveStrely;
    private Hitboxy<Rectangle> hitboxyStriel;
/**
 * konstruktor
 */
    public StrelyZaklad(String obrazokStrely, int riadok, int maximalnyPocetStriel) {
        var s = NacitavacObrazkov.nacitajObrazok(obrazokStrely);
        this.strela = NacitavacObrazkov.podobrazok(s, riadok, 1, "strela");
        this.maximalnyPocetStriel = maximalnyPocetStriel;
        this.praveStrely = new ArrayList<Strela>();
        this.laveStrely = new ArrayList<Strela>();
        this.hitboxyStriel = new Hitboxy<Rectangle>();
    }

    /**
     * animuj strely
     * @param g grafika
     */
    public void animujStrely(Graphics g) {
        for (Strela s : this.praveStrely) {
            s.setxStrely(s.getxStrely() + RYCHLOST_STRELY);
            g.drawImage(s.getObrStrely(), s.getxStrely(), s.getyStrely(), null);
        }

        for (Strela s : this.laveStrely) {
            s.setxStrely(s.getxStrely() - RYCHLOST_STRELY);
            g.drawImage(s.getObrStrely(), s.getxStrely(), s.getyStrely(), null);
        }
    }

    /**
     * pridaj strelu
     * @param smerStrely smer
     * @param x x strely
     * @param y y strely
     */
    public void pridajStrelu(String smerStrely, int x, int y) {
        var p = this.laveStrely.size() + this.praveStrely.size();
        if (p < this.maximalnyPocetStriel) {
            var s = new Strela(this.strela, x , y);
            switch (smerStrely) {
                case "lava" -> this.laveStrely.add(s);
                case "prava" -> this.praveStrely.add(s);
            }
        }
    }

    /**
     * obnov suradnice hitboxov
     */
    public void obnovSuradniceHitboxov() {
        this.hitboxyStriel.odstranVsetkyHitboxy();
        for (Strela s : this.praveStrely) {
            this.hitboxyStriel.pridajHitboxy(s.vratHitboxyStrely());
        }
        for (Strela s : this.laveStrely) {
            this.hitboxyStriel.pridajHitboxy(s.vratHitboxyStrely());
        }
    }

    /**
     * odstran vsetky strely
     */
    public void odstranStrely() {
        this.laveStrely.clear();
        this.praveStrely.clear();
        this.hitboxyStriel.odstranVsetkyHitboxy();
    }

    /**
     * vrat hitboxy striel
     * @return hitboxy striel
     */
    public Hitboxy<Rectangle> getHitboxyStriel() {
        return this.hitboxyStriel;
    }

    /**
     * vrat preve strely
     * @return preve strely
     */
    public ArrayList<Strela> getPraveStrely() {
        return this.praveStrely;
    }
    /**
     * vrat lave strely
     * @return lave strely
     */
    public ArrayList<Strela> getLaveStrely() {
        return this.laveStrely;
    }
}
