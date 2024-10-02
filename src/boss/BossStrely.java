package boss;

import strely.StrelyZaklad;

/**
 * trieda pre strely boosa
 *
 * @author Ľubomír Bojda
 */
public class BossStrely extends StrelyZaklad {
    /**
     * konstruktor
     * @param riadok riadok obrazku strely
     * @param maximalnyPocetStriel max pocet striel na obrazovke
     */
    public BossStrely(int riadok, int maximalnyPocetStriel) {
        super("/textury/strely.png", riadok, maximalnyPocetStriel);
    }


}
