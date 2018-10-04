
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
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
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
//            System.out.println(key + ": " + follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int pos = 0;
	    while (pos < myText.length){
	        int start = indexOf(myText, key, pos);
	        if (start == -1 || start + key.length() >= myText.length){
	            break;
            }
            String next = myText[start+1];
	        follows.add(next);
	        pos = start + key.length() + 1;
        }
        return follows;
    }

    private int indexOf(String[] words, String target, int start){
        for (int i = start; i < words.length; i++){
            if (words[i].equals(target)){
                return i;
            }
        }
        return -1;
    }

    public void testIndexOf(){
        String[] words = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
        //                  0       1     2      3      4      5      6       7    8     9          10
        int res0 = indexOf(words, "this", 0);
        int res1 = indexOf(words, "this", 3);
        int res2 = indexOf(words, "frog", 0);
        int res3 = indexOf(words, "frog", 5);
        int res4 = indexOf(words, "simple", 2);
        int res5 = indexOf(words, "test", 5);
        System.out.println(res0);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
        System.out.println(res5);
    }

    public static void main(String[] args){
        MarkovWordOne mo = new MarkovWordOne();
        mo.testIndexOf();
    }
}
