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


public class PassengerWaitGraph extends ApplicationFrame {

	private TimeSeries Passenger = new TimeSeries("Passenger Wait Time", Millisecond.class);
	
	public PassengerWaitGraph(final String title){
		super(title);
		PassengerData Wait = new PassengerData();
	    new Thread(Wait).start();
	    
	    TimeSeriesCollection dataset = new TimeSeriesCollection();
	    dataset.addSeries(Passenger);
	    JFreeChart chart = ChartFactory.createTimeSeriesChart(
	            "Average Passenger Wait Time",
	            "Time",
	            "Time Waited",
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
	
	 class PassengerData implements Runnable 
	    {
	     private Random randPassengerData = new Random();

	        public void run() 
	        {
	        	double AveragePassengerWaitTime = 1.0 ;
	        	int passengerWaitTotal = 0;
	        	double count = 1;
	        	
	            while(true) 
	            {
	            	passengerWaitTotal = (passengerWaitTotal + randPassengerData.nextInt(10) );
	            	AveragePassengerWaitTime = passengerWaitTotal / count ;
	                Passenger.addOrUpdate(new Millisecond(), AveragePassengerWaitTime);
	                count++;
	                
	                try {
	                    Thread.sleep(100);
	                    
	                } catch (InterruptedException ex) {
	                    System.out.println(ex);
	                }
	                
	                 
	            }
	        }
	    }
	
    
}
