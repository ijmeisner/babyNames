/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babynames;

import babynames.models.Baby;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
   private JLabel headerLabel;
   private JPanel controlPanel;
   private final ArrayList<Baby> foo;

   public BabyGUI(ArrayList<Baby> house){
     foo = house;
      prepareGUI();
   }

   private void prepareGUI(){
      mainFrame = new JFrame("CSC 420 Group Project");
      mainFrame.setSize(600,600);
      mainFrame.setLayout(new GridLayout(3, 1));
      
      mainFrame.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
 
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
          chartPanelFoo.setPreferredSize(new java.awt.Dimension(700, 900));
          mainFrame.add(chartPanelFoo);
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
}