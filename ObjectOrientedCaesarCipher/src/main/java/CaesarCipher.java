public class CaesarCipher {
    /*public Interface: What it does
    * private Implementation: How it does it
    * In our CaesarCypher example, you want other classes to be able to call encrypt
    * But they should not know about the implementation details, such as the fact you
    * made a variable called shifted alphabet, keeping these details private means that
    * you can change them and be sure that no other code relies on the private implementation details
    * Fields: private Typically part of implementation
    * Methods: depends Things object does: public
    * Helpers: private*/
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        // constructor
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++){
            char c = sb.charAt(i);
            int idx = alphabet.indexOf(c);
            if (idx != -1){
                c = shiftedAlphabet.charAt(idx);
                sb.setCharAt(i, c);
            }
        }

        return sb.toString();
    }

    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
}
