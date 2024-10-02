package pauza;

import levely.TypLevelu;
import rozhranie.NacitavacObrazkov;
import zakladHry.VytvorenieHry;

import javax.swing.JOptionPane;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * trieda pre pauzu v hre
 * @author Ľubomír Bojda
 */
public class Pauza implements MouseListener {

    private BufferedImage obrazok;
    private boolean daSaPokracovat;
    private VytvorenieHry vytvorenieHry;
    private Rectangle pokracovat;
    private Rectangle opakovat;
    private Rectangle mapa;
    private Rectangle menu;

    /**
     * konstruktor
     * @param vytvorenieHry vytvorenie hry
     */
    public Pauza(VytvorenieHry vytvorenieHry) {
        this.vytvorenieHry = vytvorenieHry;
        this.obrazok = NacitavacObrazkov.nacitajObrazok("/textury/pauza.png");
        this.pokracovat = new Rectangle(440, 247, 400, 128);
        this.opakovat = new Rectangle(440, 375, 400, 128);
        this.mapa = new Rectangle(440, 503, 400, 128);
        this.menu = new Rectangle(440, 631, 400, 128);
        this.daSaPokracovat = true;
    }

    /**
     * kliknutie mysi - akcia
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        var p = e.getPoint();

        if (this.vytvorenieHry.getHra().getPohybHraca().getOvladanieHraca().jeStlacenaKlavesaESC()) {
            if (this.pokracovat.contains(p)) {
                if (this.daSaPokracovat) {
                    this.vytvorenieHry.getHra().getPohybHraca().getOvladanieHraca().setStlacenaKlavesaESC(false);
                }
            }
            if (this.opakovat.contains(p)) {
                if (this.vytvorenieHry.getHra().getPohybHraca().getTypLevelu() == TypLevelu.MAPA) {
                    JOptionPane.showMessageDialog(null, "Si v menu, pre opakovanie musis byt v leveli.");
                } else {
                    this.vytvorenieHry.getHra().restartujAktualnyLevel();
                    this.vytvorenieHry.getHra().getPohybHraca().getOvladanieHraca().setStlacenaKlavesaESC(false);
                    this.setPauzaObrazok("/textury/pauza.png");
                    this.setDaSaPokracovat(true);
                }
            }
            if (this.mapa.contains(p)) {
                this.vratHracaNaMapu();
                this.vytvorenieHry.getHra().getPohybHraca().getOvladanieHraca().setStlacenaKlavesaESC(false);
                this.setPauzaObrazok("/textury/pauza.png");
                this.setDaSaPokracovat(true);
            }
            if (this.menu.contains(p)) {
                this.vratHracaNaMapu();
                this.vytvorenieHry.getHra().getPohybHraca().getOvladanieHraca().setStlacenaKlavesaESC(false);
                this.setPauzaObrazok("/textury/pauza.png");
                this.setDaSaPokracovat(true);
                this.vytvorenieHry.vypniHru();
                this.vytvorenieHry.getHlavneMenu().getFrame().setVisible(true);
            }
        }

    }

    /**
     * vrati hraca na mapu z levelu
     */
    public void vratHracaNaMapu() {
        this.vytvorenieHry.getHra().restartujAktualnyLevel();
        this.vytvorenieHry.getHra().getMapa().setAktualnaMiestnost(this.vytvorenieHry.getHra().getMapa().getMiestnostMapySpawn());
        this.vytvorenieHry.getHra().getMapa().setSuborSPozadim(this.vytvorenieHry.getHra().getMapa().getMiestnostMapySpawn().getSuborMiestnosti());
        var level = TypLevelu.MAPA;
        this.vytvorenieHry.getHra().getPohybHraca().setTypLevelu(level);
        this.vytvorenieHry.getHra().getPohybHraca().setyHraca(level.getyHraca());
        this.vytvorenieHry.getHra().getPohybHraca().setxHraca(level.getxHraca());
    }

    /**
     * nastacvi obrazok pauzy
     * @param cestaObrazku cesta obrazku
     */
    public void setPauzaObrazok(String cestaObrazku) {
        this.obrazok = NacitavacObrazkov.nacitajObrazok(cestaObrazku);
    }

    /**
     * vrati obrazok pauzy
     * @return obr. pauzy
     */
    public BufferedImage getObrazok() {
        return this.obrazok;
    }

    /**
     * vrati true ak sa da pokracovat v hre
     * @param daSaPokracovat true/false
     */
    public void setDaSaPokracovat(boolean daSaPokracovat) {
        this.daSaPokracovat = daSaPokracovat;
    }

    /**
     * stlacenie mysi - akcia
     * @param e the event to be processed
     */

    @Override
    public void mousePressed(MouseEvent e) {

    }
    /**
     * pustenie mysi - akcia
     * @param e the event to be processed
     */

    @Override
    public void mouseReleased(MouseEvent e) {

    }
    /**
     * vstup mysi - akcia
     * @param e the event to be processed
     */

    @Override
    public void mouseEntered(MouseEvent e) {

    }
    /**
     * odchod mysi - akcia
     * @param e the event to be processed
     */

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
