package services.admin.client;

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
public class EnterOrOutAdmin extends HttpServlet {

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
        String[] cl_acc_id = (String[]) reqMap.get("cl_acc_id");
        String[] cl_acc_l = (String[]) reqMap.get("cl_acc_l");
        String[] acc_r = (String[]) reqMap.get("acc_r");
        String[] description = (String[]) reqMap.get("description");
        switch (event_type[0]){
            case "login" : event_type[0]= SyslogData.Event_Types.adm_cl_auth.login.toString(); break;
            case "logout" : event_type[0]= SyslogData.Event_Types.adm_cl_auth.logout.toString(); break;
        }

        Map<String, String> message = JDBC.admin.client.EnterOrOutAdmin.log(
                host_ip[0].isEmpty()    ? null : host_ip[0],
                host_mac[0].isEmpty()   ? null : host_mac[0],
                login_name[0].isEmpty() ? null : login_name[0],
                login_id[0].isEmpty()   ? null : login_id[0],
                result_type[0].isEmpty()? null : result_type[0],
                event_type[0].isEmpty() ? null : event_type[0],
                cl_acc_id[0].isEmpty()  ? null : cl_acc_id[0],
                cl_acc_l[0].isEmpty()   ? null : cl_acc_l[0],
                acc_r[0].isEmpty()      ? null : acc_r[0],
                description[0].isEmpty()? null : description[0],
                datetime[0].isEmpty()   ? null : datetime[0]
        );

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
