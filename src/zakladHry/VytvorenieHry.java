package zakladHry;

import menu.HlavneMenu;
import pauza.Pauza;
import rozhranie.OknoHry;

import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 * trieda vytvori vsetko podstatne pre start hry
 *@author Ľubomír Bojda
 */
public class VytvorenieHry implements Runnable {
    private OknoHry oknoHry;
    private Thread thread;
    private boolean hraBezi;
    private Graphics g;
    private BufferStrategy bs;
    private Hra hra;
    private JButton spat;
    private Pauza pauza;
    private HlavneMenu hlavneMenu;

    /**
     * konstruktor
     * @param hlavneMenu hlavne menu
     */
    public VytvorenieHry(HlavneMenu hlavneMenu) {
        this.hlavneMenu = hlavneMenu;
        this.pauza = new Pauza(this);
        this.hraBezi = false;
        this.hra = new Hra(this);
        this.inicializujHru();
        this.spat = new JButton("ahoj");
        this.spat.setBounds(100, 100, 100, 100);
        this.spat.setVisible(true);
        this.oknoHry.getOkno().add(this.spat);
    }

    /**
     * loop hry
     *
     * @author CodeNMore - (https://www.youtube.com/watch?v=vFRuEgEdO9Q&list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ&index=4);
     */
    @Override
    public void run() {


        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (this.hraBezi) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {

                this.tickHry();
                this.renderHry();

                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                ticks = 0;
                timer = 0;
            }


        }
    }

    /**
     * inicializacie zakladov hry
     */
    public void inicializujHru() {
        this.oknoHry = new OknoHry("Semestralna praca 2");
        this.oknoHry.getPlatno().addKeyListener(this.hra.getPohybHraca().getOvladanieHraca());
        this.oknoHry.getPlatno().addMouseListener(this.pauza);
    }

    /**
     * prikazy v metode sa vykonavaju v loope
     */
    public void tickHry() {
        if (!this.hra.getPohybHraca().getOvladanieHraca().jeStlacenaKlavesaESC()) {
            this.hra.aktualizaciaSuradnic();
        }
    }
/**
 * prikazy v metode sa vykonavaju v loope a metoda vykresluje na platno
 */
    public void renderHry() {
        this.bs = this.oknoHry.getPlatno().getBufferStrategy();
        if (this.bs == null) {
            this.oknoHry.getPlatno().createBufferStrategy(3);
            return;
        }
        this.g = this.bs.getDrawGraphics();
        this.g.clearRect(0, 0, OknoHry.SIRKA_OKNA, OknoHry.VYSKA_OKNA);
        //zaciatok renderu

        if (!this.hra.getPohybHraca().getOvladanieHraca().jeStlacenaKlavesaESC()) {
            this.hra.vykreslenie(this.g);
        } else {
            this.g.drawImage(this.pauza.getObrazok(), 0, 0, null);
        }


        //koniec renderu

        this.bs.show();
        this.g.dispose();
    }

    /**
     * zapnutie hry
     */
    public void zapniHru() {
        if (this.hraBezi) {
            return;
        }
        this.hraBezi = true;
        this.thread = new Thread(this);
        this.thread.start();
        this.oknoHry.getOkno().setVisible(true);
    }

    /**
     * vypnutie hry
     */
    public void vypniHru() {
        if (!this.hraBezi) {
            return;
        }
        this.hraBezi = false;
        this.oknoHry.getOkno().setVisible(false);
    }

    /**
     * vrat hru
     * @return hra
     */
    public Hra getHra() {
        return this.hra;
    }

    /**
     * vrat hlavne menu
     * @return hl. menu
     */
    public HlavneMenu getHlavneMenu() {
        return this.hlavneMenu;
    }

    /**
     * vrat pauzu
     * @return pauza
     */
    public Pauza getPauza() {
        return this.pauza;
    }
}
