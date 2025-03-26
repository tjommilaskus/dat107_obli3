package no.hvl.dat107;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "firma_ansatt_oppgave.ansatt")
public class Ansatt {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ansatt;
    private String brukernavn;
    private String fornavn;
    private String etternavn;
    private LocalDate ans_dato;
    private String stilling;
    private Float lonn_mnd;
    private Integer avd_id;

    public Ansatt() {}

    //public Ansatt(String nyBrukernavn, String nyFornavn, String nyEtternavn, LocalDate nyAnsettelsesdato, String nyStilling, Float nyLonn_Mnd) {}

   public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate ans_dato, String stilling, Float lonn_mnd, Integer avd_id) {
       this.brukernavn = brukernavn;
       this.fornavn = fornavn;
       this.etternavn = etternavn;
       this.ans_dato = ans_dato;
       this.stilling = stilling;
       this.lonn_mnd = lonn_mnd;
       this.avd_id = avd_id;

   }

    public Integer getAnsatt_id() {
       return id_ansatt;
   }
   public void setAnsatt_id(int ansatt_id) {
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
   public void setLonn_mnd(float lonn_mnd) {
       this.lonn_mnd = lonn_mnd;
   }

   public Integer getAvd_id() {
       return avd_id;
   }
   public void setAvd_id(Integer avd_id) {
       this.avd_id = avd_id;
   }

    @Override
    public String toString() {
        return String.format("\tAnsatt: id: %d, brukernavn: %s, fornavn: %s, etternavn: %s, ansettelsesdato: %s, stilling: %s, månedslønn: %.2f, avdeling: %d",  id_ansatt, brukernavn,
                fornavn, etternavn, ans_dato, stilling, lonn_mnd, avd_id);
    }
}


