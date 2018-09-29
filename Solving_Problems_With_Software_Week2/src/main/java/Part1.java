//public class Part1 {
//    public String findSimpleGene(String dna){
//        String res = "";
//        int startIndex = dna.indexOf("ATG");
//        if (startIndex == -1){  // no ATG
//            return "";
//        }
//        int stopIndex = dna.indexOf("TAA", startIndex+3);
//        if (stopIndex == -1){ // no TAA
//            return "";
//        }
//        res = dna.substring(startIndex, stopIndex+3);
//        if (res.length() % 3 == 0){
//            return res;
//        }
//        else{
//            return "";
//        }
//    }
//
//    public void testSimpleGene(){
//        String dna, gene;
//
//        dna = "ATCGCCATC";  // DNA with no "ATG"
//        System.out.println("DNA strand is " + dna);
//        gene = findSimpleGene(dna);
//        System.out.println("Gene is " + gene);
//
//        dna = "ATGGCCTCC";  // DNA with no "TAA"
//        System.out.println("DNA strand is " + dna);
//        gene = findSimpleGene(dna);
//        System.out.println("Gene is " + gene);
//
//        dna = "GCCATCTCG";  // DNA with no "ATG" or "TAA"
//        System.out.println("DNA strand is " + dna);
//        gene = findSimpleGene(dna);
//        System.out.println("Gene is " + gene);
//
//        dna = "ATGGCCTAAATC";  // DNA with ATG, TAA and substring between them is a multiple of 3(a gene)
//        System.out.println("DNA strand is " + dna);
//        gene = findSimpleGene(dna);
//        System.out.println("Gene is " + gene);
//
//        dna = "ATGGCAGTAA";  // DNA with ATG, TAA and substring between them is not a multiple of 3
//        System.out.println("DNA strand is " + dna);
//        gene = findSimpleGene(dna);
//        System.out.println("Gene is " + gene);
//    }
//
//    public static void main(String[] args){
//        Part1 part1 = new Part1();
//        part1.testSimpleGene();
//    }
//}
