package no.hvl.dat107;


import jakarta.persistence.*;
import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("minPersistenceUnit");
    }

    public static void main(String[] args) {
        AnsattDAO ansattDAO = new AnsattDAO();
        AvdelingDAO avdelingDAO = new AvdelingDAO();

        System.out.println("1 = Søk etter ansatt med ID");
        System.out.println("2 = Søk etter ansatt med brukernavn");
        System.out.println("3 = Legg til ny ansatt");
        System.out.println("4 = Skriv ut alle ansatte");
        System.out.println("5 = Oppdater verdi på en ansatt");
        System.out.println("6 = Søk etter avdeling med avd_id");
        System.out.println("7 = Liste alle avdelinger");
        System.out.println("8 = Ligg til en sjef på en avdeling");

        int valg = scanner.nextInt();
        scanner.nextLine();

        switch (valg){
            case 1:
                //Søk etter ansatt med id
                System.out.print("Ansatt ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                Ansatt a = ansattDAO.finnAnsattMedId(id);
                if (a != null) {
                    System.out.println("Ansatt med ID: " + a);
                } else {
                    System.out.println("Ingen ansatt med ID: " + id);
                }
                break;

            case 2:
                //Søk etter ansatt med brukernavn
                System.out.print("Søk etter ansatt, Skriv brukernavn: ");
                String bn = scanner.nextLine();
                Ansatt b = ansattDAO.finnAnsattMedBrukernavn(bn);
                if (b != null) {
                    System.out.println("Ansatt med Brukernavn: " + b);
                } else {
                    System.out.println("Ingen ansatt med Brukernavn: " + bn);
                }
                break;

            case 3:
                //Legge til ny ansatt
                System.out.print("Skriv inn brukernavn: ");
                String brukernavn = scanner.nextLine();
                System.out.print("Skriv inn fornavn: ");
                String fornavn = scanner.nextLine();
                System.out.print("Skriv inn etternavn: ");
                String etternavn = scanner.nextLine();
                System.out.print("Skriv inn ansettelsesdato YYYY-MM-DD: ");
                LocalDate ansDate = LocalDate.parse(scanner.nextLine());
                System.out.print("Skriv inn stilling: ");
                String stilling = scanner.nextLine();
                System.out.print("Skriv inn månedslønn: ");
                Float lonn = scanner.nextFloat();
                scanner.nextLine();
                System.out.print("Skriv avdelings ID: ");
                int avdId = scanner.nextInt();
                scanner.nextLine();

                // Hent avdeling fra database
                Avdeling avdeling = avdelingDAO.finnAvdelingMedId(avdId);
                if (avdeling == null) {
                    System.out.println("Avdeling med ID " + avdId + " finnes ikke.");
                    break;
                }

                // Opprett ny ansatt
                Ansatt ny_ansatt = new Ansatt(brukernavn, fornavn, etternavn, ansDate, stilling, lonn, avdeling);
                ansattDAO.leggTilAnsatt(ny_ansatt);

                System.out.println("Ny ansatt lagt til: " + ny_ansatt);
                break;

            case 4:
                //skriver ut alle ansatte
                List<Ansatt> ansatte = ansattDAO.listeAvAnsatte();
                if (ansatte == null || ansatte.isEmpty()) {
                    System.out.println("Ingen ansatte funnet.");
                }
                break;

            case 5:
                //Oppdater ansatt
                System.out.println("Skriv inn følgende:");
                System.out.print("Ansatt ID: ");
                Integer id_a = scanner.nextInt();
                scanner.nextLine();

                Ansatt eksisterendeAnsatt = ansattDAO.finnAnsattMedId(id_a);
                if (eksisterendeAnsatt == null) {
                    System.out.println("Ingen ansatt med ID: " + id_a);
                    break;
                }

                System.out.print("Ny stilling: ");
                String nyStilling = scanner.nextLine();
                System.out.print("Ny lønn: ");
                Float nyLonn = scanner.nextFloat();
                scanner.nextLine();

                ansattDAO.oppdatereAnsatt(id_a, nyStilling, nyLonn);
                System.out.println("Oppdatert ansatt med ID: " + id_a);
                System.out.println(ansattDAO.finnAnsattMedId(id_a));
                break;

            case 6:
                System.out.print("Skriv inn Avdelingsnummer: ");
                Integer avdID = scanner.nextInt();
                scanner.nextLine();

                Avdeling avdelingResult = avdelingDAO.finnAvdelingMedId(avdID);

                if(avdelingResult != null) {
                    System.out.println("Avdeling funnet: " + avdelingResult.getNavn());
                    System.out.println("Sjef: " + (avdelingResult.getSjef() != null ?
                            avdelingResult.getSjef().getFornavn() + " " + avdelingResult.getSjef().getEtternavn() : "Ingen sjef"));
                } else {
                    System.out.println("Ingen avdeling med ID " + avdID);
                }
                break;

            case 7:
                List<Avdeling> avdelinger = avdelingDAO.listeAvAvdelinger();
                if (avdelinger == null || avdelinger.isEmpty()) {
                    System.out.println("Ingen avdelinger funnet.");
                }
                break;
            case 8:
                // Legg til en sjef for en avdeling
                System.out.print("Skriv inn Ansattnummer for den nye sjefen: ");
                Integer ansattID = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Skriv inn Avdelingsnummer som skal få ny sjef: ");
                Integer avd_ID = scanner.nextInt();
                scanner.nextLine();

                Ansatt ansatt = ansattDAO.finnAnsattMedId(ansattID);
                Avdeling avdeling_Result = avdelingDAO.finnAvdelingMedId(avd_ID);

                if (ansatt != null && avdeling_Result != null) {
                    ansattDAO.leggTilSjef(ansattID, avd_ID);
                } else {
                    if (ansatt == null) {
                        System.out.println("Kunne ikke finne ansatt med ID: " + ansattID);
                    }
                    if (avdeling_Result == null) {
                        System.out.println("Kunne ikke finne avdeling med ID: " + avd_ID);
                    }
                    System.out.println("Oppdatering av sjef ble ikke utført.");
                }
                break;

            default:
                System.out.println("Ugyldig valg. Vennligst prøv igjen.");
                break;
        }

        scanner.close();
    }
}