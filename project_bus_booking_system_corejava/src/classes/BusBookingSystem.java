package classes;
import java.util.*;

 public class BusBookingSystem{
    List<Bus> bus= new ArrayList<Bus>();
    List<Booking> booking= new ArrayList<Booking>();

    void addbus (int bus_id,String bus_number,String Source,String Destination, int total_seats,int available_seats,String Departuredate,String Departuretime){
        for(Bus b : bus){
            if(b.getBus_id() == bus_id||b.getBus_number().equals(bus_number)){
                System.out.println("bus was already added , ");
                return;
            }


        }
        Bus b= new Bus();


        b.setBus(bus_id, bus_number, Source, Destination, total_seats,available_seats,Departuredate,Departuretime);
        bus.add(b);
        System.out.println("bus added successfully!");
    }
    void viewbuses(){
        for(Bus b:bus){
            System.out.println("bus id: "+b.getBus_id());
            System.out.println("bus number: "+b.getBus_number());
            System.out.println("source: "+ b.getSource());
            System.out.println("destination: "+b.getDestination());
            System.out.println("total seats: "+b.getTotal_seats());
            System.out.println("departure date: "+b.getDeparture_date());
            System.out.println("departure time: "+b.getDeparture_time());
            System.out.println("_________________");
            System.out.println("                  ");


        }

    }
    void bookticket(int unique_ticketid,String name,int bus_id){
        for(Booking book :booking){
            if(book.getUnique_ticketid()==unique_ticketid){
                System.out.println("payment id won't be same , you already booked ticket through this id");
                return;
            }
        }

        for(Bus b:bus){
            if(b.getBus_id()==bus_id){
                Booking book= new Booking();
                book.setBooking(unique_ticketid,bus_id,name);
                booking.add(book);
                System.out.println("your booking is confirmed for name :" + name + " and this is your unique  ticket id: " +book.getUnique_ticketid());
                System.out.println("your seat number is: " + b.getAvailable_seats() + " in bus which is going from " + b.getSource() + " to " + b.getDestination() + " and bus number is " + b.getBus_number() + " and here are the departure date: " + b.getDeparture_date() + " and time: " + b.getDeparture_time());
                b.setAvailable_seats(b.getAvailable_seats());



            }
            else{
                System.out.println("please right correct details only");
            }
        }


    }

    void cancelticket(int unique_ticket_id) {
        Booking booking1 = null;
        for (Booking book : booking) {
            if (book.getUnique_ticketid() == unique_ticket_id) {
                System.out.println("your booking is cancelled !");

                booking1 = book;
                break;
            }
        }
        try {
            int busId = booking1.getBus_id();
            booking.remove(booking1);
            for(Bus b:bus){
                if(b.getBus_id()==busId){
                    b.setAvailable_seats_after_removal(b.getAvailable_seats());
                    break;
                }
                else{
                    System.out.println("please enter right details");
                }
            }
        }
        catch(NullPointerException e) {
            System.out.println("please give only right ticket id");
        }


    }
    void availableseats(int bus_id){

        for(Bus b:bus) {
            if (b.getBus_id() == bus_id) {
                System.out.println("bus id: " + b.getBus_id() + " seats avilable " + b.getAvailable_seats() + " total seats: " + b.getTotal_seats());

            }
            else{
                System.out.println("enter the right details");
            }
        }

    }
    void passengerlist(){
        for(Booking book:booking){
            System.out.println("passenger name: "+book.getPassenger_name()+" booked ticket in "+book.getBus_id()+" bus id and their unique ticket id is: "+ book.getUnique_ticketid());

        }
    }


}
