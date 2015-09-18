package accessToDataBase.Servlets.Admin_Client.DeviceAdmin;

import accessToDataBase.JDBC.Admin.DeviceAdmin.changeDeviceImpl;
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
public class changeDevice extends HttpServlet {

    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final changeDeviceImpl devicesDB = (changeDeviceImpl) appContext.getBean("changeDeviceAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        Map<String, String> message;
        if(adminTable.get("deviceAdmin")){
            String[] id = (String[]) reqMap.get("deviceId");
            String[] ip = (String[]) reqMap.get("ip");
            String[] specification = (String[]) reqMap.get("specification");

            message = devicesDB.change( Integer.parseInt(id[0]),
                                        specification[0],
                                        ip[0]
            );

        }else {
            message = new HashMap<String, String>();
            message.put("message", "Error - wrong pass");
        }

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
