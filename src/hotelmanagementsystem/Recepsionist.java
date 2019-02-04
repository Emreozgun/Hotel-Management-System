/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import static hotelmanagementsystem.Guest.BOOKING_FILE;
import static hotelmanagementsystem.PersonInfo.PERSON_INFO_FILE;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;



/**
 *
 * @author emre
 */
public class Recepsionist extends PersonInfo{
    
    private int employeeID;
    protected static String PERSON_INFO_FILE = "FileDatabase/RecepsionistLogin.txt";
    
            
    private Calendar CheckIn;
    private Calendar CheckOut;
        
    public Recepsionist() {
        
        super("", "", -1, "-1", "");
        this.employeeID = -1;
        
    }
    
    public Recepsionist(String theName, String theSurname, int theAge, String theId, 
                                            String thePhone,int theEmployeeID){
        
        super(theName, theSurname, theAge, theId, thePhone);
        this.employeeID = theEmployeeID;
    }
    
    /**
     * That function will execute make over from guest booking 
     * @param person all informations about guest,recepsionist take that
     * info to reservation.
     * @param rooms informations about booking(room number)
     */
    protected void booking(Guest person, Room[] rooms) {
        
        person.booking(rooms);
        
    }
    
    /**
     * 
     * @return check-in date as Calendar
     */
    public Calendar checkIn(Guest person){
    
        return person.CheckIn;
    }
    /**
     * 
     * @return check-out date as Calendar
     */
    public Calendar checkOut(Guest person){
        
        return person.CheckOut;
    }
    /**
     * 
     * @param userName
     * @param password
     * @throws LoginFailException to error about login
     */
    @Override
    public void login(String userName, String password) throws LoginFailException {
        
        
        try {
        
        FileReader fileReader = new FileReader(Recepsionist.PERSON_INFO_FILE);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        

            this.writeLoginToFile(Recepsionist.PERSON_INFO_FILE, userName, password);
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + Recepsionist.PERSON_INFO_FILE + "'");
        } 

    }
    
        public void cancelBooking(Guest person) {

        try {

            File inFile = new File(BOOKING_FILE);
            boolean theCancel = false ;
            
            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }
            File cancelFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader read = new BufferedReader(new FileReader(BOOKING_FILE));
            PrintWriter print = new PrintWriter(new FileWriter(cancelFile));

            String line = null;
            
            Set<String> guestInfo = new HashSet();

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = read.readLine()) != null) {

                String data[] = line.split(",");
                if (data[5].equals(person.Id)) {
                    System.out.println("" + data[5] + " " + person.Id);
                    theCancel = true;
                    print.println(line);
                    print.flush();
                }
            }
            print.close();
            read.close();

            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            if (!cancelFile.renameTo(inFile)) {
                System.out.println("Could not rename file");
            }
            
            if(theCancel == true){
                System.out.println("");
                 return;
            }
                

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}