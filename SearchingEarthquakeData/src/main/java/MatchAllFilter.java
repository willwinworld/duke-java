import java.util.ArrayList;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> allFilter;

    public MatchAllFilter(){
        this.allFilter = new ArrayList<Filter>();
    }

    public void addFilter(Filter f){
        this.allFilter.add(f);
    }

    public boolean satisfies(QuakeEntry qe){
        for (Filter f : this.allFilter){
            if (!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }

    public String getName(){
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.allFilter.size(); i++){
            Filter currFilter = this.allFilter.get(i);
            String filterName = currFilter.getName();
            output.append(filterName).append(" ");
        }
        return output.toString();
    }
}
