package com.mycompany.app;

import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

import static org.junit.Assert.assertTrue;

public class ContainerIT {
    @Rule
    public GenericContainer mysql = new GenericContainer("mysql");

    //@Rule
    //public GenericContainer postgres = new GenericContainer("postgres");

    @Test
    public void test1() {
        System.out.println("Test1");
        assertTrue(mysql.isRunning());
        //assertTrue(postgres.isRunning());
    }
}
