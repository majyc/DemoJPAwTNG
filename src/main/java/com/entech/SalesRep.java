package com.entech;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SalesRep {
    @Id
    private int repid;

    @Column(name = "lastName")
    private String lname;

    @Column(name = "firstName")
    private String fname;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "salesrep_customer",
            joinColumns = {@JoinColumn(name = "repid")},
            inverseJoinColumns = {@JoinColumn(name = "custid")}
    )
    private final Set<Customer> customers = new HashSet<>();

    public int getRepId() {
        return repid;
    }

    public void setId(int id) {
        this.repid = id;
    }

    public String getLName() {
        return lname;
    }

    public void setLName(String lname) {
        this.lname = lname;
    }

    public String getFName() {
        return fname;
    }

    public void setFName(String fname) {
        this.fname = fname;
    }

    public void addCustomer(Customer cust) {
        customers.add( cust );
        cust.getReps().add( this );
    }

    public void removeCustomer(Customer cust) {
        customers.remove( cust );
        cust.getReps().remove( this );
    }
}
