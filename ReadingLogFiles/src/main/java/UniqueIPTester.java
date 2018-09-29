public class UniqueIPTester {
    public void testUniqIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("E:\\Java_Project\\ReadingLogFiles\\src\\main\\java\\short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + "IPs");
    }

//    public static void main(String[] args){
//        UniqueIPTester instance = new UniqueIPTester();
//        instance.testUniqIP();
//    }
}
