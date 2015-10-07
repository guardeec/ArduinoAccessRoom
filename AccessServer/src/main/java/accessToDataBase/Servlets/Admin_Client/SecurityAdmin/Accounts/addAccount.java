package accessToDataBase.Servlets.Admin_Client.SecurityAdmin.Accounts;

import accessToDataBase.JDBC.Admin.SecurityAdmin.Accounts.AddAccountImpl;
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
 * Created by root on 11.08.15.
 */
public class addAccount extends HttpServlet {
    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final AddAccountImpl accountDB = (AddAccountImpl) appContext.getBean("addAccountSecurityAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        Map<String, String> message = null;
        if(adminTable.get("securityAdmin")){
            String[] login = (String[]) reqMap.get("login");
            String[] password = (String[]) reqMap.get("password");
            String[] userId = (String[]) reqMap.get("userId");
            String[] technical = (String[]) reqMap.get("technical");
            String[] reception = (String[]) reqMap.get("reception");
            String[] hr = (String[]) reqMap.get("hr");
            String[] security = (String[]) reqMap.get("security");
            if (!userId[0].isEmpty() && !login[0].isEmpty() && !password[0].isEmpty() && (!technical[0].isEmpty() || !reception[0].isEmpty() || !hr[0].isEmpty() || !security[0].isEmpty())){
                message = accountDB.add(    login[0],
                                            password[0],
                                            Integer.parseInt(userId[0]),
                                            !technical[0].isEmpty(),
                                            !reception[0].isEmpty(),
                                            !hr[0].isEmpty(),
                                            !security[0].isEmpty()
                );
            }else {
                message = new HashMap<String, String>();
                message.put("message", "Success cant add account without DB_roles");
            }
        }else {
            message = new HashMap<String, String>();
            message.put("message", "wrong pass");
        }

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}

//колонка "user_id" в таблице "accounts" не существует
