package hrac;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * trieda pre ovladanie hraca
 *
 * @author Ľubomír Bojda
 */
public class OvladanieHraca implements KeyListener {
    private boolean stlacenaKlavesaW;
    private boolean stlacenaKlavesaA;
    private boolean stlacenaKlavesaS;
    private boolean stlacenaKlavesaD;
    private boolean stlacenaKlavesaMedzernik;
    private boolean stlacenaKlavesaEnter;
    private boolean stlacenaKlavesaESC;
    private boolean stlacenaKalvesaL;

    /**
     * zistuje ci je stlacena klavesa W
     * @return je W stlacene
     */
    public boolean jeStlacenaKlavesaW() {
        return this.stlacenaKlavesaW;
    }
    /**
     * zistuje ci je stlacena klavesa A
     * @return je A stlacene
     */
    public boolean jeStlacenaKlavesaA() {
        return this.stlacenaKlavesaA;
    }
    /**
     * zistuje ci je stlacena klavesa S
     * @return je S stlacene
     */
    public boolean jeStlacenaKlavesaS() {
        return this.stlacenaKlavesaS;
    }
    /**
     * zistuje ci je stlacena klavesa D
     * @return je D stlacene
     */
    public boolean jeStlacenaKlavesaD() {
        return this.stlacenaKlavesaD;
    }
    /**
     * zistuje ci je stlacena klavesa medzernik
     * @return je medzernik stlaceny
     */
    public boolean jeStlacenaKlavesaMedzernik() {
        return this.stlacenaKlavesaMedzernik;
    }
    /**
     * zistuje ci je stlacena klavesa ESC
     * @return je ESC stlacene
     */
    public boolean jeStlacenaKlavesaESC() {
        return this.stlacenaKlavesaESC;
    }
    /**
     * zistuje ci je stlacena klavesa L
     * @return je L stlacene
     */
    public boolean jeStlacenaKlavesaL() {
        return this.stlacenaKalvesaL;
    }

    /**
     * zistuje ci je stlacena klavesa ENTER
     * @return je ENTER stlacene
     */
    public boolean jeStlacenaKlavesaEnter() {
        return this.stlacenaKlavesaEnter;
    }

    /**
     * konstruktor - nacita zac. hodnoty
     */
    public OvladanieHraca() {
        this.stlacenaKlavesaW = false;
        this.stlacenaKlavesaA = false;
        this.stlacenaKlavesaS = false;
        this.stlacenaKlavesaD = false;
        this.stlacenaKlavesaMedzernik = false;
        this.stlacenaKlavesaEnter = false;
        this.stlacenaKlavesaESC = false;
        this.stlacenaKalvesaL = false;
    }

    /**
     * vykona po napisani e
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * po stlaceni tlacitka zisti, ktore bolo slacene
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int klavesa = e.getKeyCode();

        if (klavesa == KeyEvent.VK_W) {
            this.stlacenaKlavesaW = true;
        }

        if (klavesa == KeyEvent.VK_A) {
            this.stlacenaKlavesaA = true;
        }

        if (klavesa == KeyEvent.VK_S) {
            this.stlacenaKlavesaS = true;
        }

        if (klavesa == KeyEvent.VK_D) {
            this.stlacenaKlavesaD = true;
        }

        if (klavesa == KeyEvent.VK_SPACE) {
            this.stlacenaKlavesaMedzernik = true;
        }

        if (klavesa == KeyEvent.VK_ENTER) {
            this.stlacenaKlavesaEnter = true;
        }

        if (klavesa == KeyEvent.VK_ESCAPE) {
            this.stlacenaKlavesaESC = !this.stlacenaKlavesaESC;
        }

        if (klavesa == KeyEvent.VK_L) {
            this.stlacenaKalvesaL = true;
        }
    }

    /**
     * po pusteni tlacitka, zisti, ktore bolo pustene
     * @param e the event to be processed
     */

    @Override
    public void keyReleased(KeyEvent e) {
        int klavesa = e.getKeyCode();

        if (klavesa == KeyEvent.VK_W) {
            this.stlacenaKlavesaW = false;
        }

        if (klavesa == KeyEvent.VK_A) {
            this.stlacenaKlavesaA = false;
        }

        if (klavesa == KeyEvent.VK_S) {
            this.stlacenaKlavesaS = false;
        }

        if (klavesa == KeyEvent.VK_D) {
            this.stlacenaKlavesaD = false;
        }

        if (klavesa == KeyEvent.VK_SPACE) {
            this.stlacenaKlavesaMedzernik = false;
        }

        if (klavesa == KeyEvent.VK_ENTER) {
            this.stlacenaKlavesaEnter = false;
        }

        if (klavesa == KeyEvent.VK_L) {
            this.stlacenaKalvesaL = false;
        }
    }

    /**
     * setter na to ci je stlacena klavesa ESC
     * @param stlacenaKlavesaESC je stlacene ESC
     */
    public void setStlacenaKlavesaESC(boolean stlacenaKlavesaESC) {
        this.stlacenaKlavesaESC = stlacenaKlavesaESC;
    }
}
