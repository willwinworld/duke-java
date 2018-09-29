import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub

    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData){
            if (qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData){
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        int count = 0;
        for (QuakeEntry qe : list){
            if (qe.getMagnitude() > 5.0){
                System.out.println(qe.getLocation() + ", " + "mag = " + qe.getMagnitude() + ", " + "depth = " + qe.getDepth() + ", " + "title = " + qe.getInfo());
                count++;
            }
        }
        System.out.println("Found " + count + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
//        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);

        // TODO
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData){
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double minDepth = -10000.0;
        double maxDepth = -5000.0;
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        ArrayList<QuakeEntry> result = filterByDepth(list, minDepth, maxDepth);
        int count = 0;
        for (QuakeEntry qe : result){
            System.out.println(qe.getLocation() + ", " + "mag = " + qe.getMagnitude() + ", depth = " + qe.getDepth() + ", title = " + qe.getInfo());
            count++;
        }
        System.out.println("Found " + count + " quakes that match that criteria");
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> res = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData){
            String title = qe.getInfo();
            if (title.contains(phrase)){
                if (where.equals("start")){
                    if (title.startsWith(phrase)){
                        res.add(qe);
                    }
                }
                else if (where.equals("any")){
                    if (title.contains(phrase)){
                        res.add(qe);
                    }
                }
                else if (where.equals("end")){
                    if (title.endsWith(phrase)){
                        res.add(qe);
                    }
                }
            }
        }
        return res;
    }

    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        String phrase0 = "California";
        ArrayList<QuakeEntry> result = filterByPhrase(list, "end", phrase0);
        int count0 = 0;
        for (QuakeEntry qe : result){
            System.out.println(qe.getLocation() + ", " + "mag = " + qe.getMagnitude() + ", depth = " + qe.getDepth() + ", title = " + qe.getInfo());
            count0++;
        }
        System.out.println("Found " + count0 + " quakes that match " + phrase0);

        String phrase1 = "Can";
        ArrayList<QuakeEntry> result1 = filterByPhrase(list, "any", phrase1);
        int count1 = 0;
        for (QuakeEntry qe : result1){
            System.out.println(qe.getLocation() + ", " + "mag = " + qe.getMagnitude() + ", depth = " + qe.getDepth() + ", title = " + qe.getInfo());
            count1++;
        }
        System.out.println("Found " + count1 + " quakes that match " + phrase1);

        String phrase2 = "Explosion";
        ArrayList<QuakeEntry> result2 = filterByPhrase(list, "start", phrase2);
        int count2 = 0;
        for (QuakeEntry qe : result2){
            System.out.println(qe.getLocation() + ", " + "mag = " + qe.getMagnitude() + ", depth = " + qe.getDepth() + ", title = " + qe.getInfo());
            count2++;
        }
        System.out.println("Found " + count2 + " quakes that match " + phrase2);
    }
}
