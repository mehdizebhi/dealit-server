package ir.dealit.restful.util;

public class SearchExtractHelper {

    private final static String[] SYMBOLS = {" ", ".", ",", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_"};

    public static String[] extractSearchKeywords(String search){
        return search.split(" ");
    }
}
