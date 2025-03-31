package no.hvl.dat107;

import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
@Table (name = "firma_ansatt_oppgave.avdeling")
public class Avdeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avd_id")
    private Integer id;

    @Column (nullable = false)
    private String navn;

    @OneToOne
    @JoinColumn (name = "sjef_id", nullable = false)
    private Ansatt sjef;

    @OneToMany(mappedBy = "avdeling", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<Ansatt> ansatt = new ArrayList<>();

    public Avdeling() {}

    public Avdeling(String navn, Ansatt sjef) {
        this.navn = navn;
        this.sjef = sjef;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Ansatt getSjef() {
        return sjef;
    }

    public void setSjef(Ansatt sjef) {
        this.sjef = sjef;
    }

    public ArrayList<Ansatt> getAnsatt() {
        return ansatt;
    }

    public void setAnsatt(ArrayList<Ansatt> ansatt) {
        this.ansatt = ansatt;
    }

    @Override
    public String toString() {
        String sjefInfo = sjef != null ? sjef.getFornavn() + " " + sjef.getEtternavn() : "Ingen sjef";
        return String.format("Avdeling: id=%d, navn=%s, sjef=%s", id, navn, sjefInfo);
    }
}