package Stages.loop_Methods;

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
    send the data to server
     */
    public static Map<String, String> make(Map<String, String> input, String httpAddress){

        URLConnection conn = null;
        String message =    "user_card="+input.get("user_card")+"&"+
                            "device_id="+input.get("device_id")
        ;

        try {
            conn = new URL( "http://"+httpAddress+"?"+message).openConnection();
            message = readStreamToString(conn.getInputStream(), "UTF-8");


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Неправильный адрес сервера или сервер недоступен");
            message = null;
        }


        if(message != null){
            Map<String, String> output = Http2Map.parseHttp2Map(message);
            return output;
        }
        else {
            Map<String, String> output = new HashMap<String, String>();
            output.put("user_id", "NO_CONNECTION");
            output.put("user_name", "NO_CONNECTION");
            output.put("user_access", "0");
            return output;
        }
    }

}
