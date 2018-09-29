import java.util.ArrayList;
import edu.duke.FileResource;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1){
                myWords.add(word);
                myFreqs.add(1);
            }
            else{
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq+1);
            }
        }
    }

    public void tester(){
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        for (String word : myWords){
            int index = myWords.indexOf(word);
            int freq = myFreqs.get(index);
            System.out.println(freq + " " + word);
        }
        System.out.println("The word that occurs most often and its count are: " + myWords.get(findIndexOfMax()) + " " + myFreqs.get(findIndexOfMax()));
    }

    public int findIndexOfMax(){
        int max_value = myFreqs.get(0);
        int max_index = 0;
        for (int i = 0; i < myFreqs.size(); i++){
            if (myFreqs.get(i) > max_value){
                max_value = myFreqs.get(i);
                max_index = i;
            }
        }
        return max_index;
    }

    public static void main(String[] args){
        WordFrequencies object = new WordFrequencies();
        object.tester();
    }
}
