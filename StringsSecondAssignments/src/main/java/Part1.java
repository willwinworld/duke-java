//public class Part1 {
//    public int findStopCodon(String dna, int startIndex, String stopCodon){
//        // stop codon could be any of three stop codons "TAA" "TAG" "TGA"
//        // start codon "ATG"
//        // integer parameter named startIndex that represents where the first occurrence of ATG occurs in dna
//        // this method returns the index of the first occurrence of stopCodon that appears past startIndex and is a
//        // multiple of 3 away from startIndex, if there is no such stopCodon, this method returns the length of the
//        // dna strand
//        int dna_length = dna.length();
//        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
//        if (stopIndex == -1){
//            return dna_length;
//        }
//        String res = dna.substring(startIndex, stopIndex+3);
//        if (res.length() % 3 == 0){
//            return stopIndex;
//        }
//        else{
//            return dna_length;
//        }
//    }
//
//    public void testFindStopCodon(){
//        String dna, stopCodon;
//        int startIndex = 0;
//        int res = 0;
//
//        dna = "ATGGCCTAA";
//        stopCodon = "TAA";
//        System.out.println("DNA strand is " + dna);
//        res = findStopCodon(dna, startIndex, stopCodon);
//        System.out.println("Index of first occurrence of stopCodon is " + res);
//
//        dna = "ATGGCCTAG";
//        stopCodon = "TAG";
//        System.out.println("DNA strand is " + dna);
//        res = findStopCodon(dna, startIndex, stopCodon);
//        System.out.println("Index of first occurrence of stopCodon is " + res);
//
//        dna = "ATGGCCTGA";
//        stopCodon = "TGA";
//        System.out.println("DNA strand is " + dna);
//        res = findStopCodon(dna, startIndex, stopCodon);
//        System.out.println("Index of first occurrence of stopCodon is " + res);
//
//        dna = "ATGGCTGA";
//        stopCodon = "TGA";
//        System.out.println("DNA strand is " + dna);
//        res = findStopCodon(dna, startIndex, stopCodon);
//        System.out.println("Index of first occurrence of stopCodon is " + res);
//    }
//
//    public String findGene(String dna){
//        int startIndex = dna.indexOf("ATG");
//        if (startIndex == -1){
//            return "";
//        }
//        int TAA_index = findStopCodon(dna, startIndex, "TAA");
//        int TAG_index = findStopCodon(dna, startIndex, "TAG");
//        int TGA_index = findStopCodon(dna, startIndex, "TGA");
//
//        int minimum = Math.min(TAA_index, Math.min(TAG_index, TGA_index));
//        if (minimum == dna.length()){
//            return "";
//        }else{
//            return dna.substring(startIndex, minimum+3);
//        }
//    }
//
//    public void testFindGene(){
//        String dna, gene;
//
//        dna = "TAAGCCCTC";  // DNA with no "ATG"
//        System.out.println("DNA strand is " + dna);
//        gene = findGene(dna);
//        System.out.println("Gene is " + gene);
//
//        dna = "ATGGCCTCCTAG";  // DNA with "ATG" and one valid stop codon
//        System.out.println("DNA strand is " + dna);
//        gene = findGene(dna);
//        System.out.println("Gene is " + gene);
//
//        dna = "ATGGCCTCCTAACTATAGCCCTGA";  // DNA with "ATG" and multiple valid stop codons
//        System.out.println("DNA strand is " + dna);
//        gene = findGene(dna);
//        System.out.println("Gene is " + gene);
//
//        dna = "ATGGCCCTCGCC";  // DNA with "ATG" and no valid stop codon
//        System.out.println("DNA strand is " + dna);
//        gene = findGene(dna);
//        System.out.println("Gene is " + gene);
//    }
//
//    public void printAllGenes(String dna){
//        int init_ATG_index = dna.indexOf("ATG");
//        String init_dna = dna;
//        if (init_ATG_index == -1){
//            System.out.println("");
//        }
//        else{
//            while (init_ATG_index != -1){
//                String res = findGene(init_dna);
//                System.out.println(res);
//                init_ATG_index = init_dna.indexOf("ATG", init_ATG_index+3);
////                System.out.println(init_ATG_index);
//                init_dna = init_dna.substring(res.length());
//            }
//        }
//    }
//
//    public static void main(String[] args){
//        Part1 part1 = new Part1();
////        part1.testFindStopCodon();
////        part1.testFindGene();
//        part1.printAllGenes("ATGGCCTAA ATGTCCCGCACCTAG ATGGGTCTCACGTGA");
//    }
//}
