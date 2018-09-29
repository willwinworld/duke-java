public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for (int i=0; i < sb.length(); i++){
            char currChar = sb.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            String shiftedAlphabet;
            if (i % 2 == 0){
                shiftedAlphabet = shiftedAlphabet1;
            }
            else{
                shiftedAlphabet = shiftedAlphabet2;
            }
            if (Character.isLowerCase(currChar) && idx != -1) {
                char newChar = shiftedAlphabet.toLowerCase().charAt(idx);
                sb.setCharAt(i, newChar);
            }
            else if (Character.isUpperCase(currChar) && idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();
    }

    public String decrypt(String input){
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cct.encrypt(input);
    }
}
