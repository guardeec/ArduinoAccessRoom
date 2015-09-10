package accessToDataBase.Servlets.Admin_Client.SecurityAdmin.Roles;

import accessToDataBase.JDBC.Admin.SecurityAdmin.Roles.getRoleImpl;
import accessToDataBase.JDBC.Authorisation.getAdminTypeImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public class getRole extends HttpServlet {

    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final getRoleImpl roleDB = (getRoleImpl) appContext.getBean("getRoleSecurityAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        ArrayList<Map> message = new ArrayList<>();
        if(adminTable.get("securityAdmin")){
            String[] roleId = (String[]) reqMap.get("roleId");
            String[] title = (String[]) reqMap.get("title");

            message = roleDB.get(   !roleId[0].isEmpty() ? Integer.parseInt(roleId[0]) : null,
                                    !title[0].isEmpty() ? title[0] : null
            );

        }else {
            Map<String, String> m = new HashMap<String, String>();
            m.put("message", "wrong pass");
            message.add(m);
        }

        PrintWriter out = response.getWriter();
        for(Map a :message){
            out.println(a.entrySet());
        }
    }
}
