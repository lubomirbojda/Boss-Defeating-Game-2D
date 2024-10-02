package rozhranie;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

/**
 * metoda na nacitavanie obrazkov do hry
 * @author Ľubomír Bojda
 */
public class NacitavacObrazkov {
    /**
     * konstanta velkosti hraca
     */
    public static final int VELKOST_HRACA_A_OBRAZKU_POZADIA = 64;
    /**
     * konstanta velkosti stely
     */
    public static final int VELKOST_STRELY = 32;
    /**
     * konstanta velkosti boss
     */
    public static final int VELKOST_BOSSA = 128;
    /**
     * konstanta pocetu stlpcov pozadia
     */
    public static final int POCET_STLPCOV_POZADIA = (OknoHry.SIRKA_OKNA / VELKOST_HRACA_A_OBRAZKU_POZADIA) + 2;

    /**
     * metoda na nacitanie obrazku
     * @param cesta cesta obr.
     * @return obrazok
     */
    public static BufferedImage nacitajObrazok(String cesta) {
        try {
            return ImageIO.read(NacitavacObrazkov.class.getResourceAsStream(cesta));
        } catch (IOException e) {
            System.out.println("Nepodarilo sa nacitat obrazok.");
            throw new RuntimeException(e);
        }
    }

    /**
     * metoda z obrazku vyreze mensi obrazok
     * @param obr obrazok
     * @param riadok na vyrezanie
     * @param stlpec na vyrezanie
     * @param typObrazku typ obrazku
     * @return orezany obrazok
     */
    public static BufferedImage podobrazok(BufferedImage obr, int riadok, int stlpec, String typObrazku) {

        return switch (typObrazku) {
            case "hrac" ->
                    obr.getSubimage((stlpec - 1) * VELKOST_HRACA_A_OBRAZKU_POZADIA, (riadok - 1) * VELKOST_HRACA_A_OBRAZKU_POZADIA, VELKOST_HRACA_A_OBRAZKU_POZADIA, VELKOST_HRACA_A_OBRAZKU_POZADIA);
            case "strela" ->
                    obr.getSubimage((stlpec - 1) * VELKOST_STRELY, (riadok - 1) * VELKOST_STRELY, VELKOST_STRELY, VELKOST_STRELY);
            case "boss" ->
                    obr.getSubimage((stlpec - 1) * VELKOST_BOSSA, (riadok - 1) * VELKOST_BOSSA, VELKOST_BOSSA, VELKOST_BOSSA);
            case "pozadie" ->
                    obr.getSubimage(stlpec * VELKOST_HRACA_A_OBRAZKU_POZADIA, riadok * VELKOST_HRACA_A_OBRAZKU_POZADIA, VELKOST_HRACA_A_OBRAZKU_POZADIA, VELKOST_HRACA_A_OBRAZKU_POZADIA);
            default -> null;
        };

    }

}
