/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

/**This class just holds room number.
 *
 * @author emre
 */
public class Room {

    private static int roomNum;
    // private int dayPrice;
    /**
     * Constructor will founding with roomNum
     * @param roomNum is the variable to Id of Room
     */
    public Room(int roomNum) {
        this.roomNum = roomNum;
    }

    public Room() {
        this.roomNum = 0;
    }
    /**
     * 
     * @return room number as integer
     */
    public int getRoomNum() {

        return roomNum;
    }
    /**
     * 
     * @param theRoomNum is variable to set room number as integer
     */
    public void setRoomNum(int theRoomNum){
        
        this.roomNum = theRoomNum;
    }

}
