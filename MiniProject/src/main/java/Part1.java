import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
//    public void readOneFile(int year){
//        String fname = "data/yob" + year + ".txt";
//        FileResource fr = new FileResource(fname);
//        CSVParser parser = fr.getCSVParser(false);
//        for (CSVRecord rec : parser){
//            String name = rec.get(0);
//            String gender = rec.get(1);
//        }
//    }
//
//    public void printNames(){
//        FileResource fr = new FileResource();
//        for (CSVRecord rec : fr.getCSVParser(false)){
//            int numBorn = Integer.parseInt(rec.get(2));
//            if (numBorn <= 100){
//                System.out.println("Name " + rec.get(0) +
//                        " Gender " + rec.get(1) +
//                        " Num Born " + rec.get(2));
//            }
//        }
//    }

    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")){
                totalBoys += numBorn;
            }
            else{
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
    }

    public void testTotalBirths (){
        FileResource fr = new FileResource("");
        totalBirths(fr);
    }

    public int getRank(int year, String name, String gender){
        String fname = "E:\\Java_Project\\MiniProject\\src\\main\\java\\us_babynames\\us_babynames_by_year\\yob" + year + ".csv";
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser(false);
        int tempRank = 0;
        int rank = 0;
        for (CSVRecord rec : parser){
           if (rec.get(1).equals(gender)){
               tempRank += 1;
                if (rec.get(0).equals(name)){
                    rank = tempRank;
                    break;
                }
           }
        }
        if (rank == 0){
            return -1;
        }
        return rank;
    }

    public String getName(int year, int rank, String gender){
        String fname = "E:\\Java_Project\\MiniProject\\src\\main\\java\\us_babynames\\us_babynames_by_year\\yob" + year + ".csv";
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser(false);
        int tempRank = 0;
        String name = "NO NAME";
        for (CSVRecord rec : parser){
            if (rec.get(1).equals(gender)){
                tempRank += 1;
                if (rank == tempRank){
                    name = rec.get(0);
                }
            }
        }
        return name;
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(year, rank, gender);
        System.out.println(String.format("%s born in %s would be %s if she was born in %s",name, year, newName, newYear));
    }

    public int yearOfHighestRank(String name, String gender){
        String basic_path = "E:\\Java_Project\\MiniProject\\src\\main\\java\\us_babynames\\us_babynames_test";
        ArrayList<String> selected_files = new ArrayList<>();
        selected_files.add(basic_path+"\\yob2012short.csv");
        selected_files.add(basic_path+"\\yob2013short.csv");
        selected_files.add(basic_path+"\\yob2014short.csv");
        HashMap<Integer, Integer> storage = new HashMap<>();
        for (int i = 0; i < selected_files.size(); i++){
            FileResource fr = new FileResource(selected_files.get(i));
            CSVParser parser = fr.getCSVParser(false);

            boolean found = false;
            int tempRank = 0;
            for (CSVRecord rec : parser){
                if (rec.get(1).equals(gender)){
                    tempRank++;
                    if (rec.get(0).equals(name)){
                        found = true;
                        break;
                    }
                }
            }
            if (!found){
                return -1;
            }
            String[] selected_file_list = selected_files.get(i).split("\\\\");
            String selected_file = selected_file_list[selected_file_list.length-1];
            List<String> allMatches = new ArrayList<>();
            Matcher m = Pattern.compile("\\d+").matcher(selected_file);
            while (m.find()){
                allMatches.add(m.group());
            }
            int year = Integer.parseInt(allMatches.get(0));
            storage.put(year, tempRank);
        }
        System.out.println(storage);
        int minKey = 0;
        int minValue = Integer.MAX_VALUE;
        for (int key : storage.keySet()){
            int value = storage.get(key);
            if (value < minValue){
                minValue = value;
                minKey = key;
            }
        }
        return minKey;
    }

    public double getAverageRank(String name, String gender){
        String basic_path = "E:\\Java_Project\\MiniProject\\src\\main\\java\\us_babynames\\us_babynames_test";
        ArrayList<String> selected_files = new ArrayList<>();
        selected_files.add(basic_path+"\\yob2012short.csv");
        selected_files.add(basic_path+"\\yob2013short.csv");
        selected_files.add(basic_path+"\\yob2014short.csv");
        double totalRank = 0.0;
        for (int i = 0; i < selected_files.size(); i++){
            FileResource fr = new FileResource(selected_files.get(i));
            CSVParser parser = fr.getCSVParser(false);

            boolean found = false;
            int tempRank = 0;
            for (CSVRecord rec : parser){
                if (rec.get(1).equals(gender)){
                    tempRank++;
                    if (rec.get(0).equals(name)){
                        found = true;
                        break;
                    }
                }
            }
            totalRank += tempRank;
            if (!found){
                return -1.0;
            }
        }
        return totalRank / selected_files.size();
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        String basic_path = "E:\\Java_Project\\MiniProject\\src\\main\\java\\us_babynames\\us_babynames_test\\";
        String fileName = "yob" + year + "short.csv";
        String fullPath = basic_path + fileName;
        FileResource fr = new FileResource(fullPath);
        CSVParser parser = fr.getCSVParser(false);
        int totalBirth = 0;
        for (CSVRecord rec : parser){
            if (rec.get(1).equals(gender)){
                if (rec.get(0).equals(name)){
                    break;
                }
                totalBirth += Integer.parseInt(rec.get(2));
            }
        }
        return totalBirth;
    }

    public static void main(String[] args){
//        String s = "aaa";
//        char[] chs = s.toCharArray();
//        System.out.println(s.toCharArray());
//        for (int i = 0; i < s.length(); i++){
//            int index = chs[i] - 'a';
//            System.out.println(index);
        Part1 part1 = new Part1();
//        int res = part1.yearOfHighestRank("xxx", "M");
//        System.out.println(res);
//        double res = part1.getAverageRank("Jacob", "M");
//        System.out.println(res);
        int res = part1.getTotalBirthsRankedHigher(2012, "Ethan", "M");
        System.out.println(res);
    }
}

