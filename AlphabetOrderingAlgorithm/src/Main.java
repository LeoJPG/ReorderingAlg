public class Main {

    public static void main(String args[]){
        String word = "aabdcaacdaabdbabaabcaacaacab";
        String word2 = "a";
        String s = "zzjhjhzzh";
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
        Mapping mapping = new Mapping();
        String reorderedNoBacktrack = AlphabetReorderingAlg.reorder(s, mapping, false);
        String reorderedWithBacktrack = AlphabetReorderingAlg.reorder(s, mapping, true);
        System.out.println(reorderedNoBacktrack);
        System.out.println(reorderedWithBacktrack);
        System.out.println(LyndonFactorizer.factorize(reorderedNoBacktrack, false));
    }
}
