/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import java.util.Calendar;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emre
 */
public class GuestTest {
    
    public GuestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getCheckIn method, of class Guest.
     */
    @Test
    public void testGetCheckIn() {
        System.out.println("getCheckIn");
        Calendar d1 = Calendar.getInstance();
        d1.set(2017, 2, 25);
        Calendar d2 = Calendar.getInstance();
        d2.set(2017, 3, 2);
        Guest instance = new Guest("Emre", "Ozgun", 20, "5201786834", 
                "05371478695", d1, d2);
        Calendar result = instance.getCheckIn();
        assertEquals(d1, result);
    }

    /**
     * Test of getCheckOut method, of class Guest.
     */
    @Test
    public void testGetCheckOut() {
        System.out.println("getCheckIn");
        Calendar d1 = Calendar.getInstance();
        d1.set(2017, 2, 25);
        Calendar d2 = Calendar.getInstance();
        d2.set(2017, 3, 2);
        Guest instance = new Guest("Emre", "Ozgun", 20, "5201786834", 
                "05371478695", d1, d2);
        Calendar result = instance.getCheckOut();
        assertEquals(d2, result);
    }

    /**
     * Test of cancelBooking method, of class Guest.
     */
    @Test
    public void testCancelBooking() {
        System.out.println("cancelBooking");
        Guest instance = new Guest();
        instance.cancelBooking();

    }

    /**
     * Test of login method, of class Guest.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        Calendar d1,d2;
        String userName,password;
        Guest person;
        
        d1 = Calendar.getInstance();
        d1.set(2017, 2, 25);
        d2 = Calendar.getInstance();
        d2.set(2017, 3, 2);
        userName = "emreozgunn";
        password = "14789632";
        person = new Guest("Emre", "Ozgun", 21, "5201789678", 
                    "05348796325" ,d1 ,d2 );
        System.out.println("erme");
        person.login(userName, password);
    }

    /**
     * Test of booking method, of class Guest.
     */
    @Test
    public void testBooking() {
        System.out.println("booking");
        Calendar d1 = Calendar.getInstance();
        d1.set(2017, 2, 25);
        Calendar d2 = Calendar.getInstance();
        d2.set(2017, 3, 2);
        String userName = "aaaa";
        String password = "0000";
        Guest person = new Guest("Emre", "Ozgun", 21, "5201789678", 
                    "05348796325" ,d1 ,d2 );
        
        Room rooms[] = new Room[10];
        for (int i = 0 ; i < 10 ; i++) {
            rooms[i] = new Room(i);
        }
        person.booking(rooms);

    }
    
}
