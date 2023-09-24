public class Chart {
    public int processID ;
    public int from;
    public int to;
    private int inTime;
    private int outTime;
    private int Q;
    public Chart(int processID,int from , int to) {
        this.processID = processID;
        this.from = from;
        this.to = to;
        this.Q = -10;

    }
    public Chart(int processID, int inTime, int outTime,int Q) {
        this.processID = processID;
        this.inTime = inTime;
        this.outTime = outTime;
        this.Q = Q;
    }
    @Override
    public String toString() {
        return "|" + this.inTime + "| --P" + this.processID + "-- |" + this.outTime + "|";
    }
    public String toString2() {
        return "| P"+ this.processID+" " + from + " -> "+ to;
    }
    public int getProcessID() {
        return processID;
    }
    public void setProcessID(int processID) {
        this.processID = processID;
    }
    public int getFrom() {
        return from;
    }
    public int getoutTime() {
        return outTime;
    }
    public void setFrom(int from) {
        this.from = from;
    }
    public int getTo() {
        return to;
    }
    public void setTo(int to) {
        this.to = to;
    }
    public void setInTime(int inTime) {
        this.inTime = inTime;
    }
    public void setOutTime(int outTime) {
        this.outTime = outTime;
    }
    public void setQ(int q) {
        Q = q;
    }
    public int getQ() {
        return Q;
    }
}
