package com.entech;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;

import static com.entech.App.*;

public class SystemTest {

    public static void main(String[] args) {
        setUp();
        testAddCustomers();
        testAddSalesReps();
        testAssignRepsToCustomers();
        ENTITY_MANAGER_FACTORY.close();
    }


    public static void setUp() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Transaction tx;
        Session session;
        try {
            session = em.unwrap( Session.class );
            tx = session.beginTransaction();
            em.createQuery( "DELETE FROM Customer where custid in (1, 2, 4)" ).executeUpdate();
            em.createQuery( "DELETE FROM SalesRep where repid in (1, 2, 4)" ).executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void testAddCustomers() {
        addCustomer( 1, "Sue", "Smith" );
        addCustomer( 2, "Sam", "Smith" );
        addCustomer( 3, "Sid", "Smith" );
        addCustomer( 4, "Sally", "Smith" );
        getCustomer( 1 );
        getCustomers();
        changeFName( 4, "Mark" );
        deleteCustomer( 3 );
    }

    public static void testAddSalesReps() {
        addSalesRep( 1, "Sue", "Jones" );
        addSalesRep( 2, "Sam", "Jones" );
        addSalesRep( 3, "Sid", "Jones" );
        addSalesRep( 4, "Sally", "Jones" );
        getSalesRep( 1 );
        deleteSalesRep( 3 );
    }

    public static void testAssignRepsToCustomers() {
        assignRepToCustomer( 1, 2 );
    }

}
