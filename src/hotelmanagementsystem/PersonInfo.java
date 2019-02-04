/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/** Abstract class includes all person informations.That class holds
 * name,surname,age,Id,telephone number,and UserType(Guest or Recepsionist)
 * And includes some function processing about hotel system.Login function is 
 * the general function for all Users.
 * @author emre
 */
public abstract class PersonInfo implements PersonInfoInterface {
 
    protected static String PERSON_INFO_FILE;

    protected String name;
    protected String surname;
    protected int age;
    protected String Id;
    protected String telephone;
    protected UserType authority;
    
    /** The constructor is the founder of all information about person.
     * The constructor have all personal information. All information will 
     * inherit with that abstract class.
     * 
     * @param theName the name info about guest to doing reservation as 
     * String 
     * @param theSurname the surname info about guest to doing reservation as 
     * String
     * @param theAge age of guest to doing reservation as Integer
     * @param theId identification number of guest as Integer
     * @param thePhone personel telephone number of guest as String 
     */
    public PersonInfo(String theName, String theSurname, int theAge, String theId, String thePhone){
        this.name = theName;
        this.surname = theSurname;
        this.age = theAge;
        this.Id = theId;
        this.telephone = thePhone;
    }
    /**
     * throws Exception when there is login error.
     */
    public class LoginFailException extends Exception {

        public LoginFailException() {
            super("Login Fail");
        }
        
    }
    
    /**
     *
     * @throws LoginFailException to login check
     */
    public abstract void login(String userName, String password) throws LoginFailException;
    
    /**
     * 
     * @param bookingFile booking text file path to save guest info as String
     * @param userName is the nickName of the entrance for hotel system as String
     * @param password is the password of the entrance for hotel system as String
     */
    public void writeLoginToFile(String bookingFile,String userName, String password){
        
                    
        try {
            // Assume default encoding.
            FileWriter filewrt = new FileWriter(bookingFile, true);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter writeToFile = new BufferedWriter(filewrt);
            writeToFile.write(userName + ",");
            writeToFile.write(password + ",");
            writeToFile.write(this.getName() + ",");
            writeToFile.write(this.getSurname() + ",");
            writeToFile.write(this.getAge() + ",");
            writeToFile.write(this.getPhone() + ",");
            writeToFile.write(this.getId() + "");
            writeToFile.newLine();

            // Always close files.
            writeToFile.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + bookingFile + "'");
        } 
        
    }
    /**
     * 
     * @return person name as String 
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @return person surname as String 
     */
    public String getSurname() {
        return surname;
    }
    
    /**
     * 
     * @return person age as integer 
     */
    public int getAge(){
	return age;
    }
    
    /**
     * 
     * @return person ID as String 
     */
    public String getId(){
        
	return Id;
    }
    /**
     * 
     * @return person telephone number as String 
     */
    public String getPhone() {
        return telephone;
    }
    /**
     * 
     * @return person userType(Guest or Recepsionist) as String 
     */
    public UserType getUserType() {
        return authority;
    }
    /**
     * 
     * @param theName person name to set 
     */
    public void setName(String theName) {
        this.name = theName;
    }
    
    /**
     * 
     * @param theSurname person surname to set 
     */
    public void setSurname(String theSurname) {
        this.surname = theSurname;
    }
    
    /**
     * 
     * @param theAge person age to set 
     */
    public void setAge(int theAge) {
        this.age = theAge;
    }
    
    /**
     * 
     * @param theId person identity to set 
     */
    public void setId(String theId) {
        
        this.Id = theId;
    }

    /**
     * 
     * @param thePhone person phone number to set 
     */
    public void setPhone(String thePhone) {
        this.telephone = thePhone;
    }
    /**
     * 
     * @param type person UserType as Recepsionist or Guest to set 
     */
    public void setUserType(UserType type) {
        this.authority = type;
    } 
    
    
}
