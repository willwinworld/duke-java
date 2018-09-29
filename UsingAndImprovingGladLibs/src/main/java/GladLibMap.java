import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    private ArrayList<String> categoriesUsed;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "E:\\Java_Project\\UsingAndImprovingGladLibs\\src\\main\\java\\data";

    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        myRandom = new Random();
        categoriesUsed = new ArrayList<String>();
    }

    private void initializeFromSource(String source) {
        ArrayList<String> categories = new ArrayList<String>();
        categories.add("adjective.txt");
        categories.add("noun.txt");
        categories.add("color.txt");
        categories.add("country.txt");
        categories.add("name.txt");
        categories.add("animal.txt");
        categories.add("time.txt");
        categories.add("verb.txt");
        categories.add("fruit.txt");
        for (String fileName : categories){
            ArrayList<String> fileNameList = readIt(source+"/"+fileName);
            myMap.put(fileName, fileNameList);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitue(String label){
        if (myMap.containsKey(label)) {
            ArrayList<String> words = myMap.get(label);
            return randomFrom(words);
        } else if (label.equals("number")) {
            return ""+myRandom.nextInt(50)+5;
        } else {
            return "**UNKNOWN**";
        }
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    private void totalWordsInMap() {
        int total = 0;
        for (String category : myMap.keySet()) {
            total += myMap.get(category).size();
        }
        System.out.println("The total number of words that were possible to pick from: " + total);
    }

    private void totalWordsConsidered() {
        int i = 0;
        int total = 0;
        while (i < categoriesUsed.size()) {
            String category = categoriesUsed.get(i);
            if (!category.equals("number")) {
                total += myMap.get(category).size();
            }
            i++;
        }
        System.out.println("This GladLib used " + categoriesUsed.size() + " categories.");
        System.out.println("The total number of words in these categories that were " +
                "possible to pick from: " + total);
    }
}
