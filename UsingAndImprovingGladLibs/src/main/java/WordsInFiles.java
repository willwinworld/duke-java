import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> record;

    public WordsInFiles(){
        record = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f.toString());
        String fileName = f.getName();
        for (String word : fr.words()){
            if (!record.keySet().contains(word)){
                record.put(word, new ArrayList<String>());
                record.get(word).add(fileName);
            }
            else{
                ArrayList<String> fileNames = record.get(word);
                if (!fileNames.contains(fileName)){
                    fileNames.add(fileName);
                }
            }
        }
    }

    public void buildWordFileMap(){
        record.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public int maxNumber(){
        int max = 0;
        for (String key : record.keySet()){
            ArrayList<String> fileNames = record.get(key);
            if (fileNames.size() > max){
                max = fileNames.size();
            }
        }
        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> result = new ArrayList<String>();
        for (String key : record.keySet()){
            if (record.get(key).size() == number){
                result.add(key);
            }
        }
        return result;
    }

    public void printFilesIn(String word){
        for (String key : record.keySet()){
            if (key.equals(word)){
                for (String fileName : record.get(key)){
                    System.out.println(fileName);
                }
            }
        }
    }

    public void tester(){
        buildWordFileMap();
        int max = maxNumber();
        ArrayList<String> list = wordsInNumFiles(max);
        System.out.println("The greatest number of files a word appears in is "+max+", and there are "+list.size()+ " such words: ");
        for (int k = 0; k < list.size(); k++) {
            System.out.println(list.get(k)+" ");
        }
        System.out.println("\t");
        for (int k = 0; k < list.size(); k++) {
            printFilesIn(list.get(k));
        }
    }

    public static void main(String[] args){
        WordsInFiles object = new WordsInFiles();
        object.tester();
    }
}
