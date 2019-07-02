import java.util.Map;

public class Main {

    public static void main(String args[]){
        String word = "aabdcaacdaabdbabaabcaacaacab";
        String word2 = "MFLIEKRRKLIQKKANYHSDPTTVFNHLCGSRPATLLLETAEVNKKNNLESIMIVDSAIRVSAVKNSVKITALSENGAEILSILKENPHKKIKFFEKNKSINLIFPSLDNNLDEDKKIFSLSVFDSFRFIMKSVNNTKRTSKAMFFGGLFSYDLISNFESLPNVKKKQKCPDFCFYLAETLLVVDHQKKTCLIQSSLFGRNVDEKNRIKKRTEEIEKKLEEKLTSIPKNKTTVPVQLTSNISDFQYSSTIKKLQKLIQKGEIFQVVPSRKFFLPCDNSLSAYQELKKSNPSPYMFFMQDEDFILFGASPESSLKYDEKNRQIELYPIAGTRPRGRKKDGTLDLDLDSRIELEMRTNHKELAEHLMLVDLARNDLARICEPGSRYVSDLVKVDKYSHVMHLVSKVVGQLKYGLDALHAYSSCMNMGTLTGAPKVRAMQLIAEYEGEGRGSYGGAIGYFTDLGNLDTCITIRSAYVESGVATIQAGAGVVFNSIPEDVKESLNKAQAVINAIKKAHFTMGSS".toLowerCase();
        String s = "zzjhjhzh";
        String s2 = "abcabcdabcaba";
        //Map<String, String> sequences = new ReadFastaToString().read();
        //String wordFasta = new ReadFastaToString();
        char[] order = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        /*
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
        String reorderedNoBacktrack = AlphabetReorderingAlg.reorder(word, mapping, false);
        //mapping = new Mapping();
        //String reorderedWithBacktrack = AlphabetReorderingAlg.reorder(word, mapping, true);
        //System.out.println(reorderedWithBacktrack);
        System.out.println(reorderedNoBacktrack);
        //System.out.println(LyndonFactorizer.factorize(reorderedWithBacktrack, false));
        System.out.println(LyndonFactorizer.factorize(reorderedNoBacktrack, false));
    }
}
