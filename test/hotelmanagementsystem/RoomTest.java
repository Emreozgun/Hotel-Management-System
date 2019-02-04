/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emre
 */
public class RoomTest {
    
    public RoomTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getRoomNum method, of class Room.
     */
    @Test
    public void testGetRoomNum() {
        System.out.println("getRoomNum");
        int theRoomNum = 12;        
        Room instance = new Room(theRoomNum);
        int result = instance.getRoomNum();
        assertEquals(result, theRoomNum);
    }

    /**
     * Test of setRoomNum method, of class Room.
     */
    @Test
    public void testSetRoomNum() {
        System.out.println("setRoomNum");
        int theRoomNum = 12;
        Room instance = new Room(theRoomNum);
        instance.setRoomNum(theRoomNum);

    }
    
}
