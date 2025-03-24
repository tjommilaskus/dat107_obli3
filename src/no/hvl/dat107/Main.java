package no.hvl.dat107;

import jakarta.persistence.*;

public class Main {
    public static void main(String[] args) {

        Ansatt a = finnAnsattMedId(2001);
        System.out.println(a);
    }
    public static Ansatt finnAnsattMedId(int id) {

        System.out.println("Kobler til databasen ");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("minPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        try {
            return  em.find(Ansatt.class, id);
        } finally {
            em.close();
            emf.close();
        }
    }

}
