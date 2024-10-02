package kolizie;

/**
 * enum pre ziskavanie velkosti roznych hitboxov
 *
 * @author Ľubomír Bojda
 */
public enum VelkostHitboxov {
    HRAC(32, 50),
    BOSS(90, 65),
    STRELA(20, 20);

    private int sirka;
    private int vyska;

    /**
     * konstruktor
     * @param sirka sirka hitboxov
     * @param vyska vyska hitboxov
     */
    VelkostHitboxov(int sirka, int vyska) {
        this.sirka = sirka;
        this.vyska = vyska;
    }

    /**
     * vrati sirku hitboxov
     * @return sirka
     */
    public int getSirka() {
        return this.sirka;
    }

    /**
     * vrati vysku hitboxov
     * @return vyska
     */
    public int getVyska() {
        return this.vyska;
    }
}
