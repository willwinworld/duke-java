import java.util.*;


public class LargestQuakes {
    public void findLargestQuakes(){
        // Read and store earthquake data
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Read " + list.size() + " quakes");
        /*
        // Print all the earthquakes and the number
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        // Print the index location of the largest magnitude earthquake
        int index = indexOfLargest(list);
        System.out.println("index: " + index + ", mag: " + list.get(index).getMagnitude());
        */

        // Print the five earthquakes of largest magnitude
        ArrayList<QuakeEntry> largest = getLargest(list, 5);
        for (QuakeEntry quake : largest) {
            System.out.println(quake);
        }
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int maxIndex = 0;
        double largestMagnitude = data.get(maxIndex).getMagnitude();
        for (QuakeEntry qe : data){
            if (qe.getMagnitude() > largestMagnitude){
                maxIndex++;
                largestMagnitude = qe.getMagnitude();
            }
        }
        return maxIndex;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> dataRemovable = quakeData;
        ArrayList<QuakeEntry> largest = new ArrayList<QuakeEntry>();
        // Until either largest contains howMany elements or data runs out
        while (largest.size() < howMany || dataRemovable.size() == 0) {
            // Find
            int index = indexOfLargest(dataRemovable);
            largest.add(dataRemovable.get(index));
            dataRemovable.remove(index);
        }
        return largest;
    }
}
// 如何避免重复代码
// 从而引入了java的interface
/*
 * public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f){
 *     ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
 *     for(QuakeEntry qe : quakeData){
 *         if (f.satisfies(qe)){
 *             answer.add(qe);
 *         }
 *     }
 *     return answer;
 * }
 * But how do you make Filters with different .satisfies() methods
 *
 * public interface Filter{
 *     public boolean satisfies(QuakeEntry qe);
 * }
 *
 * Filter is an interface
 * Instead of defining behavior of methods, an interface is just a type, a type that promises that certain methods will
 * will exist in all classes which implement the interface. Here, the filter interface promises one such method.
 * Public boolean satisfies(QuakeEntry qe). Once you have declared an interface, you can write classes that implement it.
 * When you write a class that implements an interface, you must define all of the methods promised in the interface
 * specification. Objects of the class can then be treated as the interface type. if you write a class that implements
 * filter, objects that you make from that class can be assigned to variables of type filter or passed as parameters to
 * methods that expect a filter.
 *
 * public class MinMagFilter implements Filter{
 *  private double magMin;
 *  public MinMagFilter(double min){
 *    magMin = min;
 *  }
 *  public boolean satisfies(QuakeEntry qe){
 *    return qe.getMagnitude() >= magMin;
 *  }
 * }
 * 当类要实现接口时，必须要实现接口中的所有方法
 * public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f){
 *   ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
 *   for(QuakeEntry qe : quakeData){
 *     if (f.satisfies(qe)){
 *       answer.add(qe);
 *     }
 *   }
 *   return answer;
 * }
 * Filter f = new MinMagFilter(4.0);
 * ArrayList<QuakeEntry> largeQuakes = filter(list, f);
 * f = new DistanceFilter(myLoc, 100);
 * ArrayList<QuakeEntry> shallowQuakes = filter(list, f);
 *
 *
 * public class MatchAllFilter implements Filter{
 *   private ArrayList<Filter> filters;
 *   public MatchAllFilter(){
 *     filters = new ArrayList<Filter>();
 *   }
 *   public void addFilter(Filter f){
 *     filters.add(f);
 *   }
 *   public boolean satisfies(QuakeEntry qe){
 *     for (Filter f: filters){
 *       if(!f.satisfies(qe)){
 *         return false;
 *       }
 *     }
 *     return true;
 *   }
 * }
 *
 * Interface Summary
 * 定义接口，没有body
 * Specify methods classes must have
 *
 * public interface Filter {
 *   public boolean satisfies(QuakeEntry qe);
 * }
 *
 * Implementing Interfaces:
 * Write class with "implements InterfaceName"
 * Define all required methods
 *
 * public class MinMagFilter implements Filter {
 *   private double magMin;
 *   public MinMagFilter(double min){
 *     magMin = min;
 *   }
 *   public boolean satisfies(QuakeEntry qe){
 *     return qe.getMagnitude() >= magMin;
 *   }
 * }
 *
 * Using Interface Types:
 * Can use interface name as type
 * Can call methods in the interface
 *
 * public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f){
 *   ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
 *   for(QuakeEntry qe : quakeData){
 *     if(f.satisfies(qe)){
 *       answer.add(qe);
 *     }
 *   }
 *   return answer;
 * }
 *
 * Type compatibility: use class as interface
 * Will still use methods in class: dynamic dispatch
 *
 * Filter f = new MinMagFilter(4.0);
 * ArrayList<QuakeEntry> largeQuakes = filter(list, f);
 */
