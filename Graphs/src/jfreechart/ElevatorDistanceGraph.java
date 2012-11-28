package jfreechart;



import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;


import javax.swing.JFrame;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

   
    

	public class ElevatorDistanceGraph extends ApplicationFrame
{
    	 /** The plot. */
       private XYPlot plot;
      
        // Amount of Distance Elevator travels before AutoMainenance
       private int maxDistance = 1000;
       // Number of Elevators In System       
       private int ElevatorCount=5;
 
       // 10 Elevators Initialised 
        private TimeSeries Elevator1 = new TimeSeries("Elevator1", Millisecond.class);
        private TimeSeries Elevator2 = new TimeSeries("Elevator2", Millisecond.class);
        private TimeSeries Elevator3 = new TimeSeries("Elevator3", Millisecond.class);
        private TimeSeries Elevator4 = new TimeSeries("Elevator4", Millisecond.class);
        private TimeSeries Elevator5 = new TimeSeries("Elevator5", Millisecond.class);
        private TimeSeries Elevator6 = new TimeSeries("Elevator6", Millisecond.class);
        private TimeSeries Elevator7 = new TimeSeries("Elevator7", Millisecond.class);
        private TimeSeries Elevator8 = new TimeSeries("Elevator8", Millisecond.class);
        private TimeSeries Elevator9 = new TimeSeries("Elevator9", Millisecond.class);
        private TimeSeries Elevator10 = new TimeSeries("Elevator10", Millisecond.class);
        
       
       
      public ElevatorDistanceGraph(final String title)
       {
       super(title);
       ElevatorData myElevatorData = new ElevatorData();
       new Thread(myElevatorData).start();

        TimeSeriesCollection dataset = createDataSet(); // Dataset for Graph
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
           "Elevator Distance Vs Time",
           "Time",
           "Distance",
           dataset,
           true,
           true,
           false
       );
       XYPlot plot = chart.getXYPlot();
       ValueAxis axis = plot.getDomainAxis();
       axis.setAutoRange(true);
       axis.setFixedAutoRange(40000.0);

       JFrame frame = new JFrame("GraphTest");
       ChartPanel label = new ChartPanel(chart);
       frame.getContentPane().add(label);
            
       
       
       frame.pack();
       frame.setVisible(true);
       }
       
    
    
    private TimeSeriesCollection createDataSet()
    {   	
    	TimeSeriesCollection dataset = new TimeSeriesCollection();
    	
    	switch(ElevatorCount){
    	case 1:   dataset.addSeries(Elevator1); break;
    	case 2:   dataset.addSeries(Elevator1);
    			  dataset.addSeries(Elevator2); break;
    	case 3:   dataset.addSeries(Elevator1);
    			  dataset.addSeries(Elevator2);
		  		  dataset.addSeries(Elevator3); break;
    	case 4:   dataset.addSeries(Elevator1);
		  		  dataset.addSeries(Elevator2);
		  		  dataset.addSeries(Elevator3);
		          dataset.addSeries(Elevator4); break;
    	case 5:   dataset.addSeries(Elevator1);
		  		  dataset.addSeries(Elevator2);
		  		  dataset.addSeries(Elevator3);
		  		  dataset.addSeries(Elevator4);
		  		  dataset.addSeries(Elevator5); break;
    	case 6:   dataset.addSeries(Elevator1);
		  		  dataset.addSeries(Elevator2);
		  		  dataset.addSeries(Elevator3);
		  		  dataset.addSeries(Elevator4);
		  		  dataset.addSeries(Elevator5);
		  		  dataset.addSeries(Elevator6); break;
    	case 7:   dataset.addSeries(Elevator1);
		  		  dataset.addSeries(Elevator2);
		  		  dataset.addSeries(Elevator3);
		  		  dataset.addSeries(Elevator4);
		  		  dataset.addSeries(Elevator5);
		  		  dataset.addSeries(Elevator6); 
		  		  dataset.addSeries(Elevator7); break;
    	case 8:   dataset.addSeries(Elevator1);
    			  dataset.addSeries(Elevator2);
    			  dataset.addSeries(Elevator3);
    			  dataset.addSeries(Elevator4);
			      dataset.addSeries(Elevator5);
			      dataset.addSeries(Elevator6); 
			      dataset.addSeries(Elevator7); 
			      dataset.addSeries(Elevator8);break;
    	case 9:   dataset.addSeries(Elevator1);
    			  dataset.addSeries(Elevator2);
    			  dataset.addSeries(Elevator3);
    			  dataset.addSeries(Elevator4);
    			  dataset.addSeries(Elevator5);
    			  dataset.addSeries(Elevator6); 
    			  dataset.addSeries(Elevator7); 
    			  dataset.addSeries(Elevator8);
    			  dataset.addSeries(Elevator9);break;
    	case 10:   dataset.addSeries(Elevator1);
    			  dataset.addSeries(Elevator2);
    			  dataset.addSeries(Elevator3);
    			  dataset.addSeries(Elevator4);
    		      dataset.addSeries(Elevator5);
    			  dataset.addSeries(Elevator6); 
    			  dataset.addSeries(Elevator7); 
    			  dataset.addSeries(Elevator8);
    			  dataset.addSeries(Elevator9);
    			  dataset.addSeries(Elevator10);break;  	
    	
    	}    	
    	
    
    	return dataset;
    	
    	
    }

   

     class ElevatorData implements Runnable 
    {
     private Random randElevatorData = new Random();

        public void run() 
        {
        	int num1 = 0;
        	int num2 = 0;
        	int num3 = 0;
        	int num4 = 0;
        	int num5 = 0;
        	int num6 = 0;
        	int num7 = 0;
        	int num8 = 0;
        	int num9 = 0;
        	int num10 = 0;
        	
            while(true) 
            {
                num1 = (num1+randElevatorData.nextInt(10))% maxDistance;
                num2 = (num2+randElevatorData.nextInt(10))% maxDistance;
                num3 = (num2+randElevatorData.nextInt(10))% maxDistance;
                num4 = (num2+randElevatorData.nextInt(10))% maxDistance;
                num5 = (num2+randElevatorData.nextInt(10))% maxDistance;
                num6 = (num2+randElevatorData.nextInt(10))% maxDistance;
                num7 = (num2+randElevatorData.nextInt(10))% maxDistance;
                num8 = (num2+randElevatorData.nextInt(10))% maxDistance;
                num9 = (num2+randElevatorData.nextInt(10))% maxDistance;
                num10 = (num2+randElevatorData.nextInt(10))% maxDistance;
                
                Elevator1.addOrUpdate(new Millisecond(), num1);
                Elevator2.addOrUpdate(new Millisecond(), num2);
                Elevator2.addOrUpdate(new Millisecond(), num3);
                Elevator3.addOrUpdate(new Millisecond(), num4);
                Elevator4.addOrUpdate(new Millisecond(), num5);
                Elevator6.addOrUpdate(new Millisecond(), num6);
                Elevator7.addOrUpdate(new Millisecond(), num7);
                Elevator8.addOrUpdate(new Millisecond(), num8);
                Elevator9.addOrUpdate(new Millisecond(), num9);
                Elevator10.addOrUpdate(new Millisecond(), num10);
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
    
     
    
   /* public static void main(String[] args){

    	final Main demo = new Main("ElevatorDistance");
    	demo.pack();
    	RefineryUtilities.centerFrameOnScreen(demo);
    	demo.setVisible(true);

    }*/



	
}
