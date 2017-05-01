/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babynames;
import babynames.models.Baby;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 *
 * @author Titoriano
 */
public class Main {
  /**
   * @param args the command line arguments
   */
  ArrayList<Baby> house = new ArrayList<>();

  public static void main(String[] args) {
    // TODO: Add jframe and GUI Functionallity 
    
    // Declares Baby name storage    
    ArrayList<Baby> house = new ArrayList<>();
    BabyCsvReader bcr;
    try {
      bcr = new BabyCsvReader(new File("./names.csv"));
      Baby current = bcr.readEntry();
      System.out.println("Baby names in the list: "+house.size());
      while(current != null) {
        house.add(current);
        current = bcr.readEntry();
      }  
      System.out.println("Baby names in the list: "+house.size());
    }
    catch(FileNotFoundException e) {
      System.out.println(house.size());
    }
    int index = 0;
    System.out.println(house.get(index).getBaby()); 
    
    //getAll(house);
    /*
      Similarily you can do the following to get specific elements of object
      house.get(index).getName();
        **or**
      Change contents
      house.get(index0.setName(Name);
    */
  }
  public static void getAll(ArrayList<Baby> house){
    for (int i=0; i < house.size(); i++){
      System.out.println(house.get(i).getBaby()); 
    }
  }
}
