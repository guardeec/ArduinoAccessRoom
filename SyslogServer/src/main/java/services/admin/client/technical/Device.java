package services.admin.client.technical;

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
    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("JDBC_config.xml");
    private final JDBC_Impl.admin.client.technical.DeviceImpl DAO = (JDBC.admin.client.technical.Device) appContext.getBean("device");

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

        String[] device_ip = (String[]) reqMap.get("device_ip");
        String[] device_specification = (String[]) reqMap.get("device_specification");
        String[] device_id = (String[]) reqMap.get("device_id");

        Map<String, String> message = DAO.log(
                host_ip[0],
                host_mac[0],
                login_name[0],
                Integer.parseInt(login_id[0]),
                Integer.parseInt(admin_type[0]),
                Boolean.parseBoolean(result_type[0]),
                Integer.parseInt(event_type[0]),
                device_ip[0],
                device_specification[0],
                Integer.parseInt(device_id[0]),
                Date.valueOf(datetime[0])
        );

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}