package levely;

import rozhranie.NacitavacObrazkov;
import rozhranie.OknoHry;

/**
 * enum pre typ levelu s hodnotami zaciatocnych pozicii hraca a bossa
 * @author Ľubomír Bojda
 */
public enum TypLevelu {
    MAPA((OknoHry.SIRKA_OKNA / 2) - (NacitavacObrazkov.VELKOST_HRACA_A_OBRAZKU_POZADIA / 2),
            (OknoHry.VYSKA_OKNA / 2) - (NacitavacObrazkov.VELKOST_HRACA_A_OBRAZKU_POZADIA / 2), 0, 0),
    LEVEL1(1020, 700, 10, 600),
    LEVEL2(300, 300, 800, 400),
    LEVEL3(300, 300, 800, 400);

    private int xHraca;
    private int yHraca;
    private int xBossa;
    private int yBossa;

    /**
     * konstruktor
     * @param xHraca x hraca
     * @param yHraca y hraca
     * @param xBossa x bossa
     * @param yBossa y bossa
     */
    TypLevelu(int xHraca, int yHraca, int xBossa, int yBossa) {
        this.xHraca = xHraca;
        this.yHraca = yHraca;
        this.xBossa = xBossa;
        this.yBossa = yBossa;
    }

    /**
     * getter pre x hraca
     * @return x hraca
     */
    public int getxHraca() {
        return this.xHraca;
    }

    /**
     * getter pre y hraca
     * @return y hraca
     */
    public int getyHraca() {
        return this.yHraca;
    }

    /**
     * getter pre x bossa
     * @return x bossa
     */
    public int getxBossa() {
        return this.xBossa;
    }
    /**
     * getter pre y bossa
     * @return y bossa
     */
    public int getyBossa() {
        return this.yBossa;
    }
}
