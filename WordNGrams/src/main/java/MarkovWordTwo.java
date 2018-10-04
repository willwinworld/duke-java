import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key = myText[index];
        String key2 = myText[index+1];
        sb.append(key);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key, key2);
//            System.out.println(key + ": " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key2;
            key2 = next;
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int first = indexOf(myText, key1, key2, 0);
        while (first != -1 && first+2 < myText.length){
            follows.add(myText[first+2]);
            first = indexOf(myText, key1, key2, first+key1.length()+1);
        }
        return follows;
    }

    private int indexOf(String[] words, String target1,  String target2, int start){
        for(int i=start; i<words.length && i+1<words.length; i++){
            if (words[i].equals(target1) && words[i+1].equals(target2)){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        MarkovWordOne mo = new MarkovWordOne();
        mo.testIndexOf();
    }
}
