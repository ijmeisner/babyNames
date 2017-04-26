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
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Titoriano
 * @param <T>
 */
public class Nursery<T> {
  ArrayList<T> store;
  
  public Nursery() {
    
    store = new ArrayList<>();
    /*
    try (Scanner scanner = new Scanner(new File("./Pokemon.csv"))) {
        while(scanner.hasNextLine()) {
          String line = scanner.nextLine();
          String[] lineArray = line.split(",");
          try{
          store.add(new Baby (  Integer.parseInt(lineArray[0]),
                                lineArray[1],
                                Integer.parseInt(lineArray[2]),
                                lineArray[3].charAt(0),
                                Integer.parseInt(lineArray[4]) ));
          }
          catch(Exception e){
            
          }
      }
    }*/
  }
  
  
  
  public void add(T item) {
    store.add(item);
  }
  
  public T index(int index) {
    try{
      store.get(index);
    }
    catch(Exception e){
      return null;
    }
    return store.get(index);
  }
  
  // Baby foo = (Baby) Store.get(0);

  public List all() {
    return store;
  }
}
