import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> codonsCount;

    public CodonCount(){
        codonsCount = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna){
        codonsCount.clear();
        dna = dna.trim().substring(start);
//        System.out.println(dna);
        for (int i = 0; i < dna.length(); i = i + 3){
            if (i+3 <= dna.length()){
                String codon = dna.substring(i, i+3);
//                System.out.println(codon);
                if (codonsCount.containsKey(codon)){
                    int count = codonsCount.get(codon);
                    count += 1;
                    codonsCount.put(codon, count);
                }
                else{
                    codonsCount.put(codon, 1);
                }
            }
        }
    }

    public String getMostCommonCodon(){
        int maxCount = 0;
        String largestCountCodon = "";
        for (String key : codonsCount.keySet()){
            if (codonsCount.get(key) > maxCount){
                maxCount = codonsCount.get(key);
                largestCountCodon = key;
            }
        }
        return largestCountCodon;
    }

    public void printCodonCounts(int start, int end){
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
        for (String key : codonsCount.keySet()){
            int count = codonsCount.get(key);
            if (start <= count && count <= end){
                System.out.println(key + " " + count) ;
            }
        }
    }

    public void tester(){
        buildCodonMap(0, "CGTTCAAGTTCAA");
        int uniqueCount0 = 0;
        for (String key : codonsCount.keySet()){
            uniqueCount0 += 1;
        }
        int startIndex0 = 0;
        System.out.println("Reading frame starting with " + startIndex0 + " results in " + uniqueCount0 + " unique codons");
        System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + codonsCount.get(getMostCommonCodon()));
        int start0 = 1;
        int end0 = 5;
        printCodonCounts(start0, end0);

        buildCodonMap(1, "CGTTCAAGTTCAA");
        int uniqueCount1 = 0;
        for (String key : codonsCount.keySet()){
            uniqueCount1 += 1;
        }
        int startIndex1 = 1;
        System.out.println("Reading frame starting with " + startIndex1 + " results in " + uniqueCount1 + " unique codons");
        System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + codonsCount.get(getMostCommonCodon()));
        int start1 = 1;
        int end1 = 5;
        printCodonCounts(start1, end1);

        buildCodonMap(2, "CGTTCAAGTTCAA");
        int uniqueCount2 = 0;
        for (String key : codonsCount.keySet()){
            uniqueCount2 += 1;
        }
        int startIndex2 = 2;
        System.out.println("Reading frame starting with " + startIndex2 + " results in " + uniqueCount2 + " unique codons");
        System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + codonsCount.get(getMostCommonCodon()));
        int start2 = 1;
        int end2 = 5;
        printCodonCounts(start2, end2);
    }

    public static void main(String[] args){
        CodonCount object = new CodonCount();
        object.tester();
    }
}
