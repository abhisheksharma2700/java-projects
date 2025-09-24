package classes;
import java.util.*;

class Main extends BusBookingSystem {
    BusBookingSystem b = new BusBookingSystem();

    Scanner sc = new Scanner(System.in);

     void create_bus(){
         try {
             System.out.println("Enter the bus id: ");
             int id = sc.nextInt();
             System.out.println("Enter the bus number");
             String num = sc.next();
             System.out.println("Enter the source (city name , from where bus has started!): ");
             String Source = sc.next();
             System.out.println("Enter the Destination(city name, to where the bus reached): ");
             String desti = sc.next();
             System.out.println("Enter the total seats: ");
             int seat = sc.nextInt();
             System.out.println("Enter the date of departure in dd-mm-year format");
             String Departuredate = sc.next();
             System.out.println("Now Enter the time of departure that help passenger to come on time");
             String Departuretime = sc.next();
             b.addbus(id, num, Source, desti, seat, seat, Departuredate, Departuretime);
         }catch(InputMismatchException e){
             System.out.println("don't be hurry fill the details properly");
         }
    }
    void veiwbus(){
        b.viewbuses();
    }
    void ticket_booking(){
         try {
             System.out.println("Enter a unique id that will be used as your ticket id: ");
             int unique_ticketid = sc.nextInt();
             System.out.println("Enter passenger name");
             String passengername = sc.next();
             System.out.println("Enter the bus_id");
             int busid = sc.nextInt();
             b.bookticket(unique_ticketid,passengername,busid);
         }catch (InputMismatchException e){
             System.out.println("please carefully enter the details");
         }


    }
    void ticket_cancel(){
         try {
             System.out.println("Enter ticket id to cancel ticket ");
             int ticketid = sc.nextInt();
             b.cancelticket(ticketid);
         }catch(InputMismatchException e){
             System.out.println("please enter details correct");
         }
    }
    void seat_available(){
         try {
             System.out.println("for which bus you want to see available seats , enter bus id of respective bus ");
             int bus_id = sc.nextInt();
             b.availableseats(bus_id);
         } catch(InputMismatchException e){
             System.out.println("enter the correct bus id ");
         }
    }
    void list_of_passenger(){
        System.out.println("this is the list of all passengers");
        b.passengerlist();
    }

    public static void main(String[] args) {
        int exit = 1;
        Main main = new Main();
        do {
            System.out.println("=== Bus Booking System ===");
            System.out.println("1. Add Bus");
            System.out.println("2. View Buses");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Show Available Seats");
            System.out.println("6. Show Passenger List");
            System.out.println("7. Exit");
            System.out.println("Enter your choice: ");
            Scanner sc = new Scanner(System.in);
            int number;
            number = sc.nextInt();

            switch (number) {

                case 1:
                        main.create_bus();
                        break;

                case 2:
                    main.veiwbus();
                    System.out.println("these are all the buses we have created before ");
                    break;

                case 3:
                    main.ticket_booking();
                    break;

                case 4:
                    main.ticket_cancel();
                    break;


                case 5:
                    main.seat_available();
                    break;

                case 6:
                    main.list_of_passenger();
                    break;

                case 7:
                    exit = 2;
                    break;
            }

        } while (exit < 2);

    }

}

