package hrac;

import strely.StrelyZaklad;
import rozhranie.OknoHry;

/**
 * trieda pre strely hraca
 * @author Ľubomír Bojda
 */
public class HracStrely extends StrelyZaklad {
    /**
     * konstruktor
     * @param riadok riadok obrazku strely
     * @param maximalnyPocetStriel max pocet striel na obrazovke
     */
    public HracStrely(int riadok, int maximalnyPocetStriel) {
        super("/textury/strelaHrac.png", riadok, maximalnyPocetStriel);
    }

    /**
     * urci smer strely podla toho kam sa pozera hrac
     * @param riadok riadok obr hraca
     * @return smer strely
     */
    public String urciSmerStrely(int riadok) {
        return switch (riadok) {
            case 2 -> "lava";
            case 3 -> "prava";
            default -> "";
        };
    }

    /**
     * odsrani strely, ktore su mimo obrazovku
     */
    public void odstranStrelyMimoPlochu() {
        super.getLaveStrely().removeIf(strela -> strela.getxStrely() + 32 < 0);
        super.getPraveStrely().removeIf(strela -> strela.getxStrely() + 32 > OknoHry.SIRKA_OKNA);
    }
}
