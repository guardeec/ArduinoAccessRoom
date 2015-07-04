package Stages.loop_Methods;
import java.util.HashMap;
import java.util.Map;

public abstract class Http2Map {

    //parsing http to Map
    public static Map<String,String> parseHttp2Map(String input){

        input = input.replace(",", "&");
        input = input.replace("[", "");
        input = input.replace("]", "");
        input = input.replace(" ", "");
        String[] params = input.split("&");

        Map<String, String> output = new HashMap();
        for (int i=0; i<params.length; i++){
            String[] keyAndValue = params[i].split("=");
            output.put(keyAndValue[0], keyAndValue[1]);
        }

        return output;
    }

}
