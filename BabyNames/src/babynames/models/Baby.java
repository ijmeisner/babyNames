/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babynames.models;

/**
 *
 * @author Titoriano
 */
public class Baby {
  private int id;
  private String name;
  private int year;
  private String gender;
  private int count;
  
  public Baby(int id, String name, int year, String gender, int count) {
    this.id = id;
    this.name = name;
    this.year = year;
    this.gender = gender;
    this.count = count;
  }
  
  public String getBaby(){
    String baby = "" 
    +"    ID: "+this.getId()+"\n"
    +"  Name: "+this.getName()+"\n"
    +"  Year: "+this.getYear()+"\n"
    +"Gender: "+this.getGender()+"\n"
    +" Count: "+this.getCount()+"\n";
    
    return baby;
  }
  public int getId(){
    return this.id;
  }
  public void setId(int id){
    this.id = id;
  }
  
  public String getName(){
    return this.name;
  }
  public void setName(String name){
    this.name = name;
  }

  public int getYear(){
    return this.year;
  }
  public void setYear(int year){
    this.year = year;
  }
  
  public String getGender(){
    return this.gender;
  }
  public void setGender(String gender){
    this.gender = gender;
  }
  
  public int getCount(){
    return this.count;
  }
  public void setCount(int count){
    this.count = count;
  } 
}
