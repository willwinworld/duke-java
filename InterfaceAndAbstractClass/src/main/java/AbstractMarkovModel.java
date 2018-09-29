
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    /*
    抽象类不用去实现所有的接口方法, 接口中有setTraining, getRandomText, setRandom三个方法
    而在AbstractMarkovModel中不用全部实现这三个方法，但是在继承的子类中必须要实现没有实现的方法
    */
    protected String myText;
    protected Random myRandom;
    protected int myOrder;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public String toString(){
        return "MarkovModel of order " + myOrder;
    }

    abstract public String getRandomText(int numChars);  // 抽象方法
    // 在抽象类中的成员方法可以包括一般方法和抽象方法。抽象方法就是以abstract修饰的方法，这种方法只声明返回的数据类型、方法名称和所需的参数，没
    //有方法体，也就是说抽象方法只需要声明而不需要实现。当一个方法为抽象方法时，意味着这个方法必须被子类的方法所重写，否则其子类的该方法仍然是
    //abstract的，而这个子类也必须是抽象的，即声明为abstract。抽象类中不一定包含抽象方法，但是包含抽象方法的类一定要被声明为抽象类。
    // 抽象类本身不具备实际的功能，只能用于派生其子类。抽象类中可以包含构造方法， 但是构造方法不能被声明为抽象。

    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int len = key.length();
        int index = myText.indexOf(key);
        while (index + len < myText.length() && index != -1){
            follows.add(myText.substring(index+len, index+len+1));
            index = myText.indexOf(key, index+1);
        }
        return follows;
    }
}
