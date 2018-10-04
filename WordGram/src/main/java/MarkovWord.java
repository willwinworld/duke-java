import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private int myOrder;
    private Random myRandom;

    public MarkovWord(int order){
        myOrder = order;
        myRandom = new Random();
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public String getRandomText(int numWords){
        if(myText == null || myText.length == 0)
            return "";
        StringBuilder res = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);
        WordGram kgram = new WordGram(myText, index, myOrder);
        res.append(kgram.toString()).append(" ");
        for(int i=0; i<numWords-myOrder; ++i){
            ArrayList<String> follows = getFollows(kgram);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            res.append(next).append(" ");
            kgram = kgram.shiftAdd(next);
        }
        return res.toString().trim();
    }

    public int indexOf(String[] words, WordGram target, int start){
        int len = target.length();
        for(int i=start; i<words.length-len; ++i){
            WordGram tmp = new WordGram(words, i, len);
            if(tmp.equals(target))
                return i;
        }
        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram){
        int len = kGram.length();
        ArrayList<String> follows = new ArrayList<String>();
        int index = indexOf(myText, kGram, 0);
        while(index+len<myText.length && index != -1){
            follows.add(myText[index+len]);
            index = indexOf(myText, kGram, index+1);
        }
        return follows;
    }
}
