package accessToDataBase.Servlets.Authorisation;

import accessToDataBase.JDBC.Authorisation.checkRunnableImpl;
import accessToDataBase.JDBC.Authorisation.getAdminTypeImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by root on 12.08.15.
 */
public class getAdminType extends HttpServlet {
    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPasswordHash = (String[]) reqMap.get("adminPasswordHash");

        Map<String, Boolean> message = authorisationDB.get(adminName[0], adminPasswordHash[0]);

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
