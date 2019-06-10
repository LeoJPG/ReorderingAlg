import java.util.*;

public abstract class AlphabetReorderingAlg {


    public static String reorder(String word, Mapping map, boolean LocNoBacktrack){
        //Makes exponent parikh vector
        ExponentParikhVector expParikhVect = new ExponentParikhVector(word);
        //Prints the EPV
        //System.out.println(expParikhVect);
        //factorizes each vector (reverse order)
        ArrayList<CharExpFactPair> factorsEPV = LyndonFactorizer.factorize(expParikhVect);
        //Prints the factors of the Exponent Parikh Vector
        //System.out.println(factorsEPV);
        boolean good = false;
        Collections.sort(factorsEPV);

        for(CharExpFactPair pri : factorsEPV){
            String stringPrefix = beforeChar(word, pri.getCharacter());
            map.reset(stringPrefix.length());
            map.assign(pri.getCharacter());
            ArrayList<String> xs = getXsAfterChar(word, pri.getCharacter());
            good = assignToXs(map, pri, xs);
        }

        return factorsEPV.toString();
    }




//-------------------------------------------------------------------------

    /**
     * Return the prefix of the string s up until character c
     * @param s Reference String
     * @param c Reference character
     * @return Substring of s until character c
     */
    private static String beforeChar(String s, char c){
        int n = s.indexOf(c);
        return s.substring(0, n);
    }

    /**
     * Get all pieces of string that exist between the runs of the char provided
     * (ignoring any prefix to the first occurrence of char)
     */
    private static ArrayList<String> getXsAfterChar(String s, char aChar) {
        ArrayList<String> resultList = new ArrayList<>();
        String[] components =  s.split(aChar + "+");
        resultList.addAll(Arrays.asList(components));
        return resultList;
     }

    /**
     * Choose the leftmost pr with the minimal number of factors
     */
    private CharExpFactPair choosePi(ArrayList<CharExpFactPair> flPrs){
        return flPrs.get(0);
    }

    /**
     * We need to assign ordering to the letters
     * by considering each exponent group (largest to smallest) and by leftmost first
     * @param map a mapping object "map" for storing the ordering
     * @param facts an object CharExpFactPair, that contains Lyndon factors for the exponents of this letter
     * @param Xs the array of string components occurring after each run of the letter in the original string
     * @return boolean that represent if it is inconsistent ("good or bad")
     */
    private static boolean assignToXs(Mapping map, CharExpFactPair facts, ArrayList<String> Xs) {
        boolean good = true;
        for(String fact : facts.getCountFactors()){
            int lf = fact.length();
            List<String> xsBlock = Xs.subList(0, lf);
            Xs = new ArrayList<>(Xs.subList(lf, Xs.size()));
            ExpAndBLock expAndXBlock = new ExpAndBLock(fact, xsBlock);
            // We map each exponent to their set of x blocks
            Map<String, String> expXsDict = new HashMap<String, String>();
            for(Map.Entry<String, String> expXPair: expXsDict.entrySet()){
                String key = expXPair.getKey();
                String value = expXPair.getValue();
                //checks if exponent is in the hashMap
                if(expXsDict.containsKey(key)){
                    expXsDict.replace(key, expXsDict.get(key) + value);
                }
                else{
                    expXsDict.put(key, value);
                }
            }
        }
        return good;
    }

    //Used to iterate over pairs of exponents and its substring block
    private class ExpAndBLock{

        private String exponent;
        private List<String> xBlock;

        public ExpAndBLock(String exponent, List<String> xBlock) {
            this.exponent = exponent;
            this.xBlock = xBlock;
        }

        public String getExponent() {
            return exponent;
        }

        public List<String> getxBlock() {
            return xBlock;
        }
    }

}
