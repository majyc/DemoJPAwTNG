package com.entech;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class CustomerTest {
    Customer customer;

    @BeforeClass
    public void setUp() {
        customer = new Customer( 1 );
    }

    @Test
    public void testGetId() {
        assertEquals( customer.getId(), 1 );
    }

    @Test
    public void testSetId() {
        customer.setId( 2 );
        assertEquals( customer.getId(), 2 );
    }

    @Test
    public void testSetLName() {
        customer.setLName( "Jones" );
        assertEquals( customer.getLName(), "Jones" );
    }
}