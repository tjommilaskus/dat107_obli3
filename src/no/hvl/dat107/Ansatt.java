package no.hvl.dat107;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "firma_ansatt_oppgave.ansatt")
public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ansatt")
    private Integer id_ansatt;

    @Column(name = "brukernavn")
    private String brukernavn;

    @Column(name = "fornavn")
    private String fornavn;

    @Column(name = "etternavn")
    private String etternavn;

    @Column(name = "ans_dato")
    private LocalDate ans_dato;

    @Column(name = "stilling")
    private String stilling;

    @Column(name = "lonn_mnd")
    private Float lonn_mnd;

    @ManyToOne
    @JoinColumn(name = "avd_id", nullable = false)
    private Avdeling avdeling;

    public Ansatt() {}

    public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate ans_dato, String stilling, Float lonn_mnd, Avdeling avdeling) {
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ans_dato = ans_dato;
        this.stilling = stilling;
        this.lonn_mnd = lonn_mnd;
        this.avdeling = avdeling;
    }

    public Integer getId_ansatt() {
        return id_ansatt;
    }

    public void setId_ansatt(Integer id_ansatt) {
        this.id_ansatt = id_ansatt;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public LocalDate getAns_dato() {
        return ans_dato;
    }

    public void setAns_dato(LocalDate ans_dato) {
        this.ans_dato = ans_dato;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public Float getLonn_mnd() {
        return lonn_mnd;
    }

    public void setLonn_mnd(Float lonn_mnd) {
        this.lonn_mnd = lonn_mnd;
    }

    public Avdeling getAvdeling() {
        return avdeling;
    }

    public void setAvdeling(Avdeling avdeling) {
        this.avdeling = avdeling;
    }

    @Override
    public String toString() {
        return String.format(
                "\tAnsatt: id: %d, brukernavn: %s, fornavn: %s, etternavn: %s, ansettelsesdato: %s, stilling: %s, månedslønn: %.2f, avdeling: %d",
                id_ansatt, brukernavn, fornavn, etternavn, ans_dato, stilling, lonn_mnd, avdeling.getId());
    }
}
