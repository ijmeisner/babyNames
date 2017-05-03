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
   /*
   public static void main(String[] args){
      BabyGUI  babyGUI = new BabyGUI();      
      babyGUI.showTextFieldDemo();
   }
*/
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

      JLabel  name1Label= new JLabel("Name 1", JLabel.RIGHT);
      JLabel  name2Label = new JLabel("Name 2", JLabel.CENTER);
      final JTextField name1Text = new JTextField(6);
      final JTextField name2Text = new JTextField(6);      

      JButton compareButton = new JButton("Compare");
      compareButton.addActionListener((ActionEvent e) -> {
          XYDataset dataset = createDataset(foo, name1Text.getText(), name2Text.getText());
          JPanel chartPanelFoo = createChartPanel(dataset);
          chartPanelFoo.setPreferredSize(new java.awt.Dimension(700, 900));
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
   
   private JPanel createChartPanel(XYDataset dataset) {
        String chartTitle = "Baby Names";
        String xAxisLabel = "Year";
        String yAxisLabel = "Count";

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        return new ChartPanel(chart);
    }
   
   private XYDataset createDataset(ArrayList<Baby> foo, String name1, String name2) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries(name1);
        XYSeries series2 = new XYSeries(name2);
        
        for(int i=0; i < foo.size(); i++){
          if(name1.equals(foo.get(i).getName())){
            series1.add(foo.get(i).getYear(), foo.get(i).getCount());
            System.out.println(foo.get(i).getYear());
          } else if(name2.equals(foo.get(i).getName())){
            series2.add(foo.get(i).getYear(), foo.get(i).getCount());
          }
        }
       
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        
        return dataset;
    }
}