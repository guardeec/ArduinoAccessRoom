package services.arduino.client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class UnauthorizedAccess extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map reqMap = request.getParameterMap();
        String[] device_id = (String[]) reqMap.get("device_id");
        String[] device_ip = (String[]) reqMap.get("device_ip");
        String[] device_mac = (String[]) reqMap.get("device_mac");
        String[] description = (String[]) reqMap.get("description");
        String[] datetime = (String[]) reqMap.get("datetime");



        Map<String, String> message = JDBC.arduino.client.UnauthorizedAccess.log(
                device_id[0].isEmpty()  ? null : device_id[0],
                device_ip[0].isEmpty()  ? null : device_ip[0],
                device_mac[0].isEmpty() ? null : device_mac[0],
                description[0].isEmpty()? null : description[0],
                datetime[0].isEmpty()   ? null : datetime[0]
        );

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
