import java.util.ArrayList;
import java.util.Scanner;

public class Formand {

    ArrayList<Medlem> medlemsListe = new ArrayList<>();

    public Medlem registrerMedlem(Medlem medlem) {

        if (medlem == null) {
            throw new IllegalArgumentException("Medlem-objektet er null");
        }
        if (medlem.getNavn() == null || medlem.getNavn().isBlank()) {
            throw new IllegalArgumentException("Navn mangler");
        }
        if (medlem.getAdresse() == null || medlem.getAdresse().isBlank()) {
            throw new IllegalArgumentException("Adresse mangler");
        }
        if (medlem.getEmail() == null || medlem.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email mangler");
        }
        if (medlem.getTlf() == null || medlem.getTlf().isBlank()) {
            throw new IllegalArgumentException("Telefonnummer mangler");
        }
        if (medlem.getAlder() <= 0) {
            throw new IllegalArgumentException("Alder skal være større end 0");
        }
        if (medlem.getMedlemsType() == null) {
            throw new IllegalArgumentException("Medlemstype mangler");
        }
        if (medlem.getSpillerType() == null) {
            throw new IllegalArgumentException("Spillertype mangler");
        }
        if (medlem.getRolleType() == null) {
            throw new IllegalArgumentException("Rolletype mangler");
        }

        for (Medlem m : medlemsListe) {
            if (m.getId() == medlem.getId()) {
                throw new IllegalArgumentException("ID " + medlem.getId() + " findes allerede");
            }
        }

        medlemsListe.add(medlem);
        return medlem;
    }
    // FJERN MEDLEM via ID
    public boolean fjernMedlem(int id) {
        for (Medlem m : medlemsListe) {
            if (m.getId() == id) {
                medlemsListe.remove(m);
                System.out.println("Medlem fjernet: " + m.getNavn());
                return true;
            }
        }

        System.out.println("Intet medlem med ID " + id + " fundet.");
        return false;
    }



    public Medlem opretMedlemViaBrugerInput() {

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

        System.out.print("Medlemstype (AKTIV/PASSIV): ");
        Medlem.medlemsType mType = Medlem.medlemsType.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Spillertype (JUNIOR/SENIOR): ");
        Medlem.spillerType sType = Medlem.spillerType.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Rolletype (MOTIONIST/KONKURRENCE): ");
        Medlem.rolleType rType = Medlem.rolleType.valueOf(sc.nextLine().toUpperCase());

        Medlem medlem = new Medlem(navn, adresse, alder, email, tlf, id, "Medlem", mType, sType, rType);

        return registrerMedlem(medlem);
    }

    public ArrayList<Medlem> getMedlemsListe() {
        return medlemsListe;
    }
}