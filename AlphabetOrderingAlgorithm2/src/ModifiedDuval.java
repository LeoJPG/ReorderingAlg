import java.util.ArrayList;
import java.util.List;

public abstract class ModifiedDuval {

    public static ArrayList<String> factor(String s, PartialOrder partialOrder){
        int h = 0;
        char[] chars = s.toCharArray();
        ArrayList<String> resultList = new ArrayList<>();
        while (h < chars.length){
            int i = h;
            int j = h + 1;
            while (j < chars.length && (!partialOrder.hasCharMapped(chars[j], chars[i]) || chars[j] >= chars[i])){
                if(chars[j] == chars[i]){
                    i++;
                }else if(!partialOrder.hasCharMapped(chars[j], chars[i])){
                    partialOrder.assignBiggerThan(chars[j], chars[i]);
                }else{
                    i = h;
                }
                j++;
            }
            while(h <= i){
                resultList.add(new String(chars, h, j-i));
                h += j - i;
            }
        }
        return resultList;
    }
}
