import java.io.*;
import java.util.ArrayList;

//Medlemmer Linje 14-86
//Betaling Linje 88-108
//Konkurrencespillere Linje 111-187

public class Filehandler {

    private static final String MEDLEMSFIL = "medlemmer.txt";
    private static final String BETALINGSFIL = "betalinger.txt";
    private static final String FIL = "konkurrencespillere.txt";

    // Gemmer alle medlemmer
    public static void gemMedlemmer(ArrayList<Medlem> medlemmer) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(MEDLEMSFIL))) {

            for (Medlem m : medlemmer) {
                pw.println("-------------------");
                pw.println("ID: " + m.getId());
                pw.println("Navn: " + m.getNavn() + " " + m.getEfternavn());
                pw.println("Adresse: " + m.getAdresse());
                pw.println("Alder: " + m.getAlder());
                pw.println("Email: "  + m.getEmail());
                pw.println("Telefon: " + m.getTlf());
                pw.println("Beskaeftigelse: " +  m.getBeskaeftigelse());
                pw.println("Medlemstype: " + m.getMedlemsType());
                pw.println("Spillertype: " + m.getSpillerType());
                pw.println("Rolletype: " + m.getRolleType());
                pw.println("-------------------");
                pw.println();
            }

        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til medlemmer.txt");
        }
    }

    // Genindlæser filen af medlemmer
    public static ArrayList<Medlem> hentMedlemmer() {
        ArrayList<Medlem> liste = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(MEDLEMSFIL))) {

            String linje;
            String navn = "", efternavn = "", adresse = "", email = "", tlf = "", besk = "";
            int alder = 0, id = 0;
            Medlem.medlemsType mType = null;
            Medlem.spillerType sType = null;
            Medlem.rolleType rType = null;


            while ((linje = br.readLine()) != null) {

                if (linje.startsWith("ID:")) {
                    id = Integer.parseInt(linje.substring(4).trim());
                }
                else if (linje.startsWith("Navn:")) {
                    String[] s = linje.substring(6).split(" ");
                    navn = s[0];
                    efternavn = s.length > 1 ? s[1] : "";
                }
                else if (linje.startsWith("Adresse:")) adresse = linje.substring(9).trim();
                else if (linje.startsWith("Alder:")) alder = Integer.parseInt(linje.substring(7).trim());
                else if (linje.startsWith("Email:")) email = linje.substring(7).trim();
                else if (linje.startsWith("Tlf:")) tlf = linje.substring(9).trim();
                else if (linje.startsWith("Beskaeftigelse:")) besk = linje.substring(15).trim();
                else if (linje.startsWith("Medlemstype:")) mType = Medlem.medlemsType.valueOf(linje.substring(13).trim());
                else if (linje.startsWith("Spillertype:")) sType = Medlem.spillerType.valueOf(linje.substring(13).trim());
                else if (linje.startsWith("Rolletype:")) rType = Medlem.rolleType.valueOf(linje.substring(11).trim());

                else if (linje.isBlank()) {
                    if (id != 0) {
                        liste.add(new Medlem(
                                navn, efternavn, adresse, alder, email, tlf, id,
                                besk, mType, sType, rType
                        ));
                        id = 0; // Nulstil så du ikke tilføjer igen
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Ingen medlemmer.txt fundet – tom liste oprettet.");
        }

        return liste;
    }

    // Gemmer betaling
    public static void gemBetaling(Kontigent k) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BETALINGSFIL, true))) {

            pw.println("-------------------");
            pw.println("Navn: " + k.getMedlem().getNavn() + " " + k.getMedlem().getEfternavn());
            pw.println("År: " + k.getAar());
            pw.println("Pris: " + k.getPris() + "kr");
            pw.println("Status: " + k.getStatus());

            if (k.getStatus() == Kontigent.Status.BETALT) {
                pw.println("Betalt d.: " + k.getBetalingsDato());
            }

            pw.println("-------------------");
            pw.println();

        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til betalinger.txt");
        }
    }

    // GEMMER konkurrencespillere i ultra-simpelt format
    public static void gemKonkurrenceSpillere(ArrayList<KonkurrenceSpiller> spillere) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FIL))) {

            for (KonkurrenceSpiller k : spillere) {

                int id = k.getMedlem().getId();
                String navn = k.getMedlem().getNavn();
                int rank = k.getRank();

                // Lav disciplin-liste
                StringBuilder d = new StringBuilder();
                for (int i = 0; i < k.getDiscipliner().size(); i++) {
                    d.append(k.getDiscipliner().get(i));
                    if (i < k.getDiscipliner().size() - 1) d.append(",");
                }

                // Format: ID;NAVN;RANK;DISCIPLINER
                pw.println(id + ";" + navn + ";" + rank + ";" + d);
            }

        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til " + FIL);
        }
    }

    // HENTER konkurrencespillere fra tekstfilen
    public static ArrayList<KonkurrenceSpiller> hentKonkurrenceSpillere() {

        ArrayList<KonkurrenceSpiller> spillere = new ArrayList<>();
        ArrayList<Medlem> medlemmer = Filehandler.hentMedlemmer();

        File file = new File(FIL);
        if (!file.exists()) return spillere;

        try (BufferedReader br = new BufferedReader(new FileReader(FIL))) {

            String linje;

            while ((linje = br.readLine()) != null) {

                if (linje.isBlank()) continue;

                String[] dele = linje.split(";");

                int id = Integer.parseInt(dele[0]);
                String navn = dele[1]; // bruges ikke, fordi vi finder Medlem-objektet
                int rank = Integer.parseInt(dele[2]);

                // Discipliner
                String[] discSplit = dele[3].split(",");
                ArrayList<KonkurrenceSpiller.Disciplin> discipliner = new ArrayList<>();
                for (String d : discSplit) {
                    discipliner.add(KonkurrenceSpiller.Disciplin.valueOf(d.trim()));
                }

                // Find Medlem ud fra ID
                Medlem medlem = null;
                for (Medlem m : medlemmer) {
                    if (m.getId() == id) {
                        medlem = m;
                        break;
                    }
                }

                if (medlem != null) {
                    spillere.add(new KonkurrenceSpiller(medlem, rank, discipliner));
                }
            }

        } catch (Exception e) {
            System.out.println("Fejl ved læsning af " + FIL + ": " + e.getMessage());
        }

        return spillere;
    }
}