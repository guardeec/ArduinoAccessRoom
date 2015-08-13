package accessToDataBase.Servlets.Authorisation;

import accessToDataBase.JDBC.Authorisation.checkRunnableImpl;
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
public class checkRunnable extends HttpServlet {
    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final checkRunnableImpl authorisationDB = (checkRunnableImpl) appContext.getBean("checkRunnable");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        Map<String, Boolean> message = authorisationDB.check();
        message.put("Server", true);

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
