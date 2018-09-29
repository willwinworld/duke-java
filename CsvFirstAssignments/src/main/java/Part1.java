import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Part1 {
    public CSVParser tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        return parser;
    }

    public String countryInfo(CSVParser parser, String country){
        String res = "";
        for (CSVRecord csvRecord : parser){
            String rowCountry = csvRecord.get(0);
            if (country.equals(rowCountry)){
                String exports = csvRecord.get(1);
                String value = csvRecord.get(2);
                res = rowCountry + ": " + exports + ": " + value;
                break;
            }
        }
        if (res.length() == 0){
            return "NOT FOUND";
        }
        else{
            return res;
        }
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord csvRecord : parser){
            String exports = csvRecord.get(1);
            if (exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(csvRecord.get(0));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord csvRecord : parser){
            String exports = csvRecord.get(1);
            if (exports.contains(exportItem)){
                count++;
            }
        }
        return count;
    }

    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord csvRecord : parser){
            if (csvRecord.get(2).length() > amount.length()){
                System.out.println(csvRecord.get(0) + " " + csvRecord.get(2));
            }
        }
    }

    public static void main(String[] args){
        Part1 part1 = new Part1();
        try{
            FileResource fr = new FileResource("E:\\Java_Project\\CsvFirstAssignments\\src\\main\\java\\exports\\exportdata.csv");
            CSVParser parser = fr.getCSVParser();
//            part1.countryInfo(parser, "Germany");
//            part1.listExportersTwoProducts(parser, "gold", "diamonds");
            part1.bigExporters(parser, "$999,999,999");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
