package Servlets;

import DB_Object.Syslog;
import JDBC.DAO;
import methods.Parsing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Kolomeec on 28.04.2015.
 */
@WebServlet(name = "LogAdd")
public class LogAdd extends HttpServlet {

    ApplicationContext context = new ClassPathXmlApplicationContext("JDBC-config.xml");
    DAO logging = (DAO) context.getBean("Syslog_DAO");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] Request = Parsing.parse(request.getQueryString());
        //[0] - Date
        //[1] - Type
        //[2] - Host
        //[3] - Message

        String[] Content = Parsing.parseContent(Request[3]);
        //[0] - user
        //[1] - status

        logging.Add(new Syslog(Request[0], Request[1], Request[2], Request[3]));

        PrintWriter out = response.getWriter();
        out.println("Added");
    }
}
