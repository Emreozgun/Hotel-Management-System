/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**Main Class
 *
 * @author emre
 */
public class HotelManagementSystem {
    
    public static class WrongDateException extends Exception {
        
    }
    /**
     * This function returns date as String format.
     * 
     * @param y year about check-In or check-Out date
     * @param m month about check-In or check-Out date
     * @param d date about check-In or check-Out date
     * @return date as String format
     * @throws hotelmanagementsystem.HotelManagementSystem.WrongDateException 
     */
    public static String getDateStr(int y, int m, int d) throws WrongDateException {
        
        
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.set(y, m, d);
        try {
            return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        } catch (IllegalArgumentException e) {
            throw new WrongDateException();
        }
    }
    /**
     * 
     * @param dateStr date as String format 
     * @return Calendar from String 
     * @throws ParseException 
     */
    public static Calendar getDateFromStr(String dateStr) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr));
        return calendar;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
             
        Recepsionist.PERSON_INFO_FILE = "FileDatabase/RecepsionistLogin.txt";
        Guest.PERSON_INFO_FILE = "FileDatabase/GuestLogin.txt";
        Guest.BOOKING_FILE = "FileDatabase/emre.txt";        
        
        MainWindow start = new MainWindow();
    }

}
