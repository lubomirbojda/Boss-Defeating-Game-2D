package mapa;

import java.util.HashMap;

/**
 * trieda jednej miestnosti mapy
 * @author Ľubomír Bojda
 */
public class MiestnostMapy {
    private HashMap<String, MiestnostMapy> vychodyMiestnosti;
    private String suborMiestnosti;

    /**
     * konstruktoe
     * @param suborMiestnosti cesta k suboru
     */
    public MiestnostMapy(String suborMiestnosti) {
        this.vychodyMiestnosti = new HashMap<>();
        this.suborMiestnosti = suborMiestnosti;
    }

    /**
     * pridaj vychod miestnosti
     * @param smer smer
     * @param miestnostMapy miestnost
     */
    public void pridajVychod(String smer, MiestnostMapy miestnostMapy) {
        this.vychodyMiestnosti.put(smer, miestnostMapy);
    }

    /**
     * vrat vychod v smere
     * @param smer semr vychodu
     * @return vychod
     */
    public MiestnostMapy getVychod(String smer) {
        return this.vychodyMiestnosti.get(smer);
    }

    /**
     * vrat subor miestnosti
     * @return subor miestnosti
     */
    public String getSuborMiestnosti() {
        return this.suborMiestnosti;
    }
}
