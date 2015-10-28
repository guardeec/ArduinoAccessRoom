package Concept;



import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by root on 27.10.15.
 */
public class test {

    private static String readStreamToString(InputStream in, String encoding) throws IOException {
        StringBuffer b = new StringBuffer();
        InputStreamReader r = new InputStreamReader(in, encoding);
        int c;
        while ((c = r.read()) != -1) {
            b.append((char)c);
        }
        return b.toString();
    }


    public void test(){


        try {
            String answer = null;

            URL url = new URL("http://localhost:8080/getO");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Type", "POJO");

            System.out.println("Что отправляем на сервер:");
            System.out.println("3");
            System.out.println("Header:");
            System.out.println("Pojo");

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write("3");
            writer.flush();

            String object="";
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            object = reader.readLine();
            writer.close();
            reader.close();
            conn.disconnect();

            System.out.println("Сервер выдаёт JSON:");
            System.out.println(object);
            System.out.println("Header:");
            System.out.println(conn.getHeaderField("Type"));

            System.out.println();
            System.out.println();


            url = new URL("http://localhost:8080/whatO");
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Type", "POJO");

            System.out.println("Что отправляем на сервер:");
            System.out.println(object);
            System.out.println("Header:");
            System.out.println("Pojo");

            writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(object);
            writer.flush();

            object="";
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            object = reader.readLine();
            writer.close();
            reader.close();
            conn.disconnect();

            System.out.println("Что за объект распознал сервер:");
            System.out.println(object);
            System.out.println("Header:");
            System.out.println(conn.getHeaderField("Type"));
            System.out.println(conn.getResponseMessage());


            /*
            answer = readStreamToString(conn.getInputStream(), "UTF-8");
            System.out.println(answer);

            conn = new URL("http://localhost:8080/whatO?"+answer).openConnection();
            answer = readStreamToString(conn.getInputStream(), "UTF-8");*/

           // System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
