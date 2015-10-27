package services.arduino.client;

import JDBC.SyslogData;

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
public class EnterOrOutTheRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map reqMap = request.getParameterMap();
        String[] device_id = (String[]) reqMap.get("device_id");
        String[] device_ip = (String[]) reqMap.get("device_ip");
        String[] device_mac = (String[]) reqMap.get("device_mac");
        String[] user_id = (String[]) reqMap.get("user_id");
        String[] user_name = (String[]) reqMap.get("user_name");
        String[] card_id = (String[]) reqMap.get("card_id");
        String[] user_type = (String[]) reqMap.get("user_type");
        String[] event_type = (String[]) reqMap.get("event_type");
        String[] result_type = (String[]) reqMap.get("result_type");
        String[] datetime = (String[]) reqMap.get("datetime");
        String[] user_type_id = (String[]) reqMap.get("user_type_id");
        String[] description = (String[]) reqMap.get("description");
        switch (event_type[0]){
            case "login" : event_type[0]= SyslogData.Event_Types.room_auth.login.toString(); break;
            case "logout" : event_type[0]= SyslogData.Event_Types.room_auth.logout.toString(); break;
        }

        Map<String, String> message = JDBC.arduino.client.EnterOrOutTheRoom.log(
                device_id[0].isEmpty()      ? null : device_id[0],
                device_ip[0].isEmpty()      ? null : device_ip[0],
                device_mac[0].isEmpty()     ? null : device_mac[0],
                user_id[0].isEmpty()        ? null : user_id[0],
                user_name[0].isEmpty()      ? null : user_name[0],
                card_id[0].isEmpty()        ? null : card_id[0],
                user_type_id[0].isEmpty()   ? null : user_type_id[0],
                user_type[0].isEmpty()      ? null : user_type[0],
                event_type[0].isEmpty()     ? null : event_type[0],
                result_type[0].isEmpty()    ? null : result_type[0],
                description[0].isEmpty()    ? null : description[0],
                datetime[0].isEmpty()       ? null : datetime[0]
        );

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}