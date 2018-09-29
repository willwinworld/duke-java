import java.util.*;

public class QuakeSort {
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from){
        int minIdx = from;
        for (int i = from+1; i < quakes.size(); i++){
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()){
                minIdx = i;
            }
        }
        return minIdx;
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in){
        for (int i=0; i<in.size(); i++){
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }
    }

    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "E:\\Java_Project\\SearchingEarthquakeData\\src\\main\\java\\nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        sortByMagnitude(list);
        for (QuakeEntry qe: list){
            System.out.println(qe);
        }
    }
}
