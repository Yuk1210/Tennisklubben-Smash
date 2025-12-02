import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Kasser kasser = new Kasser(); // Kasser-objekt
        Formand formand = new Formand(); // Formand-objekt
        boolean running = true;

        while (running) {

            System.out.println("\n=== HOVEDMENU ===");
            System.out.println("1. Formand");
            System.out.println("2. Coach");
            System.out.println("3. Kasser");
            System.out.println("4. Afslut");
            System.out.print("Vælg: ");

            int choice = input.nextInt();
            input.nextLine(); // fjern newline

            switch (choice) {

                case 1 -> { // Formand
                    boolean kørFormand = true;

                    while (kørFormand) {
                        System.out.println("\n=== FORMAND MENU ===");
                        System.out.println("1. Opret medlem");
                        System.out.println("2. Fjern medlem");
                        System.out.println("3. Vis alle medlemmer");
                        System.out.println("9. Tilbage til hovedmenu");
                        System.out.print("Vælg: ");

                        int fValg = input.nextInt();
                        input.nextLine(); // fjern newline

                        switch (fValg) {

                            case 1 -> { // Opret medlem
                                System.out.println("\n--- Opret Medlem ---");
                                try {
                                    Medlem nyt = Formand.opretMedlemViaBrugerInput(formand.medlemsListe);
                                    formand.medlemsListe.add(nyt);

                                    // Automatisk kontingent oprettelse
                                    int juniorPris = 800;
                                    int seniorPris = 1500;
                                    int aeldreRabat = 1125;
                                    int passivPris = 250;
                                    int aar = 2025;
                                    Kontigent k = new Kontigent(juniorPris, seniorPris, aeldreRabat, passivPris, aar, nyt, Kontigent.Status.IKKEBETALT);
                                    kasser.addKontigenter(k);

                                    System.out.println("Medlem oprettet!");
                                } catch (Exception e) {
                                    System.out.println("Uventet fejl: " + e.getMessage());
                                }
                            }

                            case 2 -> { // Fjern medlem
                                System.out.println("\n--- Fjern Medlem ---");
                                System.out.print("Indtast ID på medlem der skal fjernes: ");
                                int id;
                                try {
                                    id = Integer.parseInt(input.nextLine().trim());
                                    formand.fjernMedlem(id);
                                } catch (NumberFormatException e) {
                                    System.out.println("Ugyldigt tal.");
                                }
                            }

                            case 3 -> { // Vis medlemmer
                                System.out.println("\n--- Medlemsliste ---");
                                if (formand.medlemsListe.isEmpty()) {
                                    System.out.println("Ingen medlemmer registreret.");
                                } else {
                                    for (Medlem m : formand.medlemsListe) {
                                        System.out.println(m.getId() + " - " + m.getNavn());
                                    }
                                }
                            }

                            case 9 -> { // Tilbage til hovedmenu
                                kørFormand = false;
                            }

                            default -> System.out.println("Ugyldigt valg. Prøv igen.");
                        }
                    }
                }

                case 2 -> { // Coach
                    System.out.println("Salam Coach Carter - Menu (under udvikling)");
                    // Her kan du senere tilføje ranglister og kampregistrering
                }

                case 3 -> { // Kasser
                    boolean kørKasser = true;

                    while (kørKasser) {
                        System.out.println("\n=== KASSER MENU ===");
                        System.out.println("1. Vis alle kontingenter");
                        System.out.println("2. Registrer betaling");
                        System.out.println("3. Se samlet sum");
                        System.out.println("4. Tilbage til hovedmenu");
                        System.out.print("Vælg: ");

                        int kValg = input.nextInt();
                        input.nextLine();

                        switch (kValg) {

                            case 1 -> { // Vis kontingenter
                                for (Kontigent k : kasser.getKontigenter()) {
                                    int dageTilbage = k.dageTilbage();
                                    System.out.println(
                                            k.getMedlem().getNavn() + " | " +
                                                    k.getMedlem().getSpillerType() + " | " +
                                                    k.getMedlem().getMedlemsType() + " | " +
                                                    k.getStatus() + " | " +
                                                    k.getPris() + " kr | " +
                                                    "Dage tilbage: " + dageTilbage
                                    );
                                }
                            }

                            case 2 -> { // Registrer betaling
                                System.out.print("Indtast navn på medlem der har betalt: ");
                                String navn = input.nextLine();
                                kasser.registrerBetaling(navn);
                            }

                            case 3 -> { // Se samlet sum
                                double sumBetalt = kasser.sumBetalt();
                                double sumIkkeBetalt = kasser.sumIkkeBetalt();
                                System.out.println("Samlet betalt: " + sumBetalt + " kr");
                                System.out.println("Samlet ikke betalt: " + sumIkkeBetalt + " kr");
                            }

                            case 4 -> kørKasser = false; // Tilbage til hovedmenu

                            default -> System.out.println("Ugyldigt valg!");
                        }
                    }
                }

                case 4 -> { // Afslut
                    System.out.println("Program afsluttes.");
                    running = false;
                }

                default -> System.out.println("Ugyldigt valg!");
            }
        }
    }
}
