package no.hvl.dat107;


import jakarta.persistence.*;
import no.hvl.dat107.dao.AnsattDAO;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("minPersistenceUnit");
    }

    public static void main(String[] args) {
        AnsattDAO ansattDAO = new AnsattDAO();

        System.out.println("1 = Søk etter ansatt med ID");
        System.out.println("2 = Søk etter ansatt med brukernavn");
        System.out.println("3 = Ligg til ny ansatt");
        System.out.println("4 = Skriv ut alle ansatte");
        System.out.println("5 = Oppdater verdi på en ansatt");

        switch (scanner.nextInt()){
            case 1 :
                //Søk etter ansatt med id
                System.out.print("Ansatt ID: ");
                Integer id = scanner.nextInt();
                scanner.nextLine();
                Ansatt a = ansattDAO.finnAnsattMedId(id);
                System.out.print("Ansatt med ID:" + a);
                break;

            case 2 :
                //Søk etter ansatt med brukernavn
                System.out.print("Søk etter ansatt: Skriv brukernavn: ");
                String brukernavn = scanner.nextLine();
                Ansatt b = ansattDAO.finnAnsattMedBrukernavn(brukernavn);
                System.out.print("Ansatt med Brukernavn: " + b);
                break;
            case 3 :
                //Ligge til ny ansatt
                Ansatt ny_ansatt = new Ansatt();

                ny_ansatt.getAnsatt_id();
                System.out.print("Skriv inn brukernavn: ");
                ny_ansatt.setBrukernavn(scanner.nextLine());
                System.out.print("Skriv inn fornavn: ");
                ny_ansatt.setFornavn(scanner.nextLine());
                System.out.print("Skriv inn etternavn: ");
                ny_ansatt.setEtternavn(scanner.nextLine());
                System.out.print("Skriv inn ansettelsesdato YYYY-MM-DD: ");
                ny_ansatt.setAns_dato(LocalDate.parse(scanner.nextLine()));
                System.out.print("Skriv inn stilling: ");
                ny_ansatt.setStilling(scanner.nextLine());
                System.out.print("Skriv inn månedslønn: ");
                ny_ansatt.setLonn_mnd(scanner.nextFloat());
                System.out.print("Skriv avdelings nr: ");
                ny_ansatt.setAvd_id(scanner.nextInt());
                scanner.nextLine();

                ansattDAO.LeggTilAnsatt(ny_ansatt);
                System.out.println("Nyeste ansatt: " + ny_ansatt);
                break;
            case 4 :
                //skriver ut alle
                ansattDAO.ListeAvAnsatte();
                break;
            case 5 :
                //Oppdater ansatt
                System.out.print("Skriv inn følgende");
                Integer id_a = scanner.nextInt();
                String stilling = scanner.nextLine();
                Float lonn = scanner.nextFloat();
                ansattDAO.OppdatereAnsatt(id_a, stilling, lonn);
            default:
                scanner.close();
        }

   }
}