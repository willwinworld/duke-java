import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordPlay {
    public boolean isVowel(char ch){
        char[] charArray = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        if (new String(charArray).indexOf(ch) == -1){
            return false;
        }
        return true;
    }

    public String replaceVowels(String phrase, char ch){
        StringBuilder res = new StringBuilder(phrase);
        for(int i = 0; i < res.length(); i++){
            if (isVowel(res.charAt(i))){
                res.setCharAt(i, ch);
            }
        }
        return res.toString();
    }

    public String emphasize(String phrase, char ch){
        char other;
        if (Character.isUpperCase(ch)){
            other = Character.toLowerCase(ch);
        }
        else{
            other = Character.toUpperCase(ch);
        }
        StringBuilder res = new StringBuilder(phrase);
        for (int i = 0; i < res.length(); i++){
            if (res.charAt(i) == ch || res.charAt(i) == other){
                if (i % 2 == 0){
                    res.setCharAt(i, '*');
                }
                else{
                    res.setCharAt(i, '+');
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args){
        WordPlay instance = new WordPlay();
//        System.out.println(instance.isVowel('a'));
//        String res = instance.replaceVowels("Hello World", '*');
//        System.out.println(res);
        String res1 = instance.emphasize("dna ctgaaactga", 'a');
        System.out.println(res1);
        String res2 = instance.emphasize("Mary Bella Abracadabra", 'a');
        System.out.println(res2);
    }
}
