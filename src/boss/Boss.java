package boss;

import animacie.bossAnimacie.Animacia;
import animacie.bossAnimacie.AnimaciaZaklad;
import animacie.bossAnimacie.animacieDoStran.Dolava;
import animacie.bossAnimacie.animacieDoStran.Dole;
import animacie.bossAnimacie.animacieDoStran.Doprava;
import animacie.bossAnimacie.animacieDoStran.Hore;
import kolizie.VelkostHitboxov;
import levely.TypLevelu;
import rozhranie.NacitavacObrazkov;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * trieda boss pre bossa do kazdeho levelu
 *
 * @author Ľubomír Bojda
 */
public class Boss extends BossStrely {
    private int xBossa;
    private int yBossa;
    private int zivotyBossa;
    private int rychlostBossa;
    private int riadokObrazku;
    private int stlpecObrazku;
    private int pocitadlo;
    private int pocitadloPreHitBossa;
    private int pocitadloPreHitHraca;
    private BufferedImage obrazokBossa;
    private BufferedImage podobrazokBossa;
    private HashMap<String, Animacia> animacie;
    private int zivotyHraca;

    /**
     * konstruktor
     * @param riadok riadok obrazku
     * @param stlpec stlpec obrazku
     */
    public Boss(int riadok, int stlpec) {
        super(riadok, 5);
        this.rychlostBossa = 5;
        this.riadokObrazku = riadok;
        this.stlpecObrazku = stlpec;
        this.animacie = new HashMap<>();
        this.pocitadlo = 1;
        this.zivotyBossa = 100;
        this.pocitadloPreHitBossa = 60;
        this.pocitadloPreHitHraca = 60;
        this.obrazokBossa = NacitavacObrazkov.nacitajObrazok("/textury/postavaBoss.png");
        this.podobrazokBossa = NacitavacObrazkov.podobrazok(this.obrazokBossa, this.riadokObrazku, this.stlpecObrazku, "boss");
        this.zivotyHraca = 3;
        this.nacitajBossoveAnimacie();
    }

    /**
     * metoda na nastavenie zac. pozicie bossa podla hodnot enumu
     * @param typLevelu enum
     */
    public void nastavZaciatocnuPoziciuBossa(TypLevelu typLevelu) {
        this.xBossa = typLevelu.getxBossa();
        this.yBossa = typLevelu.getyBossa();
    }

    /**
     * vykresli postavu bossa na platno
     * @param g grafiuka na vykreslovanie
     */
    public void vykresliPostavuBossa(Graphics g) {
        this.menObrazok();
        g.drawImage(this.podobrazokBossa, this.xBossa, this.yBossa, null);

        g.setColor(Color.BLACK);
        g.fillRect(950, 784, 300, 40);
        g.setColor(Color.RED);
        g.fillRect(950, 784, (300 / 100) * this.zivotyBossa, 40);
        g.setColor(Color.WHITE);
        g.drawRoundRect(950, 784, 300, 40, 7, 7);

        var x = 48;
        var y = 776;
        for (int i = 0; i < this.zivotyHraca; i++) {
            g.drawImage(NacitavacObrazkov.nacitajObrazok("/textury/zivotHrac.png"), x * i, y, null);
        }
    }

    /**
     * metoda na zmenu obrazkov bossa pri hybani
     */
    public void menObrazok() {

        if (this.pocitadlo >= 1 && this.pocitadlo < 10) {
            this.stlpecObrazku = 1;
            this.zmenObrazokBossa(this.stlpecObrazku);
        }

        if (this.pocitadlo >= 10 && this.pocitadlo < 20) {
            this.stlpecObrazku = 2;
            this.zmenObrazokBossa(this.stlpecObrazku);
        }

        if (this.pocitadlo >= 20 && this.pocitadlo <= 30) {
            this.stlpecObrazku = 3;
            this.zmenObrazokBossa(this.stlpecObrazku);
        }

        if (this.pocitadlo >= 30 && this.pocitadlo <= 40) {
            this.stlpecObrazku = 2;
            this.zmenObrazokBossa(this.stlpecObrazku);
        }

        if (this.pocitadlo == 40) {
            this.pocitadlo = 1;
        } else {
            this.pocitadlo++;
        }
    }

    /**
     * zmeni obrazok bossa podla zadaneho stlpca
     * @param stlpec stlpec obrazku
     */
    public void zmenObrazokBossa(int stlpec) {
        this.podobrazokBossa = NacitavacObrazkov.podobrazok(this.obrazokBossa, this.riadokObrazku, stlpec, "boss");
    }

    /**
     * vrati obdlznik ako hitboxy bossa
     * @return hitboxy bossa
     */
    public Rectangle hitboxyBossa() {
        Rectangle boss = new Rectangle();
        boss.setBounds(this.xBossa + 19, this.yBossa + 38, VelkostHitboxov.BOSS.getSirka(), VelkostHitboxov.BOSS.getVyska());
        return boss;
    }

    /**
     * vrati x-ovu suradnicu bossa
     * @return x bossa
     */
    public int getxBossa() {
        return this.xBossa;
    }

    /**
     * vrati y-ovu suradnicu bossa
     * @return y bossa
     */
    public int getyBossa() {
        return this.yBossa;
    }

    /**
     * nastavi x-ovu suradni bossa
     * @param xBossa x bossa
     */
    public void setxBossa(int xBossa) {
        this.xBossa = xBossa;
    }

    /**
     * nastavi y-ovu suradnicu bossa
     * @param yBossa y bossa
     */
    public void setyBossa(int yBossa) {
        this.yBossa = yBossa;
    }

    /**
     * vrati rychlost bossa
     * @return rychlost bossas
     */
    public int getRychlostBossa() {
        return this.rychlostBossa;
    }

    /**
     * prida animaciu do hashmapu
     * @param animaciaNaPridanie animacie na pridanie
     */
    public void pridajAnimaciu(Animacia animaciaNaPridanie) {
        this.animacie.put(animaciaNaPridanie.getNazov(), animaciaNaPridanie);
    }

    /**
    * vrati animaciu podla nazvu
    */
    public Optional<Animacia> getAnimacia(String nazovAnimacie) {
        return Optional.ofNullable(this.animacie.get(nazovAnimacie));
    }

    /**
     * resetuje hodnoty kazdej animacie na zakladne
     */
    public void resetujVsetkyAnimacie() {
        for (Map.Entry<String, Animacia> entry : this.animacie.entrySet()) {
            String key = entry.getKey();
            Animacia value = entry.getValue();

            ((AnimaciaZaklad)value).resetujHodnotyAnimacie();
        }
    }

    /**
     * nacita animacie bossovi do hashmapu
     */
    public void nacitajBossoveAnimacie() {
        this.pridajAnimaciu(new Hore(this));
        this.pridajAnimaciu(new Dole(this));
        this.pridajAnimaciu(new Dolava(this));
        this.pridajAnimaciu(new Doprava(this));
    }

    /**
     * ak sa da, uberie zivot bossovi
     */
    public void uberZivotyBossovi() {
        if (this.pocitadloPreHitBossa == 60) {
            this.zivotyBossa -= 5;
            this.pocitadloPreHitBossa = 0;
        }
    }

    /**
     * ak sa da, uberie zivot hracovi
     */
    public void uberZivotHraca() {
        if (this.pocitadloPreHitHraca == 60) {
            this.zivotyHraca -= 1;
            this.pocitadloPreHitHraca = 0;
        }
    }

    /**
     * prida 1 k pocitadlam, ktore sluzia na chvilkovu ignoracie kolizie po nejakej kolizii
     */
    public void pocitadloPridaj() {
        if (this.pocitadloPreHitBossa != 60) {
            this.pocitadloPreHitBossa++;
        }

        if (this.pocitadloPreHitHraca != 60) {
            this.pocitadloPreHitHraca++;
        }
    }

    /**
     * vrati pocet zivotov bossa
     * @return pocet zivotov bossa
     */
    public int getZivotyBossa() {
        return this.zivotyBossa;
    }

    /**
     * vrati pocet zivotov hraca
     * @return pocetr zivotov hraca
     */
    public int getZivotyHraca() {
        return this.zivotyHraca;
    }

    /**
     * nastavi pocet zivotov bossa
     * @param zivotyBossa pocet tivotov bossa
     */
    public void setZivotyBossa(int zivotyBossa) {
        this.zivotyBossa = zivotyBossa;
    }

    /**
     * nastavi pocet zivotov hraca
     * @param zivotyHraca pocet zivotov hraca
     */
    public void setZivotyHraca(int zivotyHraca) {
        this.zivotyHraca = zivotyHraca;
    }
}
