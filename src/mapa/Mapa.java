package mapa;

import levely.LevelZaklad;
import levely.TypLevelu;
import rozhranie.NacitavacObrazkov;
import rozhranie.OknoHry;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * trieda mapy hry
 * @author Ľubomír Bojda
 */

public class Mapa extends LevelZaklad {
    private String suborSPozadim;
    private MiestnostMapy aktualnaMiestnost;
    private TypLevelu typLeveluMapy;
    private MiestnostMapy miestnostMapySpawn;

    /**
     * konstruktor
     * @param riadok riadok obr.
     * @param stlpec stlpec obr.
     */
    public Mapa(int riadok, int stlpec) {
        super(riadok, stlpec, TypLevelu.MAPA);
        super.pridajCisloNeutralnejPrekazky(0);
        super.pridajCisloNeutralnejPrekazky(2);
        this.suborSPozadim = "/pozadia/mapaSpawn.txt";
        this.miestnostMapySpawn = new MiestnostMapy("/pozadia/mapaSpawn.txt");
        this.typLeveluMapy = TypLevelu.MAPA;
        this.nacitajMiestnosti();
    }

    /**
     * vrati hitboxy vstupu do levelu
     * @return vstup do levelu - hitboxy
     */
    public Rectangle vstupDoLevelu() {
        var n = NacitavacObrazkov.VELKOST_HRACA_A_OBRAZKU_POZADIA;
        var vstup = new Rectangle();

        switch (this.suborSPozadim) {
            case "/pozadia/mapaL1.txt":
                vstup.setBounds(8 * n, 5 * n, 3 * n, 3 * n);
                this.typLeveluMapy = TypLevelu.LEVEL1;
                return vstup;
            case "/pozadia/mapaL2.txt":
                vstup.setBounds(8 * n, 5 * n, 3 * n, 3 * n);
                this.typLeveluMapy = TypLevelu.LEVEL2;
                return vstup;
            case "/pozadia/mapaL3.txt":
                vstup.setBounds(8 * n, 5 * n, 3 * n, 3 * n);
                this.typLeveluMapy = TypLevelu.LEVEL3;
                return vstup;
        }

        return null;
    }

    /**
     * vyberie pozadie pre mapu na zaklade suradnic
     * @param xHraca x hraca
     * @param yHraca y hraca
     * @return
     */
    public String vyberPozadie(int xHraca, int yHraca) {
        String smerPohnutia = "";

        if (yHraca + NacitavacObrazkov.VELKOST_HRACA_A_OBRAZKU_POZADIA < 0) {
            smerPohnutia = "sever";
            this.aktualnaMiestnost = this.aktualnaMiestnost.getVychod(smerPohnutia);
            this.suborSPozadim = this.aktualnaMiestnost.getSuborMiestnosti();
            return smerPohnutia;
        }

        if (yHraca > OknoHry.VYSKA_OKNA) {
            smerPohnutia = "juh";
            this.aktualnaMiestnost = this.aktualnaMiestnost.getVychod(smerPohnutia);
            this.suborSPozadim = this.aktualnaMiestnost.getSuborMiestnosti();
            return smerPohnutia;
        }

        if (xHraca > OknoHry.SIRKA_OKNA) {
            smerPohnutia = "vychod";
            this.aktualnaMiestnost = this.aktualnaMiestnost.getVychod(smerPohnutia);
            this.suborSPozadim = this.aktualnaMiestnost.getSuborMiestnosti();
            return smerPohnutia;
        }

        if (xHraca + NacitavacObrazkov.VELKOST_HRACA_A_OBRAZKU_POZADIA < 0) {
            smerPohnutia = "zapad";
            this.aktualnaMiestnost = this.aktualnaMiestnost.getVychod(smerPohnutia);
            this.suborSPozadim = this.aktualnaMiestnost.getSuborMiestnosti();
            return smerPohnutia;
        }

        return smerPohnutia;
    }

    /**
     * nacita pozadie mapy
     * @param g grafika na vykreslenie
     */
    public void nacitajPozadie(Graphics g) {
        super.getNeutralnePrekazkyHitboxy().odstranVsetkyHitboxy();
        super.nacitajPozadie(g, this.suborSPozadim, "/textury/pozadie.png");
    }

    /**
     * nacita miestnosti mapy
     */
    public void nacitajMiestnosti() {
        MiestnostMapy miestnostMapyL1 = new MiestnostMapy("/pozadia/mapaL1.txt");
        MiestnostMapy miestnostMapyL2 = new MiestnostMapy("/pozadia/mapaL2.txt");
        MiestnostMapy miestnostMapyL3 = new MiestnostMapy("/pozadia/mapaL3.txt");

        miestnostMapyL1.pridajVychod("vychod", this.miestnostMapySpawn);
        miestnostMapyL2.pridajVychod("juh", this.miestnostMapySpawn);
        miestnostMapyL3.pridajVychod("zapad", this.miestnostMapySpawn);

        this.miestnostMapySpawn.pridajVychod("zapad", miestnostMapyL1);
        this.miestnostMapySpawn.pridajVychod("sever", miestnostMapyL2);
        this.miestnostMapySpawn.pridajVychod("vychod", miestnostMapyL3);

        this.aktualnaMiestnost = this.miestnostMapySpawn;
    }

    /**
     * vrati typ levelu mapy
     * @return typ levelu mapy
     */
    public TypLevelu getTypLeveluMapy() {
        return this.typLeveluMapy;
    }

    /**
     * nastavi aktualnu miestnost hraca
     * @param aktualnaMiestnost miestno aktulna hraca
     */
    public void setAktualnaMiestnost(MiestnostMapy aktualnaMiestnost) {
        this.aktualnaMiestnost = aktualnaMiestnost;
    }

    /**
     * vrati zaciatocnu miestnost hraca
     * @return zac. miestnost hraca
     */
    public MiestnostMapy getMiestnostMapySpawn() {
        return this.miestnostMapySpawn;
    }

    /**
     * nastaviu subor s pozadim
     * @param suborSPozadim subor s pozadim
     */
    public void setSuborSPozadim(String suborSPozadim) {
        this.suborSPozadim = suborSPozadim;
    }
}
