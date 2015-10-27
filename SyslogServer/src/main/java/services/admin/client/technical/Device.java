package services.admin.client.technical;

import JDBC.SyslogData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class Device extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map reqMap = request.getParameterMap();
        String[] host_ip = (String[]) reqMap.get("host_ip");
        String[] host_mac = (String[]) reqMap.get("host_mac");
        String[] login_name = (String[]) reqMap.get("login_name");
        String[] login_id = (String[]) reqMap.get("login_id");
        String[] result_type = (String[]) reqMap.get("result_type");
        String[] event_type = (String[]) reqMap.get("event_type");
        String[] datetime = (String[]) reqMap.get("datetime");
        String[] device_ip = (String[]) reqMap.get("device_ip");
        String[] device_specification = (String[]) reqMap.get("device_specification");
        String[] device_id = (String[]) reqMap.get("device_id");
        String[] device_mac = (String[]) reqMap.get("device_mac");
        String[] description = (String[]) reqMap.get("description");
        switch (event_type[0]){
            case "create" : event_type[0]= SyslogData.Event_Types.tech_dep.create.toString(); break;
            case "delete" : event_type[0]= SyslogData.Event_Types.tech_dep.delete.toString(); break;
            case "update" : event_type[0]= SyslogData.Event_Types.tech_dep.update.toString(); break;
        }

        Map<String, String> message = JDBC.admin.client.technical.Device.log(
                host_ip[0].isEmpty()                ? null : host_ip[0],
                host_mac[0].isEmpty()               ? null : host_mac[0],
                login_name[0].isEmpty()             ? null : login_name[0],
                login_id[0].isEmpty()               ? null : login_id[0],
                result_type[0].isEmpty()            ? null : result_type[0],
                event_type[0].isEmpty()             ? null : event_type[0],
                device_ip[0].isEmpty()              ? null : device_ip[0],
                device_specification[0].isEmpty()   ? null : device_specification[0],
                device_id[0].isEmpty()              ? null : device_id[0],
                device_mac[0].isEmpty()             ? null : device_mac[0],
                description[0].isEmpty()            ? null : description[0],
                datetime[0].isEmpty()               ? null : datetime[0]
        );

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
