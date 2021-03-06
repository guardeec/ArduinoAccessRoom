package accessToDataBase.Servlets.Admin_Client.SecurityAdmin.Polices;

import accessToDataBase.JDBC.Admin.SecurityAdmin.Policy.changePolicyOnDeviceImpl;
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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 11.08.15.
 */
public class changePolicyOnDevice extends HttpServlet {
    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final changePolicyOnDeviceImpl policyDB = (changePolicyOnDeviceImpl) appContext.getBean("changePolicyOnDeviceSecurityAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        Map<String, String> message = null;
        String[] deviceId = (String[]) reqMap.get("deviceId");
        String[] roleId = (String[]) reqMap.get("roleId");
        String[] access = (String[]) reqMap.get("status");
        if(adminTable.get("securityAdmin")){

            message = policyDB.change(  Integer.parseInt(deviceId[0]),
                                        Integer.parseInt(roleId[0]),
                                        Boolean.parseBoolean(access[0])
            );
        }else {
            message = new HashMap<String, String>();
            message.put("message", "wrong pass");
        }

        //log
        Map<String, String> a = adminClientMethods.changeAccesOnDeviceLog(
            /*
            String host_ip,
            String host_mac,

            String login_name,
            String login_id,

            String result_type,

            String device_id,
            String role_id,

            String access_type,

            String datetime,

            String device_spec,
            String role_title,
            String description
         */
            "",
            "",

            adminName[0],
            "",

            message.get("message").contains("Success") ? "true" : "false",

            deviceId[0],
            roleId[0],

            Boolean.toString(Boolean.parseBoolean(access[0])),

            java.sql.Date.valueOf(LocalDate.now()).toString(),

            "",
            "",
            ""
        );
        System.out.println(a.entrySet());

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
