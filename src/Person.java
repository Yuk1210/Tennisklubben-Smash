 public class Person {
    protected String navn;
    protected String efternavn;
    protected String adresse;
    protected int alder;
    protected String email;
    protected String tlf;
    protected int id;
    protected String beskaeftigelse;

    public Person(String navn, String efternavn, String adresse, int alder, String email, String tlf, int id, String beskaeftigelse){
        this.navn = navn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.alder = alder;
        this.email = email;
        this.tlf = tlf;
        this.id = id;
        this.beskaeftigelse = beskaeftigelse;
    }

    public String getNavn(){return navn;}

    public String getEfternavn(){return efternavn;}

    public String getAdresse(){return adresse;}

    public int getAlder(){return alder;}

    public String getEmail(){return email;}

    public String getTlf(){return tlf;}

    public int getId(){return id;}

    public String getBeskaeftigelse(){return beskaeftigelse;}

    @Override
    public String toString() {
        return "Person{" +
                "navn='" + navn +
                ", efternavn = " + efternavn +
                ", adresse =" + adresse +
                ", alder = " + alder +
                ", email = " + email +
                ", tlf = " + tlf +
                ", id = " + id +
                ", beskaeftigelse = " + beskaeftigelse;
    }
}