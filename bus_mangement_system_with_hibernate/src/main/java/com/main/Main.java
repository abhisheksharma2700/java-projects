package com.main;

import com.entities.Bus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Bus bus= new Bus();
        bus.setBus_id(1);
        bus.setBus_number("rj14as2700");
        bus.setSource("jaipur");
        bus.setDestination("delhi");
        bus.setTotal_seats(10);
        bus.setAvailable_seats(5);
        bus.setDate("26 november ");
        bus.setTime("12 pm");

        Configuration cfg= new Configuration();
        cfg.configure("/hibernate.cfg.xml");
        SessionFactory sessionFactory= cfg.buildSessionFactory();
        Session session= sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();

     try{
         session.save(bus);
         transaction.commit();
         System.out.println("bus details added into database");


     } catch (Exception e) {
         transaction.rollback();
         e.printStackTrace();
         System.out.println("bus details not added");
     }


    }
}
