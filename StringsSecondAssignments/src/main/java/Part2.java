public class Part2 {
    public int howMany(String stringa, String stringb){
        int lastIndex = 0;
        int count = 0;
        while (lastIndex != -1){
            lastIndex = stringb.indexOf(stringa, lastIndex);
            if (lastIndex != -1){
                count++;
                lastIndex += stringa.length();
            }
        }
        return count;
    }

    public void testHowMany(){
        int res;

        res = howMany("GAA", "ATGAACGAATTGAATC");
        System.out.println(res);

        res = howMany("AA", "ATAAAA");
        System.out.println(res);
    }

    public static void main(String[] args){
        Part2 part2 = new Part2();
        part2.testHowMany();
    }
}
