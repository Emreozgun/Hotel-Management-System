/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emre
 */public class Guest extends PersonInfo {

    protected static String BOOKING_FILE = "FileDatabase/emre.txt";
    protected static String PERSON_INFO_FILE = "FileDatabase/GuestLogin.txt";

    protected Calendar CheckIn;
    protected Calendar CheckOut;

    public Guest() {
        super("", "", -1, "-1", "");

    }

    /**
     * The constructor is the founder of all information about guest. The
     * constructor have all personel information.All information will come from
     * PersonInfo abstract class and add check-In, check-Out date
     *
     * @param theName the name info about guest to doing reservation
     * @param theSurname the surname info about guest to doing reservation
     * @param theAge age of guest to doing reservation
     * @param theId identification number of guest
     * @param thePhone personel telephone number of guest
     * @param theCheckIn check-in date of guest for reservation
     * @param theCheckOut check-out date of guest for reservation
     */
    public Guest(String theName, String theSurname, int theAge, String theId,
            String thePhone, Calendar theCheckIn, Calendar theCheckOut) {
        super(theName, theSurname, theAge, theId, thePhone);

        this.CheckIn = theCheckIn;
        this.CheckOut = theCheckOut;

        if (!this.CheckOut.after(this.CheckIn)) {
            this.CheckOut = (Calendar) this.CheckIn.clone();
            this.CheckOut.add(Calendar.DATE, 1);
        }

    }


    /**
     *
     * @return check-In date of Guest
     */
    public Calendar getCheckIn() {

        return CheckIn;
    }

    /**
     * This function convert Calendar date to String
     *
     * @return Check-in date as String format
     * @throws hotelmanagementsystem.HotelManagementSystem.WrongDateException
     */
    private String getCheckInStr() throws HotelManagementSystem.WrongDateException {
        try {
            System.out.println("" + CheckIn.toString());
            return new SimpleDateFormat("yyyy-MM-dd").format(CheckIn.getTime());
        } catch (IllegalArgumentException e) {
            System.out.println("test");
            throw new HotelManagementSystem.WrongDateException();
        }
    }

    /**
     *
     * @return check-Out date of Guest
     */
    public Calendar getCheckOut() {

        return CheckOut;
    }

    /**
     * This function convert Calendar date to String
     *
     * @return check-Out date as String format
     */
    private String getCheckOutStr() throws HotelManagementSystem.WrongDateException {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").format(CheckOut.getTime());
        } catch (IllegalArgumentException e) {
            throw new HotelManagementSystem.WrongDateException();
        }
    }
    /**
     * This function is for the guest to cancel the reservation.
     * Check all the records and delete the guest reservation.
     */
    public void cancelBooking() {

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
                if (data[5].equals(this.Id)) {
                    System.out.println("" + data[5] + " " + this.Id);
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

    /**
     * Keeps records of guests who enter the system.
     *
     * @param userName is name for enter the system
     * @param password is password for enter the system
     * @throws hotelmanagementsystem.PersonInfo.LoginFailException
     */
    @Override
    public void login(String userName, String password) throws LoginFailException {

        try {

            FileReader fileReader = new FileReader(Guest.PERSON_INFO_FILE);
            BufferedReader bufferReader = new BufferedReader(fileReader);

            this.writeLoginToFile(Guest.PERSON_INFO_FILE, userName, password);
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + PERSON_INFO_FILE + "'");
        }

    }

    /**
     * The general function of booking check available date and rooms then write
     * personal info to text file
     *
     * @param rooms array hold rooms ıd number to reservation
     */
    protected void booking(Room[] rooms) {

        try {
            File file = new File(BOOKING_FILE);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            int resevedRoomCount = 0; // reserved rooms number

            Set<Integer> emptyRooms = new HashSet();
            for (Room r : rooms) {
                emptyRooms.add(r.getRoomNum());
            }

            while ((line = bufferedReader.readLine()) != null) {
                String data[] = line.split(",");

                if (data.length != 8) {
                    // hatali satir
                    continue;
                }

                int roomNumber = Integer.parseInt(data[0]);
                System.out.println("" + data[6] + " " + data[7]);
                Calendar rCi = HotelManagementSystem.getDateFromStr(data[6]);
                Calendar rCo = HotelManagementSystem.getDateFromStr(data[7]);

                Calendar gCi = this.getCheckIn();
                Calendar gCo = this.getCheckOut();

                /* A: odanin check in ve check outu onceyse problem yok
                B: odanin check in ve check outu sonraysa problem yok
                A veya B degilse oda dolu
                 */
                if (!(rCi.before(gCi) && rCo.before(gCi)) || (rCi.after(gCo) && rCo.before(gCo))) {
                    resevedRoomCount++;

                    emptyRooms.remove(roomNumber);

                }

            }
            fileReader.close();

            if (emptyRooms.size() > 0) {
                Iterator iter = emptyRooms.iterator();
                Integer roomNumber = (Integer) iter.next();
                System.out.println("" + this.getCheckIn());
                writeBookingToFile(BOOKING_FILE, this, rooms[roomNumber]);

            } else {
                // oda yok
                System.out.println("Not available room");
            }

        } catch (IOException ex) {
            Logger.getLogger(Recepsionist.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Recepsionist.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Writes all the informations about guest to the text file. Thrown an
     * exception when there is a file open error.
     *
     * @param bookingFile booking text file path to save guest info
     * @param person that variable hold all guest
     * informations(name,surname,phone etc.)
     * @param room variable hold information about room(room Id etc.)
     */
    private void writeBookingToFile(String bookingFile, Guest person, Room room) {

        try {
            FileWriter filewrt = new FileWriter(bookingFile, true);

            BufferedWriter writeToFile = new BufferedWriter(filewrt);
            writeToFile.write(room.getRoomNum() + ",");
            writeToFile.write(person.getName() + ",");
            writeToFile.write(person.getSurname() + ",");

            writeToFile.write(person.getAge() + ",");
            writeToFile.write(person.getPhone() + ",");
            writeToFile.write(person.getId() + ",");
            writeToFile.write(person.getCheckInStr() + ",");
            writeToFile.write(person.getCheckOutStr() + "");
            writeToFile.newLine();

            writeToFile.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + bookingFile + "'");
        } catch (HotelManagementSystem.WrongDateException ex) {
            Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void booking(Room rooms) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
