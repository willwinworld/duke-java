import edu.duke.FileResource;


public class TestCaesarCipher {
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

    private int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int key = maxDex - 4;
        if (maxDex < 4)
            key = 26 - (4 - maxDex);
        return key;
    }

    public String breakCaesarCipher(String input){
        int key = getKey(input);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(input);
    }

    public void simpleTests(){
        String fname = "";
        FileResource fr = new FileResource(fname);
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(fr.toString());
        System.out.println("encrypted string: \n" + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("decrypted string: \n" + decrypted);
    }
}
