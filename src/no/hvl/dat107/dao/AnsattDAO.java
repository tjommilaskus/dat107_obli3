package no.hvl.dat107.dao;

import jakarta.persistence.*;
import no.hvl.dat107.Ansatt;
import no.hvl.dat107.Avdeling;

import java.util.List;

public class AnsattDAO {

    private EntityManagerFactory emf;

    public AnsattDAO() {
        emf = Persistence.createEntityManagerFactory("minPersistenceUnit");
    }

    public Ansatt finnAnsattMedId(int ansatt_id) {

        EntityManager em = emf.createEntityManager();

        Ansatt ansatt = null;
        try {
            ansatt = em.find(Ansatt.class, ansatt_id);
        } finally {
            em.close();
        }
        return ansatt;
    }
    public Ansatt finnAnsattMedBrukernavn(String brukernavn) {

        EntityManager em = emf.createEntityManager();


        try {
            String q = """
                    select a from Ansatt as a where a.brukernavn = :brukernavn
                    """;

            TypedQuery<Ansatt> query = em.createQuery(q, Ansatt.class);
            query.setParameter("brukernavn", brukernavn);

            return query.getSingleResult();
        }catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }

    }

    public List<Ansatt> listeAvAnsatte(){

        String jpql = "SELECT a FROM Ansatt a";
        List<Ansatt> ansatte = null;

        System.out.println("Kobler til database...");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Ansatt> query = em.createQuery(jpql, Ansatt.class);
            ansatte = query.getResultList();
        } finally {
            em.close();
        }
        System.out.println("Resultat:");
        for (Ansatt p : ansatte) {
            System.out.println(p);
        }
        System.out.println("Ferdig!");
        return ansatte;
    }

    public void oppdatereAnsatt(int id_ansatt, String nyStilling, Float nyLonn) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Ansatt ansatt = em.find(Ansatt.class, id_ansatt);
            if (ansatt != null) {
                ansatt.setStilling(nyStilling);
                ansatt.setLonn_mnd(nyLonn);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    public Ansatt leggTilAnsatt(Ansatt ansatt) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ansatt);
            em.getTransaction().commit();
            return ansatt;
        } finally {
            em.close();
        }
    }

    public void leggTilSjef(Integer id_ansatt, Integer avd_id){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Ansatt ansatt = em.find(Ansatt.class, id_ansatt);
            Avdeling avdeling = em.find(Avdeling.class, avd_id);

            if (ansatt != null && avdeling != null) {
                avdeling.setSjef(ansatt);
                System.out.println("Updated sjef for avdeling " + avdeling.getNavn() + " to " + ansatt.getFornavn() + " " + ansatt.getEtternavn());
            } else {
                if (ansatt == null) {
                    System.out.println("Error: Ansatt with ID " + id_ansatt + " not found.");
                }
                if (avdeling == null) {
                    System.out.println("Error: Avdeling with ID " + avd_id + " not found.");
                }
            }
            tx.commit();
        } catch (Exception e) {
            System.err.println("Error updating sjef: " + e.getMessage());
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }
}
