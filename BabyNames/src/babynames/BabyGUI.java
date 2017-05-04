/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babynames;

import babynames.models.Baby;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
   private JPanel mainPanel;
   private JPanel babyEntryPanel;
   private JPanel babyEntryTitle;
   private JPanel babyEntryForm;
   private JLabel headerLabel;
   private JPanel controlPanel;
   private JPanel chartPanel;
   private final ArrayList<Baby> foo;
   private File fileName;

   public BabyGUI(ArrayList<Baby> house, File babyFile){
     this.foo = house;
     this.fileName = babyFile;
      prepareGUI();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("CSC 420 Group Project");
      mainFrame.setSize(900,700);
      mainFrame.setLayout(new GridLayout(1, 2));
      
      mainFrame.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });
      mainPanel = new JPanel();
      babyEntryPanel = new JPanel();
      babyEntryForm = new JPanel();
      babyEntryTitle = new JPanel();
      chartPanel = new JPanel();
      
      mainPanel.setLayout     (new GridLayout(3,1));
      babyEntryPanel.setLayout(new GridLayout(3,1));
      babyEntryForm.setLayout(new FlowLayout());
      
      
      headerLabel = new JLabel("", JLabel.CENTER);        

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      //mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(babyEntryPanel);
      babyEntryPanel.add(babyEntryTitle);
      babyEntryPanel.add(babyEntryForm);
 
      mainFrame.setVisible(true);  
   }
   void showTextFieldDemo(){
      headerLabel.setText("Baby Names");
      String maleString = "M";
      String femaleString = "F";

      JLabel  name1Label= new JLabel("Name 1", JLabel.RIGHT);
      JLabel  name2Label = new JLabel("Name 2", JLabel.CENTER);
     
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
          chartPanelFoo.setPreferredSize(new java.awt.Dimension(350, 450));
          chartPanel.removeAll();
          chartPanel.add(chartPanelFoo);
          mainFrame.setVisible(true);
      }); 
      JLabel  babyEntryName = new JLabel("Baby Name", JLabel.RIGHT);
      final JTextField babyEntryText = new JTextField(6);
      
      JLabel babyEntryGender = new JLabel("Gender", JLabel.RIGHT);
      JRadioButton maleEntry = new JRadioButton(maleString);
      maleEntry.setMnemonic(KeyEvent.VK_M);
      maleEntry.setActionCommand(maleString);
      
      JRadioButton femaleEntry = new JRadioButton(femaleString);
      femaleEntry.setMnemonic(KeyEvent.VK_F);
      femaleEntry.setActionCommand(femaleString);
      
      ButtonGroup entryGroup = new ButtonGroup();
      entryGroup.add(maleEntry);
      entryGroup.add(femaleEntry);
      
      JLabel  babyEntryYear = new JLabel("Year", JLabel.RIGHT);
      final JTextField babyEntryTextYear = new JTextField(6);
      JLabel  babyEntryCount = new JLabel("Count", JLabel.RIGHT);
      final JTextField babyEntryTextCount = new JTextField(6);
      
      JButton addButton = new JButton("Add");
      addButton.addActionListener((ActionEvent e) -> {
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
                            Integer.parseInt(babyEntryTextCount.getText());
          System.out.println(babyObjStr);
          
          try {
              FileWriter writer = new FileWriter(this.fileName,true);
              BufferedWriter bw = new BufferedWriter(writer);
              bw.write(babyObjStr);
              System.out.println("Baby names in the list: "+ foo.size());
          }
          catch (IOException ex) {
            Logger.getLogger(BabyGUI.class.getName()).log(Level.SEVERE, null, ex);
          }

          System.out.print(foo.get(nextId-1).getBaby());
      }); 
      
      //controlPanel.setSize(700, 700);
      controlPanel.add(name1Label);
      controlPanel.add(name1Text);
      controlPanel.add(male1Button);
      controlPanel.add(female1Button);
      controlPanel.add(name2Label);       
      controlPanel.add(name2Text);
      controlPanel.add(male2Button);
      controlPanel.add(female2Button);
      controlPanel.add(compareButton);
      controlPanel.add(chartPanel);
      
      //babyEntryPanel.setSize(200,700);
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
   
   private JPanel createChartPanel(XYDataset dataset) {
        String chartTitle = "Baby Names";
        String xAxisLabel = "Year";
        String yAxisLabel = "Count";

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        return new ChartPanel(chart);
    }
   
   private XYDataset createDataset(ArrayList<Baby> foo, String name1, String gender1, String name2, String gender2) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries(name1);
        XYSeries series2 = new XYSeries(name2);
        boolean isName1, isName2, isGender1, isGender2;
        
        
        for(int i=0; i < foo.size(); i++){
          isName1 = name1.equals(foo.get(i).getName());
          isGender1 = gender1.equals(foo.get(i).getGender());
          isName2 = name2.equals(foo.get(i).getName());
          isGender2 = gender2.equals(foo.get(i).getGender());
         
          if(isName1 && isGender1){
            series1.add(foo.get(i).getYear(), foo.get(i).getCount());
          } else if(isName2 && isGender2){
            series2.add(foo.get(i).getYear(), foo.get(i).getCount());
          }
        }
       
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        
        return dataset;
    }

  private void addBabyName(String text, String actionCommand) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}