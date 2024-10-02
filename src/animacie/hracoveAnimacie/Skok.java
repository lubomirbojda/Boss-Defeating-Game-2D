package animacie.hracoveAnimacie;

import hrac.PohybHraca;

/**
 * trieda pre skok hraca
 *
 * @author Ľubomír Bojda
 */
public class Skok {
    private int pocitadlo;
    private boolean jeVSkoku;
    private boolean jeVPade;
    private boolean hracDopadol;
    private final PohybHraca pohybHraca;

    /**
     * konstruktor
     * @param pohybHraca trieda pohybhraca
     */
    public Skok(PohybHraca pohybHraca) {
        this.pohybHraca = pohybHraca;
        this.pocitadlo = 1;
        this.jeVSkoku = false;
        this.jeVPade = false;
        this.hracDopadol = false;
    }

    /**
     * vykonaj skok
     * @param vyskaSkoku hodnota ako vysoko hrac vyskoci
     */
    public void skoc (int vyskaSkoku) {

        if (this.pocitadlo <= vyskaSkoku) {
            var y = this.pohybHraca.getyHraca() - 10;
            this.pohybHraca.setyHraca(y);
            this.pocitadlo++;
        } else {
            this.jeVSkoku = false;
            this.jeVPade = true;
            this.hracDopadol = false;
            this.pocitadlo = 1;
        }

    }

    /**
     * metoda na vykonanie padu postavy hraca
     *
     */
    public void spadni() {
        if (!this.hracDopadol) {
            var y = this.pohybHraca.getyHraca() + 10;
            this.pohybHraca.setyHraca(y);
        } else {
            this.jeVPade = false;
        }
    }

    /**
     * setter hrac v pade
     * @param jeVPade true/false
     */
    public void setJeVPade(boolean jeVPade) {
        this.jeVPade = jeVPade;
    }

    /**
     * getter ci je hrac v pade
     * @return heac je v pade
     */
    public boolean getJeVPade() {
        return this.jeVPade;
    }

    /**
     * metoda na reset pocitadla pre skok
     */
    public void resetujPocitadlo() {
        this.pocitadlo = 1;
    }

    /**
     * setter hrac je v skoku
     * @param jeVSkoku true/false
     */
    public void setJeVSkoku(boolean jeVSkoku) {
        this.jeVSkoku = jeVSkoku;
    }

    /**
     * setter hrac dopadol po skoku
     * @param hracDopadol true/false
     */
    public void setHracDopadol(boolean hracDopadol) {
        this.hracDopadol = hracDopadol;
    }

    /**
     * getter ci je hrav v skoku
     * @return je hrac v skoku
     */
    public boolean getJeVSkoku() {
        return this.jeVSkoku;
    }

}
