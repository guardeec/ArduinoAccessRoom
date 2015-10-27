package log.methods;

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
 * Created by root on 01.10.15.
 */
public class HttpRequest {

    private static String readStreamToString(InputStream in, String encoding) throws IOException {
        StringBuffer b = new StringBuffer();
        InputStreamReader r = new InputStreamReader(in, encoding);
        int c;
        while ((c = r.read()) != -1) {
            b.append((char)c);
        }
        return b.toString();
    }

    public static Map<String, String> makeInMap(String message, String httpAddress){
        Map<String, String> output;
        URLConnection conn;
        try {
            System.out.println("http://"+httpAddress+"?"+message);
            conn = new URL( "http://"+httpAddress+"?"+message).openConnection();
            message = readStreamToString(conn.getInputStream(), "UTF-8");
            output = HttpParse.parseHttp2Map(message);
        } catch (IOException e) {
            e.printStackTrace();
            output = new HashMap<String, String>();
            output.put("message", "Error NoO Connection"+ "----"+ "http://"+httpAddress+"?"+message);
        }
        return output;
    }

    public static List<Map> makeInList(String message, String httpAddress){
        List<Map> output;
        try {
            URLConnection conn = new URL( "http://"+httpAddress+"?"+message).openConnection();
            message = readStreamToString(conn.getInputStream(), "UTF-8");
            output = HttpParse.parseHttp2ArrayList(message);
        } catch (IOException e) {
            output = new ArrayList<Map>();
            Map<String, String> map = new HashMap();
            map.put("message", "Error no connection");
        }
        return output;
    }
}
