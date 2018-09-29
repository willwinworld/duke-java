import java.util.ArrayList;
import java.util.List;

public class CaesarBreaker {
    public int[] countLetters(String word){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k=0; k < word.length(); k++){
            char ch = Character.toLowerCase(word.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] vals){
        int maxDex = 0;
        for (int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }

    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);  // The location we will assume is where the e was shifted
        int dkey = maxDex - 4;  // we'll find the distance from this location to E, E has index 4, since we start with
        // 0 for A, and then get B, C, D, E for 1, 2, 3, 4, respectively.
        if (maxDex < 4){
            dkey = 26 - (4-maxDex);  // if the maximal index is less than 4, we'll need to wrap around from 26 to find
            // the shift use for E
        }
        return cc.encrypt(encrypted, 26-dkey);
    }

    public String halfOfString(String message, int start){
        StringBuilder result = new StringBuilder();
        StringBuilder copy = new StringBuilder(message);
        List<Integer> retain_index = new ArrayList<Integer>();
        while (start < message.length()){
            retain_index.add(start);
            start += 2;
        }
        System.out.println(retain_index);
        for (int index : retain_index){
            result.append(copy.charAt(index));
        }
        return String.valueOf(result);
    }

    public int getKey(String s){
        int[] frequency = countLetters(s);
        int maxDex = maxIndex(frequency);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        return 26-dkey;
    }

    public String decryptTwoKeys(String encrypted){
        // prints the two keys
        // then returns the decrypted String
        String encryptedFirstCharacter = halfOfString(encrypted, 0);
        String encryptedSecondCharacter = halfOfString(encrypted, 1);
        int keyFirstCharacter = getKey(encryptedFirstCharacter);
        int keySecondCharacter = getKey(encryptedSecondCharacter);
        System.out.println(keyFirstCharacter);
        System.out.println(keySecondCharacter);
        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted, keyFirstCharacter, keySecondCharacter);
    }

    public static void main(String[] args){
        CaesarBreaker instance = new CaesarBreaker();
//        System.out.println(Arrays.toString(instance.countLetters("eeeeeeeeeeeeeeeees")));
//        System.out.println(instance.maxIndex(instance.countLetters("eeeeeeeeeeeeeeeees")));
        System.out.println(instance.halfOfString("Qbkm Zgis", 0));
        System.out.println(instance.halfOfString("Qbkm Zgis", 1));
    }
}
