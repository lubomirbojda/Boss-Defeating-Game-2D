package hrac;

import levely.TypLevelu;
import rozhranie.NacitavacObrazkov;
import animacie.hracoveAnimacie.Skok;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

/**
 * trieda pre pohyb hraca po obrazovke
 *
 * @author Ľubomír Bojda
 */
public class PohybHraca extends HracStrely {

    private int xHraca;
    private int yHraca;
    private int rychlostHraca;
    private int pocitadlo;
    private int riadokObrazku;
    private int stlpecObrazku;
    private BufferedImage obrazokHraca;
    private BufferedImage podobrazokHraca;
    private OvladanieHraca ovladanieHraca;
    private TypLevelu typLevelu;
    private boolean mozeSaPohnutHore;
    private boolean mozeSaPohnutDole;
    private boolean mozeSaPohnutDolava;
    private boolean mozeSaPohnutDoprava;
    private boolean mozeSkocit;
    private Skok skok;
    private int pocitadloPreStrelu;

    /**
     * konstruktor - nastavi hodnoty
     */
    public PohybHraca() {
        super(1, 3);
        this.skok = new Skok(this);
        this.mozeSkocit = false;
        this.mozeSaPohnutHore = true;
        this.mozeSaPohnutDolava = true;
        this.mozeSaPohnutDole = true;
        this.mozeSaPohnutDoprava = true;
        this.typLevelu = null;
        this.pocitadlo = 1;
        this.xHraca = TypLevelu.MAPA.getxHraca();
        this.yHraca = TypLevelu.MAPA.getyHraca();
        this.riadokObrazku = 1;
        this.stlpecObrazku = 2;
        this.rychlostHraca = 4;
        this.inicializujObrazokHraca();
        this.ovladanieHraca = new OvladanieHraca();
        this.pocitadloPreStrelu = 60;
    }

    /**
     * inicializuje obrazok hraca
     */
    public void inicializujObrazokHraca() {
        this.obrazokHraca = NacitavacObrazkov.nacitajObrazok("/textury/postavaHrac.png");
        this.podobrazokHraca = NacitavacObrazkov.podobrazok(this.obrazokHraca, this.riadokObrazku, this.stlpecObrazku, "hrac");
    }

    /**
     * nastavi true/false podla toho ci sa hrac moze danym smerom pohnut
     * @param mozeSaPohnutHore moze sa pohnut danym smerom
     */
    public void setMozeSaPohnutHore(boolean mozeSaPohnutHore) {
        this.mozeSaPohnutHore = mozeSaPohnutHore;
    }

    /**
     * nastavi true/false podla toho ci sa hrac moze danym smerom pohnut
     * @param mozeSaPohnutDole moze sa pohnut danym smerom
     */
    public void setMozeSaPohnutDole(boolean mozeSaPohnutDole) {
        this.mozeSaPohnutDole = mozeSaPohnutDole;
    }

    /**
     * nastavi true/false podla toho ci sa hrac moze danym smerom pohnut
     * @param mozeSaPohnutDolava moze sa pohnut danym smerom
     */
    public void setMozeSaPohnutDolava(boolean mozeSaPohnutDolava) {
        this.mozeSaPohnutDolava = mozeSaPohnutDolava;
    }

    /**
     * nastavi true/false podla toho ci sa hrac moze danym smerom pohnut
     * @param mozeSaPohnutDoprava moze sa pohnut danym smerom
     */
    public void setMozeSaPohnutDoprava(boolean mozeSaPohnutDoprava) {
        this.mozeSaPohnutDoprava = mozeSaPohnutDoprava;
    }

    /**
     * nastavi true/false podla toho ci hrac moze skocit
     * @param mozeSkocit moze skocit
     */
    public void setMozeSkocit(boolean mozeSkocit) {
        this.mozeSkocit = mozeSkocit;
    }

    /**
     * vrati x-ovu poziciu hraca
     * @return x hraca
     */
    public int getxHraca() {
        return this.xHraca;
    }

    /**
     * vrati y-ovu poziciu hraca
     * @return y hraca
     */
    public int getyHraca() {
        return this.yHraca;
    }

    /**
     * nastaci x hraca
     * @param xHraca x hraca
     */
    public void setxHraca(int xHraca) {
        this.xHraca = xHraca;
    }

    /**
     * nastaci y hraca
     * @param yHraca y hraca
     */
    public void setyHraca(int yHraca) {
        this.yHraca = yHraca;
    }

    /**
     * vykresli postavu hraca
     * @param g grafika na vykreslovanie
     */
    public void vykresliPostavuHraca(Graphics g) {
        g.drawImage(this.podobrazokHraca, this.xHraca, this.yHraca, null);
        this.zmenObrazokHraca(this.riadokObrazku, 2);
    }

    /**
     * podla toho co pouzivatel stlacil, pohne s postavou hraca
     */
    public void pohybHraca() {

        if (this.ovladanieHraca.jeStlacenaKlavesaW()) {
            if (this.typLevelu == TypLevelu.MAPA) {
                this.menObrazok(4);
                if (this.mozeSaPohnutHore) {
                    this.yHraca -= this.rychlostHraca;
                }
            }
        } else if (this.ovladanieHraca.jeStlacenaKlavesaS()) {
            if (this.typLevelu == TypLevelu.MAPA) {
                this.menObrazok(1);
                if (this.mozeSaPohnutDole) {

                    this.yHraca += this.rychlostHraca;
                }
            }
        } else if (this.ovladanieHraca.jeStlacenaKlavesaA()) {
            this.menObrazok(2);
            if (this.mozeSaPohnutDolava) {

                this.xHraca -= this.rychlostHraca;
            }
        } else if (this.ovladanieHraca.jeStlacenaKlavesaD()) {
            this.menObrazok(3);
            if (this.mozeSaPohnutDoprava) {

                this.xHraca += this.rychlostHraca;
            }
        }

        if (this.typLevelu != TypLevelu.MAPA) {
            if (this.ovladanieHraca.jeStlacenaKlavesaMedzernik()) {
                if (this.mozeSkocit) {
                    this.skok.setJeVSkoku(true);
                }
            }


            if (this.skok.getJeVSkoku()) {
                this.skok.skoc(28);
            }

            if (this.skok.getJeVPade()) {
                this.skok.spadni();
            }

            if (this.ovladanieHraca.jeStlacenaKlavesaL()) {
                if (this.pocitadloPreStrelu == 60) {
                    var xS = this.xHraca + 32 - 16;
                    var yS = this.yHraca + 32 - 16;
                    super.pridajStrelu(super.urciSmerStrely(this.riadokObrazku), xS, yS);
                    this.pocitadloPreStrelu = 0;
                }
            }

            if (this.pocitadloPreStrelu != 60) {
                this.pocitadloPreStrelu++;
            }
        }


    }

    /**
     * vrati triedu na ovladanie hraca
     * @return ovladanie hraca
     */
    public OvladanieHraca getOvladanieHraca() {
        return this.ovladanieHraca;
    }

    /**
     * meni obrazok hraca, aby vezeralo ze chodi
     * @param riadok riadok obrazku
     */
    public void menObrazok(int riadok) {

        this.riadokObrazku = riadok;

        if (this.pocitadlo >= 1 && this.pocitadlo < 10) {
            this.stlpecObrazku = 1;
            this.zmenObrazokHraca(this.riadokObrazku, this.stlpecObrazku);
        }

        if (this.pocitadlo >= 10 && this.pocitadlo < 20) {
            this.stlpecObrazku = 2;
            this.zmenObrazokHraca(this.riadokObrazku, this.stlpecObrazku);
        }

        if (this.pocitadlo >= 20 && this.pocitadlo <= 30) {
            this.stlpecObrazku = 3;
            this.zmenObrazokHraca(this.riadokObrazku, this.stlpecObrazku);
        }

        if (this.pocitadlo >= 30 && this.pocitadlo <= 40) {
            this.stlpecObrazku = 2;
            this.zmenObrazokHraca(this.riadokObrazku, this.stlpecObrazku);
        }

        if (this.pocitadlo == 40) {
            this.pocitadlo = 1;
        } else {
            this.pocitadlo++;
        }
    }

    /**
     * vrati triedu skok hraca
     * @return skok
     */
    public Skok getSkok() {
        return this.skok;
    }

    /**
     * zmeni obr. hraca podla zadaneho riadka a stlpca
     * @param riadok riadok obr.
     * @param stlpec stlpec obr.
     */
    public void zmenObrazokHraca(int riadok, int stlpec) {
        this.podobrazokHraca = NacitavacObrazkov.podobrazok(this.obrazokHraca, riadok, stlpec, "hrac");
    }

    /**
     * nastavi typ levelu
     * @param typLevelu typ levelu
     */
    public void setTypLevelu(TypLevelu typLevelu) {
        this.typLevelu = typLevelu;
    }

    /**
     * vrati typ levelu
     * @return typ levelu
     */
    public TypLevelu getTypLevelu() {
        return this.typLevelu;
    }
    /**
    *nastavi riadok obrazku
    */
    public void setRiadokObrazku(int riadokObrazku) {
        this.riadokObrazku = riadokObrazku;
    }
}
