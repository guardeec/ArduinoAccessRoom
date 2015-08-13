package accessToDataBase.Servlets.Arduino_Client;

import accessToDataBase.JDBC.Arduino.cashAdminDBImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by root on 12.08.15.
 */
public class cashAdminDB extends HttpServlet {
    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final cashAdminDBImpl adminDB = (cashAdminDBImpl) appContext.getBean("cashAdminDB");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] deviceId = (String[]) reqMap.get("device_id");
        ArrayList<Map> message = adminDB.get(Integer.parseInt(deviceId[0]));
        PrintWriter out = response.getWriter();
        for(Map a :message){
            out.println(a.entrySet());
        }
    }
}
