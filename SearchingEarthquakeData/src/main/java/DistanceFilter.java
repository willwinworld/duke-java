public class DistanceFilter implements Filter {
    private Location location;
    private double disMax;

    public DistanceFilter(Location loc, double max){
        location = loc;
        disMax = max;
    }

    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(location) < disMax;
    }

    public String getName(){
        return "Distance";
    }
}
