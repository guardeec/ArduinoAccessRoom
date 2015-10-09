package services.arduino.client;

import JDBC_Impl.arduino.client.EnterOrOutTheRoomImpl;
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
public class EnterOrOutTheRoom extends HttpServlet {
    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("JDBC_config.xml");
    private final JDBC_Impl.arduino.client.EnterOrOutTheRoomImpl DAO = (JDBC.arduino.client.EnterOrOutTheRoom) appContext.getBean("enterOrOutTheRoom");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map reqMap = request.getParameterMap();
        String[] device_id = (String[]) reqMap.get("device_id");
        String[] device_ip = (String[]) reqMap.get("device_ip");
        String[] user_id = (String[]) reqMap.get("user_id");
        String[] user_name = (String[]) reqMap.get("user_name");
        String[] card_id = (String[]) reqMap.get("card_id");
        String[] user_type = (String[]) reqMap.get("user_type");
        String[] event_type = (String[]) reqMap.get("event_type");
        String[] result_type = (String[]) reqMap.get("result_type");
        String[] datetime = (String[]) reqMap.get("datetime");

        Map<String, String> message = DAO.log(
                Integer.parseInt(device_id[0]),
                Integer.parseInt(device_ip[0]),
                Integer.parseInt(user_id[0]),
                user_name[0],
                Integer.parseInt(card_id[0]),
                Integer.parseInt(user_type[0]),
                Integer.parseInt(event_type[0]),
                Boolean.parseBoolean(result_type[0]),
                Date.valueOf(datetime[0])
        );

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}