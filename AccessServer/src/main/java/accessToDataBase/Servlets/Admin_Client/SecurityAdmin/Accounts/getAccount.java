package accessToDataBase.Servlets.Admin_Client.SecurityAdmin.Accounts;

import accessToDataBase.JDBC.Admin.SecurityAdmin.Accounts.GetAccountImpl;
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
import java.util.List;
import java.util.Map;

/**
 * Created by root on 11.08.15.
 */
public class getAccount extends HttpServlet {
    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final GetAccountImpl accountsDB = (GetAccountImpl) appContext.getBean("getAccountSecurityAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        List<Map> message = new ArrayList<>();
        if(adminTable.get("securityAdmin")){
            String[] login = (String[]) reqMap.get("login");
            String[] id = (String[]) reqMap.get("id");
            String[] emplyee_id = (String[]) reqMap.get("employee_id");
            message = accountsDB.get( login[0].isEmpty() ? null : login[0],
                    id[0].isEmpty() ? null : Integer.parseInt(id[0]),
                    emplyee_id[0].isEmpty() ? null : Integer.parseInt(emplyee_id[0])

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
