package strely;

import kolizie.VelkostHitboxov;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * trieda pre stelu bossa/hraca
 *  @author Ľubomír Bojda
 */
public class Strela {
    private int xStrely;
    private int yStrely;
    private BufferedImage obrStrely;

    /**
     * konstruktor
     * @param obrStrely obrazok strely
     * @param zacX zaciatocne x
     * @param zacY zaciatocne y
     */
    public Strela(BufferedImage obrStrely, int zacX, int zacY) {
        this.xStrely = zacX;
        this.yStrely = zacY;
        this.obrStrely = obrStrely;
    }

    /**
     * get x strely
     * @returnx strely
     */
    public int getxStrely() {
        return this.xStrely;
    }

    /**
     * get y strely
     * @returnx y strely
     */
    public int getyStrely() {
        return this.yStrely;
    }

    /**
     * get obr strely
     * @return obr strely
     */
    public BufferedImage getObrStrely() {
        return this.obrStrely;
    }

    /**
     * set x strely
     * @param xStrely x strely
     */

    public void setxStrely(int xStrely) {
        this.xStrely = xStrely;
    }

    /**
     * vrat hitbox strely
     * @return
     */
    public Rectangle vratHitboxyStrely() {
        return new Rectangle(this.xStrely + 12, this.yStrely + 12, VelkostHitboxov.STRELA.getSirka(), VelkostHitboxov.STRELA.getVyska());
    }
}
