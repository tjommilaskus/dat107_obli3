package no.hvl.dat107;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "firma_ansatt_oppgave.ansatt")
public class Ansatt {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int ansatt_id;
    @Column
    private String brukernavn;
    @Column
    private String fornavn;
    @Column
    private String etternavn;
    @Column
    private LocalDate annsettelse_dato;
    @Column
    private String stilling;
    @Column
    private float lonn_mnd;
    @Column
    private int avdeling_id;
    @Column
    private String rolle;


    public Ansatt() {};

   public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate annsettelse_dato, String stilling, float lonn_mnd, int avdeling_id, String rolle) {
       this.brukernavn = brukernavn;
       this.fornavn = fornavn;
       this.etternavn = etternavn;
       this.annsettelse_dato = annsettelse_dato;
       this.stilling = stilling;
       this.lonn_mnd = lonn_mnd;
       this.avdeling_id = avdeling_id;
       this.rolle = rolle;

   }
   public int getAnsatt_id() {
       return ansatt_id;
   }
   public void setAnsatt_id(int ansatt_id) {
       this.ansatt_id = ansatt_id;
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
   public LocalDate getAnnsettelse_dato() {
       return annsettelse_dato;
   }
   public void setAnnsettelse_dato(LocalDate annsettelse_dato) {
       this.annsettelse_dato = annsettelse_dato;
   }
   public String getStilling() {
       return stilling;
   }
   public void setStilling(String stilling) {
       this.stilling = stilling;
   }
   public float getLonn_mnd() {
       return lonn_mnd;
   }
   public void setLonn_mnd(float lonn_mnd) {
       this.lonn_mnd = lonn_mnd;
   }
   public String getRolle() {
       return rolle;
   }
   public void setRolle(String rolle) {
       this.rolle = rolle;
   }
   public int getAvdeling_id() {
       return avdeling_id;
   }
   public void setAvdeling_id(int avdeling_id) {
       this.avdeling_id = avdeling_id;
   }

   public void skrivUt() {
       System.out.println("AnsattID: " + ansatt_id);
       System.out.println("Navn: " + fornavn + " " + etternavn);
       System.out.println("Brukernavn: " + brukernavn);
       System.out.println("Stilling" + stilling);
       System.out.println("Månedslønn: " + lonn_mnd);
       System.out.println("Annsettelsesdato: " + annsettelse_dato);
       System.out.println("Rolle: " + rolle);
       System.out.println("avdeling_id: " + avdeling_id);
   }

}
