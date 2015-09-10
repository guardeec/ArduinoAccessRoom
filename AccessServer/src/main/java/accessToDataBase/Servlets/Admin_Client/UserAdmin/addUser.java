package accessToDataBase.Servlets.Admin_Client.UserAdmin;

import accessToDataBase.JDBC.Admin.UserAdmin.addUserImpl;
import accessToDataBase.JDBC.Admin.UserAdmin.addUserToCardImpl;
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
public class addUser extends HttpServlet {

    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final addUserImpl usersDB = (addUserImpl) appContext.getBean("addUserAdmin");
    private final addUserToCardImpl cardsDB = (addUserToCardImpl) appContext.getBean("addUserToCardAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        Map<String, String> message = null;
        if(adminTable.get("userAdmin")){
            String[] name = (String[]) reqMap.get("name");
            message = usersDB.add(name[0]);
            if(!message.get("message").contains("Error when adding user")){
                String[] cardId = (String[]) reqMap.get("cardId");
                String[] roleId= (String[]) reqMap.get("roleId");
                message = cardsDB.add(  Integer.parseInt(message.get("id")),
                                        Integer.parseInt(cardId[0]),
                                        Integer.parseInt(roleId[0])
                );
            }
        }else {
            message = new HashMap<String, String>();
            message.put("message", "wrong pass");
        }
        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
