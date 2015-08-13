package accessToDataBase.Servlets.Admin_Client.UserAdmin;

import accessToDataBase.JDBC.Admin.UserAdmin.getUserImpl;
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
public class getUser extends HttpServlet {

    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final getUserImpl userDB = (getUserImpl) appContext.getBean("getUserAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        ArrayList<Map> message = new ArrayList<>();
        if(adminTable.get("userAdmin")){
            String[] id = (String[]) reqMap.get("userId");
            String[] name = (String[]) reqMap.get("name");
            message = userDB.get(   id[0].isEmpty() ? Integer.parseInt(id[0]) : null,
                                    name[0].isEmpty() ? name[0] : null
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
