package classes;

public class Bus {


        private int bus_id;
        private String bus_number ;
        private String source;
        private String destination;
        private int total_seats;
        private int  available_seats;
        private String departure_date;
        private String departure_time;
        private int available_seats_after_removal;

        public void  setBus(int bus_id,String bus_number,String source,String destination,int total_seats,int available_seats,String departure_date,String departure_time){
            this.bus_id=bus_id;
            this.bus_number=bus_number;
            this.source=source;
            this.destination=destination;
            this.total_seats=total_seats;
            this.available_seats=available_seats;
            this.departure_date=departure_date;
            this.departure_time=departure_time;


        }

        public void setAvailable_seats(int available_seats){
            this.available_seats=available_seats-1;

        }
        public void setAvailable_seats_after_removal(int available_seats){
            this.available_seats=available_seats+1;
        }

        public int getBus_id(){
            return bus_id;
        }
        public String getBus_number(){
            return bus_number;
        }
        public String getSource(){
            return source;
        }
        public String getDestination(){
            return  destination;
        }
        public int getTotal_seats(){
            return total_seats;
        }
        public int getAvailable_seats()
        {
            return available_seats;
        }
        public String getDeparture_date(){
            return departure_date;
        }
        public String getDeparture_time(){
            return departure_time;
        }
    }

