package Servlets.Arduino_Client;

import JDBC.Arduino;
import String_Methods.Parsing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kolomeec on 23.04.2015.
 */
public class Arduino_GetAcces extends HttpServlet {

    ApplicationContext appContext = new ClassPathXmlApplicationContext("JDBC_config.xml");
    Arduino DAO = (Arduino) appContext.getBean("Arduino_DAO");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] deviceId = (String[]) reqMap.get("device_id");
        String[] card = (String[]) reqMap.get("user_card");

        Map<String, String> message = DAO.Get(card[0], deviceId[0]);

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
