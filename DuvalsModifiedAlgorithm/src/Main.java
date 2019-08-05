import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Map;

public class Main {

    public static void main(String args[]) {
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
        Map<String, String> sequence = new ReadFastaToString().read(System.getProperty("user.dir") + "\\GCF_000009605.1_ASM960v1_protein.faa");
        for(Map.Entry<String, String> entry : sequence.entrySet()){
            System.out.println("ID: " + entry.getKey());
            ArrayList<String> modifiedFact = ModifiedDuval.factor(entry.getValue(), true);
            System.out.print("Previous factors: ");
            System.out.println(LyndonFactorizer.factorize(entry.getValue(), false).size());
            System.out.print("New factors: ");
            System.out.println(modifiedFact.size());
            System.out.print("Size of string: ");
            System.out.println(entry.getValue().length());
            System.out.println();
        }
        //ModifiedDuval.reorderSequence(sequence);
        //modifiedVSfirstOrder();

    }

    public static void modifiedVSfirstOrder() {
        Map<String, String> sequence = new ReadFastaToString().read(System.getProperty("user.dir") + "\\GCF_000009605.1_ASM960v1_protein.faa");
        String pathname = System.getProperty("user.dir") + "\\ModifiedVS1stAppearance.txt";
        File file = new File(pathname);
        try {
            PrintWriter printWriter = new PrintWriter(file);
            Duval duval = new Duval();
            for(Map.Entry<String, String> entry : sequence.entrySet()){
                printWriter.println(entry.getKey());
                ArrayList<String> modifiedFact = ModifiedDuval.factor(entry.getValue(), false);
                ArrayList<String> firstOrdFact;
                char[] order = duval.getOrderOfAppareance(entry.getValue());
                firstOrdFact = duval.factor(order, entry.getValue().toCharArray());
                printWriter.print(modifiedFact.size());
                printWriter.print(",");
                printWriter.println(firstOrdFact.size());
                printWriter.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
