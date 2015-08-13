package accessToDataBase.Servlets.Admin_Client.GuestCardAdmin;

import accessToDataBase.JDBC.Admin.GuestCardAdmin.addGuestImpl;
import accessToDataBase.JDBC.Admin.GuestCardAdmin.addGuestToCardImpl;
import accessToDataBase.JDBC.Authorisation.getAdminTypeImpl;
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
 * Created by root on 06.08.15.
 */
public class addGuestCard extends HttpServlet {

    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final addGuestImpl guestNameDB = (addGuestImpl) appContext.getBean("addGuestAdmin");
    private final addGuestToCardImpl guestCardDB = (addGuestToCardImpl) appContext.getBean("addToCardGuestAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        Map<String, String> message = null;
        if(adminTable.get("guestAdmin")){
            String[] name = (String[]) reqMap.get("name");
            message = guestNameDB.addGuest(name[0]);

            if (!message.get("message").contains("Error when adding guest")) {
                String[] cardId = (String[]) reqMap.get("cardId");
                message = guestCardDB.add(Integer.parseInt(message.get("id")), Integer.parseInt(cardId[0]));
            }

        }else {
            message = new HashMap<>();
            message.put("message", "wrong pass");
        }

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
