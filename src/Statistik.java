import java.util.ArrayList;
import java.util.Scanner;

public class Statistik {

    private int kampeSpillet;
    private int kampeVundet;
    private int kampeTabt;

    public Statistik(Medlem medlem){
        this.kampeSpillet = 0;
        this.kampeVundet = 0;
        this.kampeTabt = 0;
    }

    public void registrerKamp(boolean vundet){
        kampeSpillet++;
        if(vundet)kampeVundet++;
        else kampeTabt++;
    }

    public int getKampeSpillet() {return kampeSpillet;}
    public int getKampeVundet() {return kampeVundet;}
    public int getKampeTabt() {return kampeTabt;}

    public double getWinrate(){
        if(kampeSpillet == 0) return 0.0;
        return(double) kampeVundet/kampeSpillet * 100;
    }

    @Override
    public String toString() {
        return "Kampe spillet: " + kampeSpillet +
                "\nKampe vundet: " + kampeVundet +
                "\nKampe tabt: " + kampeTabt +
                String.format("\nWinrate: %.2f%%", getWinrate());
    }

    public static void seStats(ArrayList<Medlem> medlemmer) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Indtast dit medlems-ID: ");
        int id = sc.nextInt();

        //Find medlem
        for(Medlem m : medlemmer){
            if(m.getId() == id){
                System.out.println("\n--- DINE STATS ---");
                System.out.println("Navn: " + m.getNavn());
                System.out.println("Spiller type: " + m.getSpillerType());
                System.out.println("-------------------");
                System.out.println(m.getSpillerType());
                return;
            }
        }

        System.out.println("Ingen medlem fundet med ID: " + id);
    }
}
