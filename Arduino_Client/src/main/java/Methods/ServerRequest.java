package Methods;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guardeec on 22.06.15.
 */
public abstract class ServerRequest {

    /*
    ** return String of Http_response
    */
    private static String readStreamToString(InputStream in, String encoding) throws IOException {
        StringBuffer b = new StringBuffer();
        InputStreamReader r = new InputStreamReader(in, encoding);
        int c;
        while ((c = r.read()) != -1) {
            b.append((char)c);
        }
        return b.toString();
    }

    /*
    looking for value in Map
    where "param" is name of value
     */
    private static String mapParse(String message, String param){

        if(message.lastIndexOf(",") < message.lastIndexOf(param)){
            message = message.substring (   message.lastIndexOf(param+"="),
                                            message.indexOf("]", message.lastIndexOf(param+"="))
            );
        }
        else {
            message = message.substring (   message.lastIndexOf(param+"="),
                                            message.indexOf(",", message.lastIndexOf(param+"="))
            );
        }

        return message;
    }

    /*
    send the data to server
     */
    public static Map<String, String> make(Map<String, String> input, String httpAddress){

        URLConnection conn = null;
        String message;
        try {
            conn = new URL( "http://"+httpAddress+"?"+
                            input.get("card")+
                            "&"+
                            input.get("device_id")
            ).openConnection();
            message = readStreamToString(conn.getInputStream(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("Неправильный адрес сервера или сервер недоступен");
            message = null;
        }

        Map<String, String> output = new HashMap<String, String>();
        if(message != null){
            output.put("id", mapParse(message, "id"));
            output.put("name", mapParse(message, "name"));
            output.put("right_id", mapParse(message, "right_id"));
        }
        else {
            output.put("id", "NO_CONNECTION");
            output.put("name", "NO_CONNECTION");
            output.put("right_id", "0");
        }

        return output;
    }

}
