package services.user.client;

import JDBC_Impl.user.client.EnterOrOutTheSystemImpl;
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
public class EnterOrOutTheSystem extends HttpServlet {
    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("JDBC_config.xml");
    private final JDBC_Impl.user.client.EnterOrOutTheSystemImpl DAO = (JDBC.user.client.EnterOrOutTheSystem) appContext.getBean("enterOrOutTheSystem");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map reqMap = request.getParameterMap();
        String[] host_ip = (String[]) reqMap.get("host_ip");
        String[] host_mac = (String[]) reqMap.get("host_mac");
        String[] user_id = (String[]) reqMap.get("user_id");
        String[] event_type = (String[]) reqMap.get("event_type");
        String[] datetime = (String[]) reqMap.get("datetime");

        Map<String, String> message = DAO.log(
                host_ip[0],
                host_mac[0],
                Integer.parseInt(user_id[0]),
                Integer.parseInt(event_type[0]),
                Date.valueOf(datetime[0])
        );

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
