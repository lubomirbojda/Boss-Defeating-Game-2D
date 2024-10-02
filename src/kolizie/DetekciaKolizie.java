package kolizie;

import java.awt.Rectangle;

/**
 * trieda na detekovanie kolizie v hre
 * @author Ľubomír Bojda
 */
public class DetekciaKolizie {
    private Rectangle hitboxyHrac;
    private Rectangle hitboxyBoss;
    private Hitboxy<Rectangle> hitoxyStrelyHrac;
    private Hitboxy<Rectangle> hitboxyStrelyBoss;
    private Hitboxy<Rectangle> hitboxyNeutralne;

    /**
     * konstruktor
     */
    public DetekciaKolizie() {
        this.hitboxyBoss = new Rectangle();
        this.hitboxyHrac = new Rectangle();
        this.hitboxyStrelyBoss = new Hitboxy<Rectangle>();
        this.hitboxyNeutralne = new Hitboxy<Rectangle>();
        this.hitoxyStrelyHrac = new Hitboxy<Rectangle>();
    }

    /**
     * vrati true ak hrac mal kolizie s bossom alebo jeho strelou
     * @return true/false
     */
    public boolean testKolizieHrac() {
        boolean hit = false;
        for (Rectangle r : this.hitboxyStrelyBoss.getHitboxy()) {
            if (r.intersects(this.hitboxyHrac)) {
                hit = true;
            }
        }

        if (this.hitboxyHrac.intersects(this.hitboxyBoss)) {
            hit = true;
        }

        return hit;
    }

    /**
     * vrati true ak hrac zasiahol jeho strelou bossa
     * @return true/false
     */
    public boolean testKolizieBossAHracovaStrela() {
        boolean hit = false;
        for (Rectangle r : this.getHitoxyStrelyHrac().getHitboxy()) {
            if (r.intersects(this.hitboxyBoss)) {
                hit = true;
            }
        }

        return hit;
    }

    /**
     * testuje kolizie neutralnych prekazok na mape, cez ktore nemoze hrac chodit
     * @param x1 x1 ciary na otestovanie
     * @param y1 y1 ciary na otestovanie
     * @param x2 x2 ciary na otestovanie
     * @param y2 y2 ciary na otestovanie
     * @return true/false
     */
    public boolean testKolizieNeutralne(double x1, double y1, double x2, double y2) {
        for (Rectangle r : this.hitboxyNeutralne.getHitboxy()) {
            if (r.intersectsLine(x1, y1, x2, y2)) {
                return true;
            }
        }

        return false;
    }

    /**
     * otestuje ci hrac ma nad sebou prekazku
     * @return true/false
     */
    public boolean testKolizieNeutralneHore() {
        //horna strana hracovych hitboxov
        var x1 = this.hitboxyHrac.getX();
        var y1 = this.hitboxyHrac.getY();
        var x2 = this.hitboxyHrac.getMaxX();
        var y2 = this.hitboxyHrac.getY();

        return this.testKolizieNeutralne(x1 + 12, y1, x2 - 12, y2);
    }
    /**
     * otestuje ci hrac ma pod sebou prekazku
     * @return true/false
     */
    public boolean testKolizieNeutralneDole() {
        //dolna strana hracovych hitboxov
        var x1 = this.hitboxyHrac.getX();
        var y1 = this.hitboxyHrac.getMaxY();
        var x2 = this.hitboxyHrac.getMaxX();
        var y2 = this.hitboxyHrac.getMaxY();

        return this.testKolizieNeutralne(x1 + 12, y1, x2 - 12, y2);
    }
    /**
     * otestuje ci hrac ma nalavo od seba prekazku
     * @return true/false
     */

    public boolean testKolizieNeutralneVlavo() {
        //lava strana hracovych hitboxov
        var x1 = this.hitboxyHrac.getX();
        var y1 = this.hitboxyHrac.getY();
        var x2 = this.hitboxyHrac.getX();
        var y2 = this.hitboxyHrac.getMaxY();

        return this.testKolizieNeutralne(x1, y1 + 12, x2, y2 - 12);
    }
    /**
     * otestuje ci hrac ma napravo od seba prekazku
     * @return true/false
     */
    public boolean testKolizieNeutralneVpravo() {
        //prava strana hracovych hitboxov
        var x1 = this.hitboxyHrac.getMaxX();
        var y1 = this.hitboxyHrac.getY();
        var x2 = this.hitboxyHrac.getMaxX();
        var y2 = this.hitboxyHrac.getMaxY();

        return this.testKolizieNeutralne(x1, y1 + 12, x2, y2 - 12);
    }

    /**
     * vrati obdlznik s hracovymi hitboxami
     * @return hrac - hitboxy
     */
    public Rectangle getHitboxyHrac() {
        return this.hitboxyHrac;
    }

    /**
     * vrati obdlznik s bossovymi hitboxami
     * @return boss - hitboxy
     */
    public Rectangle getHitboxyBoss() {
        return this.hitboxyBoss;
    }

    /**
     * vrati hitboxy bossovych striel
     * @return boss strely
     */
    public Hitboxy<Rectangle> getHitboxyStrelyBoss() {
        return this.hitboxyStrelyBoss;
    }

    /**
     * vrati hitboxy neutralnych prekazok
     * @return hitboxy neutralnych prek.
     */
    public Hitboxy<Rectangle> getHitboxyNeutralne() {
        return this.hitboxyNeutralne;
    }

    /**
     * vrati hitboxy hracovych striel
     * @return hracove strely - hitboxy
     */
    public Hitboxy<Rectangle> getHitoxyStrelyHrac() {
        return this.hitoxyStrelyHrac;
    }
}
