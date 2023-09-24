import java.util.ArrayList;

public class ReadyQueue {
    private ArrayList<PROCESS> queue;
    public ReadyQueue()
    {
        queue = new ArrayList<>();
    }
    public int size() {
        return queue.size();
    }

    // private boolean contain(PROCESS process){
    //     return queue.contains(process);
    // }


    public PROCESS dequeue()
    {
        PROCESS p = null;
        if (!isEmpty())
        {
            p = queue.get(0);
            queue.remove(p);
        }
        return p;
    }



    public Boolean isEmpty()
    {
        return (queue.size() == 0);
    }



    public PROCESS peek()
    {
        return queue.get(queue.size() - 1);
    }



    public void enqueue(PROCESS process)
    {
        queue.add(process);
    }

    public PROCESS get_FCFS(){
        PROCESS temp = null;
        int min_ = Integer.MAX_VALUE;
        for(PROCESS p : queue){
            if(p.getAT()< min_) {
                temp = p;
                min_ = p.getAT();
            }
        }
        

        return temp;
    }

    public PROCESS get_shortest_jop(){
        PROCESS temp = null;
        int min_ = Integer.MAX_VALUE;
        for(PROCESS p : queue){
            if(p.getBT()< min_) {
                temp = p;
                min_ = p.getBT();
            }
        }
        
        return temp;
    }
    public void remove(PROCESS p) {
        queue.remove(p);
    }
    public PROCESS get_highest_priority(){
        PROCESS temp = queue.get(0);
        int min_ = Integer.MAX_VALUE;
        for(PROCESS p : queue){
            if(p.getPriority()< min_) {
                min_ = p.getPriority();
                temp = p;
            }
        }
        
        return temp;
    }
    public void readyQueue_display(){
        for(PROCESS p : queue){
            System.out.println("BT: " + p.getBT() + " id" + p.get_ID() + " Quantum" + p.getQ());
        }

    }

}
