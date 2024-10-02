package animacie.bossAnimacie;

import boss.Boss;

/**
 * zakladna trieda pre kazdu animaciu
 *
 * @author Ľubomír Bojda
 */
public class AnimaciaZaklad {
    private final Boss boss;
    private boolean jeAnimaciaDokoncena;
    private int porovnanieY;
    private int porovnanieX;

    /**
     * konstruktor
     * @param boss boss na animacie
     */
    public AnimaciaZaklad(Boss boss) {
        this.boss = boss;
        this.jeAnimaciaDokoncena = false;
        this.porovnanieY = 0;
        this.porovnanieX = 0;
    }

    /**
     * getter na bossa
     * @return boss
     */
    public Boss getBossa() {
        return this.boss;
    }

    /**
     * zistuje ci je animacia dokoncena
     * @return ci je dokoncena animacia
     */
    public boolean jeAnimaciaDokoncena() {
        return  this.jeAnimaciaDokoncena;
    }

    /**
     * nastavuje ci je animacia dokoncena
     * @param jeAnimaciaDokoncena true/false
     */
    public void setJeAnimaciaDokoncena(boolean jeAnimaciaDokoncena) {
        this.jeAnimaciaDokoncena = jeAnimaciaDokoncena;
    }

    /**
     * getter na hodnotu y na porovnanie
     * @return porovnanie y
     */
    public int getPorovnanieY() {
        return this.porovnanieY;
    }

    /**
     * getter na hodnotu x na porovnanie
     * @return porovnanie x
     */
    public int getPorovnanieX() {
        return this.porovnanieX;
    }

    /**
     * setter na porovnanie y
     * @param porovnanieY porovnanie y
     */
    public void setPorovnanieY(int porovnanieY) {
        this.porovnanieY = porovnanieY;
    }

    /**
     * setter na porovnanie x
     * @param porovnanieX porovnanie x
     */
    public void setPorovnanieX(int porovnanieX) {
        this.porovnanieX = porovnanieX;
    }

    /**
     * resetuje vsetky hodnoty animacie na zakldane
     */
    public void resetujHodnotyAnimacie() {
        this.porovnanieX = 0;
        this.porovnanieY = 0;
        this.jeAnimaciaDokoncena = false;
    }
}
