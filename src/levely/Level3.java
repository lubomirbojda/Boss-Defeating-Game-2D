package levely;


import java.util.Random;
/**
 * trieda pre level 3
 * @author Ľubomír Bojda
 */
public class Level3 extends LevelZaklad implements AnimacieBossa {
    /**
     * konstruktor
     * @param riadokObrBossa riadok obr. bossa
     * @param stlpecObrBossa stlpec obr. bossa
     */
    public Level3(int riadokObrBossa, int stlpecObrBossa) {
        super(riadokObrBossa, stlpecObrBossa, TypLevelu.LEVEL3);
        super.pridajCisloNeutralnejPrekazky(0);
        super.pridajCisloNeutralnejPrekazky(9);
    }
    /**
     * animuje bossa - meni suradnice
     */
    @Override
    public void animujBossa() {
        var r = new Random();
        var x = (super.getxBossa() + 64) - 16;
        var y = (super.getyBossa() + 64) - 16;
    }
}
