import java.util.List;

public class KonkurrenceSpiller {

    private Medlem m;
    private int rank;

    public enum Disciplin {
        SINGLE, DOUBLE, MIXED
    }

    private List<Disciplin> discipliner;


    public KonkurrenceSpiller(Medlem m, int rank, List<Disciplin> discipliner) {
        this.m = m;
        this.rank = rank;
        this.discipliner = discipliner;
    }

    public Medlem getMedlem() {
        return m;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public List<Disciplin> getDiscipliner() {
        return discipliner;
    }

    public void vindKamp(){
        rank +=10;
    }

    public void tabtKamp(){
        rank -=5;
    }
}



