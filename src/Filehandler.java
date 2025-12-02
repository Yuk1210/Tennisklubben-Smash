import java.io.*;
import java.util.ArrayList;

public class Filehandler {

    private static final String MEDLEMSFIL = "medlemmer.txt";
    private static final String BETALINGSFIL = "betalinger.txt";

    // Gemmer alle medlemmer
    public static void gemMedlemmer(ArrayList<Medlem> medlemmer) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(MEDLEMSFIL))) {

            for (Medlem m : medlemmer) {
                pw.println(
                        m.getNavn() + ";" +
                                m.getAdresse() + ";" +
                                m.getAlder() + ";" +
                                m.getEmail() + ";" +
                                m.getTlf() + ";" +
                                m.getId() + ";" +
                                m.getBeskaeftigelse() + ";" +
                                m.getMedlemsType() + ";" +
                                m.getSpillerType() + ";" +
                                m.getRolleType()
                );
            }

        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til medlemmer.txt");
        }
    }

    // Læser filen af medlemmer
    public static ArrayList<Medlem> hentMedlemmer() {
        ArrayList<Medlem> liste = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(MEDLEMSFIL))) {

            String linje;
            while ((linje = br.readLine()) != null) {
                String[] d = linje.split(";");

                Medlem m = new Medlem(
                        d[0], d[1], Integer.parseInt(d[2]), d[3],
                        d[4], Integer.parseInt(d[5]), d[6],
                        Medlem.medlemsType.valueOf(d[7]),
                        Medlem.spillerType.valueOf(d[8]),
                        Medlem.rolleType.valueOf(d[9])
                );

                liste.add(m);
            }

        } catch (IOException e) {
            System.out.println("Ingen medlemmer.txt fundet – tom liste oprettet.");
        }

        return liste;
    }

    // Gemmer betaling
    public static void gemBetaling(Kontigent k) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BETALINGSFIL, true))) {

            pw.println(
                    k.getMedlem().getNavn() + ";" +
                            k.getPris() + ";" +
                            k.getAar() + ";" +
                            k.getStatus()
            );

        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til betalinger.txt");
        }
    }
}