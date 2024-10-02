package animacie.bossAnimacie.animacieDoStran;

import animacie.bossAnimacie.Animacia;
import animacie.bossAnimacie.AnimaciaZaklad;
import boss.Boss;
/**
 *Trieda animacie bossa - pohyb hore
 *
 * @author Ľubomír Bojda
 */
public class Hore extends AnimaciaZaklad implements Animacia {
    /**
     * Konstruktor
     * @param boss boss ktoreho ideme animovat
     */
    public Hore(Boss boss) {
        super(boss);
    }
    /**
     * Vrati nazov animacie
     * @return nazov animacie
     */
    @Override
    public String getNazov() {
        return "hore";
    }
    /**
     * metoda na vykonanie animacie - posunu bossa
     * @param xPosun - o kolko posun v smere x
     * @param yPosun - o kolko posun v smere y
     */
    @Override
    public void vykonajAnimaciu(int xPosun, int yPosun) {
        var boss = super.getBossa();

        boss.setyBossa(boss.getyBossa() - boss.getRychlostBossa());

        super.setPorovnanieY(super.getPorovnanieY() + boss.getRychlostBossa());
        if (super.getPorovnanieY() >= yPosun) {
            super.setJeAnimaciaDokoncena(true);
        }
    }
    /**
     * zistenie, ci je animacia dokoncena
     * @return je dokoncena animacia
     */
    @Override
    public boolean animaciaDokoncena() {
        return super.jeAnimaciaDokoncena();
    }

}
