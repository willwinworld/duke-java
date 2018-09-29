import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2){
        String q1LastWord = qe1.getInfo().substring(qe1.getInfo().lastIndexOf(" ")+1);
        String q2LastWord = qe2.getInfo().substring(qe2.getInfo().lastIndexOf(" ")+1);
        int compareResult = q1LastWord.compareTo(q2LastWord);
        if (compareResult == 0) {
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }
        return compareResult;
    }
}
