import edu.duke.FileResource;

import java.util.Arrays;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for(String word : resource.words()){
            int word_length = word.length();
            if (!Character.isLetter(word.charAt(0))){
                word_length -= 1;
            }
            if (!Character.isLetter(word.charAt(word.length()-1))){
                word_length -= 1;
            }
            counts[word_length] += 1;
        }
    }

    public void testCountWordLengths(){
        String fname = "E:\\Java_Project\\JavaProgrammingArraysListsData\\src\\main\\java\\smallHamlet.txt";
        FileResource fr = new FileResource(fname);
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        System.out.println(Arrays.toString(counts));
        System.out.println(indexOfMax(counts));
    }

    public int indexOfMax(int[] values){
        if (values == null || values.length == 0)
            return -1;
        int largest = 0;
        for (int i = 1; i < values.length; i++){
            if (values[i] > values[largest])
                largest = i;
        }
        return largest;
    }

    public static void main(String[] args){
        WordLengths instance = new WordLengths();
        instance.testCountWordLengths();
    }
}
