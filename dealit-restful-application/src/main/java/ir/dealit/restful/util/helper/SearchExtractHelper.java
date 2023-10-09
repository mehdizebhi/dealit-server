package ir.dealit.restful.util.helper;

public class SearchExtractHelper {

    private final static String[] SYMBOLS = {" ", ".", ",", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_"};

    public static String[] extractSearchKeywords(String search){
        return search.split(" ");
    }
}
