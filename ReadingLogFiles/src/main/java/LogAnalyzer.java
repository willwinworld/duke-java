import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename){
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()){
            LogEntry logEntry = WebLogParser.parseEntry(line);
            records.add(logEntry);
        }
    }

    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records){
           String ipAddr = le.getIpAddress();
           if (!uniqueIPs.contains(ipAddr)){
               uniqueIPs.add(ipAddr);
           }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num){
        for (LogEntry le : records){
            if (le.getStatusCode() > num){
                System.out.println(le.toString());
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIP = new ArrayList<String>();
        for (LogEntry le : records){
           String str = le.getAccessTime().toString().substring(4, 10);
//            System.out.println(str);
           if (someday.equals(str)){
               String ip = le.getIpAddress();
               if (!uniqueIP.contains(ip)){
                   uniqueIP.add(ip);
               }
           }
        }
        return uniqueIP;
    }

    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIP = new ArrayList<String>();
        for (LogEntry le : records){
            if (low <= le.getStatusCode() && le.getStatusCode() <= high){
                if (!uniqueIP.contains(le.getIpAddress())){
                    uniqueIP.add(le.getIpAddress());
                }
            }
        }
        return uniqueIP.size();
    }

    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for(LogEntry le: records){
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)){
                counts.put(ip, 1);
            }
            else{
                counts.put(ip, counts.get(ip)+1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
        int max = 0;
        for (int count: counts.values()){
            if (count > max){
                max = count;
            }
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
        ArrayList<String> result = new ArrayList<String>();
        int max = mostNumberVisitsByIP(counts);
        for (String key : counts.keySet()){
            if (counts.get(key) == max){
                result.add(key);
            }
        }
        return result;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dateMap){
        String maxDay = "";
        // For each day in dateMap
        for (String date : dateMap.keySet()) {
            // Retrieve its ArrayList of IP addresses
            ArrayList<String> ips = dateMap.get(date);
            // If on first day or if current day has more visits than on maxDay
            if (maxDay.equals("") || ips.size() > dateMap.get(maxDay).size()) {
                maxDay = date;
            }
        }
        return maxDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dateMap, String date){
        // Get IP addresses for given date
        ArrayList<String> ips = dateMap.get(date);

        // Map each IP address to the number of times this IP address visited the website on given date
        HashMap<String, Integer> visits = new HashMap<String, Integer>();
        for (String ip : ips) {
            if (visits.containsKey(ip)) {
                int num = visits.get(ip);
                visits.put(ip, num + 1);
            } else {
                visits.put(ip, 1);
            }
        }

        return iPsMostVisits(visits);
    }

    public void printAll(){
        for (LogEntry le : records){
            System.out.println(le);
        }
    }
}
