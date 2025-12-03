import java.util.List;

public class KonkurrenceSpiller {

    private Medlem medlem;
    private int rank;

    public enum Disciplin {
        SINGLE, DOUBLE, MIXED
    }

    private List<Disciplin> discipliner;


    public KonkurrenceSpiller(Medlem medlem, int rank, List<Disciplin> discipliner) {
        this.medlem = medlem;
        this.rank = rank;
        this.discipliner = discipliner;
    }

    public Medlem getMedlem() {
        return medlem;
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

    @Override
    public String toString (){
        return medlem.getNavn() + " Rank: " + rank + discipliner;
    }
}



