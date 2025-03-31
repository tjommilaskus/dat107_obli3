package no.hvl.dat107.dao;

import jakarta.persistence.*;
import no.hvl.dat107.Ansatt;
import no.hvl.dat107.Avdeling;

import java.util.List;

public class AvdelingDAO {

    private EntityManagerFactory emf;

    public AvdelingDAO() {
        emf = Persistence.createEntityManagerFactory("minPersistenceUnit");
    }

    public Avdeling finnAvdelingMedId(int avd_id) {

        EntityManager em = emf.createEntityManager();

        Avdeling avdeling = null;
        try {
            avdeling = em.find(Avdeling.class, avd_id);
        } finally {
            em.close();
        }
        return avdeling;
    }

    public Avdeling finnAvdelingMedNavn(String navn) {

        EntityManager em = emf.createEntityManager();

        try {
            String q = """
                    select a from Avdeling as a where a.navn = :navn
                    """;

            TypedQuery<Avdeling> query = em.createQuery(q, Avdeling.class);
            query.setParameter("navn", navn);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }

    }

    public List<Avdeling> listeAvAvdelinger(){

        String jpql = "SELECT a FROM Avdeling a";
        List<Avdeling> avdelinger = null;

        System.out.println("Kobler til database...");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Avdeling> query = em.createQuery(jpql, Avdeling.class);
            avdelinger = query.getResultList();
        } finally {
            em.close();
        }
        System.out.println("Resultat:");
        for (Avdeling a : avdelinger) {
            System.out.println(a);
        }
        System.out.println("Ferdig!");
        return avdelinger;
    }

    public void oppdatereAvdeling(int avd_id, String nyttNavn, Ansatt nySjef) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Avdeling avdeling = em.find(Avdeling.class, avd_id);
            if (avdeling != null) {
                avdeling.setNavn(nyttNavn);
                avdeling.setSjef(nySjef);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    public Avdeling leggTilAvdeling(Avdeling avdeling) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(avdeling);
            em.getTransaction().commit();
            return avdeling;
        } finally {
            em.close();
        }
    }
}