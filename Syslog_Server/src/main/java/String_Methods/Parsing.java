package String_Methods;

/**
 * Created by Kolomeec on 26.04.2015.
 */
public class Parsing {
    public static String[] parse(String a){
        String[] b = a.split("[|]+");
        return b;
    }
    public static String[] parseContent(String a){
        String[] b = a.split("[%]+");
        return b;
    }
}
