import java.util.Arrays;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public int hashCode(){
        return this.toString().hashCode();
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        // TODO: Complete this method
        String ret = String.join(" ", myWords);
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if(this.length() != other.length()){
            return false;
        }
        for(int i=0; i<this.length(); ++i){
            if(!this.wordAt(i).equals(other.wordAt(i))){
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        for(int i=1; i<myWords.length; i++){
            out.myWords[i-1] = myWords[i];
        }
        out.myWords[myWords.length-1] = word;
        return out;
    }

    public static void main(String[] args){
         String[] myWords = {"this", "is", "a", "test"};
         WordGram test = new WordGram(myWords, 0, myWords.length);
        WordGram out = test.shiftAdd("yes");
        System.out.println(Arrays.toString(out.myWords));
    }
}