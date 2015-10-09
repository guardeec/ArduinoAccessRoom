package services.admin.client.security;

import JDBC_Impl.admin.client.security.AccountImpl;
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
public class Account extends HttpServlet {
    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("JDBC_config.xml");
    private final JDBC_Impl.admin.client.security.AccountImpl DAO = (JDBC.admin.client.security.Account) appContext.getBean("account");

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

        /*
        Нужен парсинг db_roles
         */

        Map<String, String> message = DAO.log(
                host_ip[0],
                host_mac[0],
                login_name[0],
                Integer.parseInt(login_id[0]),
                Integer.parseInt(admin_type[0]),
                Boolean.parseBoolean(result_type[0]),
                Integer.parseInt(event_type[0]),
                account_login[0],
                Integer.parseInt(employee_id[0]),
                Integer.parseInt(account_id[0]),
                Integer.parseInt(db_roles[0]),
                Date.valueOf(datetime[0])
        );

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}