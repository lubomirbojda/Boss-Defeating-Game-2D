package levely;


import java.util.Random;
/**
 * trieda pre level 2
 * @author Ľubomír Bojda
 */
public class Level2 extends LevelZaklad implements AnimacieBossa {
    /**
     * konstruktor
     * @param riadokObrBossa riadok obr. bossa
     * @param stlpecObrBossa stlpec obr. bossa
     */
    public Level2(int riadokObrBossa, int stlpecObrBossa) {
        super(riadokObrBossa, stlpecObrBossa, TypLevelu.LEVEL2);
        super.pridajCisloNeutralnejPrekazky(0);
        super.pridajCisloNeutralnejPrekazky(7);
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
        var dole1 = super.getAnimacia("dole").get();

        if (!hore1.animaciaDokoncena()) {
            hore1.vykonajAnimaciu(0, 500);
            if (r.nextInt(10) == 1) {
                super.pridajStrelu("lava", x, y);
                super.pridajStrelu("prava", x, y);
            }
        } else if (!dole1.animaciaDokoncena()) {
            dole1.vykonajAnimaciu(0, 500);
        } else {
            super.odstranStrely();
            super.resetujVsetkyAnimacie();
        }
    }
}
