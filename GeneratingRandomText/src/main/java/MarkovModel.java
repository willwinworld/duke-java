import java.util.ArrayList;
import java.util.Random;


public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int keyLength;

    public MarkovModel(int num) {
        keyLength = num;
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-keyLength);  // 随机生成起始下标
        String key = myText.substring(index, index+keyLength);
        sb.append(key);
        for(int k=0; k < numChars-keyLength; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }

    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        int length = myText.length();
        int keyLength = key.length();
        while (pos < length){
            int index = myText.indexOf(key, pos);
            if (index == -1 || (index >= length - keyLength)){
                break;
            }
            follows.add(String.valueOf(myText.charAt(index + keyLength)));
            pos = index + keyLength;
        }
        return follows;
    }
}
