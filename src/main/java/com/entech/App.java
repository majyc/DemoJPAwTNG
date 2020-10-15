package com.entech;

import javax.persistence.*;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory( "com.entech" );

    public static void main(String[] args) {
        System.out.println( "Hello World!" );
    }

    public static void deleteCustomer(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Customer cust;

        try {
            et = em.getTransaction();
            et.begin();
            cust = em.find( Customer.class, id );
            em.remove( cust );
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void changeFName(int id, String fname) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Customer cust;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find customer and make changes
            cust = em.find( Customer.class, id );
            cust.setFName( fname );

            // Save the customer object
            em.persist( cust );
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void getCustomers() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Customer c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Customer> tq = em.createQuery( strQuery, Customer.class );
        List<Customer> custs;
        try {
            // Get matching customer object and output
            custs = tq.getResultList();
            custs.forEach( cust -> System.out.println( cust.getFName() + " " + cust.getLName() ) );
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static Customer getCustomer(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT c FROM Customer c WHERE c.id = :custID";

        // Issue the query and get a matching Customer
        TypedQuery<Customer> tq = em.createQuery( query, Customer.class );
        tq.setParameter( "custID", id );

        Customer cust = null;
        try {
            // Get matching customer object and output
            cust = tq.getSingleResult();
            System.out.println( cust.getFName() + " " + cust.getLName() );
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return cust;
    }

    public static void addCustomer(int id, String fname, String lname) {
        // The EntityManager class allows operations such as create, read, update, delete
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        // Used to issue transactions on the EntityManager
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new customer
            Customer cust = new Customer();
            cust.setId( id );
            cust.setFName( fname );
            cust.setLName( lname );

            // Save the customer object
            em.persist( cust );
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static SalesRep getSalesRep(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :repID is a parameterized query value is set below
        String query = "SELECT sr FROM SalesRep sr WHERE sr.id = :repID";

        // Issue the query and get a matching Customer
        TypedQuery<SalesRep> tq = em.createQuery( query, SalesRep.class );
        tq.setParameter( "repID", id );

        SalesRep rep = null;
        try {
            // Get matching customer object and output
            rep = tq.getSingleResult();
            System.out.println( rep.getFName() + " " + rep.getLName() );
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return rep;
    }

    public static void addSalesRep(int id, String fname, String lname) {
        // The EntityManager class allows operations such as create, read, update, delete
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        // Used to issue transactions on the EntityManager
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new customer
            SalesRep rep = new SalesRep();
            rep.setId( id );
            rep.setFName( fname );
            rep.setLName( lname );

            // Save the customer object
            em.persist( rep );
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }


    public static void deleteSalesRep(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        SalesRep rep;

        try {
            et = em.getTransaction();
            et.begin();
            rep = em.find( SalesRep.class, id );
            em.remove( rep );
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    static void assignRepToCustomer(int repId, int custId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            SalesRep rep = em.find( SalesRep.class, repId );
            Customer cust = em.find( Customer.class, custId );
            rep.addCustomer( cust );
            em.persist( rep );
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public void close() {
        ENTITY_MANAGER_FACTORY.close();
    }
}
