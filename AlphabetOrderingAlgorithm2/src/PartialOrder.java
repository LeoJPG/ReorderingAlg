import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;

public class PartialOrder {

    private DefaultDirectedGraph<Character, DefaultEdge> nodes;

    public PartialOrder() {
        this.nodes = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    /**
     * @return true if it was successfully assigned
     */
    //TODO: improve time complexity
    public void assignBiggerThan(Character character, Character smaller) {
        if(!nodes.containsVertex(character)){
            nodes.addVertex(character);
        }
        if(!nodes.containsVertex(smaller)){
            nodes.addVertex(smaller);
        }
    }

    public boolean hasCharMapped(Character character){
        BreadthFirstIterator<Character, DefaultEdge> breadthFirstIterator = new BreadthFirstIterator<>(nodes);
        while(breadthFirstIterator.hasNext()){
            Character aChar = breadthFirstIterator.next();
            if(aChar == character){
                return true;
            }
        }
        return false;
    }
}
