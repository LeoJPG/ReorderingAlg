import java.util.*;

public class PartialOrder {

    private HashMap<Character, HashSet<Character>> nodes;

    public PartialOrder() {
        this.nodes = new HashMap<>();
    }

    /**
     * @return true if it was successfully assigned
     */
    //TODO: improve time complexity
    public void assignBiggerThan(Character character, Character smaller) {
        if(nodes.get(character) != null) {
            nodes.get(character).add(smaller);
        }else {
            HashSet<Character> set = new HashSet<>();
            set.add(smaller);
            nodes.put(character, set);
        }
        /*if (nodes.containsKey(smaller)) {
            for (Character aChar : nodes.get(smaller)) {
                assignBiggerThan(character, aChar);
            }
        }*/
    }

    public boolean hasCharMapped(Character character, Character smaller) {
        HashSet<Character> charSet = nodes.get(character);
        if(charSet != null){
            return charSet.contains(smaller);
        }
        return false;
    }

    public Comparator<Character> getComparator() {
        Comparator<Character> comparator = new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                if (nodes.get(o1).contains(o2)) {
                    return 1;
                } else {
                    return o1.compareTo(o2);
                }
            }
        };
        return comparator;
    }
}
