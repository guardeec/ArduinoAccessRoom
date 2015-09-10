package Stages.WorkStructure.cashAdminDB;

import Stages.Methods.HttpParse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 12.08.15.
 */
public class cashAdminDBRequest {
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
    public static List<Map> make(String httpAddress){
        List<Map> output;
        try {
            URLConnection conn = new URL( "http://"+httpAddress).openConnection();
            String message = readStreamToString(conn.getInputStream(), "UTF-8");
            output = HttpParse.parseHttp2ArrayList(message);
        } catch (IOException e) {
            output = new ArrayList<Map>();
            Map<String, String> map = new HashMap();
            map.put("message", "Error");
        }
        return output;
    }
}
