package no.hvl.dat107;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {

    private static EntityManagerFactory emf;
    static {
        emf = Persistence.createEntityManagerFactory("minPersistenceUnit");
    }
    public static void main(String[] args) {

        String jpql = "SELECT a FROM Ansatt a"; //NB! Dette er ikke SQL, men JPQL !!!
        List<Ansatt> ansatt;

        System.out.println("Kobler til database...");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Ansatt> query = em.createQuery(jpql, Ansatt.class);
            ansatt = query.getResultList();
        } finally {
            em.close();
        }
        System.out.println("Resultat:");
        for (Ansatt p : ansatt) {
            System.out.println(p);
        }
        System.out.println("Ferdig!");
    }
}