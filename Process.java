package Osproject;



import javafx.beans.property.SimpleIntegerProperty;


public class Process {

	public int process_id=0;
	public int priority;
	
	private final SimpleIntegerProperty Process_burst_Time;
    private final SimpleIntegerProperty alrivat_time; 
    private final SimpleIntegerProperty Wait_Time;
    private final SimpleIntegerProperty Turn_Around_Time; 

	Process(int process_id,int alrival_Time,int process_burst_Time,int Wait_Time,int Turn_Around_Time)
   	{super();
   	    
   	    this.process_id=process_id;
   	    this.Process_burst_Time=new SimpleIntegerProperty(process_burst_Time);
   	    this.alrivat_time=new SimpleIntegerProperty(alrival_Time);
   	    this.Wait_Time=new SimpleIntegerProperty(Wait_Time);
   	    this.Turn_Around_Time=new SimpleIntegerProperty(Turn_Around_Time);
   	    
   	}
    
	Process(int process_id,int alrival_Time,int process_burst_Time,int priority,int Wait_Time,int Turn_Around_Time)
   	{super();
   	    
   	    this.process_id=process_id;
   	    this.priority=priority;
   		this.Process_burst_Time=new SimpleIntegerProperty(process_burst_Time);
   	    this.alrivat_time=new SimpleIntegerProperty(alrival_Time);
   	    this.Wait_Time=new SimpleIntegerProperty(Wait_Time);
   	    this.Turn_Around_Time=new SimpleIntegerProperty(Turn_Around_Time);
   	}
    
	
	public int getAlrivat_time() {
		return alrivat_time.get();
	}

public int getProcess_burst_Time() {
	return Process_burst_Time.get();
}
public int getProcess_id() {
	return process_id;
}
public int getWait_Time() {
	return Wait_Time.get();
}
public int getTurn_Around_Time() {
	return Turn_Around_Time.get();
}

public int getPriority() {
	return priority;
}





}
 