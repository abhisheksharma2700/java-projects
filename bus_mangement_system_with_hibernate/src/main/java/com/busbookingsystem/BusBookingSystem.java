package com.busbookingsystem;

import java.sql.*;
import java.sql.Date;
import java.util.*;


public class BusBookingSystem {
    private static final String url="jdbc:mysql://localhost:3306/booking";
    private static final String username="root";
    private static final String password="Abhishek2700@";
    Scanner sc = new Scanner(System.in);

    void connection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    void add_bus() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            try {

                System.out.println("Enter the bus id: ");
                int id = sc.nextInt();
                System.out.println("Enter the bus number");
                String bus_number = sc.next();
                System.out.println("Enter the source (city name , from where bus has started!): ");
                String Source = sc.next();
                System.out.println("Enter the Destination(city name, to where the bus reached): ");
                String desti = sc.next();
                System.out.println("Enter the total seats: ");
                int seat = sc.nextInt();
                System.out.println("Enter the date of departure in year-mm-dd format");
                String Departuredate = sc.next();
                System.out.println("Now Enter the time of departure that help passenger to come on time");
                String Departuretime = sc.next();
                int available_seats = seat;
                String add_busquery = ("insert into bus_data(bus_id,bus_number,source,destination,total_seats,departure_date,departure_time,available_seats) values (?,?,?,?,?,?,?,?)");
                PreparedStatement preparedstatement = connection.prepareStatement(add_busquery);
                preparedstatement.setInt(1, id);
                preparedstatement.setString(2, bus_number);
                preparedstatement.setString(3, Source);
                preparedstatement.setString(4, desti);
                preparedstatement.setInt(5, seat);
                preparedstatement.setString(6, Departuredate);
                preparedstatement.setString(7, Departuretime);
                preparedstatement.setInt(8, seat);

                int rowAffected = preparedstatement.executeUpdate();
                if (rowAffected > 0) {
                    System.out.println("bus added into bus_data");
                } else {
                    System.out.println("bus not added to database");
                }


            } catch (InputMismatchException e) {
                System.out.println("please carefully enter the details");
            } catch (Exception e) {
                System.out.println("already created bus with this bus id");
            }
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    void view_bus(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
            String view_busquery= ("select * from bus_data");
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery(view_busquery);
            while(resultSet.next()) {
                int bus_id = resultSet.getInt("bus_id");
                String bus_number = resultSet.getString("bus_number");
                String source = resultSet.getString("source");
                String destination = resultSet.getString("destination");
                int total_seats = resultSet.getInt("total_seats");
                Date departure_date = resultSet.getDate("departure_date");
                String departure_time = resultSet.getString("departure_time");
                int available_seats= resultSet.getInt("available_seats");
                System.out.println("bus_id: " + bus_id);
                System.out.println("bus_number: " + bus_number);
                System.out.println("source: " + source);
                System.out.println("destination: " + destination);
                System.out.println("total seats: " + total_seats);
                System.out.println("available seats: "+available_seats);
                System.out.println("departure date: " + departure_date);
                System.out.println("departure time: " + departure_time);
                System.out.println("---------------------------");
                System.out.println("                           ");
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    void book_ticket(){
        try{
            Connection connection= DriverManager.getConnection(url,username,password);

            try {

                int unique_ticketid= sc.nextInt();
                System.out.println("Enter passenger name");
                String passenger_name = sc.next();
                System.out.println("Enter the bus_id");
                int bus_id = sc.nextInt();
                String addquery=("insert into passenger_data(unique_ticketid,passenger_name,bus_id) values(?,?,?)");
                PreparedStatement preparedStatement= connection.prepareStatement(addquery);
                preparedStatement.setInt(1,unique_ticketid);
                preparedStatement.setString(2,passenger_name);
                preparedStatement.setInt(3,bus_id);
                try {
                    int rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected>0){
                        System.out.println("your ticket booked ! Happy journey ");
                    }else{
                        System.out.println("your data not saved at database");
                    }
                }catch(Exception e){
                    System.out.println("ticket not booked make sure , your ticket id is unique and bus_id must be correct  ");

                }
                {
                    String available_seatsQuery=("select available_seats from bus_data where bus_id="+bus_id);
                    Statement statement= connection.createStatement();
                    ResultSet resultSet=statement.executeQuery(available_seatsQuery);
                    while(resultSet.next()){
                        int available_seats= resultSet.getInt("available_seats");
                        System.out.println("your seat number is:  "+available_seats);
                    }
                }
                String seat_query=("update bus_data set available_seats=available_seats-1 where bus_id="+bus_id);
                int rowsAffected2=preparedStatement.executeUpdate(seat_query);
                if(rowsAffected2>0){
                    System.out.println("seat reserved !");
                }else{
                    System.out.println("seat not booked");
                }

            }
            catch(InputMismatchException e){
                System.out.println("don't be hurry fill the details properly");
            }
            connection.close();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    void cancel_ticket(){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            try {
                System.out.println("Enter ticket id to cancel ticket ");
                int unique_ticketid = sc.nextInt();
                System.out.println("Enter bus_id for confirmation of cancel your ticket  ");
                int bus_id = sc.nextInt();
                String ticketid_checker = ("update  passenger_data set unique_ticketid=unique_ticketid+0 where unique_ticketid=" + unique_ticketid);
                Statement statement1 = connection.createStatement();
                Statement statement2= connection.createStatement();

                int rowAffected = statement1.executeUpdate(ticketid_checker);
                if (rowAffected > 0) {
                    System.out.println("ticket id found in database");
                    String remove_by_ticketid = ("select b.bus_id from bus_data as b inner join passenger_data as p on b.bus_id=p.bus_id ");
                    ResultSet resultSet = statement2.executeQuery(remove_by_ticketid);
                    while (resultSet.next()) {
                        int bus_id1 = resultSet.getInt("bus_id");
                        if (bus_id1 == bus_id) {
                            String deletequery = ("delete from passenger_data where unique_ticketid=" + unique_ticketid);


                            String unique_ticketidQuery = ("select * from passenger_data where unique_ticketid=" + unique_ticketid);
                            Statement statement3 = connection.createStatement();
                            Statement statement4= connection.createStatement();

                            statement3.executeQuery(unique_ticketidQuery);
                            int rowsAffected = statement4.executeUpdate(deletequery);
                            if (rowsAffected > 0) {
                                System.out.println("data deleted from database");
                                String seat_increserQuery = ("select bus_id from bus_data where bus_id=" + bus_id);
                                Statement statement5 = connection.createStatement();
                                Statement statement6= connection.createStatement();
                                ResultSet resultSet2 = statement5.executeQuery(seat_increserQuery);
                                while (resultSet2.next()) {
                                    int bus_idget = resultSet2.getInt("bus_id");
                                    if (bus_idget == bus_id) {
                                        String delete_seatquery = ("update bus_data set available_seats=available_seats+1 where bus_id=" + bus_id);
                                        int rowsaffected = statement6.executeUpdate(delete_seatquery);
                                        if (rowsaffected > 0) {
                                            System.out.println("booking canceled");
                                        } else {
                                            System.out.println("booking not cancelled ");
                                        }

                                    }
                                }


                            } else {
                                System.out.println("data not deleted from database");
                            }


                        }

                    }
                } else {
                    System.out.println("incorrect ticket id");
                }


            } catch (InputMismatchException e) {
                System.out.println("please enter details correct");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                //System.out.println("please make sure you have entered right unique_ticketid and bus_id ");
            }
            connection.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    void available_seats(){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            try {
                System.out.println("for which bus you want to see available seats , enter bus id of respective bus ");
                int bus_id = sc.nextInt();

                String available_seatsQuery = ("select available_seats from bus_data where bus_id=" + bus_id);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(available_seatsQuery);
                while (resultSet.next()) {
                    int available_seats = resultSet.getInt("available_seats");
                    System.out.println("available seats: " + available_seats);
                }


            }  catch (Exception e) {
                System.out.println("please enter the right bus id to check the availability of seats");
            }
            connection.close();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    void passenger_list(){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("=== These are all the passengers with all of their details ===");


            String joinQuery = ("select * from bus_data as b inner join passenger_data as p on b.bus_id=p.bus_id");
            PreparedStatement preparedStatement = connection.prepareStatement(joinQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            //resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                int unique_ticketid = resultSet.getInt("unique_ticketid");
                String passenger_name = resultSet.getString("passenger_name");
                int bus_id = resultSet.getInt("bus_id");
                String bus_number = resultSet.getString("bus_number");
                String source = resultSet.getString("source");
                String destination = resultSet.getString("destination");
                int total_seats = resultSet.getInt("total_seats");
                Date departure_date = resultSet.getDate("departure_date");
                String departure_time = resultSet.getString("departure_time");
                int available_seats = resultSet.getInt("available_seats");

                System.out.println("passenger unique_ticketid: " + unique_ticketid);
                System.out.println("passenger name: " + passenger_name);
                System.out.println("bus id :" + bus_id);
                System.out.println("bus number: " + bus_number);
                System.out.println("we started from: " + source);
                System.out.println("our destination is: " + destination);
                System.out.println("this are the total seats in our bus: " + total_seats);
                System.out.println("on this date we departure:" + departure_date);
                System.out.println("this is the time of our departure: " + departure_time);
                System.out.println("this are available seats in our bus after bookings: " + available_seats);
                System.out.println("-----------------------------------------------------");
                System.out.println("                                                     ");
            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public static void main(String[] args) {
        int exit = 1;
       BusBookingSystem b= new BusBookingSystem();
        b.connection();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

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
                        b.add_bus();
                        break;

                    case 2:
                        b.view_bus();
                        System.out.println("these are all the buses we have created before ");
                        break;

                    case 3:
                        System.out.println("Enter a unique id that will be used as your ticket id: ");
                        b.book_ticket();
                        break;

                    case 4:
                        b.cancel_ticket();
                        break;

                    case 5:
                        b.available_seats();
                        break;

                    case 6:
                        b.passenger_list();
                        break;

                    case 7:
                        exit = 2;
                        break;
                    default:
                        System.out.println("please enter among the numbers given on main menu");
                }


            } while (exit < 2);
            connection.close();



        }catch(SQLException e){
            System.out.println(e.getMessage());
        }



    }

}





