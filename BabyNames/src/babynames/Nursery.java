/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babynames;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Titoriano
 * @param <T>
 */
public class Nursery<T> {
  ArrayList<T> store;
  
  public Nursery() {    
    store = new ArrayList<>();
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
  
  public List all() {
    return store;
  }
}
