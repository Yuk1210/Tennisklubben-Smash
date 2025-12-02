import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Coach {

    private String navn;

    public Coach(String navn) {
        this.navn = navn;
    }

    public List<KonkurrenceSpiller> udtagJunior(List<KonkurrenceSpiller> spillerListe) {

        List<KonkurrenceSpiller> juniorSpiller = new ArrayList<>();

        for (KonkurrenceSpiller spiller : spillerListe) {
            if (spiller.getMedlem().getSpillerType() == Medlem.spillerType.JUNIOR) {
                juniorSpiller.add(spiller);
            }
        }
        Collections.sort(juniorSpiller, (a, b) -> Integer.compare(a.getRank(), b.getRank()));
        if (juniorSpiller.size() > 5) {
            juniorSpiller = juniorSpiller.subList(0, 5);

        }
        return juniorSpiller;
    }


    public List<KonkurrenceSpiller> udtagSenior(List<KonkurrenceSpiller> spillerListe) {

        List<KonkurrenceSpiller> seniorSpiller = new ArrayList<>();

        for (KonkurrenceSpiller spiller : spillerListe) {
            if (spiller.getMedlem().getSpillerType() == Medlem.spillerType.SENIOR) {
                seniorSpiller.add(spiller);
            }
        }
        Collections.sort(seniorSpiller, (a, b) -> Integer.compare(a.getRank(), b.getRank()));
        if (seniorSpiller.size() > 5) {
            seniorSpiller = seniorSpiller.subList(0, 5);
        }
        return seniorSpiller;
    }

    /* HVAD SKER DER I NEDENSTÅENDE LOOP?

Går igennem alle konkurrencespillere
Kigger på én spiller ad gangen
Tjekker om er du JUNIOR? og Spiller du SINGLE?
Hvis begge ting er sande → tilføj til juniorSingle-listen
Gå videre til den næste spiller
Til sidst: returnér listen med kun junior-single spillere

     */

    public List<KonkurrenceSpiller> udtagJuniorSingle(List<KonkurrenceSpiller> spillerList) {
        List<KonkurrenceSpiller> juniorSingle = new ArrayList<>();

        for (KonkurrenceSpiller k : spillerList) {
            if (k.getMedlem().getSpillerType() == Medlem.spillerType.JUNIOR &&
                    k.getDiscipliner().contains(KonkurrenceSpiller.Disciplin.SINGLE)) {
                juniorSingle.add(k);
            }
        }
        Collections.sort(juniorSingle, (a, b) -> Integer.compare(a.getRank(), b.getRank()));
        if (juniorSingle.size() > 5) {
            juniorSingle = juniorSingle.subList(0, 5);
        }
        return juniorSingle;
    }

    public List<KonkurrenceSpiller> udtagJuniorDouble(List<KonkurrenceSpiller> spillerList) {
        List<KonkurrenceSpiller> juniorDouble = new ArrayList<>();

        for (KonkurrenceSpiller k : spillerList) {
            if (k.getMedlem().getSpillerType() == Medlem.spillerType.JUNIOR &&
                    k.getDiscipliner().contains(KonkurrenceSpiller.Disciplin.DOUBLE)) {
                juniorDouble.add(k);
            }
        }
        Collections.sort(juniorDouble, (a, b) -> Integer.compare(a.getRank(), b.getRank()));
        if (juniorDouble.size() > 5) {
            juniorDouble = juniorDouble.subList(0, 5);
        }
        return juniorDouble;
    }

    public List<KonkurrenceSpiller> udtagJuniorMixed(List<KonkurrenceSpiller> spillerList) {
        List<KonkurrenceSpiller> juniorMixed = new ArrayList<>();

        for (KonkurrenceSpiller k : spillerList) {
            if (k.getMedlem().getSpillerType() == Medlem.spillerType.JUNIOR &&
                    k.getDiscipliner().contains(KonkurrenceSpiller.Disciplin.MIXED)) {
                juniorMixed.add(k);
            }
        }
        Collections.sort(juniorMixed, (a, b) -> Integer.compare(a.getRank(), b.getRank()));
        if (juniorMixed.size() > 5) {
            juniorMixed = juniorMixed.subList(0, 5);
        }
        return juniorMixed;
    }

    public List<KonkurrenceSpiller> udtagSeniorSingle(List<KonkurrenceSpiller> spillerList) {
        List<KonkurrenceSpiller> seniorSingle = new ArrayList<>();

        for (KonkurrenceSpiller k : spillerList) {
            if (k.getMedlem().getSpillerType() == Medlem.spillerType.SENIOR &&
                    k.getDiscipliner().contains(KonkurrenceSpiller.Disciplin.SINGLE)) {
                seniorSingle.add(k);
            }
        }
        Collections.sort(seniorSingle, (a, b) -> Integer.compare(a.getRank(), b.getRank()));
        if (seniorSingle.size() > 5) {
            seniorSingle = seniorSingle.subList(0, 5);
        }
        return seniorSingle;
    }

    public List<KonkurrenceSpiller> udtagSeniorDouble(List<KonkurrenceSpiller> spillerList) {
        List<KonkurrenceSpiller> seniorDouble = new ArrayList<>();

        for (KonkurrenceSpiller k : spillerList) {
            if (k.getMedlem().getSpillerType() == Medlem.spillerType.SENIOR &&
                    k.getDiscipliner().contains(KonkurrenceSpiller.Disciplin.DOUBLE)) {
                seniorDouble.add(k);
            }
        }
        Collections.sort(seniorDouble, (a, b) -> Integer.compare(a.getRank(), b.getRank()));
        if (seniorDouble.size() > 5) {
            seniorDouble = seniorDouble.subList(0, 5);
        }
        return seniorDouble;
    }

    public List<KonkurrenceSpiller> udtagSeniorMixed(List<KonkurrenceSpiller> spillerList) {
        List<KonkurrenceSpiller> seniorMixed = new ArrayList<>();

        for (KonkurrenceSpiller k : spillerList) {
            if (k.getMedlem().getSpillerType() == Medlem.spillerType.SENIOR &&
                    k.getDiscipliner().contains(KonkurrenceSpiller.Disciplin.MIXED)) {
                seniorMixed.add(k);
            }
        }
        Collections.sort(seniorMixed, (a, b) -> Integer.compare(a.getRank(), b.getRank()));
        if (seniorMixed.size() > 5) {
            seniorMixed = seniorMixed.subList(0, 5);
        }
        return seniorMixed;
    }

    public void registrerKamp(KonkurrenceSpiller spiller, Modstander modstander, int mig, int mod) {

        RegistrerKamp kamp = new RegistrerKamp(spiller, modstander);    // Lav en kamp

        kamp.registrerResultat(mig, mod);                             // Registrer resultat
        if (mig > mod) {
            spiller.vindKamp();                                            // Opdater rank for din spiller
        } else {
            spiller.tabtKamp();                                              // Hvis vores spiller vinder → bliver bedre
        }

        kamp.printKamp();
    }

    public void printListe(List<KonkurrenceSpiller> spillerList) {
        for (KonkurrenceSpiller k : spillerList){
            System.out.println(
                    k.getMedlem().getNavn() + "Rank: " + k.getRank() + "Disciplin: " + k.getDiscipliner()
            );
        }
    }
}