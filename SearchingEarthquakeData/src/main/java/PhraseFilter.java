public class PhraseFilter implements Filter{
    private String requestType;
    private String phrase;

    public PhraseFilter(String rt, String ph){
        requestType = rt;
        phrase = ph;
    }

    public boolean satisfies(QuakeEntry qe){
        String title = qe.getInfo();
        if (requestType.equals("start")){
            return title.startsWith(phrase);
        }
        else if (requestType.equals("end")){
            return title.endsWith(phrase);
        }
        else if (requestType.equals("any")){
            return title.contains(phrase);
        }
        return false;
    }

    public String getName(){
        return "Phrase";
    }
}
