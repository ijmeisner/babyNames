/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babynames;

import babynames.models.Baby;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Titoriano
 */
public class BabyCsvReader {
  final private Scanner sc;
  public BabyCsvReader(File file) throws FileNotFoundException {
    sc = new Scanner(file);
    if(sc.hasNext()) {
      
      System.out.println("The scanner has opened the file!");
    }
    else {
            System.out.println("The scanner has NOT opened the file!");
    }
  }

  public Baby readEntry() {
    try {
      if(sc.hasNextLine()) {
          String line = sc.nextLine();
          String[] lineArray = line.split(",");
          try{
            return new Baby (  Integer.parseInt(lineArray[0]),
                                lineArray[1],
                                Integer.parseInt(lineArray[2]),
                                lineArray[3].charAt(0),
                                Integer.parseInt(lineArray[4]) );
          }
          catch(NumberFormatException e) {
            e.printStackTrace();
            return null;
          }
      }
      else {
        return null;
      }
    } 
    catch(Exception e) {
      return null;
    }
  }
}
