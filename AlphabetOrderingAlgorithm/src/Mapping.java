import java.util.ArrayList;
import java.util.Objects;

public class Mapping {

    private ArrayList<Letter> letters;
    private int alphabetLoc;


    /**
     * Constructor of mapping
     * @param n n >= 0 is the position in the alphabet from which the assignments should begin
     */
    public Mapping(int n) {
        this.alphabetLoc = 'a' + n;
        this.letters = new ArrayList<>();
    }

    public Mapping(){
        this(0);
    }

    /**
     * Assign the next available alphabet character to the letter,
     * if not already assigned
     * @param character character from the alphabet to be assigned
     */
    public void assign(char character){
        Letter newLetter = new Letter(character);
        if(!letters.contains(newLetter)){
            newLetter.setAlphabetLocation(this.alphabetLoc);
            letters.add(newLetter);
            alphabetLoc += 1;
        }
    }

    /**
     * Unassign all assignments that were after n (not including n)
     * @param letter
     */
    public void unassignUpTo(Letter letter){
        ArrayList<Letter> newLetters = new ArrayList<>();
        int n = letters.indexOf(letter);
        for(Letter aLetter : letters){
            if(aLetter.getAlphabetLocation() <= n){
                newLetters.add(aLetter);
            }
        }
        letters = newLetters; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        alphabetLoc += 1;
    }

    /**
     * Resets the alphabet_loc to position n in alphabet (n is 0-indexed)
     * @param n index
     */
    public void reset(int n){
        alphabetLoc = 'a' + n;
    }

    /**
     * Discard all assignments and reset the alphabetLoc
     */
    public void clear(){
        letters.clear();
        this.reset(0);
    }


    /**
     * Translate the given string using the current mapping
     * @return The translated string
     */
    public String mapString(String string){
        StringBuilder out = new StringBuilder();
        for(char character : string.toCharArray()){
            if (!letters.contains(new Letter(character))) {
                this.assign(character);
            }
            Letter newLetter = letters.get(letters.indexOf(new Letter(character)));
            out.append(newLetter.getLetter());
        }
        return out.toString();
    }

    /**
     * Assign all letters in the string if they don't already have assignments
     */
    public void assignAll(String string){
        for(Character character : string.toCharArray()){
            assign(character);
        }
    }

    public int lookUp(char x){
        Letter lookLetter =  new Letter(x);
        if(letters.contains(lookLetter)){
            return letters.get(letters.indexOf(lookLetter)).getAlphabetLocation();
        }
        else{
            return (char) -1;
        }
    }

    public int getAlphabetLoc() {
        return alphabetLoc;
    }

    @Override
    public String toString() {
        return "Mapping{" +
                "letters=" + letters +
                ", alphabetLoc=" + alphabetLoc +
                '}';
    }

    //---------------Letter class------------------------------------------------
    private class Letter{
        char letter;
        int alphabetLocation;

        Letter(char letter) {
            this.letter = letter;
            this.alphabetLocation = -1; // -1 is used to represent that the current letter of the alphabet has not been assigned to a position yet
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Letter letter1 = (Letter) o;
        return letter == letter1.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter);
    }

    public void setAlphabetLocation(int alphabetLocation) {
        this.alphabetLocation = alphabetLocation;
    }

    public char getLetter() {
        return letter;
    }

    public int getAlphabetLocation() {
        return alphabetLocation;
    }
}
//---------------------------------------------------------------------------
}
