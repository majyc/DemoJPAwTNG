package com.entech;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
 * 2. The Java Persistance API (JPA) makes it easy to use object data with
 * a database. With it you can Persist object data in a database.
 *
 * Hibernate is a Object Relational Mapping system that implements JPA.
 */
@Entity
@Table(name = "customer")
public class Customer {

    // All entities must define a primary key which you define with
    // the @Id annotation
    @Id

    // You can override the default column name
    // Map id to the CustID field in the DB
    // You can have it auto generate
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "custID", unique = true)
    private int id;


    @Column(name = "firstName", nullable = false)
    private String fName;

    @Column(name = "lastName", nullable = false)
    private String lName;

    @ManyToMany(mappedBy = "customers", fetch = FetchType.EAGER)
    private final Set<SalesRep> reps = new HashSet<>();

    public Customer(int i) {
        id = i;
    }

    public Customer() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public Set<SalesRep> getReps() {
        return reps;
    }
}
