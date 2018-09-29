import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;


public class Part2 {
    public CSVRecord coldestHourInFile(CSVParser parser){
        // 返回CSVRecord, 最冷的温度信息的那一行, 如果遇到温度是-9999, 应该直接忽略
        float coldestTemperature = Float.MAX_VALUE;
        CSVRecord res = null;
        for (CSVRecord csvRecord : parser){
            if (!csvRecord.get("TemperatureF").equals("-9999")){
                if (Float.parseFloat(csvRecord.get("TemperatureF")) < coldestTemperature){
                    coldestTemperature = Float.parseFloat(csvRecord.get("TemperatureF"));
                    res = csvRecord;
                }
            }
        }
        return res;
    }

    public void testColdestHourInFile(){
        FileResource fr = new FileResource("E:\\Java_Project\\CsvFirstAssignments\\src\\main\\java\\nc_weather\\2014\\weather-2014-01-04.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord res = coldestHourInFile(parser);
        System.out.println(res.get("TemperatureF"));
        System.out.println(res.get("TimeEST"));
    }

    public String fileWithColdestTemperature(){
        // 从很多个files中返回有最低温度的文档的名字
        String basic_path = "E:\\Java_Project\\CsvFirstAssignments\\src\\main\\java\\nc_weather";
        ArrayList<String> selected_files = new ArrayList<String>();
        selected_files.add(basic_path+"\\2014"+"\\weather-2014-01-01.csv");
        selected_files.add(basic_path+"\\2014"+"\\weather-2014-01-02.csv");
        selected_files.add(basic_path+"\\2014"+"\\weather-2014-01-03.csv");

        CSVRecord res = null;
        String fileWithColdestTemperature = "";
        float coldestTemperature = Float.MAX_VALUE;
        for (int i = 0; i < selected_files.size(); i++){
//            System.out.println(selected_files.get(i));
            FileResource fr = new FileResource(selected_files.get(i));
            CSVParser parser = fr.getCSVParser();
            res = coldestHourInFile(parser);
            if (Float.parseFloat(res.get("TemperatureF")) < coldestTemperature){
                fileWithColdestTemperature = selected_files.get(i);
            }
        }
        return fileWithColdestTemperature;
    }

    public void printRowInfo(CSVParser parser){
        for (CSVRecord csvRecord : parser){
            System.out.println(csvRecord.get("DateUTC") + ":" + " " + csvRecord.get("TemperatureF"));
        }
    }


    public void testFileWithColdestTemperature(){
        String fileWithColdestTemperature = fileWithColdestTemperature();
        String year_month_day = fileWithColdestTemperature.split("\\\\")[fileWithColdestTemperature.split("\\\\").length-1];
        System.out.println(year_month_day);
        System.out.println(String.format("Coldest day was in file %s", year_month_day));
        FileResource fr = new FileResource(fileWithColdestTemperature);
        CSVParser parser = fr.getCSVParser();
        CSVParser copyParser = fr.getCSVParser();
        String coldestTemperature = coldestHourInFile(parser).get("TemperatureF");
        System.out.println(String.format("Coldest temperature on that day was %s", coldestTemperature));
        System.out.println("All the Temperatures on the coldest day were:");
        printRowInfo(copyParser);   // parser只能用一次
    }

    public int findLowestHumidity(CSVParser parser){
        int lowestHumidity = Integer.MAX_VALUE;
        for (CSVRecord csvRecord : parser){
            if (!csvRecord.get("Humidity").equals("N/A")){
                if (Integer.parseInt(csvRecord.get("Humidity")) < lowestHumidity){
                    lowestHumidity = Integer.parseInt(csvRecord.get("Humidity"));
                }
            }
        }
        return lowestHumidity;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser, int lowestHumidity){
        CSVRecord res = null;
        for (CSVRecord csvRecord : parser){
            if (Integer.parseInt(csvRecord.get("Humidity")) == lowestHumidity){
                res = csvRecord;
                break;
            }
        }
        return res;
    }

    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource("E:\\Java_Project\\CsvFirstAssignments\\src\\main\\java\\nc_weather\\2014\\weather-2014-01-20.csv");
        CSVParser parser0 = fr.getCSVParser();
        int lowestHumidity = findLowestHumidity(parser0);
        System.out.println(lowestHumidity);
        CSVParser parser1 = fr.getCSVParser();  // parser 用一次就会失效
        CSVRecord csv = lowestHumidityInFile(parser1, lowestHumidity);
        System.out.println(String.format("Lowest Humidity was %s at %s", csv.get("Humidity"), csv.get("DateUTC")));
    }

    public CSVRecord lowestHumidityInManyFiles(){
        String basic_path = "E:\\Java_Project\\CsvFirstAssignments\\src\\main\\java\\nc_weather";
        ArrayList<String> selected_files = new ArrayList<String>();
        selected_files.add(basic_path+"\\2014"+"\\weather-2014-01-19.csv");
        selected_files.add(basic_path+"\\2014"+"\\weather-2014-01-20.csv");

        int fileLowestHumidity = 0;
        String fileWithColdestTemperature = "";
        int lowestHumidity = Integer.MAX_VALUE;
        for (int i = 0; i < selected_files.size(); i++){
            FileResource fr = new FileResource(selected_files.get(i));
            CSVParser parser = fr.getCSVParser();
            fileLowestHumidity = findLowestHumidity(parser);
            if (fileLowestHumidity < lowestHumidity){
                lowestHumidity = fileLowestHumidity;
            }
        }

        CSVRecord res = null;
        for (int i = 0; i < selected_files.size(); i++){
            FileResource fr = new FileResource(selected_files.get(i));
            CSVParser parser = fr.getCSVParser();
            res = lowestHumidityInFile(parser, lowestHumidity);
        }
//        System.out.println(String.format("Lowest Humidity was %s at %s", res.get("Humidity"), res.get("DateUTC")));
        return res;
    }

    public void testLowestHumidityInManyFiles(){
        CSVRecord res = lowestHumidityInManyFiles();
        System.out.println(String.format("Lowest Humidity was %s at %s", res.get("Humidity"), res.get("DateUTC")));
    }

    public double averageTemperatureInFile(CSVParser parser){
        int records_count = 0;
        double total_temperatures = 0.0;
        for (CSVRecord csvRecord : parser){
            total_temperatures += Float.parseFloat(csvRecord.get("TemperatureF"));
            records_count += 1;
        }
        return total_temperatures / records_count;
    }

    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource("E:\\Java_Project\\CsvFirstAssignments\\src\\main\\java\\nc_weather\\2014\\weather-2014-01-20.csv");
        CSVParser parser = fr.getCSVParser();
        System.out.println(String.format("Average temperature in file is %s", averageTemperatureInFile(parser)));
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        int records_count = 0;
        double total_temperatures = 0.0;
        for (CSVRecord csvRecord : parser){
            if (Integer.parseInt(csvRecord.get("Humidity")) >= value){
                total_temperatures += Float.parseFloat(csvRecord.get("TemperatureF"));
                records_count += 1;
            }
        }
        if (records_count == 0){
            return 0.0;
        }else{
            return total_temperatures / records_count;
        }
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr0 = new FileResource("E:\\Java_Project\\CsvFirstAssignments\\src\\main\\java\\nc_weather\\2014\\weather-2014-01-20.csv");
        CSVParser parser0 = fr0.getCSVParser();
        double averageTemperature;
        averageTemperature = averageTemperatureWithHighHumidityInFile(parser0, 80);
        if (averageTemperature == 0.0){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println(String.format("Average Temp when high Humidity is %s", averageTemperature));
        }
        FileResource fr1 = new FileResource("E:\\Java_Project\\CsvFirstAssignments\\src\\main\\java\\nc_weather\\2014\\weather-2014-03-20.csv");
        CSVParser parser1 = fr1.getCSVParser();
        averageTemperature = averageTemperatureWithHighHumidityInFile(parser1, 80);
        if (averageTemperature == 0.0){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println(String.format("Average Temp when high Humidity is %s", averageTemperature));
        }
    }

    public static void main(String[] args){
        Part2 part2 = new Part2();
//        part2.testColdestHourInFile();
//        part2.fileWithColdestTemperature();
//        part2.testFileWithColdestTemperature();
//        part2.testLowestHumidityInFile();
//        part2.testLowestHumidityInFile();
//        part2.testLowestHumidityInManyFiles();
//        part2.testAverageTemperatureInFile();
        part2.testAverageTemperatureWithHighHumidityInFile();
    }
}
