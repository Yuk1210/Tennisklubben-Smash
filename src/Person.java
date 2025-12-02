public class Person {
    private String navn;
    private String adresse;
    private int alder;
    private String email;
    private String tlf;
    private int id;
    private String beskaeftigelse;

    public Person(String navn, String adresse, int alder, String email, String tlf, int id, String beskaeftigelse){
        this.navn = navn;
        this.adresse = adresse;
        this.alder = alder;
        this.email = email;
        this.tlf = tlf;
        this.id = id;
        this.beskaeftigelse = beskaeftigelse;
    }

    public String getNavn(){return navn;}
    public String getAdresse(){return adresse;}
    public int getAlder(){return alder;}
    public String getEmail(){return email;}
    public String getTlf(){return tlf;}
    public int getId(){return id;}
    public String getBeskaeftigelse(){return beskaeftigelse;}

    @Override
    public String toString() {
        return "Person " +
                "navn = " + navn +
                ", adresse = " + adresse +
                ", alder = " + alder +
                ", email = " + email +
                ", tlf = " +  "+45" + tlf +
                ", id = " + id +
                ", beskaeftigelse = " + beskaeftigelse;
    }
}
