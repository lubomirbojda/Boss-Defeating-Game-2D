package levely;

import boss.Boss;
import kolizie.Hitboxy;
import rozhranie.NacitavacObrazkov;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * zakladna trieda pre kazdy level
 * @author Ľubomír Bojda
 */
public class LevelZaklad extends Boss {
    private ArrayList<Integer> cislaNeutralnychPrekazok;
    private Hitboxy<Rectangle> neutralnePrekazkyHitboxy;
    private boolean levelPrejdeny;

    /**
     * konstruktor
     * @param riadok obr bossa
     * @param stlpec obr bossa
     * @param typLevelu typ levelu
     */
    public LevelZaklad(int riadok, int stlpec, TypLevelu typLevelu) {
        super(riadok, stlpec);
        this.cislaNeutralnychPrekazok = new ArrayList<Integer>();
        this.neutralnePrekazkyHitboxy = new Hitboxy<Rectangle>();
        super.nastavZaciatocnuPoziciuBossa(typLevelu);
        this.levelPrejdeny = false;
    }

    /**
     * nacita pozadie pre level
     * @param g grafika na vykreslovanie
     * @param suborSPozadim cesta pre subor s pozadim
     * @param pngObrazok cesta pre obrazoks s pozadim
     */
    public void nacitajPozadie(Graphics g, String suborSPozadim, String pngObrazok) {

        this.neutralnePrekazkyHitboxy.odstranVsetkyHitboxy();

        InputStream is = this.getClass().getResourceAsStream(suborSPozadim);
        BufferedReader r = new BufferedReader(new InputStreamReader(is));

        int riadok = 0;
        var obr = NacitavacObrazkov.nacitajObrazok(pngObrazok);

        String line;

        while (true) {
            try {
                line = r.readLine();
                if (line == null) {
                    break;
                } else {

                    Scanner sc = new Scanner(line);
                    for (int i = 0; i < NacitavacObrazkov.POCET_STLPCOV_POZADIA; i++) {
                        int h = sc.nextInt();


                        var finalObr = NacitavacObrazkov.podobrazok(obr, 0, h, "pozadie");


                        int xObr = (i - 1) * NacitavacObrazkov.VELKOST_HRACA_A_OBRAZKU_POZADIA;
                        int yObr = (riadok - 1) * NacitavacObrazkov.VELKOST_HRACA_A_OBRAZKU_POZADIA;


                        g.drawImage(finalObr, xObr, yObr, null);

                        for (int l : this.cislaNeutralnychPrekazok) {
                            if (h == l) {
                                var hitbox = new Rectangle(xObr, yObr, NacitavacObrazkov.VELKOST_HRACA_A_OBRAZKU_POZADIA, NacitavacObrazkov.VELKOST_HRACA_A_OBRAZKU_POZADIA);
                                this.neutralnePrekazkyHitboxy.pridajHitboxy(hitbox);
                            }
                        }

                    }

                    riadok++;


                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * vrati neutralne prekazky
     * @return Hitboxy<Rectangle>
     */
    public Hitboxy<Rectangle> getNeutralnePrekazkyHitboxy() {
        return this.neutralnePrekazkyHitboxy;
    }

    /**
     * prida cisla prekazok, cez ktore hrac nemoze prejst
     * @param x
     */
    public void pridajCisloNeutralnejPrekazky(int x) {
        this.cislaNeutralnychPrekazok.add(x);
    }

    /**
     * nastavi ci je level prejdeny
     * @param levelPrejdeny je level prejdeny
     */

    public void setLevelPrejdeny(boolean levelPrejdeny) {
        this.levelPrejdeny = levelPrejdeny;
    }

    /**
     * vrati hodnotu, ci je level prejdeny
     * @return level je prejdeny
     */
    public boolean getLevelPrejdeny() {
        return this.levelPrejdeny;
    }
}
