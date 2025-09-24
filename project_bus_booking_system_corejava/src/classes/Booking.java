package classes;

public class Booking {
    private int unique_ticketid ;

    private int bus_id ;
    private String passenger_name;


    public void setBooking(int unique_ticketid, int bus_id, String passenger_name) {
        this.unique_ticketid = unique_ticketid;
        this.bus_id = bus_id;
        this.passenger_name = passenger_name;

    }


    public int getUnique_ticketid() {
        return unique_ticketid;
    }

    public int getBus_id() {
        return bus_id;
    }


    public String getPassenger_name() {
        return passenger_name;
    }
}






