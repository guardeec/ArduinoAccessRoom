package Servlets;

import DB_Object.Syslog;
import JDBC.DAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Kolomeec on 28.04.2015.
 */
@WebServlet(name = "LogGet")
public class LogGet extends HttpServlet {

    ApplicationContext context = new ClassPathXmlApplicationContext("JDBC-config.xml");
    DAO logging = (DAO) context.getBean("Syslog_DAO");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Syslog> syslog =  logging.Get("");

        String message = "";
        for(int i=0; i<syslog.size(); i++){
            message+=syslog.get(i).GetDate()+syslog.get(i).GetType()+syslog.get(i).GetHostname()+syslog.get(i).GetMessage()+"\n";
        }

        PrintWriter out = response.getWriter();
        out.println(message);

    }
}
