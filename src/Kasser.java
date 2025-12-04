 import java.util.ArrayList;
import java.util.Scanner;

public class Kasser {

    private ArrayList<Kontigent> kontigenter = new ArrayList<>();

    public void addKontigenter(Kontigent k){
        kontigenter.add(k);
    }

    public ArrayList<Kontigent> getKontigenter() {
        return kontigenter;
    }

    // Registrer betaling for et medlem
    public void registrerBetaling(String navn){
        Kontigent k = findKontigent(navn);
        if (k != null) {
            k.registrerBetaling();
            System.out.println("Betaling registreret for " + navn);
        } else {
            System.out.println("Medlem ikke fundet.");
        }
    }

    // Find kontingent på medlem
    public Kontigent findKontigent(String navn){
        for (Kontigent k : kontigenter){
            if (k.getMedlem().getNavn().equalsIgnoreCase(navn)){
                return k;
            }
        }
        return null;
    }

    // Oversigt: Betalte medlemmer
    public void oversigtBetalt(){
        System.out.println("-- Betalte medlemmer --");
        for (Kontigent k : kontigenter){
            if (k.getStatus() == Kontigent.Status.BETALT){
                System.out.println(k);
            }
        }
        double sum = sumBetalt();
        System.out.println("Samlet betalt: " + sum + " kr");
    }

    // Oversigt: Ikke-betalte medlemmer
    public void oversigtRestance(){
        System.out.println("-- Medlemmer der mangler at betale --");
        for (Kontigent k : kontigenter){
            if (k.getStatus() == Kontigent.Status.IKKEBETALT){
                System.out.println(k);
            }
        }
        double sum = sumIkkeBetalt();
        System.out.println("Samlet ikke-betalt: " + sum + " kr");
    }

    // Sum af betalte
    public double sumBetalt(){
        double sum = 0;
        for (Kontigent k : kontigenter){
            if (k.getStatus() == Kontigent.Status.BETALT){
                sum += k.getPris();
            }
        }
        return sum;
    }

    // Sum af ikke-betalte
    public double sumIkkeBetalt(){
        double sum = 0;
        for (Kontigent k : kontigenter){
            if (k.getStatus() == Kontigent.Status.IKKEBETALT){
                sum += k.getPris();
            }
        }
        return sum;
    }
}