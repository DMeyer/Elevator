import java.util.concurrent.Semaphore;


public class Elevator extends Thread{

	static int tID;
	static int MAX_LOAD_WEIGHT = 2000;
	static int MAX_PEOPLE = 20;
	
	//thread data
	private int ID;
	private Status status;
	
	//floor data
	private Semaphore currentFloorMutex = new Semaphore(1);
	private int maxFloors;
	private int currentFloor;
	private int targetFloor;
	public boolean[] queue;
	
	//elevator physics
	private Door door;
	private int numPeople;
	private int loadWeight;
	
	private enum Door{
		OPEN, CLOSED;
	}
	private enum Status{
		ACTIVE, HALTED, MAINTENCE;
	}
	
	private boolean upFlag;
	
	
	
	public Elevator(int numFloors)
	{
		ID = tID + 1;
		tID++;
		
		status = Status.ACTIVE;
		
		maxFloors = numFloors;
		currentFloor = 0;
		targetFloor = 0;
		upFlag = true;
		queue = new boolean[numFloors];
		for(int i = 0; i < numFloors; i++){
			queue[i] = false;
		}
		
		door = Door.CLOSED;
		loadWeight = 0;
		numPeople = 0;
		
		System.out.println("Elevator Created.");
	}
	
	public int getID()
	{
		return ID;
	}
	
	public boolean getDirection()
	{
		return upFlag;
	}
	public int getMaxFloors()
	{
		return maxFloors;
	}
	public int getCurrentFloor()
	{
		return currentFloor;
	}
	public boolean checkMoveSafty()
	{
		if(door == Door.OPEN || loadWeight > MAX_LOAD_WEIGHT || numPeople > MAX_PEOPLE){
			return false;
		}
		else
			return true;
	}
	
	public void setActive(){
		status = Status.ACTIVE;
	}
	public void setHalted(){
		status = Status.HALTED;
	}
	
	public void setPeople(int num)
	{
		numPeople = num;
	}
	
	public void openDoor()
	{
		door = Door.OPEN;
	}
	
	public void closeDoor()
	{
		door = Door.CLOSED;
	}
	
	public void changeDirection()
	{
		upFlag = !upFlag;
	}
	public void moveUp()
	{
		upFlag = true;
	}
	public void moveDown()
	{
		upFlag = false;
	}
	
	public void run()
	{
		while(true){
			System.out.print("Elevator: " + ID);
			System.out.println(" Floor: " + currentFloor);
			
			if(upFlag && status == Status.ACTIVE){
				currentFloor++;
			}
			else if( !upFlag && status == Status.ACTIVE){
				currentFloor--;
			}
			
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//end of while
	}//end of run
}//end of Elevator