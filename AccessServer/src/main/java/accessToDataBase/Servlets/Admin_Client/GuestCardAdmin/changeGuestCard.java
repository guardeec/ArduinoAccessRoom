package accessToDataBase.Servlets.Admin_Client.GuestCardAdmin;

import accessToDataBase.JDBC.Admin.GuestCardAdmin.changeGuestCardImpl;
import accessToDataBase.JDBC.Authorisation.getAdminTypeImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public class changeGuestCard extends HttpServlet {

    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final changeGuestCardImpl guestDB = (changeGuestCardImpl) appContext.getBean("changeGuestAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        Map<String, String> message = null;
        if(adminTable.get("guestAdmin")) {
            String[] id = (String[]) reqMap.get("id");
            String[] name = (String[]) reqMap.get("name");
            String[] cardNumber = (String[]) reqMap.get("cardNumber");

            message = guestDB.change(   Integer.parseInt(id[0]),
                                        name[0],
                                        cardNumber[0].isEmpty() ? null : Integer.parseInt(cardNumber[0])
            );
        }else {
            message = new HashMap<>();
            message.put("message", "wrong pass");
        }

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
