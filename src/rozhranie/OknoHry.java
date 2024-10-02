package rozhranie;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

/**
 * trieda okna hry
 *  @author Ľubomír Bojda
 */
public class OknoHry {

    private JFrame okno;
    /**
     * konstanta sirka okna
     */
    public static final int SIRKA_OKNA = 1280;
    /**
     * konstanta vyska okna
     */
    public static final int VYSKA_OKNA = 832;
    private Canvas platno;

    /**
     * konstruktor
     * @param nazovOkna nazov okna
     */
    public OknoHry(String nazovOkna) {
        this.okno = new JFrame(nazovOkna);
        this.platno = new Canvas();

        this.nastavOkno();
        this.nastavPlatno();

        this.okno.add(this.platno);
        this.okno.pack();

        this.okno.setVisible(true);
    }

    /**
     * nastavi hodnoty platna
     */
    public void nastavPlatno() {
        Dimension velkostPlatna = new Dimension(SIRKA_OKNA, VYSKA_OKNA);
        this.platno.setPreferredSize(velkostPlatna);
        this.platno.setMinimumSize(velkostPlatna);
        this.platno.setMaximumSize(velkostPlatna);
        this.platno.setFocusable(true);
    }

    /**
     * nastavi okno hry
     */
    public void nastavOkno() {
        this.okno.setSize(SIRKA_OKNA, VYSKA_OKNA);
        this.okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.okno.setResizable(false);
        this.okno.setLocationRelativeTo(null);
    }

    /**
     * vrati platno
     * @return platno
     */
    public Canvas getPlatno() {
        return this.platno;
    }

    /**
     * vrati okno
     * @return okno
     */
    public JFrame getOkno() {
        return this.okno;
    }
}
