/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babynames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Ijmeisner
 */

public class BabyGUI {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JPanel controlPanel;

   public BabyGUI(){
      prepareGUI();
   }
   public static void main(String[] args){
      BabyGUI  babyGUI = new BabyGUI();      
      babyGUI.showTextFieldDemo();
   }
   private void prepareGUI(){
      mainFrame = new JFrame("CSC 420 Group Project");
      mainFrame.setSize(600,1000);
      mainFrame.setLayout(new GridLayout(3, 1));
      
      mainFrame.addWindowListener(new WindowAdapter() {
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
   private void showTextFieldDemo(){
      headerLabel.setText("Baby Names"); 

      JLabel  name1Label= new JLabel("Name 1", JLabel.RIGHT);
      JLabel  name2Label = new JLabel("Name 2", JLabel.CENTER);
      final JTextField name1Text = new JTextField(6);
      final JTextField name2Text = new JTextField(6);      

      JButton compareButton = new JButton("Compare");
      compareButton.addActionListener((ActionEvent e) -> {
          JPanel chartPanelFoo = createChartPanel(name1Text.getText(), name2Text.getText());
          mainFrame.add(chartPanelFoo);
          mainFrame.setVisible(true);
      }); 
      controlPanel.add(name1Label);
      controlPanel.add(name1Text);
      controlPanel.add(name2Label);       
      controlPanel.add(name2Text);
      controlPanel.add(compareButton);
      mainFrame.setVisible(true);  
   }
   
   private JPanel createChartPanel(String name1, String name2) {
        String chartTitle = "Baby Names";
        String xAxisLabel = "Year";
        String yAxisLabel = "Count";

        XYDataset dataset = createDataset(name1, name2);

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        return new ChartPanel(chart);
    }
   
   private XYDataset createDataset(String name1, String name2) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries(name1);
        XYSeries series2 = new XYSeries(name2);
    
        series1.add(1960, 800);
        series1.add(1970, 700);
        series1.add(1980, 750);
        series1.add(1990, 300);
        series1.add(2000, 400);

        series2.add(1960, 300);
        series2.add(1970, 500);
        series2.add(1980, 700);
        series2.add(1990, 400);
        series2.add(2000, 1000);
       
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        
        return dataset;
    }
}