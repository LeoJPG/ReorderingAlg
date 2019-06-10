import java.util.ArrayList;

public class ExponentCountFactors implements Comparable<ExponentCountFactors>{

    private ArrayList<String> epvFactors;

    public ExponentCountFactors(ArrayList<String> epvFactors) {
        this.epvFactors = epvFactors;
    }

    @Override
    public int compareTo(ExponentCountFactors o) {
        if(epvFactors.size() > o.size()){
            return 1;
        }
        else if(epvFactors.size() < o.size()){
            return -1;
        }
        else{
            return 0;
        }
    }

    public int size(){
        return this.epvFactors.size();
    }

    @Override
    public String toString() {
        return epvFactors.toString();
    }
}
