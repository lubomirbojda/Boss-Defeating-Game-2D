package levely;

import java.util.Random;

/**
 * trieda pre level 1
 * @author Ľubomír Bojda
 */
public class Level1 extends LevelZaklad implements AnimacieBossa {
    /**
     * konstruktor
     * @param riadokObrBossa riadok obr. bossa
     * @param stlpecObrBossa stlpec obr. bossa
     */
    public Level1(int riadokObrBossa, int stlpecObrBossa) {
        super(riadokObrBossa, stlpecObrBossa, TypLevelu.LEVEL1);
        super.pridajCisloNeutralnejPrekazky(0);
        super.pridajCisloNeutralnejPrekazky(5);
    }

    /**
     * animuje bossa - meni suradnice
     */
    @Override
    public void animujBossa() {
        var r = new Random();
        var x = (super.getxBossa() + 64) - 16;
        var y = (super.getyBossa() + 64) - 16;

        var hore1 = super.getAnimacia("hore").get();
        var doprava1 = super.getAnimacia("doprava").get();
        var dole1 = super.getAnimacia("dole").get();
        var dolava1 = super.getAnimacia("dolava").get();

        if (!hore1.animaciaDokoncena()) {
            hore1.vykonajAnimaciu(0, 500);
            if (r.nextInt(20) == 1) {
                super.pridajStrelu("prava", x, y);
            }
        } else if (!doprava1.animaciaDokoncena()) {
            doprava1.vykonajAnimaciu(800, 0);
        } else if (!dole1.animaciaDokoncena()) {
            dole1.vykonajAnimaciu(0, 500);
            if (r.nextInt(20) == 1) {
                super.pridajStrelu("lava", x , y);
            }
        } else if (!dolava1.animaciaDokoncena()) {
            dolava1.vykonajAnimaciu(800, 0);
        } else {
            super.odstranStrely();
            super.resetujVsetkyAnimacie();
        }
    }
}
