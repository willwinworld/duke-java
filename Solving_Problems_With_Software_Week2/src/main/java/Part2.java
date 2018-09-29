public class Part2 {
    public boolean isLowerCase(Character ch){
        return ch >= 'a' && ch <= 'z';
    }

    public boolean isUpperCase(Character ch){
        return ch >= 'A' && ch <= 'Z';
    }

    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        boolean allLowercase = isLowerCase(dna.charAt(0));
        boolean allUppercase = isUpperCase(dna.charAt(0));
        if (allLowercase){
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        if (allUppercase){
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        }
        String res = "";
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1){  // no ATG
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        if (stopIndex == -1){ // no TAA
            return "";
        }
        res = dna.substring(startIndex, stopIndex+3);
        if (res.length() % 3 == 0){
            if (allLowercase){
                res = res.toLowerCase();
            }
            if (allUppercase){
                res = res.toUpperCase();
            }
            return res;
        }
        else{
            return "";
        }
    }

    public void testSimpleGene(){
        String dna, gene;

        dna = "ATCGCCATC";  // DNA with no "ATG"
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);

        dna = "ATGGCCTCC";  // DNA with no "TAA"
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);

        dna = "GCCATCTCG";  // DNA with no "ATG" or "TAA"
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);

        dna = "ATGGCCTAAATC";  // DNA with ATG, TAA and substring between them is a multiple of 3(a gene)
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);

        dna = "ATGGCAGTAA";  // DNA with ATG, TAA and substring between them is not a multiple of 3
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);

        dna = "gatgctataat";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);

        dna = "ATGGGTTAA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene is " + gene);
    }

    public static void main(String[] args){
        Part2 part2 = new Part2();
        part2.testSimpleGene();
    }
}
