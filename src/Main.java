 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Kasser kasser = new Kasser(); // Kasser-objekt
        Formand formand = new Formand(); // Formand-objekt
        Coach coach = new Coach("Coach Carter");
        boolean running = true;
        ArrayList<Medlem> medlemmer = Filehandler.hentMedlemmer();
        formand.medlemsListe = medlemmer;
        List<KonkurrenceSpiller> alleSpillere = new ArrayList<>();


        while (running) {

            System.out.println("\n=== HOVEDMENU ===");
            System.out.println("1. Formand");
            System.out.println("2. Coach");
            System.out.println("3. Kasser");
            System.out.println("4. Medlem");
            System.out.println("5. Afslut");
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
                                    Filehandler.gemMedlemmer(formand.medlemsListe);

                                    if (nyt.getRolleType() == Medlem.rolleType.KONKURRENCE) {
                                        System.out.println("Tilføjer konkurrencespiller til coach-liste...");

                                        List<KonkurrenceSpiller.Disciplin> d = new ArrayList<>();

                                        // Lad brugeren vælge SINGLE / DOUBLE / MIXED
                                        System.out.println("Vælg disciplin for spilleren:");
                                        System.out.println("1. Single");
                                        System.out.println("2. Double");
                                        System.out.println("3. Mixed");

                                        int valg = input.nextInt();
                                        input.nextLine();

                                        if (valg == 1) d.add(KonkurrenceSpiller.Disciplin.SINGLE);
                                        if (valg == 2) d.add(KonkurrenceSpiller.Disciplin.DOUBLE);
                                        if (valg == 3) d.add(KonkurrenceSpiller.Disciplin.MIXED);

                                        alleSpillere.add(new KonkurrenceSpiller(nyt, 100, d));

                                        System.out.println("Konkurrencespiller tilføjet til Coach-listen!");
                                    }





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
                case 2 -> {

                    System.out.println("");
                    System.out.println("Hej Coach Carter - Menu");
                    System.out.println("1. Vis ranglister");
                    System.out.println("2. Registrer kamp");
                    System.out.println("3. Tilbage til Smash hovedmenu");
                    System.out.println("Vælg input: ");

                    int coachValg = input.nextInt();
                    input.nextLine();

                    if (coachValg == 1) {
                        System.out.println("\n Hej Coach, Vælg disciplin:");
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
                        System.out.println("=== Registrer kamp ===");

                        if (alleSpillere.isEmpty()) {
                            System.out.println("Ingen konkurrence spillere i systemet");
                            break;
                        }

                        System.out.println("Vælg Spiller: ");
                        for (int i = 0; i < alleSpillere.size(); i++) {
                            System.out.println((i + 1) + ". " + alleSpillere.get(i).getMedlem().getNavn());
                        }

                        int spillerValg = input.nextInt() - 1;
                        input.nextLine();

                        if (spillerValg < 0 || spillerValg >= alleSpillere.size()) {
                            System.out.println("Ugyldigt valg");
                            break;
                        }

                        KonkurrenceSpiller spiller = alleSpillere.get(spillerValg);

                        System.out.println("Indtast modstanders navn: ");
                        String navn = input.nextLine();
                        Modstander modstander = new Modstander(navn);

                        System.out.println("Vores spiller score: ");
                        int mig = input.nextInt();

                        System.out.println("Vores modstander score: ");
                        int mod = input.nextInt();
                        input.nextLine();

                        coach.registrerKamp(spiller, modstander, mig, mod);

                        System.out.println("Kamp registreret:");

                    } else if (coachValg == 3) {
                        System.out.println("Tilbage til Smash hovedmenu. ");
                    } else {
                        System.out.println("Ugyldigt valg, prøv igen");
                    }
                }
                case 3 -> { // Kasser
                    boolean kørKasser = true;

                    while (kørKasser) {
                        System.out.println("\n=== KASSER MENU ===");
                        System.out.println("1. Vis alle kontingenter");
                        System.out.println("2. Registrer betaling");
                        System.out.println("3. Se samlet sum");
                        System.out.println("4. Se betalings status");
                        System.out.println("5. Tilbage til hovedmenu");
                        System.out.print("Vælg: ");

                        int kValg = input.nextInt();
                        input.nextLine();

                        ArrayList<Kontigent> konti = kasser.getKontigenter();


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

                                Kontigent k2 = kasser.findKontigent(navn);
                                if (k2 != null) {
                                    k2.registrerBetaling();
                                    Filehandler.gemBetaling(k2);
                                } else {
                                    System.out.println("Medlem ikke fundet.");
                                }
                            }

                            case 3 -> { // Se samlet sum
                                double sumBetalt = kasser.sumBetalt();
                                double sumIkkeBetalt = kasser.sumIkkeBetalt();
                                System.out.println("Samlet betalt: " + sumBetalt + " kr");
                                System.out.println("Samlet ikke betalt: " + sumIkkeBetalt + " kr");
                            }

                            case 4 -> {
                                for (Kontigent k : konti) {
                                    System.out.println(k);
                                }
                            }

                            case 5 -> kørKasser = false; // Tilbage til hovedmenu

                            default -> System.out.println("Ugyldigt valg!");
                        }
                    }
                }
                case 4 -> {
                    Statistik.seStats(medlemmer);
                }
                case 5 -> { // Afslut
                    System.out.println("Program afsluttes.");
                    running = false;
                }

                default -> System.out.println("Ugyldigt valg!");
            }
        }
    }
}
