package animacie.bossAnimacie.animacieDoStran;

import animacie.bossAnimacie.Animacia;
import animacie.bossAnimacie.AnimaciaZaklad;
import boss.Boss;

/**
 *Trieda animacie bossa - pohyb dolava
 *
 * @author Ľubomír Bojda
 */
public class Dolava extends AnimaciaZaklad implements Animacia {
    /**
     * Konstruktor
     * @param boss boss ktoreho ideme animovat
     */
    public Dolava(Boss boss) {
        super(boss);
    }

    /**
     * Vrati nazov animacie
     * @return nazov animacie
     */
    @Override
    public String getNazov() {
        return "dolava";
    }

    /**
     * metoda na vykonanie animacie - posunu bossa
     * @param xPosun - o kolko posun v smere x
     * @param yPosun - o kolko posun v smere y
     */
    @Override
    public void vykonajAnimaciu(int xPosun, int yPosun) {
        var boss = super.getBossa();

        boss.setxBossa(boss.getxBossa() - boss.getRychlostBossa());

        super.setPorovnanieX(super.getPorovnanieX() + boss.getRychlostBossa());
        if (super.getPorovnanieX() >= xPosun) {
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
