import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Medlem> medlemmer = new ArrayList<>();
        Kasser kasser = new Kasser(); // Kasser-objekt

        Coach coach = new Coach("Coach Carter");
        List<KonkurrenceSpiller> alleSpillere = new ArrayList<>();


        boolean running = true;

        while (running) {

            System.out.println("=== HOVEDMENU ===");
            System.out.println("1. Formand");
            System.out.println("2. Coach");
            System.out.println("3. Kasser");
            System.out.println("4. Afslut");

            int choice = input.nextInt();
            input.nextLine(); // fjern newline

            switch (choice) {

                case 1 -> { // Formand
                    System.out.println("-- Hej hr.formand --");
                    System.out.println("1. Opret medlem");
                    System.out.println("2. Fjern medlem");

                    int fValg = input.nextInt();
                    input.nextLine();

                    switch (fValg) {
                        case 1 -> {
                            Medlem m = opretMedlem();
                            medlemmer.add(m);

                            // Opret kontingent automatisk
                            Kontigent k = new Kontigent(2025, m, Kontigent.Status.IKKEBETALT);
                            kasser.addKontigenter(k);

                            System.out.println("Medlem oprettet:");
                            System.out.println(m);
                        }
                        case 2 -> {
                            System.out.println("Medlemsliste:");
                            for (Medlem m : medlemmer) {
                                System.out.println(m);
                            }

                            System.out.print("Indtast ID på medlem der skal fjernes: ");
                            int id = input.nextInt();
                            input.nextLine();

                            Medlem toRemove = null;
                            for (Medlem m : medlemmer) {
                                if (m.getId() == id) {
                                    toRemove = m;
                                    break;
                                }
                            }

                            if (toRemove != null) {
                                medlemmer.remove(toRemove);
                                System.out.println("Medlem fjernet: " + toRemove.getNavn());
                            } else {
                                System.out.println("Medlem ikke fundet.");
                            }
                        }
                        default -> System.out.println("Ugyldigt valg!");
                    }
                }

                case 2 -> {

                    System.out.println("Salam Coach Carter - Menu");
                    System.out.println("1. Vis ranglister");
                    System.out.println("2. Registrer kamp");
                    System.out.println("3. Tilbage til Smash hovedmenu");
                    System.out.println("Vælg input: ");

                    int coachValg = input.nextInt();
                    input.nextLine();

                    if (coachValg == 1) {
                        System.out.println("\n Salaam Coach, Vælg disciplin:");
                        System.out.println("1. Junior Single");
                        System.out.println("2. Junior Double");
                        System.out.println("3. Junior Mixed");
                        System.out.println("4. Senior Single");
                        System.out.println("5. Senior Double");
                        System.out.println("6. Senior Mixed");

                        int disciplinValg = input.nextInt();
                        input.nextLine();

                        if (disciplinValg == 1) {
                            coach.printListe(coach.udtagJuniorSingle(alleSpillere));
                        } else if (disciplinValg == 2) {
                            coach.printListe(coach.udtagJuniorDouble(alleSpillere));
                        } else if (disciplinValg == 3) {
                            coach.printListe(coach.udtagJuniorMixed(alleSpillere));
                        } else if (disciplinValg == 4) {
                            coach.printListe(coach.udtagSeniorSingle(alleSpillere));
                        } else if (disciplinValg == 5) {
                            coach.printListe(coach.udtagSeniorDouble(alleSpillere));
                        } else if (disciplinValg == 6) {
                            coach.printListe(coach.udtagSeniorMixed(alleSpillere));
                        } else System.out.println("Ugyldigt valg, prøv igen");


                    } else if (coachValg == 2) {

                    } else {
                        System.out.println("Tilbage til Smash hovedmenu. ");
                    }


                }
            }

            case 3 -> { // Kasser
                System.out.println("=== Kontingenter ===");

                // Vis alle kontingenter med pris, type og status
                for (Kontigent k : kasser.getKontigenter()) {
                    System.out.println(
                            k.getMedlem().getNavn() + " | " +
                                    k.getMedlem().getSpillerType() + " | " +
                                    k.getStatus() + " | " +
                                    k.getPris() + " kr"
                    );
                }

                // Vis samlet pris
                double sum = Kontigent.beregnSum(kasser.getKontigenter());
                System.out.println("-------------------");
                System.out.println("Samlet pris: " + sum + " kr");
            }

            case 4 -> {
                System.out.println("Program afsluttes.");
                running = false;
            }

            default -> System.out.println("Ugyldigt valg!");
        }
    }
}
// ---------------- OPRET MEDLEM ----------------
public static Medlem opretMedlem() {
    Scanner sc = new Scanner(System.in);

    System.out.print("Navn: ");
    String navn = sc.nextLine();

    System.out.print("Adresse: ");
    String adresse = sc.nextLine();

    System.out.print("Alder: ");
    int alder = sc.nextInt();
    sc.nextLine();

    System.out.print("Email: ");
    String email = sc.nextLine();

    System.out.print("Telefon: ");
    String tlf = sc.nextLine();

    System.out.print("ID: ");
    int id = sc.nextInt();
    sc.nextLine();

    System.out.println("Medlemstype (AKTIV/PASSIV): ");
    Medlem.medlemsType mType =
            Medlem.medlemsType.valueOf(sc.nextLine().toUpperCase());

    System.out.println("Rolletype (MOTIONIST/KONKURRENCE): ");
    Medlem.rolleType rType =
            Medlem.rolleType.valueOf(sc.nextLine().toUpperCase());

    // Automatisk spillerType baseret på alder
    Medlem.spillerType sType = (alder < 18) ? Medlem.spillerType.JUNIOR : Medlem.spillerType.SENIOR;

    return new Medlem(navn, adresse, alder, email, tlf, id,
            "Medlem", mType, sType, rType);
}
}

