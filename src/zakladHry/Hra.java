package zakladHry;

import hrac.PohybHraca;
import kolizie.DetekciaKolizie;
import kolizie.VelkostHitboxov;
import levely.AnimacieBossa;
import levely.Level1;
import levely.Level2;
import levely.Level3;
import levely.LevelZaklad;
import levely.TypLevelu;
import mapa.Mapa;
import rozhranie.NacitavacObrazkov;
import rozhranie.OknoHry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * zakldna trieda celej hry
 * @author Ľubomír Bojda
 */

public class Hra {
    private PohybHraca pohybHraca;
    private DetekciaKolizie detekciaKolizie;
    private Level1 level1;
    private Level2 level2;
    private Level3 level3;
    private Mapa mapa;
    private LevelZaklad aktualnyLevel;
    private boolean vykresliText;
    private VytvorenieHry vytvorenieHry;

    /**
     * konstruktor hry
     * @param vytvorenieHry vytvorenie hry
     */
    public Hra(VytvorenieHry vytvorenieHry) {
        this.vytvorenieHry = vytvorenieHry;
        this.detekciaKolizie = new DetekciaKolizie();
        this.vykresliText = false;
        this.pohybHraca = new PohybHraca();
        this.mapa = new Mapa(1, 2);
        this.level1 = new Level1(1, 2);
        this.level2 = new Level2(2, 2);
        this.level3 = new Level3(4, 2);
        this.pohybHraca.setTypLevelu(TypLevelu.MAPA);
        this.aktualnyLevel = this.mapa;
    }

    /**
     * aktualizacia vsetkych suradnic
     */
    public void aktualizaciaSuradnic() {

        this.pohybHraca.pohybHraca();
        this.kolizia();
        this.koliziaNeutralnych();

        if (this.vykresliText && this.pohybHraca.getOvladanieHraca().jeStlacenaKlavesaEnter()) {
            this.pohybHraca.setTypLevelu(this.mapa.getTypLeveluMapy());
            this.pohybHraca.setRiadokObrazku(2);
            this.pohybHraca.setxHraca(this.mapa.getTypLeveluMapy().getxHraca());
            this.pohybHraca.setyHraca(this.mapa.getTypLeveluMapy().getyHraca());
        }

    }

    /**
     * vykreslenie vsetkeho
     * @param g grafika na vykreslenie
     */
    public void vykreslenie(Graphics g) {

        switch (this.pohybHraca.getTypLevelu()) {
            case MAPA -> {
                this.vyberPozadiePreMapu();
                this.mapa.nacitajPozadie(g);
                this.aktualnyLevel = this.mapa;
            }
            case LEVEL1 -> {
                this.level1.getNeutralnePrekazkyHitboxy().odstranVsetkyHitboxy();
                this.level1.nacitajPozadie(g, "/pozadia/level1.txt", "/textury/pozadie.png");
                this.aktualnyLevel = this.level1;
            }
            case LEVEL2 -> {
                this.level2.getNeutralnePrekazkyHitboxy().odstranVsetkyHitboxy();
                this.level2.nacitajPozadie(g, "/pozadia/level2.txt", "/textury/pozadie.png");
                this.aktualnyLevel = this.level2;
            }
            case LEVEL3 -> {
                this.level3.getNeutralnePrekazkyHitboxy().odstranVsetkyHitboxy();
                this.level3.nacitajPozadie(g, "/pozadia/level3.txt", "/textury/pozadie.png");
                this.aktualnyLevel = this.level3;
            }
        }


        if (this.aktualnyLevel != this.mapa) {
            if (this.aktualnyLevel instanceof AnimacieBossa) {
                ((AnimacieBossa)this.aktualnyLevel).animujBossa();
            }
            this.aktualnyLevel.vykresliPostavuBossa(g);

        } else {
            if (this.mapa.vstupDoLevelu() != null) {
                var vlajka = NacitavacObrazkov.nacitajObrazok("/textury/vlajka.png");
                switch (this.mapa.getTypLeveluMapy()) {
                    case LEVEL1, LEVEL2, LEVEL3 -> {
                        if (this.level1.getLevelPrejdeny() || this.level2.getLevelPrejdeny() || this.level3.getLevelPrejdeny()) {
                            g.drawImage(vlajka, this.mapa.vstupDoLevelu().x + 88, this.mapa.vstupDoLevelu().y - 192, null);
                        }
                    }
                }
            }
        }


        this.pohybHraca.vykresliPostavuHraca(g);
        this.aktualnyLevel.animujStrely(g);
        this.pohybHraca.animujStrely(g);

        if (this.vykresliText) {
            g.setColor(Color.BLACK);
            var x = this.pohybHraca.getxHraca() - 80;
            var y = this.pohybHraca.getyHraca() - 80;
            g.fillRect(x, y, 200, 70);
            g.setColor(Color.WHITE);
            g.drawRoundRect(x, y, 200, 70, 7, 7);
            g.drawString("ZACNI LEVEL STLACENIM ENTER", x + 7, y + 40);
        }

    }

    /**
     * ak je hrac na mape vyber pozadie
     */
    public void vyberPozadiePreMapu() {
        switch (this.mapa.vyberPozadie(this.pohybHraca.getxHraca(), this.pohybHraca.getyHraca())) {
            case "sever" -> this.pohybHraca.setyHraca(OknoHry.VYSKA_OKNA - NacitavacObrazkov.VELKOST_HRACA_A_OBRAZKU_POZADIA);
            case "zapad" -> this.pohybHraca.setxHraca(OknoHry.SIRKA_OKNA - NacitavacObrazkov.VELKOST_HRACA_A_OBRAZKU_POZADIA);
            case "vychod" -> this.pohybHraca.setxHraca(0);
            case "juh" -> this.pohybHraca.setyHraca(0);
        }

    }

    /**
     * kolizia objektov v hre
     */
    public void kolizia() {
        this.aktualnyLevel.pocitadloPridaj();
        this.detekciaKolizie.getHitboxyHrac().setBounds(this.pohybHraca.getxHraca() + 20 , this.pohybHraca.getyHraca() + 10 , VelkostHitboxov.HRAC.getSirka(), VelkostHitboxov.HRAC.getVyska());
        this.detekciaKolizie.getHitboxyBoss().setBounds(this.aktualnyLevel.hitboxyBossa().getBounds());
        this.detekciaKolizie.getHitboxyStrelyBoss().odstranVsetkyHitboxy();
        this.detekciaKolizie.getHitoxyStrelyHrac().odstranVsetkyHitboxy();

        if (this.aktualnyLevel == this.mapa) {
            this.detekciaKolizie.getHitboxyStrelyBoss().pridajHitboxy(this.mapa.vstupDoLevelu());
            this.vykresliText = this.detekciaKolizie.testKolizieHrac();
        } else {
            this.vykresliText = false;

            this.pohybHraca.odstranStrelyMimoPlochu();
            this.aktualnyLevel.obnovSuradniceHitboxov();
            this.pohybHraca.obnovSuradniceHitboxov();
            for (Rectangle rectangle : this.aktualnyLevel.getHitboxyStriel().getHitboxy()) {
                this.detekciaKolizie.getHitboxyStrelyBoss().pridajHitboxy(rectangle);
            }
            for (Rectangle rectangle : this.pohybHraca.getHitboxyStriel().getHitboxy()) {
                this.detekciaKolizie.getHitoxyStrelyHrac().pridajHitboxy(rectangle);
            }
            if (this.detekciaKolizie.testKolizieHrac()) {
                this.aktualnyLevel.uberZivotHraca();
            }

            if (this.detekciaKolizie.testKolizieBossAHracovaStrela()) {
                this.aktualnyLevel.uberZivotyBossovi();
            }
        }

        if (this.aktualnyLevel.getZivotyBossa() == 0) {
            this.vyhra();
        }

        if (this.aktualnyLevel.getZivotyHraca() == 0) {
            this.prehra();
        }

        //System.out.println("x: " + this.pohybHraca.getxHraca() + " y: " + this.pohybHraca.getyHraca());
    }

    /**
     * kolizia neutralnych prekazok
     */
    public void koliziaNeutralnych() {

        this.detekciaKolizie.getHitboxyNeutralne().odstranVsetkyHitboxy();

        for (Rectangle rec : this.aktualnyLevel.getNeutralnePrekazkyHitboxy().getHitboxy()) {
            this.detekciaKolizie.getHitboxyNeutralne().pridajHitboxy(rec);
        }

        if (this.detekciaKolizie.testKolizieNeutralneHore()) {
            this.pohybHraca.setMozeSaPohnutHore(false);
            if (this.pohybHraca.getSkok().getJeVSkoku()) {
                this.pohybHraca.getSkok().setJeVSkoku(false);
                this.pohybHraca.getSkok().setJeVPade(true);
                this.pohybHraca.getSkok().setHracDopadol(false);
                this.pohybHraca.getSkok().resetujPocitadlo();
            }

        } else {
            this.pohybHraca.setMozeSaPohnutHore(true);
        }

        if (this.detekciaKolizie.testKolizieNeutralneDole()) {
            this.pohybHraca.setMozeSaPohnutDole(false);
            this.pohybHraca.getSkok().setHracDopadol(true);
            this.pohybHraca.getSkok().setJeVSkoku(false);
            this.pohybHraca.getSkok().setJeVPade(false);
            this.pohybHraca.getSkok().resetujPocitadlo();
            this.pohybHraca.setMozeSkocit(true);
        } else {
            this.pohybHraca.setMozeSaPohnutDole(true);
            this.pohybHraca.setMozeSkocit(false);
            if (!this.pohybHraca.getSkok().getJeVPade() && !this.pohybHraca.getSkok().getJeVSkoku()) {
                this.pohybHraca.getSkok().setHracDopadol(false);
                this.pohybHraca.getSkok().setJeVPade(true);
            }
        }

        this.pohybHraca.setMozeSaPohnutDoprava(!this.detekciaKolizie.testKolizieNeutralneVpravo());

        this.pohybHraca.setMozeSaPohnutDolava(!this.detekciaKolizie.testKolizieNeutralneVlavo());
    }

    /**
     * vrat level 1
     * @return level 1
     */
    public Level1 getLevel1() {
        return this.level1;
    }

    /**
     * vrat level 2
     * @return
     */
    public Level2 getLevel2() {
        return this.level2;
    }

    /**
     * vrat level 3
     * @return
     */
    public Level3 getLevel3() {
        return this.level3;
    }

    /**
     * vrat pohyb hraca
     * @return trieda pohyb hraca
     */
    public PohybHraca getPohybHraca() {
        return this.pohybHraca;
    }

    /**
     * vrat mapu
     * @return trieda mapy
     */
    public Mapa getMapa() {
        return this.mapa;
    }

    /**
     * resetuj level na zaciatok
     */
    public void restartujAktualnyLevel() {
        this.aktualnyLevel.nastavZaciatocnuPoziciuBossa(this.pohybHraca.getTypLevelu());
        this.pohybHraca.setxHraca(this.pohybHraca.getTypLevelu().getxHraca());
        this.pohybHraca.setyHraca(this.pohybHraca.getTypLevelu().getyHraca());
        this.aktualnyLevel.resetujVsetkyAnimacie();
        this.aktualnyLevel.odstranStrely();
        this.aktualnyLevel.obnovSuradniceHitboxov();
        this.pohybHraca.odstranStrely();
        this.pohybHraca.obnovSuradniceHitboxov();
        this.aktualnyLevel.setZivotyBossa(100);
        this.aktualnyLevel.setZivotyHraca(3);
    }

    /**
     * metoda, ktora sa vykona po vyhre hry
     */
    public void vyhra() {
        this.vytvorenieHry.getPauza().setPauzaObrazok("/textury/vyhra.png");
        this.vytvorenieHry.getPauza().setDaSaPokracovat(false);
        this.aktualnyLevel.setLevelPrejdeny(true);
        this.pohybHraca.getOvladanieHraca().setStlacenaKlavesaESC(true);
    }
    /**
     * metoda, ktora sa vykona po prehre hry
     */
    public void prehra() {
        this.vytvorenieHry.getPauza().setPauzaObrazok("/textury/prehra.png");
        this.vytvorenieHry.getPauza().setDaSaPokracovat(false);
        this.pohybHraca.getOvladanieHraca().setStlacenaKlavesaESC(true);
    }

}

