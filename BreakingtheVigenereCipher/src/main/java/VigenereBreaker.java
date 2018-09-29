import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        // message: encrypted message
        // whichSlice: indicating the index the slice should start from
        // totalSlices: indicating the length of the key
        StringBuilder s = new StringBuilder();
        for ( int i = whichSlice; i< message.length() ; i+= totalSlices) {
            s.append(message.charAt(i));
        }
        return s.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        // encrypted: a String encrypted that represents the encrypted message
        // klength: a integer that represents the key length
        // mostCommon: indicates the most common character in the language of the message
        // 利用CaesarCracker类， 同样sliceString方法
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i=0; i < klength; i++){
            String s = sliceString(encrypted, i, klength);
            int dkey = cc.getKey(s);
            key[i] = dkey;
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String message = fr.asString();
        int [] key = tryKeyLength(message, 5, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypt = vc.decrypt(message);
        System.out.println(decrypt);
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> ht = new HashSet<String>();
        for (String line : fr.lines()){
            line = line.toLowerCase();
            ht.add(line);
        }
        return ht;
    }

    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        for (String word : message.split("\\W+")){
           if (dictionary.contains(word.toLowerCase())){
               count += 1;
           }
       }
       return count;
    }

    public String mostCommonCharIn(HashSet<String> dictionary){
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (char ch = 'a'; ch <= 'z'; ++ch)
            map.put(String.valueOf(ch), 0);

        for(String word: dictionary) {
            word = word.toLowerCase();
            String[] letters = word.split("");
            for (String letter: map.keySet()) {
                for (String s: letters) {
                    if (s.equals(letter))
                        map.put(letter, map.get(letter)+1);
                }
            }
        }

        int max = 0;
        String result ="";

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            if (max < e.getValue()) {
                max = e.getValue();
                result = e.getKey();
            }
        }

        return result;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int[] wordcount = new int[100];
        for (int i = 0; i < 100; i++){
            int[] key = tryKeyLength(encrypted, i+1, 'e');
            VigenereCipher vc = new VigenereCipher(key);
            String result = vc.decrypt(encrypted);
            wordcount[i] = countWords(result, dictionary);
        }

        int largest = 0;
        int index = 0;
        for (int i = 0; i < 100; i++){
            if (wordcount[i] > largest){
                largest = wordcount[i];
                index = i;
            }
        }

        int trueKey = index + 1;
        char mostCommonChar = mostCommonCharIn(dictionary).charAt(0);
        int[] key = tryKeyLength(encrypted, truekey, mostCommonChar);
        System.out.print("The keys are ");
        for (int i = 0; i < key.length; i++) {
            System.out.print(key[i] + " ");
        }
        System.out.println("\nThe key length is "+key.length);
        VigenereCipher vc = new VigenereCipher(key);
        return vc.decrypt(encrypted);
    }

    public void breakVigenere2(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        FileResource fr2 = new FileResource("E:\\Java_Project\\BreakingtheVigenereCipher\\src\\main\\java\\dictionaries\\English");
        HashSet<String> dictionary = readDictionary(fr2);
        String decrypt = breakForLanguage(message, dictionary);
        System.out.println(decrypt);
    }

    public HashMap<String, String> breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages){
        HashMap<String, String> decryptedMessages = new HashMap<String, String>();
        String language = "";
        int wordcount = 0;
        for (String lang: languages.keySet()) {
            System.out.println("Currently breaking into "+lang);
            String decrypted_string = breakForLanguage(encrypted, languages.get(lang));
            //System.out.println(decrypted_string);
            int count = countWords(decrypted_string, languages.get(lang));
            if (wordcount < count) {
                wordcount = count;
                language = lang;
            }
            //System.out.println(count + " valid words\n");
            System.out.println();
            decryptedMessages.put(lang, decrypted_string);
        }
        System.out.println("The language of this message is " + language);
        System.out.println(wordcount + " valid words\n");
        return decryptedMessages;
    }

    public void breakVigenere3() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File d: dr.selectedFiles()) {
            FileResource fr2 = new FileResource(d.toString());
            HashSet<String> result = new HashSet<String>();
            for (String line: fr2.lines()) {
                line = line.toLowerCase();
                result.add(line);
            }
            languages.put(d.getName(), result);
            //System.out.println("Finished reading "+f.getName());
        }
        HashMap<String, String> decrypted = breakForAllLanguages(message, languages);
        //System.out.println(decrypted.get("English"));
    }

    public static void main(String[] args){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
}
