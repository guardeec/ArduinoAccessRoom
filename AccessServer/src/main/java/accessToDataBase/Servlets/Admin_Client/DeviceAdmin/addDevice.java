package accessToDataBase.Servlets.Admin_Client.DeviceAdmin;

import accessToDataBase.JDBC.Admin.DeviceAdmin.addDeviceImpl;
import accessToDataBase.JDBC.Authorisation.getAdminTypeImpl;
import log.adminClientMethods;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 06.08.15.
 */
public class addDevice extends HttpServlet {

    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final addDeviceImpl devicesDB = (addDeviceImpl) appContext.getBean("addDeviceAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        Map<String, String> message = null;
        String[] ip = (String[]) reqMap.get("ip");
        String[] specification = (String[]) reqMap.get("specification");
        if(adminTable.get("deviceAdmin")){
            message = devicesDB.add(specification[0], ip[0]);
        }else {
            message = new HashMap<String, String>();
            message.put("message", "wrong pass");
        }

        //log

        try{
            Map<String, String> a = adminClientMethods.deviceLog(
                    "", //host_ip
                    "", //host_mac
                    adminName[0],//login_name
                    "",//login_id
                    message.get("message").contains("Success") ? "true" : "false",//result_type
                    message.containsKey("id") ? message.get("id") : "",//device_id
                    "",//device_ip
                    specification[0],//device_specification
                    Date.valueOf(LocalDate.now()).toString(),//datetime
                    "create",//event_type
                    "",//device_mac
                    ""//description
            );
        }catch (HTTPException ignored){
        }


        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
