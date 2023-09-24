import java.lang.Math;

public class PROCESS {

    private int name;
    private int AT;
    private int BT;
    private int priority;
    private int quantum;
    public PROCESS(int n,int at,int bt,int p,int Q)
	{
		this.name=n;
		this.AT=at;
		this.BT=bt;
		this.priority=p;
        this.quantum = Q;
        // this.waiting_time = 0;
	}
    
    public int get_ID() {
        return name;
    }
    public int getQ() {
        return quantum;
    }

    public void setName(int name) {
        this.name = name;
 
    }
    public void set_Q(int quantum) {
        this.quantum = quantum;
 
    }

    public int getAT() {
        return this.AT;
    }

    public void setAT(int aT) {
        this.AT = aT;
    }

    public int getBT() {
        return this.BT;
    }

    public void setBT(int bT) {
        this.BT = bT;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    @Override
    public String toString() {
        return "{" +
                "processID = " + name +
                ", priority = " + priority +
                ", arrivingTime = " + AT +
                ", burstTime = " + BT +
                '}';
    }
    public void reduceTime(int time) {
        if(BT >= time)
            BT = BT - time;
        
        
    }
    
    public int get_ceil25(){
        return (int)Math.ceil(((double)quantum/4));
    }
    public int get_ceil50(){
        return (int)Math.ceil(((double)quantum/2));
    }
    // public void add_waiting(int w){
    //     this.waiting_time += w;
    // }


}
