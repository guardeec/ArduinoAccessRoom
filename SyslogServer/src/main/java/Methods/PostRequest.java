package Methods;

import Methods.POJO.PojoObject;
import com.google.gson.Gson;
import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by root on 27.10.15.
 */
public class PostRequest {

    public static PojoObject make(PojoObject object, String URL){

        //формируем json и заголовок по которому класс json объекта
        Gson gson = new Gson();
        String json = gson.toJson(object, object.getClass());
        String classType = object.getClass().getName();

        try {
            //устанавливаем соединение
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //передаём заголовок как хэдер, а json как сообщение
            conn.setRequestProperty("ObjectType", classType);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(json);
            writer.flush();

            //считываем ответ: json в сообщении и класс в хэдере
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            json = reader.readLine();
            classType = conn.getHeaderField("ObjectType");

            //закрываем соединение
            conn.disconnect();

            //десериализуем json в объект
            object = gson.fromJson(json, (Type) Class.forName(classType));

            return object;
        } catch (java.io.IOException | ClassNotFoundException ignored) {
            return null;
        }
    }

}
