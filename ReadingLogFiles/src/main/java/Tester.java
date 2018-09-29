import java.util.*;


public class Tester {
    public void testLogEntry(){
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("E:\\Java_Project\\ReadingLogFiles\\src\\main\\java\\short-test_log");
        la.printAll();
    }

    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("E:\\Java_Project\\ReadingLogFiles\\src\\main\\java\\short-test_log");
        la.countUniqueIPs();
    }

    public void testprintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("E:\\Java_Project\\ReadingLogFiles\\src\\main\\java\\short-test_log");
        la.printAllHigherThanNum(200);
    }

    public void testuniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("E:\\Java_Project\\ReadingLogFiles\\src\\main\\java\\weblog-short_log");
        System.out.println(la.uniqueIPVisitsOnDay("Sep 14").toString());
        System.out.println(la.uniqueIPVisitsOnDay("Sep 30").toString());
    }

    public void testcountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("E:\\Java_Project\\ReadingLogFiles\\src\\main\\java\\short-test_log");
        System.out.println(la.countUniqueIPsInRange(200, 299));
        System.out.println(la.countUniqueIPsInRange(300, 399));
    }

    public static void main(String[] args){
        Tester tester = new Tester();
//        tester.testLogAnalyzer();
//        tester.testuniqueIPVisitsOnDay();
        tester.testcountUniqueIPsInRange();
    }
}
