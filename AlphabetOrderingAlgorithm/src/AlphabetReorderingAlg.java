import java.util.*;
import java.util.regex.Pattern;

public abstract class AlphabetReorderingAlg {


    public static String reorder(String word, Mapping map, boolean noBacktrack){
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

        String stringPrefix = "";
        ArrayList<String> xs = new ArrayList<>();
        for(CharExpFactPair pri : factorsEPV){
            stringPrefix = beforeChar(word, pri.getCharacter());
            map.reset(stringPrefix.length());
            map.assign(pri.getCharacter());
            xs = getXsAfterChar(word, pri.getCharacter());
            good = assignToXs(map, pri, xs);
            if(good || noBacktrack){
                break;
            }
            else{
                map.clear();
            }
        }

        if (good || noBacktrack){
            if (!stringPrefix.isEmpty()){
                int n = map.getAlphabetLoc() - 'a';
                map.reset(0);
                reorder(stringPrefix, map, false);
                map.reset(n);
            }
            map.assignAll(word);
            String reordered = map.mapString(word);
            return reordered;
        }
        else{
            char uniqueChar = factorsEPV.get(0).getCharacter();
            map.clear();
            stringPrefix = beforeChar(word, uniqueChar);
            map.reset(stringPrefix.length());
            map.assign(uniqueChar);
            ArrayList<String> Xs = getXsAfterChar(word, uniqueChar);
            assignToXs(map, factorsEPV.get(0), Xs);
            if (!stringPrefix.isEmpty()){
                int n = map.getAlphabetLoc();
                map.reset(0);
                reorder(stringPrefix, map, false);
                map.reset(n);
            }
            map.assignAll(word);
            String reordered = map.mapString(word);
            return reordered;
        }
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
        String[] result = s.split(aChar + "+");
        ArrayList<String> resultList = new ArrayList<>();
        Collections.addAll(resultList,result);
        //If the pattern is at the beginning the first item in the list is an empty string. This gets rid of it
        if(result[0].equals("")){
           resultList = new ArrayList<>(resultList.subList(1, resultList.size()));
        }
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
        for (String fact : facts.getCountFactors()) {
            int lf = fact.length();
            List<String> xsBlock = Xs.subList(0, lf);
            Xs = new ArrayList<>(Xs.subList(lf, Xs.size()));
            List<ExpAndBlock> zippedExpAndXs = new ArrayList<>();
            for (int i = 0; i < fact.length(); i++) {
                int factInt = Integer.parseInt(String.valueOf(fact.charAt(i)));
                zippedExpAndXs.add(new ExpAndBlock(factInt, xsBlock.get(i)));
            }
            // We map each exponent to their set of x blocks
            Map<Integer, String> expXsDict = new HashMap<>();
            for (ExpAndBlock pair : zippedExpAndXs) {
                int exponent = pair.getExponent();
                String xBlock = pair.getxBlock();
                //checks if exponent is in the hashMap
                if (expXsDict.containsKey(exponent)) {
                    expXsDict.replace(exponent, expXsDict.get(exponent) + xBlock);
                } else {
                    expXsDict.put(exponent, xBlock);
                }
            }
            //In reverse numeric order of exponents (greatest first) is organized
            Comparator comparator = new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            };
            //Sorts by key automatically
            TreeMap<Integer, String> treeMap = new TreeMap<>(comparator);
            treeMap.putAll(expXsDict);
            if(treeMap.values().size() == 1){
                for(char x : treeMap.firstEntry().getValue().toCharArray()){
                    map.assign(x);
                }
            }
            else{
                String treeMapArray[] = treeMap.values().toArray(new String[0]);
                for (int i = 1; i < treeMap.values().size(); i++){
                    String laterVal = treeMapArray[i];
                    good = true;
                    if(laterVal.length() > treeMapArray[0].length()){
                        // problem because no longer Lyndon word
                        good = false;
                        break;
                    }
                    else{
                        for(int j = 0; j < treeMapArray[0].length(); j++){
                            char x = treeMapArray[0].charAt(j);
                            char y = laterVal.charAt(j);
                            if (x == y){
                                map.assign(x);
                            }
                            else{
                                map.assign(x);
                                map.assign(y);
                                if (map.lookUp(x) > map.lookUp(y)){
                                    good = false; //inconsistent
                                    // we have made an assignment of difference so can stop
                                }
                                break;
                            }
                        }
                    }
                    if(!good){
                        return false;
                    }
                }
            }

        }
        return good;
    }

    //Used to iterate over pairs of exponents and its substring block
    private static class ExpAndBlock {

        private int exponent;
        private String xBlock;

        public ExpAndBlock(int exponent, String xBlock) {
            this.exponent = exponent;
            this.xBlock = xBlock;
        }

        public int getExponent()  {
            return exponent;
        }

        public String getxBlock() {
            return xBlock;
        }
    }

}
