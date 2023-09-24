import java.util.Comparator;

public class Comp implements Comparator<PROCESS> {

    @Override
    public int compare(PROCESS lhs, PROCESS rhs) {
        int res = 0;
        if(rhs.getAT()>lhs.getAT()){
            res = -1;
        }
        else if(rhs.getAT()<lhs.getAT()){
            res = 1;
        }
        else{
            if(rhs.getPriority()>lhs.getPriority()){
                res = -1;
            }
            else if(rhs.getPriority()<lhs.getPriority()){
                res = 1;
            }
        }
        return res;
    }
}
