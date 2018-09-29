
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "E:\\Java_Project\\SortingEarthquakeData\\src\\main\\java\\data\\earthquakeDataSampleSix2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
//        sortByMagnitude(list);
//        for (QuakeEntry qe: list) {
//            System.out.println(qe);
//        }
//        sortByLargestDepth(list);
//        for (QuakeEntry qe: list){
//            System.out.println(qe);
//        }
//        sortByMagnitudeWithBubbleSort(list);
//        for(QuakeEntry qe : list){
//            System.out.println(qe);
//        }
        sortByMagnitudeWithBubbleSortWithCheck(list);
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}

	public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int maxIdx = from;
        for(int i = from+1; i < quakeData.size(); i++){
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()){
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in){

        for (int i=0; i<in.size(); i++){
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qlar = in.get(maxIdx);
            in.set(i, qlar);
            in.set(maxIdx, qi);
        }

    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for (int i = 1; i < quakeData.size() - numSorted; i++) {
            QuakeEntry currEntry = quakeData.get(i-1);
            QuakeEntry nextEntry = quakeData.get(i);
            double currMagn = currEntry.getMagnitude();
            double nextMagn = nextEntry.getMagnitude();
            if (!(currMagn < nextMagn)) {
                quakeData.set(i-1, nextEntry);
                quakeData.set(i, currEntry);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        int N = in.size();
        System.out.println("Printing quakes before the pass...");
        printList(in);
        for (int i = 0; i < N-1; i++) {
            onePassBubbleSort(in, i);
            System.out.println("Printing quakes after pass " + i + "...");
            printList(in);
        }
    }

    public void printList(ArrayList<QuakeEntry> printme) {
        for (int i = 0; i < printme.size(); i++) {
            System.out.println(printme.get(i));
        }
        System.out.println("\n");
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){  // 从小到大排列
        for(int i=0; i<quakes.size(); i++){
            if (i+1 < quakes.size()){
                if(quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()){
                    return false;
                }
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        if (!checkInSortedOrder(in)){
            int N = in.size();
            for (int i=0; i<N-1; i++){
                onePassBubbleSort(in, i);
                System.out.println("Printing quakes after pass " + i + "...");
                printList(in);
                if (checkInSortedOrder(in)){
                    break;
                }
            }
        }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int numPasses = 0;
        for (int i=0; i < in.size(); i++) {
            if (!checkInSortedOrder(in)) {
                int minIdx = getSmallestMagnitude(in,i);
                QuakeEntry qi = in.get(i);
                QuakeEntry qmin = in.get(minIdx);
                in.set(i,qmin);
                in.set(minIdx,qi);
                numPasses += 1;
            } else {
                break;
            }
        }
        System.out.println("Array List sorted after " + numPasses + " passes");
    }

    public static void main(String[] args){
        QuakeSortInPlace qsip = new QuakeSortInPlace();
        qsip.testSort();
    }
}
