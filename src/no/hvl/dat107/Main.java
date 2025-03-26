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
      /*  //Søk etter ansatt med id
        System.out.print("Ansatt ID: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        //Søk etter ansatt med brukernavn
        System.out.print("Søk etter ansatt: Skriv brukernavn: ");
        String brukernavn = scanner.nextLine();*/

        ansattDAO.OppdatereAnsatt(17,"CEO", 90000.00F);


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


     /*  System.out.print("Trykk Y for å vise alle ");
       String y = scanner.nextLine().toLowerCase();
        if (y.equalsIgnoreCase("Y")) {
            ansattDAO.ListeAvAnsatte();
        }
       Ansatt a = ansattDAO.finnAnsattMedId(id);
       System.out.print("Ansatt med ID:" + a);

        Ansatt b = ansattDAO.finnAnsattMedBrukernavn(brukernavn);
        System.out.print("Ansatt med Brukernavn: " + b);
*/
       scanner.close();
   }

}