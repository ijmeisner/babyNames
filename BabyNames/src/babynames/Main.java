/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babynames;
import babynames.models.Baby;
import java.io.File;
import java.io.FileNotFoundException;
/**
 *
 * @author Titoriano
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // Declares Baby name storage
    Nursery house = new Nursery();
    
    //TODO: Need class for JFrame
    //Declare JFrame class object Here
    
    BabyCsvReader bcr;
    try {
      bcr = new BabyCsvReader(new File("./names.csv"));
      
      Baby current = bcr.readEntry();
      
      System.out.println("Baby names in the list: "+house.all().size());

      while(current != null) {
        house.add(current);
        current = bcr.readEntry();
      }
      
      System.out.print("Baby names in the list: "+house.all().size());

    }
    catch(FileNotFoundException e) {
              System.out.print(house.all().size());

    } 
  }
}
