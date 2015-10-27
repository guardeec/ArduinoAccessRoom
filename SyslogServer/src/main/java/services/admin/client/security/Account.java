package services.admin.client.security;
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
public class Account extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map reqMap = request.getParameterMap();
        String[] host_ip = (String[]) reqMap.get("host_ip");
        String[] host_mac = (String[]) reqMap.get("host_mac");
        String[] login_name = (String[]) reqMap.get("login_name");
        String[] login_id = (String[]) reqMap.get("login_id");
        String[] admin_type = (String[]) reqMap.get("admin_type");
        String[] result_type = (String[]) reqMap.get("result_type");
        String[] event_type = (String[]) reqMap.get("event_type");
        String[] datetime = (String[]) reqMap.get("datetime");

        String[] account_login = (String[]) reqMap.get("account_login");
        String[] employee_id = (String[]) reqMap.get("employee_id");
        String[] account_id = (String[]) reqMap.get("account_id");
        String[] db_roles = (String[]) reqMap.get("db_roles");


        Map<String, String> message = null;

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
