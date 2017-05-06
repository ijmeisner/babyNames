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
    // Declares Baby name storage    
    ArrayList<Baby> house = new ArrayList<>();
    BabyCsvReader bcr;
    String fileName = "./names.csv";
    File babyFile=null;
    try {
      babyFile = new File(fileName);
      bcr = new BabyCsvReader(babyFile);
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
    
    //GUI set up
    BabyGUI  babyGUI = new BabyGUI(house, babyFile);      
    babyGUI.setControlPanel();
    babyGUI.setBabyEntryForm();
  }
  public static void getAll(ArrayList<Baby> house){
    for (int i=0; i < house.size(); i++){
      System.out.println(house.get(i).getBaby()); 
    }
  }
}
