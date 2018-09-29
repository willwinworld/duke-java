import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {
    public void testGetFollows(){
        MarkovOne mo = new MarkovOne();
        mo.setTraining("this is a test yes this is a test.");
        ArrayList<String> res = mo.getFollows("t");
        System.out.println(res);
    }

    public void testGetFollowsWithFile(){
        MarkovOne mo = new MarkovOne();
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        mo.setTraining(st);
        ArrayList<String> res = mo.getFollows("t");
        System.out.println(res.size());
    }

    public static void main(String[] args){
        Tester t = new Tester();
        t.testGetFollows();
//        t.testGetFollowsWithFile();
    }
}
