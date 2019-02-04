/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emre
 */
public final class MainWindow {
    
    private String fileName = "FileDatabase/Register.txt";

    private String name, surname, phone, id;
    private int age;
    private String yIn, mmIn, dIn;
    private String yOut, mmOut, dOut;
    
    private static final int HOTEL_ROOM_NUM = 10;
    
    Room rooms = new Room(HOTEL_ROOM_NUM);
    
    public MainWindow() {
        
        
        try {
            Scanner k = new Scanner(System.in);
            System.out.println("Welcome to our Hotel Management System :)");
            System.out.println("Please Login:");
            
            Login();
            
        } catch (ParseException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HotelManagementSystem.WrongDateException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void Login() throws ParseException, HotelManagementSystem.WrongDateException {
        
        Guest user = null;
        Recepsionist rec = null;

        Room rooms[] = new Room[HOTEL_ROOM_NUM];
        for (int i = 0 ; i < HOTEL_ROOM_NUM ; i++) {
            rooms[i] = new Room(i);
        }
        
        System.out.println("Do you wanna Login as Receptionist "
                    + "or Guest ?" + "Press R for Receptionist or Press G for "
                    + "Guest");
        Scanner t = new Scanner(System.in);
        String loginType = t.nextLine();
        
                    
        if(loginType.equals("r") || loginType.equals("R")){
            
        System.out.print("Enter your username:");
        String userName = t.next();
        System.out.println("Enter your password:");
        String password = t.next();
            
            System.out.println("You logged in as Receptionist...");
            
            boolean exit = false;
            
            while(true){
                
                System.out.println("--------MENU--------");
                System.out.println("1)Booking a room");
                System.out.println("2)Cancel a booked room");
                System.out.println("3)Check-In");
                System.out.println("4)Check-Out");
                System.out.println("5)Exit - Logout"); 
                String recInput = t.next();
                
                switch(recInput){
                    case "1":
                        System.out.println("Enter guest name:");
                        name = t.next();
                        System.out.println("Enter guest Surname:");
                        surname = t.next();
                        System.out.println("Guest age:");               
                        age = t.nextInt();
                        System.out.println("Guest ID number:");
                        id = t.next();
                        System.out.println("Guest Phone number:");
                        phone = t.next();
                        System.out.println("Guest Check-in date as day,month,year");
                        dIn = t.next();
                        mmIn = t.next();
                        yIn = t.next();
                        System.out.println("Guest check-out date as day,month,year");
                        dOut = t.next();
                        mmOut = t.next();
                        yOut = t.next();
                        
                        
                        int day = Integer.parseInt(dIn);
                        int month = Integer.parseInt(dIn);
                        int year = Integer.parseInt(dIn);
                        
                        int day2 = Integer.parseInt(dIn);
                        int month2 = Integer.parseInt(dIn);
                        int year2 = Integer.parseInt(dIn);
                        
                        System.out.println("" + day);

                        String dateIn = HotelManagementSystem.getDateStr(day, month, year);

            
                
                        String dateOut = HotelManagementSystem.getDateStr
                                                        (day2, month2, year2);
                        
                        user = new Guest(name, surname ,age , id ,phone,
                            HotelManagementSystem.getDateFromStr(dateIn),
                            HotelManagementSystem.getDateFromStr(dateOut)
                    );
                        
                        rec = new Recepsionist();
                        rec.booking(user, rooms);
                        break;
                    case "2":
                        String identity;
                        System.out.println("Enter guest Id number");
                        identity = t.next();
                        Recepsionist cancelRec = null;
                        Guest cancelGuest = null;
                        cancelGuest.Id = identity;
                        cancelRec.cancelBooking(cancelGuest);
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "5": 
                        exit = true;
                        return;
                    default:
                        break;
                }
                
                if(exit == true)
                    break;
  
            }
            
        }
        
        else if(loginType.equals("g") || loginType.equals("G")){
        
            System.out.print("Enter your username:");
            String userName = t.next();
            System.out.println("Enter your password:");
            String password = t.next();
            System.out.println("You logged in as Guest...");
            
            while(true){
                
                System.out.println("--------MENU--------");
                System.out.println("1)Booking a room(B)");
                System.out.println("2)Cancel a booked room(C)");
                System.out.println("3)Exit - Logout(O)");
                int guestInput = t.nextInt();
                
                if(guestInput == 1){
                    
                    System.out.println("Enter your name:");
                    name = t.next();
                    System.out.println("Enter your Surname:");
                    surname = t.next();
                    System.out.println("Your age:");               
                    age = t.nextInt();
                    System.out.println("ID number:");
                    id = t.next();
                    System.out.println("Phone number:");
                    phone = t.next();
                    System.out.println("Check-in date as day,month,year");
                    dIn = t.next();
                    mmIn = t.next();
                    yIn = t.next();
                    System.out.println("Check-out date as day,month,year");
                    dOut = t.next();
                    mmOut = t.next();
                    yOut = t.next();
                    
                    int day = Integer.parseInt(dIn);
                    int month = Integer.parseInt(dIn);
                    int year = Integer.parseInt(dIn);
                    
                    int day2 = Integer.parseInt(dIn);
                    int month2 = Integer.parseInt(dIn);
                    int year2 = Integer.parseInt(dIn);
 
                    
                    String dateIn = HotelManagementSystem.getDateStr
                                                            (day, month, year);
                    String dateOut = HotelManagementSystem.getDateStr
                                                          (day2, month2, year2);
                    
                    user = new Guest(name, surname ,age , id ,phone,
                            HotelManagementSystem.getDateFromStr(dateIn),
                            HotelManagementSystem.getDateFromStr(dateOut)
                    );
                    user.booking(rooms);
                    
                }
                
                else if(guestInput == 2){
                    String identity;
                    System.out.println("Enter your Id number");
                    identity = t.next();
                    
                    user.Id = identity;
                    user.cancelBooking();
                    
                }
                
                else if(guestInput == 3){
                    break;
                }
            }
            
        }
        
        
    }
    
}
