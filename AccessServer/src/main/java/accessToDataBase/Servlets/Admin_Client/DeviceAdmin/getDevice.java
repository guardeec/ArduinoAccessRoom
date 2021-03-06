package accessToDataBase.Servlets.Admin_Client.DeviceAdmin;

import accessToDataBase.JDBC.Admin.DeviceAdmin.getDeviceImpl;
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
 * Created by root on 06.08.15.
 */
public class getDevice extends HttpServlet {

    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final getDeviceImpl devicesDB = (getDeviceImpl) appContext.getBean("getDeviceAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        List<Map<String, String>> message/* = new ArrayList<>()*/;
        if(adminTable.get("deviceAdmin")){
            String[] id = (String[]) reqMap.get("deviceId");
            String[] specification = (String[]) reqMap.get("specification");
            String[] ip = (String[]) reqMap.get("ip");

            message = devicesDB.get(    id[0].isEmpty() ? null : Integer.parseInt(id[0]),
                                        specification[0].isEmpty() ? null : specification[0] ,
                                        ip[0].isEmpty() ? null : ip[0]
            );

        }else {
            message = new ArrayList<>();
            Map<String, String> m = new HashMap<String, String>();
            m.put("message", "wrong pass");
            message.add(m);
        }

        PrintWriter out = response.getWriter();
        for(int i=0; i<message.size(); i++){
            out.println(message.get(i).entrySet());
        }
    }
}
