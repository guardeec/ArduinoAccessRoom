package Servlets.Admin_Client;

import JDBC.Admin;
import JDBC.DAO_Admin;
import String_Methods.Parsing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by guardeec on 17.05.15.
 */
public class Add_User extends HttpServlet {

    ApplicationContext appContext = new ClassPathXmlApplicationContext("JDBC_config.xml");
    Admin add = (Admin) appContext.getBean("Admin_DAO");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] user_name = (String[]) reqMap.get("user_name");
        String[] user_card = (String[]) reqMap.get("user_card");

        ArrayList<Map> message = add.Add(   user_name[0],
                                            user_card[0]
        );

        PrintWriter out = response.getWriter();
        out.println(message.get(0).entrySet().toString());
    }

}
