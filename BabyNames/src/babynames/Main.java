/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babynames;
import babynames.models.Baby;
import java.io.File;
/**
 *
 * @author Titoriano
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
    Nursery house = new Nursery();
    
    
    house.add(new Baby(
      0,
      "TJ",
      1995,
      'M',
      173490
    ));
    
    BabyCsvReader bcr;
    try {
      bcr = new BabyCsvReader(new File("./names.csv"));
      
      Baby current = bcr.readEntry();
      
      while(current != null) {
        house.add(current);
        current = bcr.readEntry();
        System.out.print(house.all().size());
      }
    }
    catch(Exception e) {
              System.out.print(house.all().size());

    } 
  }
}
