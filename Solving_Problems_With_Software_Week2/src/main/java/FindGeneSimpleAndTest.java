public class FindGeneSimpleAndTest {
    public String findGeneSimple(String dna){
        // start codon is "ATG"
        // stop codon is "TAA"
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){  // no ATG
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1){ // no TAA
            return "";
        }
        result = dna.substring(startIndex, stopIndex+3);
        return result;
    }

    public void testFindGeneSimple(){

    }
}
