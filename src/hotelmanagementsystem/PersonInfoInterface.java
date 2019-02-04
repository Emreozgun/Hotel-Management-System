/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

/**Interface of that general hotel system as Person Info 
 *
 * @author emre
 */
public interface PersonInfoInterface {
    
    public enum UserType {
        GUEST, RECEPTIONIST
    };
    public String getName();
    public String getSurname();
    public int getAge();
    public String getId();
    public String getPhone();
    public  UserType getUserType();
    public void setName(String theName);
    public void setSurname(String theSurname);
    public void setAge(int theAge);
    public void setId(String theId);
    public void setPhone(String thePhone);
    public void setUserType(UserType type);
    public void login(String userName, String password) throws PersonInfo.LoginFailException;
    public void writeLoginToFile(String bookingFile,String userName, String password);

}
