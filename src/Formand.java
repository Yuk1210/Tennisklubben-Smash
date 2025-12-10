 import java.util.ArrayList;
import java.util.Scanner;

public class Formand {
    ArrayList<Medlem> medlemsListe = new ArrayList<>();

    public void registrerMedlem() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Fornavn: ");
        String fornavn = scanner.nextLine().trim();
        if (fornavn.isEmpty()) {
            System.out.println("Fejl: Fornavn skal udfyldes!");
            return;
        }

        System.out.print("Efternavn: ");
        String efternavn = scanner.nextLine().trim();
        if (efternavn.isEmpty()) {
            System.out.println("Fejl: Efternavn skal udfyldes!");
            return;
        }

        System.out.print("Adresse: ");
        String adresse = scanner.nextLine().trim();
        if (adresse.isEmpty()) {
            System.out.println("Fejl: Adresse skal udfyldes!");
            return;
        }

        System.out.print("Alder: ");
        int alder;
        try {
            alder = Integer.parseInt(scanner.nextLine());
            if (alder <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Fejl: Alder skal være et positivt tal!");
            return;
        }

        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (!email.contains("@") || !email.contains(".")) {
            System.out.println("Fejl: Email skal indeholde '@' og '.'!");
            return;
        }

        System.out.print("Telefon (8 cifre): ");
        String telefon = scanner.nextLine();
        if (!telefon.matches("\\d{8}")) {
            System.out.println("Fejl: Telefon skal være 8 cifre!");
            return;
        }

        System.out.print("ID: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Fejl: ID skal være et tal!");
            return;
        }

        System.out.print("Beskæftigelse: ");
        String beskæftigelse = scanner.nextLine().trim();
        if (beskæftigelse.isEmpty()) {
            System.out.println("Fejl: Beskæftigelse skal udfyldes!");
            return;
        }

        // Medlemstype
        Medlem.medlemsType medlemsType;
        while (true) {
            System.out.print("Medlemstype (AKTIV/PASSIV): ");
            String mInput = scanner.nextLine().trim().toUpperCase();
            if (mInput.equals("AKTIV")) {
                medlemsType = Medlem.medlemsType.AKTIV;
                break;
            } else if (mInput.equals("PASSIV")) {
                medlemsType = Medlem.medlemsType.PASSIV;
                break;
            } else {
                System.out.println("Fejl: Indtast enten AKTIV eller PASSIV.");
            }
        }

        // Spillertype udregnes automatisk
        Medlem.spillerType spillerType = (alder < 18) ? Medlem.spillerType.JUNIOR : Medlem.spillerType.SENIOR;

        // Rolletype
        Medlem.rolleType rolleType;
        while (true) {
            System.out.print("Rolletype (MOTIONIST/KONKURRENCE): ");
            String rInput = scanner.nextLine().trim().toUpperCase();
            if (rInput.equals("MOTIONIST")) {
                rolleType = Medlem.rolleType.MOTIONIST;
                break;
            } else if (rInput.equals("KONKURRENCE")) {
                rolleType = Medlem.rolleType.KONKURRENCE;
                break;
            } else {
                System.out.println("Fejl: Indtast enten MOTIONIST eller KONKURRENCE.");
            }
        }

        Medlem medlem = new Medlem(fornavn, efternavn, adresse, alder, email, telefon, id, beskæftigelse,
                medlemsType, spillerType, rolleType);

        medlemsListe.add(medlem);
        System.out.println("Medlem " + fornavn + " " + efternavn + " tilføjet.");
    }

    public void visMedlemmer() {
        for (Medlem medlem : medlemsListe) {
            System.out.println(medlem.getNavn());
        }
    }

    /**
     * Fjerner det første medlem i listen, hvis id matcher.
     *
     * @param id id'et på det medlem, der skal fjernes
     * @return true hvis et medlem blev fjernet, false hvis ikke fundet
     */
    public boolean fjernMedlem(int id) {
        for (Medlem m : medlemsListe) {
            if (m.getId() == id) {
                medlemsListe.remove(m);
                System.out.println("Medlem " + m.getNavn() + " (id=" + id + ") er fjernet.");
                return true;
            }
        }
        System.out.println("Intet medlem med id=" + id + " fundet.");
        return false;
    }

    public static Medlem opretMedlemViaBrugerInput(ArrayList<Medlem> eksisterendeListe) {
        Scanner sc = new Scanner(System.in);

        // ---------- NAVN ----------
        String navn = null;
        while (navn == null || !navn.matches("[A-Za-zÆØÅæøå]+")) {
            System.out.print("Navn (kun bogstaver): ");
            navn = sc.nextLine().trim();
            if (navn.isEmpty() || !navn.matches("[A-Za-zÆØÅæøå]+"))
                System.out.println("Fejl: Navn må kun indeholde bogstaver – prøv igen.");
        }

        // ---------- EFTERNAVN ----------
        String efternavn = null;
        while (efternavn == null || !efternavn.matches("[A-Za-zÆØÅæøå]+")) {
            System.out.print("Efternavn (kun bogstaver): ");
            efternavn = sc.nextLine().trim();
            if (efternavn.isEmpty() || !efternavn.matches("[A-Za-zÆØÅæøå]+"))
                System.out.println("Fejl: Efternavn må kun indeholde bogstaver – prøv igen.");
        }

        // ---------- ADRESSE ----------
        String adresse = null;
        while (adresse == null || adresse.trim().isEmpty()) {
            System.out.print("Adresse: ");
            adresse = sc.nextLine().trim();
            if (adresse.isEmpty()) System.out.println("Adresse må ikke være tom – prøv igen.");
        }

        // ---------- ALDER ----------
        int alder = 0;
        while (alder <= 0) {
            System.out.print("Alder (kun tal): ");
            String line = sc.nextLine().trim();
            if (!line.matches("\\d+")) {
                System.out.println("Fejl: Alder må kun indeholde tal – prøv igen.");
                continue;
            }
            alder = Integer.parseInt(line);
            if (alder <= 0) System.out.println("Fejl: Alder skal være et positivt tal – prøv igen.");
        }

        // ---------- EMAIL ----------
        String email = null;
        while (email == null || !email.contains("@") || !email.contains(".")) {
            System.out.print("Email: ");
            email = sc.nextLine().trim();
            if (!email.contains("@") || !email.contains("."))
                System.out.println("Fejl: Email skal indeholde '@' og '.' – prøv igen.");
        }

        // ---------- TELEFON ----------
        String tlf = null;
        while (tlf == null || !tlf.matches("\\d{8}")) {
            System.out.print("Telefon (8 cifre, kun tal): ");
            tlf = sc.nextLine().trim();
            if (!tlf.matches("\\d{8}"))
                System.out.println("Fejl: Telefon skal være præcis 8 cifre og kun indeholde tal – prøv igen.");
        }

        // ---------- ID + DUBLET-TJEK ----------
        int id = 0;
        while (id <= 0) {
            System.out.print("ID (kun tal): ");
            String line = sc.nextLine().trim();
            if (!line.matches("\\d+")) {
                System.out.println("Fejl: ID må kun indeholde tal – prøv igen.");
                continue;
            }
            id = Integer.parseInt(line);
            if (id <= 0) {
                System.out.println("Fejl: ID skal være et positivt tal – prøv igen.");
                continue;
            }
            // Tjek for dubletter
            boolean dublet = false;
            for (Medlem m : eksisterendeListe) {
                if (m.getId() == id) {
                    dublet = true;
                    break;
                }
            }
            if (dublet) {
                System.out.println("Fejl: ID findes allerede – vælg et andet.");
                id = 0; // tving ny indtastning
            }
        }

        // ---------- BESKÆFTIGELSE ----------
        String beskæftigelse = null;
        while (beskæftigelse == null || beskæftigelse.trim().isEmpty()) {
            System.out.print("Beskæftigelse: ");
            beskæftigelse = sc.nextLine().trim();
            if (beskæftigelse.isEmpty()) System.out.println("Beskæftigelse må ikke være tom – prøv igen.");
        }

        // ---------- MEDLEMSTYPE ----------
        Medlem.medlemsType mType = null;
        while (mType == null) {
            System.out.print("Medlemstype (AKTIV/PASSIV): ");
            String in = sc.nextLine().trim().toUpperCase();
            if (in.equals("AKTIV")) mType = Medlem.medlemsType.AKTIV;
            else if (in.equals("PASSIV")) mType = Medlem.medlemsType.PASSIV;
            else System.out.println("Indtast enten AKTIV eller PASSIV – prøv igen.");
        }

        // ---------- SPILLERTYPE (auto) ----------
        Medlem.spillerType sType = (alder < 18) ? Medlem.spillerType.JUNIOR : Medlem.spillerType.SENIOR;

        // ---------- ROLLETYPE ----------
        Medlem.rolleType rType;
        if (mType == Medlem.medlemsType.PASSIV) {
            rType = Medlem.rolleType.MOTIONIST;
        } else {

            System.out.print("Rolletype (MOTIONIST/KONKURRENCE): ");
            String rInput = sc.nextLine();
            rType = rInput.equalsIgnoreCase("MOTIONIST") ? Medlem.rolleType.MOTIONIST : Medlem.rolleType.KONKURRENCE;
        }
        return new Medlem(navn, efternavn, adresse, alder, email, tlf, id, "Medlem", mType, sType, rType);
    }
}