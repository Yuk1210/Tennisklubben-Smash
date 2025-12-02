public class Medlem extends Person{

    public enum medlemsType{ AKTIV,PASSIV}
    public enum spillerType{JUNIOR, SENIOR}
    public enum rolleType{MOTIONIST, KONKURRENCE}

    private medlemsType medlemsType;
    private spillerType spillerType;
    private rolleType rolleType;

    public Medlem(String navn, String adresse, int alder, String email, String tlf, int id, String beskaeftigelse, medlemsType medlemsType, spillerType spillerType, rolleType rolleType){
        super(navn, adresse, alder, email, tlf, id, beskaeftigelse);
        this.medlemsType = medlemsType;
        this.spillerType = spillerType;
        this.rolleType = rolleType;

    }

    public medlemsType getMedlemsType() {
        return medlemsType;
    }

    public void setMedlemsType(medlemsType medlemsType) {
        this.medlemsType = medlemsType;
    }

    public spillerType getSpillerType() {
        return spillerType;
    }

    public void setSpillerType(spillerType spillerType) {
        this.spillerType = spillerType;
    }

    public rolleType getRolleType() {
        return rolleType;
    }

    public void setRolleType(rolleType rolleType) {
        this.rolleType = rolleType;
    }

    @Override
    public String toString() {
        return "Medlem{" +
                "medlemsType=" + medlemsType +
                ", spillerType=" + spillerType +
                ", rolleType=" + rolleType +
                '}';
    }
}