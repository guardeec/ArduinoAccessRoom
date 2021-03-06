package accessToDataBase.Servlets.Admin_Client.UserAdmin;

import accessToDataBase.JDBC.Admin.UserAdmin.changeUserImpl;
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
public class changeUser extends HttpServlet {

    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final changeUserImpl usersDB = (changeUserImpl) appContext.getBean("changeUserAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        Map<String, String> message = null;
        if(adminTable.get("userAdmin")){
            String[] id = (String[]) reqMap.get("userId");
            String[] name = (String[]) reqMap.get("name");
            String[] roleId = (String[]) reqMap.get("roleId");
            message = usersDB.change(   Integer.parseInt(id[0]),
                                        !name[0].isEmpty() ? name[0] : null,
                                        !roleId[0].isEmpty() ? Integer.parseInt(roleId[0]) : null
            );
        }else {
            message = new HashMap<String, String>();
            message.put("message", "wrong pass");
        }
        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
