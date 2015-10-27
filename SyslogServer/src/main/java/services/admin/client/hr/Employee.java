package services.admin.client.hr;
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
public class Employee extends HttpServlet {

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
        String[] employee_name = (String[]) reqMap.get("employee_name");
        String[] employee_role_id = (String[]) reqMap.get("employee_role_id");
        String[] card_id = (String[]) reqMap.get("card_id");
        String[] employee_id = (String[]) reqMap.get("employee_id");

        String[] employee_st_id = (String[]) reqMap.get("employee_st_id");
        String[] sys_r_title = (String[]) reqMap.get("sys_r_title");
        String[] empl_st_descr = (String[]) reqMap.get("empl_st_descr");
        String[] description = (String[]) reqMap.get("description");
        switch (event_type[0]){
            case "create" : event_type[0]= SyslogData.Event_Types.hr_dep.create.toString(); break;
            case "delete" : event_type[0]= SyslogData.Event_Types.hr_dep.delete.toString(); break;
            case "update" : event_type[0]= SyslogData.Event_Types.hr_dep.update.toString(); break;
        }

        Map<String, String> message = JDBC.admin.client.hr.Employee.log(
                host_ip[0].isEmpty()            ? null : host_ip[0],
                host_mac[0].isEmpty()           ? null : host_mac[0],
                login_name[0].isEmpty()         ? null : login_name[0],
                login_id[0].isEmpty()           ? null : login_id[0],
                result_type[0].isEmpty()        ? null : result_type[0],
                event_type[0].isEmpty()         ? null : event_type[0],
                employee_name[0].isEmpty()      ? null : employee_name[0],
                employee_role_id[0].isEmpty()   ? null : employee_role_id[0],
                employee_st_id[0].isEmpty()     ? null : employee_st_id[0],
                empl_st_descr[0].isEmpty()      ? null : empl_st_descr[0],
                sys_r_title[0].isEmpty()        ? null : sys_r_title[0],
                card_id[0].isEmpty()            ? null : card_id[0],
                employee_id[0].isEmpty()        ? null : employee_id[0],
                description[0].isEmpty()        ? null : description[0],
                datetime[0].isEmpty()           ? null : datetime[0]
        );

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
