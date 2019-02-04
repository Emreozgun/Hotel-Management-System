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
public class RecepsionistTest {
    
    public RecepsionistTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of booking method, of class Recepsionist.
     */
    @Test
    public void testBooking() {
        System.out.println("booking");
        Calendar d1 = Calendar.getInstance();
        d1.set(2017, 2, 22);

        Calendar d2 = Calendar.getInstance();
        d2.set(2017, 2, 25);
        Guest person = new Guest("Emre","Ozgun", 21, "124893", 
                                "05354892417", d1, d2);
        Room rooms[] = new Room[10];
        for (int i = 0 ; i < 10 ; i++) {
            rooms[i] = new Room(i);
        }
        Recepsionist recep = new Recepsionist("Furkan", "Turk", 20, "2458996", 
                                        "05361425789", 12);
        recep.booking(person, rooms);

    }

    /**
     * Test of checkIn method, of class Recepsionist.
     */
    @Test
    public void testCheckIn() {
        System.out.println("checkIn");
        Recepsionist instance = new Recepsionist();
        Calendar d1 = Calendar.getInstance();
        d1.set(2017, 2, 22);
        Calendar d2 = Calendar.getInstance();
        d2.set(2017, 2, 25);
        Guest person = new Guest("Emre", "Ozgun", 21, "5201789678", 
                    "05348796325" ,d1 ,d2 );
        Calendar result = instance.checkIn(person);
        assertEquals(d1, result);
    }

    /**
     * Test of checkOut method, of class Recepsionist.
     */
    @Test
    public void testCheckOut() {
        System.out.println("checkOut");
        Recepsionist instance = new Recepsionist();
        Calendar d1 = Calendar.getInstance();
        d1.set(2017, 2, 22);
        Calendar d2 = Calendar.getInstance();
        d2.set(2017, 2, 25);
        Guest person = new Guest("Emre", "Ozgun", 21, "5201789678", 
                    "05348796325" ,d1 ,d2 );
        Calendar result = instance.checkOut(person);
        assertEquals(d2, result);
    }

    /**
     * Test of login method, of class Recepsionist.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        String userName = "emreeozgun";
        String password = "11235813";
        Recepsionist instance = new Recepsionist("Furkan", "Turk",
                                        20, "52018610160", "05385133847", 12);
        instance.login(userName, password);

        
        
    }
    
}
