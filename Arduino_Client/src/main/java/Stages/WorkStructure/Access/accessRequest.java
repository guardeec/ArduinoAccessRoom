package Stages.WorkStructure.Access;
import Stages.Methods.HttpParse;
import Stages.WorkStructure.CheckConnection.connectionStatus;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class accessRequest {

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

        URLConnection conn;
        String message =    "card="+input.get("card")+"&"+
                            "device_id="+input.get("device_id")
        ;

        try {
            conn = new URL( "http://"+httpAddress+"?"+message).openConnection();
            message = readStreamToString(conn.getInputStream(), "UTF-8");
            Map<String, String> output = HttpParse.parseHttp2Map(message);
            return output;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Неправильный адрес сервера или сервер недоступен");
            connectionStatus.setNoConnection();
            Map<String, String> output = new HashMap<String, String>();
            output.put("id", "NO_CONNECTION");
            output.put("name", "NO_CONNECTION");
            output.put("access", "false");
            return output;
        }
    }

}
