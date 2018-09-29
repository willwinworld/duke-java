import edu.duke.FileResource;

public class TestCaesarCipherTwo {
    private String halfOfString (String message, int start) {
        StringBuilder halfString = new StringBuilder();

        for (int k = start; k < message.length(); k += 2) {
            halfString.append(message.charAt(k));
        }
        return halfString.toString();
    }

    private int[] countLetters (String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alphabet.indexOf(ch);
            if (dex != -1) counts[dex]++;
        }
        return counts;
    }

    private int maxIndex (int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]) maxDex = k;
        }
        return maxDex;
    }

    private int getKey (String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int key = maxDex - 4;
        if (maxDex < 4) key = 26 - (4 - maxDex);
        return key;
    }

    private String breakCaesarCipher (String input) {
        String firstHalf = halfOfString(input, 0);
        String secondHalf = halfOfString(input, 1);

        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);

        System.out.println("key1: " + key1);
        System.out.println("key2: " + key2);

        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);

        return cct.decrypt(input);
    }

    public void simpleTests () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cct = new CaesarCipherTwo(17, 3);
        String encrypted = cct.encrypt(message);
        System.out.println("encrypted string: \n" + encrypted);
        String decrypted = cct.decrypt(encrypted);
        System.out.println("decrypted string: \n" + decrypted);
    }
}
