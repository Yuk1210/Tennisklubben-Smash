/* Den her klasse skal kunne
Indtast vores spiller
Indtast vores modstanderen
Gemme score for begge
Vide om kampen er afsluttet
Efter gemme resultat
Kunne printe kampen
*/

public class RegistrerKamp {
    private KonkurrenceSpiller spiller;
    private Modstander modstander;
    private int scoreMig;
    private int scoreMod;
    private boolean afsluttet;

    public RegistrerKamp(KonkurrenceSpiller spiller, Modstander modstander) {
        this.spiller = spiller;
        this.modstander = modstander;
        this.scoreMig = 0;
        this.scoreMod = 0;
        this.afsluttet = false;
    }

    public void registrerResultat(int mig, int mod) {
        this.scoreMig = mig;
        this.scoreMod = mod;
        this.afsluttet = true;
    }

    public void printKamp() {
        System.out.println(spiller.getMedlem().getNavn()
                + " vs " + modstander.getNavn());

        if (afsluttet) {
            System.out.println("Resultat: " + scoreMig + "-" + scoreMod);
        } else {
            System.out.println("Kampen er ikke spillet.");
        }
    }

    // Vi bruger disse get til at lave historik/stats senere
    public int getScoreMig(){
        return scoreMig;
    }
    public int getScoreMod(){
        return scoreMod;
    }
    public boolean isAfsluttet(){
        return afsluttet;
    }
}