public class Main {

    public static void main(String args[]){
        String word = "aabdcaacdaabdbabaabcaacaacab";
        String word2 = "a";
        //String wordFasta = new ReadFastaToString();
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

        PartialOrder partialOrder = new PartialOrder();
        /*
        String reorderedNoBacktrack = AlphabetReorderingAlg.reorder(word2, mapping, false);
        String reorderedWithBacktrack = AlphabetReorderingAlg.reorder(word, mapping, true);
        System.out.println(reorderedNoBacktrack);
        System.out.println(reorderedWithBacktrack);
        System.out.println(LyndonFactorizer.factorize(reorderedNoBacktrack, false));
        System.out.println(LyndonFactorizer.factorize(word, false));
         */

        System.out.println(ModifiedDuval.factor(word, partialOrder));
    }
}
