package accessToDataBase.Servlets.Arduino_Client;

import log.arduinoClientMethods;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

/**
 * Created by Kolomeec on 23.04.2015.
 */
public class getAccess extends HttpServlet {

    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final accessToDataBase.JDBC.Arduino.getAccess DAO = (accessToDataBase.JDBC.Arduino.getAccess) appContext.getBean("getAccess");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] deviceId = (String[]) reqMap.get("device_id");
        String[] card = (String[]) reqMap.get("card");

        Map<String, String> message = DAO.Get(card[0], deviceId[0]);

        //log
        Map<String, String> a = arduinoClientMethods.enterOrOutTheRoomLog(
                /*
            String device_id,
            String device_ip,

            String user_id,
            String user_name,

            String card_id,
            String user_type ,

            String result_type,

            String datetime,
            String event_type,

            String device_mac,
            String user_type_id,
            String description
         */
            deviceId[0],
            "",

            message.get("id").contains("UNKNOWN") ? "" : message.get("id"),
            message.get("name").contains("UNKNOWN") ? "" : message.get("name"),

            card[0],
            message.get("type").contains("UNKNOWN") ? "" : message.get("type"),

            message.get("access"),

            Date.valueOf(LocalDate.now()).toString(),
            "login",

            "",
            "",
            ""
        );
        System.out.println(a.entrySet());

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
