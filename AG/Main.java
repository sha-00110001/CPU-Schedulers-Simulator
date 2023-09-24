import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Please enter number of processes");
        int num=0;
        Scanner scanner = new Scanner(System.in);
            num= scanner.nextInt();

            List<PROCESS> array = new ArrayList<PROCESS>(num);
            for(int i=0;i<num;i++)
            {
                System.out.print("enter process "+ (i+1) +" ID: ");
                Scanner scanner2 = new Scanner(System.in);
                int ID;
               
                ID=scanner2.nextInt();
                
                System.out.print("enter process "+ (i+1) +" Arrival Time: ");
                Scanner scanner3 = new Scanner(System.in);
                int at;
                
                at=scanner3.nextInt();
                
                System.out.print("enter process "+ (i+1) +" Burst Time: ");
                Scanner scanner4 = new Scanner(System.in);
                int bt;
                
                bt=scanner4.nextInt();
                
                System.out.print("enter process "+ (i+1) + " priority: ");
                
                int p;
                Scanner scanner5 = new Scanner(System.in);
                
                p=scanner5.nextInt();

                System.out.print("enter process "+ (i+1) + " Quantum: ");
                
                int Quantum;
                Scanner scanner6 = new Scanner(System.in);
                
                Quantum=scanner6.nextInt();
                
                
                PROCESS process  = new PROCESS(ID,at,bt,p,Quantum);
                array.add(process);
            }
            ArrayList<PROCESS> copy = new ArrayList<PROCESS>(array);
            AG ob = new AG(copy,num);
            ob.Excution();
            System.out.println("List of all processes.");
            for(PROCESS p : array){
                System.out.println(p.toString());
            }
            System.out.println();
            ArrayList<Chart> his = ob.get_Excution_history();
            System.out.print(his.get(0).toString());
            for(int i = 1;i<his.size();i++) {
                System.out.print(" --P" + his.get(i).getProcessID() + "-- |" + his.get(i).getoutTime() +"|");
            }
            System.out.println();
            System.out.println("Completion Time");
            for(PROCESS p : copy){
                int completionTime = ob.get_completion_Time(p, his);
                System.out.println("P" + p.get_ID()+": t = " + completionTime);
            }
            System.out.println("Turn Around Time");
            int avgTurnAround = 0;
            for(PROCESS p : copy){
                int turnAroundTime = ob.getTurnAroundTime(p, his);
                System.out.println("P" + p.get_ID()+": t = " + turnAroundTime);
                avgTurnAround += turnAroundTime;
            }
            avgTurnAround = avgTurnAround / copy.size();
            System.out.println("Waiting Time");
            int avgWait = 0;
            for(PROCESS p : copy){
                int waitingTime = ob.getWaitingTime(p, his);
                System.out.println("P" + p.get_ID()+": t = " + waitingTime);
                avgWait += waitingTime;
            }
            
            avgWait = avgWait / copy.size();
            System.out.println("Average Turn Around Time : " + avgTurnAround);
            System.out.println("Average Waiting Time : " + avgWait);
            System.out.println("Quantum change for each process :");
            int [][] QChanges = ob.getQuantumChange();
            for (int i =0;i<QChanges.length;i++) {
                System.out.print("P" + (i+1) +" : ");
                for (int j = 0;j<QChanges[i].length;j++) {
                    System.out.print(QChanges[i][j]);
                    if(j != QChanges[i].length -1 ){
                        System.out.print(" -> ");
                    }
                }

                System.out.println();
            }
            // ArrayList<PROCESS> copy = new ArrayList<PROCESS>(array);
            // priority_schedule ob = new priority_schedule(copy,num);
            // ob.Excute();
            // System.out.println("List of all processes.");
            // for(PROCESS p : array){
            //     System.out.println(p.toString());
            // }
            // System.out.println();
            // ArrayList<Chart> his = ob.get_Excution_history();
            // System.out.print(his.get(0).toString());
            // for(int i = 1;i<his.size();i++) {
            //     System.out.print(" --P" + his.get(i).getProcessId() + "-- |" + his.get(i).getOutTime() +"|");
            // }
            // System.out.println();
            // System.out.println("Completion Time");
            // for(PROCESS p : copy){
            //     int completionTime = ob.get_completion_Time(p, his);
            //     System.out.println("P" + p.get_ID()+": t = " + completionTime);
            // }
            // System.out.println("Turn Around Time");
            // int avgTurnAround = 0;
            // for(PROCESS p : copy){
            //     int turnAroundTime = ob.getTurnAroundTime(p, his);
            //     System.out.println("P" + p.get_ID()+": t = " + turnAroundTime);
            //     avgTurnAround += turnAroundTime;
            // }
            // avgTurnAround = avgTurnAround / copy.size();
            // System.out.println("Waiting Time");
            // int avgWait = 0;
            // for(PROCESS p : copy){
            //     int waitingTime = ob.getWaitingTime(p, his);
            //     System.out.println("P" + p.get_ID()+": t = " + waitingTime);
            //     avgWait += waitingTime;
            // }
            
            // avgWait = avgWait / copy.size();
            // System.out.println("Average Turn Around Time : " + avgTurnAround);
            // System.out.println("Average Waiting Time : " + avgWait);

        //    double WT[]=ob.get_WT();
        //    double TAT[]=ob.get_TAT();
        //     System.out.println("Process execution order: ");
        //     for(int i=0;i<array.size();i++){
        //         System.out.println("---------------------------------");
        //         System.out.println("Process Name: "+array.get(i).getName());
        //         System.out.println("Waiting Time: "+WT[i]);
        //         System.out.println("TurnAround time: "+TAT[i]);
        //         System.out.println("----------------------------------");
        //     }
        //     System.out.println("Avg Turnaround time: "+ob.get_ATAT());
        //     System.out.println("Avg Waiting Time: "+ob.get_AWT());


            
            // List<PROCESS> temp  = ob.get_arr();
            // System.out.println(temp.size());
            // for(int i = 0 ;i< temp.size();i++){
            //     System.out.println("process number " + (i+1) + "the attributes are:");
            //     System.out.println("name: " + temp.get(i).getName());
            //     System.out.println("arrival time: " + temp.get(i).getAT());
            //     System.out.println("burst time: " + temp.get(i).getBT());
            //     System.out.println("priority: " + temp.get(i).getPriority());
            //     System.out.println("------------------------------------------------");
            // }
        
    }
}


//4  
// 1 0 17 4 7

//2 2 6 7 9

//3 5 11 3 4

//4 15 4 6 6
