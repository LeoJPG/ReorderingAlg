public class Main {

    public static void main(String args[]){
        String word = "aabdcaacdaabdbabaabcaacaacab";
        char[] order = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        /*
        Duval duval = new Duval();
        System.out.println(word);
        ExponentParikhVector epv = new ExponentParikhVector(word);
        System.out.println(epv.toString());
        System.out.println();
        System.out.println(LyndonFactorizer.factorize(epv));
        System.out.println();
        System.out.println(duval.factor(order, word.toCharArray()));
        System.out.println(LyndonFactorizer.factorize(word, false));
        //--------------------------------algorithm 1

        /
        */
        Mapping mapping = new Mapping();
        System.out.println(AlphabetReorderingAlg.reorder(word, mapping, false));
    }
}
