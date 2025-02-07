package babynames;

import babynames.models.Baby;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.JRadioButton;

/**
 *
 * @author Ijmeisner
 */

public class BabyGUI {
   private JFrame mainFrame;
   private JPanel rightPanel, leftPanel, babyEntryForm, controlPanel, chartPanel, stuff;
   private int height, width;
   private final ArrayList<Baby> foo;
   private final File fileName;
   private final String maleString;
   private final String femaleString;

   public BabyGUI(ArrayList<Baby> house, File babyFile){
     this.foo = house;
     this.fileName = babyFile;
     this.maleString = "M";
     this.femaleString = "F";
     prepareGUI();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("CSC 420 Group Project");
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      width = (int) screenSize.getWidth();
      height = (int) screenSize.getHeight();
      mainFrame.setSize(width,height);
      mainFrame.setLayout(new GridLayout(1, 2));
      
      mainFrame.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });
      
      addPanels();
      mainFrame.setVisible(true);  
   }
   
   // This method creates and adds all the components for comparing two baby names
   // to the left panel of the JFrame. When the compare button is pressed, the line
   // chart is generated.
   void setControlPanel(){

      JLabel  name1Label= new JLabel("Name 1:", JLabel.RIGHT);
      JLabel  name2Label = new JLabel("Name 2:", JLabel.CENTER);
     
      final JTextField name1Text = new JTextField(6);
      
      JRadioButton male1Button = new JRadioButton(maleString);
      male1Button.setMnemonic(KeyEvent.VK_M);
      male1Button.setActionCommand(maleString);
      
      JRadioButton female1Button = new JRadioButton(femaleString);
      female1Button.setMnemonic(KeyEvent.VK_F);
      female1Button.setActionCommand(femaleString);
      
      ButtonGroup group1 = new ButtonGroup();
      group1.add(male1Button);
      group1.add(female1Button);
      
      final JTextField name2Text = new JTextField(6);
      JRadioButton male2Button = new JRadioButton(maleString);
      male2Button.setMnemonic(KeyEvent.VK_M);
      male2Button.setActionCommand(maleString);
      
      JRadioButton female2Button = new JRadioButton(femaleString);
      female2Button.setMnemonic(KeyEvent.VK_F);
      female2Button.setActionCommand(femaleString);
      
      ButtonGroup group2 = new ButtonGroup();
      group2.add(male2Button);
      group2.add(female2Button);
      
      JButton compareButton = new JButton("Compare");
      compareButton.addActionListener((ActionEvent e) -> {
          String gender1 = group1.getSelection().getActionCommand();
          String gender2 = group2.getSelection().getActionCommand();
          
          XYDataset dataset = createDataset(foo, name1Text.getText(), gender1, name2Text.getText(), gender2);
          
          JPanel chartPanelFoo = createChartPanel(dataset);
          chartPanelFoo.setPreferredSize(new java.awt.Dimension((this.width/2)-8, (this.height/3)-35));
          chartPanel.removeAll();
          chartPanel.add(chartPanelFoo);
          
          mainFrame.setVisible(true);
      });
      controlPanel.add(name1Label);
      controlPanel.add(name1Text);
      controlPanel.add(male1Button);
      controlPanel.add(female1Button);
      controlPanel.add(name2Label);       
      controlPanel.add(name2Text);
      controlPanel.add(male2Button);
      controlPanel.add(female2Button);
      controlPanel.add(compareButton);
      mainFrame.setVisible(true);
   }
   
   // This method creates and adds all the components to the right panel of the
   // JFrame for adding data into our dataset. When the 'add' button is pressed,
   // the entry is appended to the CSV.
   void setBabyEntryForm(){
      JLabel  babyEntryName = new JLabel("Baby Name:", JLabel.RIGHT);
      final JTextField babyEntryText = new JTextField(6);
      
      JLabel babyEntryGender = new JLabel("Gender:", JLabel.RIGHT);
      
      JRadioButton maleEntry = new JRadioButton(maleString);
      maleEntry.setMnemonic(KeyEvent.VK_M);
      maleEntry.setActionCommand(maleString);
      
      JRadioButton femaleEntry = new JRadioButton(femaleString);
      femaleEntry.setMnemonic(KeyEvent.VK_F);
      femaleEntry.setActionCommand(femaleString);
      
      ButtonGroup entryGroup = new ButtonGroup();
      entryGroup.add(maleEntry);
      entryGroup.add(femaleEntry);
      
      JLabel  babyEntryYear = new JLabel("Year:", JLabel.RIGHT);
      final JTextField babyEntryTextYear = new JTextField(6);
      JLabel  babyEntryCount = new JLabel("Count:", JLabel.RIGHT);
      final JTextField babyEntryTextCount = new JTextField(6);
      
      JButton addButton = new JButton("Add");
      addButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int nextId = foo.size() + 1;
              foo.add (new Baby(nextId,
                      babyEntryText.getText(),
                      Integer.parseInt(babyEntryTextYear.getText()),
                      entryGroup.getSelection().getActionCommand(),
                      Integer.parseInt(babyEntryTextCount.getText())));
              
              String babyObjStr = nextId + "," +
                      babyEntryText.getText()+ "," +
                      Integer.parseInt(babyEntryTextYear.getText())+ "," +
                      entryGroup.getSelection().getActionCommand()+ "," +
                      Integer.parseInt(babyEntryTextCount.getText()) + "\n";
              
              try {
                  FileWriter writer = new FileWriter(BabyGUI.this.fileName, true);
                  writer.append(babyObjStr);
                  writer.flush();
                  writer.close();
              }catch (IOException ex) {
                  Logger.getLogger(BabyGUI.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
      });
      
      babyEntryForm.add(babyEntryName);
      babyEntryForm.add(babyEntryText);
      babyEntryForm.add(babyEntryGender);
      babyEntryForm.add(maleEntry);
      babyEntryForm.add(femaleEntry);
      babyEntryForm.add(babyEntryYear);
      babyEntryForm.add(babyEntryTextYear);
      babyEntryForm.add(babyEntryCount);
      babyEntryForm.add(babyEntryTextCount);
      babyEntryForm.add(addButton);
      
      mainFrame.setVisible(true);  
   }
   
   // This method returns the line graph which is generated using the 
   // JFreeChart Library.
   private JPanel createChartPanel(XYDataset dataset) {
        String chartTitle = "Baby Name Comparison";
        String xAxisLabel = "Year";
        String yAxisLabel = "Count";

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        return new ChartPanel(chart);
    }
   
   // This method returns the XYDataset in which the JFreeChart method uses to create
   // the line graph. Each entry in the ArrayList is tested and if it matches the
   // selected names, it is added to the dataset.
   private XYDataset createDataset(ArrayList<Baby> foo, String name1, String gender1, String name2, String gender2) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries(name1 + " " + gender1);
        XYSeries series2 = new XYSeries(name2 + " " + gender2);
        boolean isName1, isName2, isGender1, isGender2;
        
        for(int i=0; i < foo.size(); i++){
          isName1 = name1.equals(foo.get(i).getName());
          isGender1 = gender1.equals(foo.get(i).getGender());
          isName2 = name2.equals(foo.get(i).getName());
          isGender2 = gender2.equals(foo.get(i).getGender());
         
          if(isName1 && isGender1){
            series1.add(foo.get(i).getYear(), foo.get(i).getCount());
          }
          if(isName2 && isGender2){
            series2.add(foo.get(i).getYear(), foo.get(i).getCount());
          }
        }
       
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        
        return dataset;
    }

    // This method creates and adds the components of both the right and left panels
    // to the main JFrame.
    private void addPanels() {
      leftPanel = new JPanel();
      JLabel leftTitle = new JLabel("Compare Two Baby Names", JLabel.CENTER);
      rightPanel = new JPanel();
      JLabel rightTitle = new JLabel("Add a Baby to the Dataset", JLabel.CENTER);
      
      leftPanel.setLayout(new GridLayout(3,1));
      rightPanel.setLayout(new GridLayout(3,1));
            
      Color leftColor = new Color(255,182,193);
      Color rightColor = new Color(173,216,230);
      
      leftPanel.setBackground(leftColor);
      rightPanel.setBackground(rightColor);
      
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
      controlPanel.setBackground(leftColor);
      chartPanel = new JPanel();
      chartPanel.setLayout(new FlowLayout());
      chartPanel.setBackground(leftColor);
      
      babyEntryForm = new JPanel();
      babyEntryForm.setLayout(new FlowLayout());
      babyEntryForm.setBackground(rightColor);
      
      stuff = new JPanel();
      stuff.setLayout(new FlowLayout());
      stuff.setBackground(rightColor);
      
      leftPanel.add(leftTitle);
      leftPanel.add(controlPanel);
      leftPanel.add(chartPanel);
      rightPanel.add(rightTitle);
      rightPanel.add(babyEntryForm);
      rightPanel.add(stuff);

      mainFrame.add(leftPanel);
      mainFrame.add(rightPanel);
    }
}