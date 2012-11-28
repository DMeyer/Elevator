package jfreechart;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.BasicButtonListener;

import java.awt.*;
import java.awt.event.*;



public class BasicGui implements ActionListener{
    // Initialize all swing objects.
    private JFrame f = new JFrame("Basic GUI"); //create Frame
    private JPanel pnlNorth = new JPanel(); // North quadrant 
    private JPanel pnlSouth = new JPanel(); // South quadrant
    private JPanel pnlMid = new JPanel();// Mid Quadrant
    
    // Buttons some there is something to put in the panels
    private JButton btnEleDist = new JButton("Elevator Distance Graph");
    private JButton btnPWait = new JButton("Passenger Wait Graph");
    private JButton btnEleSt = new JButton("Elevator Distance Static Graph");
   

    // Menu
    private JMenuBar mb = new JMenuBar(); // Menubar
    private JMenu mnuFile = new JMenu("File"); // File Entry on Menu bar
    private JMenuItem mnuItemQuit = new JMenuItem("Quit"); // Quit sub item
    private JMenu mnuHelp = new JMenu("Help"); // Help Menu entry
    private JMenuItem mnuItemAbout = new JMenuItem("About"); // About Entry

    /** Constructor for the GUI */
    public BasicGui(){
        // Set menubar
        f.setJMenuBar(mb);
        
        //Build Menus
        mnuFile.add(mnuItemQuit);  // Create Quit line
        mnuHelp.add(mnuItemAbout); // Create About line
        mb.add(mnuFile);        // Add Menu items to form
        mb.add(mnuHelp);

        // Add Buttons
        btnEleDist.addActionListener(this);
        btnPWait.addActionListener(this);
        btnEleSt.addActionListener(this);
        pnlNorth.add(btnEleDist);
        pnlSouth.add(btnPWait);
        pnlMid.add(btnEleSt);
        
        
        // Setup Main Frame
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(pnlNorth, BorderLayout.NORTH);
        f.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
        f.getContentPane().add(pnlMid, BorderLayout.CENTER);
   
        
        // Allows the Swing App to be closed
        f.addWindowListener(new ListenCloseWdw());
        
        //Add Menu listener
        mnuItemQuit.addActionListener(new ListenMenuQuit());
        
        // Add button Listener
        
     }
  
    public class ListenMenuQuit implements ActionListener{
        public void actionPerformed(ActionEvent e){
        	
        	
        	System.exit(0);         
        }
    }
    
    
    
    public class ListenCloseWdw extends WindowAdapter{
        public void windowClosing(WindowEvent e){
        		      	
        	
        	System.exit(0);
        }
    }
    
    public void launchFrame(){
        // Display Frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack(); //Adjusts panel to components for display
        f.setVisible(true);
        
       
    }
    
    public static void main(String args[]){
        BasicGui gui = new BasicGui();
        gui.launchFrame();
    }

	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnEleDist )
			f.add(new ElevatorDistanceGraph("Elevator Graph Data"));
		if(arg0.getSource() == btnPWait )
		  f.add(new PassengerWaitGraph("Average Passenger Wait Time"));
		//if(arg0.getSource() == btnEleSt)
		 // f.add(new ElevatorDistanceStat("Elevator Distance");
			
		
	}
}
