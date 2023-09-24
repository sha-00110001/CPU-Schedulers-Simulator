import java.util.ArrayList;
import java.util.List;

public class AG {
    int arrsize;
    List<PROCESS> processes;
    ReadyQueue readyQueue;
    ArrayList<Chart> ExcutionHistory;
    int [][] quantumChange;
    public AG(List<PROCESS> processes,int n) {
        this.arrsize=n;
        this.processes = processes;
        this.readyQueue = new ReadyQueue();
        ExcutionHistory = new ArrayList<Chart>();
        quantumChange = new int[arrsize][1];
        for(int i= 0; i < arrsize; i++){
            quantumChange[i][0] = processes.get(i).getQ();
        }
    }
    // add value to array 
    private static int[] addX(int n, int arr[], int x)
    {
        int i;
    
        // create a new array of size n+1
        int newarr[] = new int[n + 1];
        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;
    
        return newarr;
    }
    // add process from list to ready queue
    private void AddToReady(List<PROCESS> processes_copy,int currentTime){
        while( processes_copy.size() >0 && processes_copy.get(0).getAT() <= currentTime){
            this.readyQueue.enqueue(processes_copy.get(0));
            processes_copy.remove(0);
        }
    }
    public void Excution(){
        ArrayList<PROCESS> procCopy = new ArrayList<PROCESS>(this.processes);
        int currentTime = procCopy.get(0).getAT();
        int statue = 0;
        AddToReady(procCopy,currentTime);
        int inTime = currentTime,outTime = currentTime;

        while(!readyQueue.isEmpty()){ 
            PROCESS inExcution = null;
            if(statue == 0){
                inExcution = readyQueue.get_FCFS();
                readyQueue.remove(inExcution);
            }
            else if(statue == 1){
                inExcution = readyQueue.get_highest_priority();
                readyQueue.remove(inExcution);

            }
            else if(statue == 2){
                inExcution = readyQueue.get_shortest_jop();
                readyQueue.remove(inExcution);

            }
            
            //till 25%
            if(inExcution.get_ceil25() >= inExcution.getBT()){
                inTime = currentTime;
                currentTime =currentTime+ inExcution.getBT();
                AddToReady(procCopy,currentTime);
                outTime = currentTime;
                statue = 0;
                quantumChange[inExcution.get_ID()-1] = addX(quantumChange[inExcution.get_ID()-1].length,quantumChange[inExcution.get_ID()-1],0);
                this.ExcutionHistory.add(new Chart(inExcution.get_ID(),inTime, outTime,0));
                //this.ExcutionHistory.add(new Chart(inExcution.get_ID(),inExcution.getQ(),0));
                continue;
            }
            //FCFS 25%
            int initTime = currentTime;
            inTime = currentTime;
            currentTime =currentTime+ inExcution.get_ceil25();
            AddToReady(procCopy,currentTime);
            inExcution.reduceTime(currentTime - inTime );
            outTime = currentTime ;
            
            if(readyQueue.isEmpty()){
                break;
            }
            
            if (inExcution.getPriority()<=readyQueue.get_highest_priority().getPriority()) {
                if(inExcution.get_ceil25() >= inExcution.getBT()){
                    
                    currentTime =currentTime+ inExcution.getBT();
                    AddToReady(procCopy,currentTime);
                    outTime = currentTime;
                    statue = 0;
                    quantumChange[inExcution.get_ID()-1] = addX(quantumChange[inExcution.get_ID()-1].length,quantumChange[inExcution.get_ID()-1],0);
                    this.ExcutionHistory.add(new Chart(inExcution.get_ID(),inTime, outTime,0));
                    continue;
                }
                else{
                    inTime = currentTime;
                    currentTime =currentTime- inExcution.get_ceil25();
                    currentTime = currentTime + inExcution.get_ceil50();
                    outTime = currentTime;
                    inExcution.reduceTime(currentTime- inTime);
                    AddToReady(procCopy,currentTime);
                }
            }
            else if(inExcution.getPriority()>readyQueue.get_highest_priority().getPriority()){
                outTime = currentTime;
                this.ExcutionHistory.add(new Chart(inExcution.get_ID(),initTime, outTime,inExcution.getQ() + (int)Math.ceil((inExcution.getQ()-inExcution.get_ceil25())/2)));
                quantumChange[inExcution.get_ID()-1] = addX(quantumChange[inExcution.get_ID()-1].length,quantumChange[inExcution.get_ID()-1],inExcution.getQ() + (int)Math.ceil((inExcution.getQ()-inExcution.get_ceil25())/2));
                inExcution.set_Q(inExcution.getQ() + (int)Math.ceil((inExcution.getQ()-inExcution.get_ceil25())/2));
                readyQueue.enqueue(inExcution);
                statue = 1;
                continue;
            }

            if(inExcution.getBT()> readyQueue.get_shortest_jop().getBT()){
                outTime = currentTime;
                quantumChange[inExcution.get_ID()-1] = addX(quantumChange[inExcution.get_ID()-1].length,quantumChange[inExcution.get_ID()-1],inExcution.getQ()+(inExcution.getQ() - inExcution.get_ceil50()));
                inExcution.set_Q(inExcution.getQ()+(inExcution.getQ() - inExcution.get_ceil50()));
                this.ExcutionHistory.add(new Chart(inExcution.get_ID(),initTime, outTime,inExcution.getQ()+(inExcution.getQ() - inExcution.get_ceil50())));
                readyQueue.enqueue(inExcution);
                statue = 2;
                continue;
            }
            else {
                for(int i =0 ; i<procCopy.size();i++){
                    if (procCopy.get(i).getAT() >= inExcution.getAT() 
                    && procCopy.get(i).getAT() < (inExcution.get_ceil50() + currentTime) && procCopy.get(i).getBT() <= inExcution.getBT()){
                        readyQueue.enqueue(procCopy.get(i));
                        procCopy.remove(procCopy.get(i));
                        i--;
                    }
                    else if (procCopy.get(i).getAT() >= inExcution.getAT() && procCopy.get(i).getAT() < (inExcution.get_ceil50() + currentTime) && procCopy.get(i).getBT() < inExcution.getBT()){
                        inTime = currentTime;
                        currentTime = procCopy.get(i).getAT();
                        inExcution.reduceTime(currentTime - inTime);
                        outTime = currentTime;
                        readyQueue.enqueue(inExcution);
                        this.ExcutionHistory.add(new Chart(inExcution.get_ID(),inTime, outTime));
                        readyQueue.enqueue(procCopy.get(i));
                        procCopy.remove(procCopy.get(i));
                        break;
                    }
                    
                }
            }
        }
    }

    public int get_completion_Time(PROCESS p, ArrayList<Chart> hist){
        int completionTime = 0;
        for(Chart c : hist){
            if(c.getProcessID() == p.get_ID())
                completionTime = c.getoutTime();
        }
        return completionTime;
    }
    public int getTurnAroundTime(PROCESS p, ArrayList<Chart> hist) {
        int completionTime = this.get_completion_Time(p,hist);
        return completionTime-p.getAT();
    }
    public int getWaitingTime(PROCESS p, ArrayList<Chart> hist) {
        int turnAroundTime = this.getTurnAroundTime(p,hist);
        return turnAroundTime-p.getBT();
    }
    public int [][] getQuantumChange(){
        return this.quantumChange;
    }
    public ArrayList<Chart> get_Excution_history(){
        return ExcutionHistory;
    }

}
