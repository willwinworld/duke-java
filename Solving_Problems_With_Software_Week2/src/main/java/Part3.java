public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int lastIndex = 0;
        int count = 0;
        while (lastIndex != -1){
            lastIndex = stringb.indexOf(stringa, lastIndex);
            if (lastIndex != -1){
                count++;
                lastIndex += stringa.length();
            }
        }
        return count >= 2;
    }

    public String lastPart(String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        if (startIndex == -1){
            return stringb;
        }
        else{
            return stringb.substring(startIndex+stringa.length());
        }
    }

    public void testing(){
        boolean res = twoOccurrences("atg", "ctgtatgta");
        System.out.println(res);
        String string0 = lastPart("an", "banana");
        String string1 = lastPart("zoo", "forest");
        System.out.println(string0);
        System.out.println(string1);
    }

    public static void main(String[] args){
        Part3 part3 = new Part3();
        part3.testing();
    }
}
