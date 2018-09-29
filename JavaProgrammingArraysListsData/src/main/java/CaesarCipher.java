public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i=0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int idx;
            boolean upperCaseChecker = true;
            if (Character.isUpperCase(currChar)){
                idx = alphabet.indexOf(currChar);
            }
            else{
                idx = alphabet.indexOf(Character.toUpperCase(currChar));
                upperCaseChecker = false;
            }
            if (idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                if (upperCaseChecker){
                    encrypted.setCharAt(i, newChar);
                }
                else{
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
        }
        return encrypted.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabetKey1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabetKey2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        for (int i=0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int idx;
            boolean upperCaseChecker = true;
            if (Character.isUpperCase(currChar)){
                idx = alphabet.indexOf(currChar);
            }
            else{
                idx = alphabet.indexOf(Character.toUpperCase(currChar));
                upperCaseChecker = false;
            }
            if (idx != -1){
                char newChar;
                if (i % 2 == 0){
                    newChar = shiftedAlphabetKey1.charAt(idx);
                }
                else{
                    newChar = shiftedAlphabetKey2.charAt(idx);
                }
                if (upperCaseChecker){
                    encrypted.setCharAt(i, newChar);
                }
                else{
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
        }
        return encrypted.toString();
    }

    public void testCaesar(){
        int key = 23;
//        FileResource fr = new FileResource();
//        String message = fr.asString();
        String message = "FIRST LEGION ATTACK EAST FLANK!";
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }

    public static void main(String[] args){
        CaesarCipher instance = new CaesarCipher();
        String res0 = instance.encrypt("First Legion", 17);
        System.out.println(res0);
        String res1 = instance.encryptTwoKeys("First Legion", 23, 17);
        System.out.println(res1);
    }
}
