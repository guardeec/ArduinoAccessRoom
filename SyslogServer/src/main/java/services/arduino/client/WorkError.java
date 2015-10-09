package services.arduino.client;

import JDBC_Impl.arduino.client.WorkErrorImpl;
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
public class WorkError extends HttpServlet {
    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("JDBC_config.xml");
    private final JDBC_Impl.arduino.client.WorkErrorImpl DAO = (JDBC.arduino.client.WorkError) appContext.getBean("workError");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map reqMap = request.getParameterMap();
        String[] device_id = (String[]) reqMap.get("device_id");
        String[] device_ip = (String[]) reqMap.get("device_ip");
        String[] client_error_type = (String[]) reqMap.get("client_error_type");
        String[] arduino_error_type = (String[]) reqMap.get("arduino_error_type");
        String[] datetime = (String[]) reqMap.get("datetime");

        Map<String, String> message = DAO.log(
                Integer.parseInt(device_id[0]),
                device_ip[0],
                Integer.parseInt(client_error_type[0]),
                Integer.parseInt(arduino_error_type[0]),
                Date.valueOf(datetime[0])
        );

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}