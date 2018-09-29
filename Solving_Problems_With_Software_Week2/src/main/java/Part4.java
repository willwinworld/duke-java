import edu.duke.URLResource;

import java.util.ArrayList;

public class Part4 {
    public ArrayList<String> findWebLinks(){
        ArrayList<String> res = new ArrayList<String>();
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        int count = 0;
        for (String s : ur.lines()){
//            System.out.println(s);
            String lowercase_s = s.toLowerCase();
            if (lowercase_s.contains("youtube.com")){
                System.out.println(lowercase_s);
                int startIndex = lowercase_s.indexOf("\"h");
                int endIndex = lowercase_s.indexOf("\"", startIndex+2);
                res.add(lowercase_s.substring(startIndex, endIndex)+"\"");
                count++;
            }
        }
        return res;
    }

    public static void main(String[] args){
        Part4 part4 = new Part4();
        part4.findWebLinks();
    }
}
