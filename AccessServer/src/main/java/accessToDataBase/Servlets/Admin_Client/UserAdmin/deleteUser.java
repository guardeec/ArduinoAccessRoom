package accessToDataBase.Servlets.Admin_Client.UserAdmin;

import accessToDataBase.JDBC.Admin.UserAdmin.deleteUserImpl;
import accessToDataBase.JDBC.Authorisation.getAdminTypeImpl;
import log.adminClientMethods;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public class deleteUser extends HttpServlet {

    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final deleteUserImpl usersDB = (deleteUserImpl) appContext.getBean("deleteUserAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        Map<String, String> message = null;
        String[] id = (String[]) reqMap.get("userId");
        if(adminTable.get("userAdmin")){

            message = usersDB.delete(Integer.parseInt(id[0]));
        }else {
            message = new HashMap<String, String>();
            message.put("message", "wrong pass");
        }

        //log
        Map<String, String> a = adminClientMethods.hrLog(
            /*
            String host_ip,
            String host_mac,

            String login_name,
            String login_id,

            String result_type,

            String employee_name,
            String employee_id,

            String employee_role_id,
            String card_id,

            String datetime,
            String event_type,

            String employee_st_id,
            String empl_st_descr,

            String sys_r_title,
            String description
         */
                "",
                "",

                adminName[0],
                "",

                message.get("message").contains("Success") ? "true" : "false",

                "",
                id[0],

                "",
                "",

                Date.valueOf(LocalDate.now()).toString(),
                "delete",

                "",
                "",

                "",
                ""
        );
        System.out.println(a.entrySet());

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
