public class Part3 {
        public int findStopCodon(String dna, int startIndex, String stopCodon){
        // stop codon could be any of three stop codons "TAA" "TAG" "TGA"
        // start codon "ATG"
        // integer parameter named startIndex that represents where the first occurrence of ATG occurs in dna
        // this method returns the index of the first occurrence of stopCodon that appears past startIndex and is a
        // multiple of 3 away from startIndex, if there is no such stopCodon, this method returns the length of the
        // dna strand
        int dna_length = dna.length();
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        if (stopIndex == -1){
            return dna_length;
        }
        String res = dna.substring(startIndex, stopIndex+3);
        if (res.length() % 3 == 0){
            return stopIndex;
        }
        else{
            return dna_length;
        }
    }

    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int TAA_index = findStopCodon(dna, startIndex, "TAA");
        int TAG_index = findStopCodon(dna, startIndex, "TAG");
        int TGA_index = findStopCodon(dna, startIndex, "TGA");

        int minimum = Math.min(TAA_index, Math.min(TAG_index, TGA_index));
        if (minimum == dna.length()){
            return "";
        }else{
            return dna.substring(startIndex, minimum+3);
        }
    }
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
//                init_dna = init_dna.substring(res.length());
//            }
//        }
//    }

    public int countGenes(String dna){
        int init_ATG_index = dna.indexOf("ATG");
        int count = 0;
        String init_dna = dna;
        if (init_ATG_index == -1){
            return count;
        }
        else{
            while (init_ATG_index != -1){
                String res = findGene(init_dna);
                count++;
                init_ATG_index = init_dna.indexOf("ATG", init_ATG_index+3);
                init_dna = init_dna.substring(res.length());
            }
        }
        return count;
    }

    public void testCountGenes(){
        int res = countGenes("ATGGCCTAAATGTCCCGCACCTAGATGGGTCTCACGTGA");
        System.out.println(res);
    }

    public static void main(String[] args){
        Part3 part3 = new Part3();
        part3.testCountGenes();
    }
}
