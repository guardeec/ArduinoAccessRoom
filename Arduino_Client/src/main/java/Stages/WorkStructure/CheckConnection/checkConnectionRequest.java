package Stages.WorkStructure.CheckConnection;

import Stages.Methods.HttpParse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 12.08.15.
 */
public class checkConnectionRequest {
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
    public static Map<String, String> make(String httpAddress){

        URLConnection conn;
        String message;
        try {
            conn = new URL( "http://"+httpAddress).openConnection();
            message = readStreamToString(conn.getInputStream(), "UTF-8");
            Map<String, String> output = HttpParse.parseHttp2Map(message);
            return output;

        } catch (IOException e) {
            Map<String, String> output = new HashMap<String, String>();
            output.put("Server", "false");
            output.put("DB", "false");
            return output;
        }
    }
}
