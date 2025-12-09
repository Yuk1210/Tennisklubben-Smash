import java.io.*;
import java.util.ArrayList;

public class FilehandlerKS {

    private static final String FIL = "konkurrencespillere.txt";

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

