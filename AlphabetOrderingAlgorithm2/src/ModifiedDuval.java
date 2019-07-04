import java.util.ArrayList;
import java.util.Objects;

public abstract class ModifiedDuval {

    private static PartialOrder partialOrder;

    public static ArrayList<String> factor(String s){
        partialOrder = new PartialOrder();
        int h = 0;
        ArrayList<ModCharacter> chars = new ArrayList<>();
        for(Character character : s.toCharArray()){
            chars.add(new ModCharacter(character));
        }
        ArrayList<String> resultList = new ArrayList<>();
        while (h < chars.size()){
            int i = h;
            int j = h + 1;
            while (j < chars.size() && (!partialOrder.hasCharMapped(chars.get(j).getaChar(), chars.get(i).getaChar()) || chars.get(j).compareTo(chars.get(i)) >= 0 )){
                if(chars.get(j).equals(chars.get(i))){
                    i++;
                }else if(!partialOrder.hasCharMapped(chars.get(j).getaChar(), chars.get(i).getaChar())){
                    partialOrder.assignBiggerThan(chars.get(j).getaChar(), chars.get(i).getaChar());
                    i = h;
                }else{
                    i = h;
                }
                j++;
            }
            while(h <= i){
                String newString = "";
                for(int x = h; x < h + j - i; x++){
                    newString += chars.get(x).getaChar();
                }
                resultList.add(newString);
                h += j - i;
            }
        }
        System.out.println(partialOrder);
        return resultList;
    }
    private static class ModCharacter implements Comparable{
        char aChar;

        public ModCharacter(char aChar) {
            this.aChar = aChar;
        }

        public char getaChar() {
            return aChar;
        }

        @Override
        public int compareTo(Object o) {
            if(partialOrder.isBiggerThan(aChar, ((ModCharacter)o).getaChar())) {
                return 1;
            } else if(partialOrder.isBiggerThan(((ModCharacter)o).getaChar(), aChar)){
                return -1;
            }else{
                return 0;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ModCharacter that = (ModCharacter) o;
            return aChar == that.aChar;
        }

        @Override
        public int hashCode() {
            return Objects.hash(aChar);
        }
    }
}
