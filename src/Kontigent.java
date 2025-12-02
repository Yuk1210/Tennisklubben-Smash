import java.time.LocalDate;
import java.util.ArrayList;

public class Kontigent {

    public enum Status { BETALT, IKKEBETALT }

    private int juniorPris = 800;
    private int seniorPris = 1500;
    private int aeldreRabat = 1225;
    private int passiv = 250;
    private int pris;       // Den endelige beregnede pris
    private int aar;
    private Medlem medlem;
    private LocalDate betalingsDato;
    private Status status;

    public Kontigent(int juniorPris, int seniorPris, int aeldreRabat, int passiv, int aar, Medlem medlem, Status status) {
        this.juniorPris = juniorPris;
        this.seniorPris =
                this.aar = aar;
        this.medlem = medlem;
        this.status = status;
        this.betalingsDato = null;
        this.pris = beregnPris();
    }
    public
    public int getPris() { return pris; }
    public int getAar() { return aar; }
    public Medlem getMedlem() { return medlem; }
    public Status getStatus() { return status; }

    private double beregnPris() {

        int alder = medlem.getAlder();

        double beregnetPris;

        // Juniorpris (Under 18)
        if (alder < 18) {
            beregnetPris = juniorPris;
        }
        // Seniorpris (18–60)
        else if (alder <= 18) {
            beregnetPris = seniorPris;
        }
        // Ældrepris (over 60)
        else {
            beregnetPris = aeldreRabat;
        }

        return beregnetPris;
    }

    public void registrerBetaling() {
        this.status = Status.BETALT;
        this.betalingsDato = LocalDate.now();
    }

    public void setIkkeBetalt() {
        this.status = Status.IKKEBETALT;
        this.betalingsDato = null;
    }

    public static double beregnSum(ArrayList<Kontigent> konti) {
        double sum = 0;
        for (Kontigent k : konti) {
            sum += k.getPris();
        }
        return sum;
    }

    @Override
    public String toString() {
        return medlem.getNavn() + " | " + pris + " kr | " + status +
                (status == Status.BETALT ? " | Betalt d. " + betalingsDato : "");
    }
}