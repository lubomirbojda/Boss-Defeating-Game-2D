package animacie.bossAnimacie;

public interface Animacia {
    String getNazov();

    void vykonajAnimaciu(int xPosun, int yPosun);

    boolean animaciaDokoncena();
}
