import java.util.Vector;


public class Controller extends Thread{

	//time data
	
	private Elevator[] elevators;
	
	public Controller()
	{
		System.out.println("Controller Enabled.");
	}
	
	public void createElevators(int numElevators)
	{
		elevators = new Elevator[numElevators];
		
		for(int i = 0; i < numElevators; i++){
			elevators[i] = new Elevator(30); //number of floors for each elevator = 30
			elevators[i].start();
		}
	}
	
	//Algorithms
	
	private void SCAN(Elevator e)
	{
		//checks if the current floor has a floor request and if it does remove the request
		
		if(e.queue[e.getCurrentFloor()] == true){
			e.queue[e.getCurrentFloor()] = false;
			//open elevator doors
			//e.openDoor();
			System.out.println("Elevator: " + e.getID() + " Let passenger off at floor: " + e.getCurrentFloor());
		}
		if(e.getCurrentFloor() >= e.getMaxFloors()-1){
			e.moveDown();
		}
		if(e.getCurrentFloor() == 0){
			e.moveUp();
		}
		
		//stops elevator if no more floor requests
		boolean hasRequest = false;
		for(int i = 0; i < e.getMaxFloors(); i++){
			if(e.queue[i] == true){
				hasRequest = true;
				break;
			}
		}
		if(hasRequest){
			e.setActive();
		}
		else
			e.setHalted();
		
	}
	
	private Vector<Integer> v = new Vector<Integer>();
	
	public void run()
	{
		v.add(new Integer(8));
		v.add(new Integer(1));
		v.add(new Integer(2));
		v.add(new Integer(17));
		v.add(new Integer(3));
		v.add(new Integer(24));

		//fill array with floor requests
		for(int i = 0; i < v.size(); i++){
			elevators[0].queue[v.get(i)] = true; 
		}
		
		while(true){
			SCAN(elevators[0]);
		}
	}
}