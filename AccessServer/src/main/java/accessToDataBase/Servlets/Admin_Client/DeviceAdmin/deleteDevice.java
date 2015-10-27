package accessToDataBase.Servlets.Admin_Client.DeviceAdmin;

import accessToDataBase.JDBC.Admin.DeviceAdmin.deleteDeviceImpl;
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
public class deleteDevice extends HttpServlet {

    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final deleteDeviceImpl devicesDB = (deleteDeviceImpl) appContext.getBean("deleteDeviceAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        Map<String, String> message = null;
        String[] id = (String[]) reqMap.get("deviceId");
        if(adminTable.get("deviceAdmin")){


            message = devicesDB.delete(Integer.parseInt(id[0]));

        }else {
            message = new HashMap<String, String>();
            message.put("message", "wrong pass");
        }

        //log

        Map<String, String> a = adminClientMethods.deviceLog(

            "",
            "",
            adminName[0],
            "",
            message.get("message").contains("Success") ? "true" : "false",

            /*
            String device_id,
            String device_ip,
            String device_specification,
             */
            id[0],
            "",
            "",

            /*
            String datetime,
            String event_type,
             */

            Date.valueOf(LocalDate.now()).toString(),
            "delete",

            /*
            String device_mac,
            String description*/

            "",
            ""
        );
        System.out.println(a.entrySet());

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
