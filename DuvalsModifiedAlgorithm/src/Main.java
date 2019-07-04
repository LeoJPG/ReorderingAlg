import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class Main {

    public static void main(String args[]){
        String word = "aabdcaacdaabdbabaabcaacaacab";
        String word2 = "a";
        //String wordFasta = new ReadFastaToString();


        PartialOrder partialOrder = new PartialOrder();
        /*
        ModifiedDuval.factor(word);
        DefaultDirectedGraph defaultDirectedGraph = new DefaultDirectedGraph(DefaultEdge.class);
        defaultDirectedGraph.addVertex("a");
        defaultDirectedGraph.addVertex("b");
        defaultDirectedGraph.addVertex("c");
        defaultDirectedGraph.addEdge("a", "b");
        defaultDirectedGraph.addEdge("a", "c");
        defaultDirectedGraph.addEdge("b", "c");
        System.out.println(defaultDirectedGraph);
        */
        System.out.println(ModifiedDuval.factor(word));
    }
}
