package Stages.Methods;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpParse {

    //parsing http to Map
    public static Map<String,String> parseHttp2Map(String input){

        input = input.replace(",", "&");
        input = input.replace("[", "");
        input = input.replace("]", "");
        input = input.replace(" ", "");
        input = input.replace("\n", "");
        input = input.replace("\r", "");
        String[] params = input.split("&");

        Map<String, String> output = new HashMap();
        for (int i=0; i<params.length; i++){
            String[] keyAndValue = params[i].split("=");
            output.put(keyAndValue[0], keyAndValue[1]);

        }

        return output;
    }

    //parsing http to Map
    public static ArrayList<Map> parseHttp2ArrayList(String input){

        ArrayList<Map> output = new ArrayList<Map>();
        input = input   .replace(",", "&")
                .replace(" ", "")
                .replace("\n", "")
                .replace("\r", "");

        for (int i=0, size = input.split("]").length; i< size; i++){
            String subString = input.substring(input.indexOf("[")+1, input.indexOf("]"));
            String start = "\\[";
            String end = "]";
            input = input.replaceFirst(start,"").replaceFirst(end, "");

            Map<String, String> map = new HashMap();
            String[] params = subString.split("&");
            for (int q=0; q<params.length; q++){
                String[] keyAndValue = params[q].split("=");
                map.put(keyAndValue[0], keyAndValue[1]);
            }
            output.add(map);
        }

        return output;
    }



}
