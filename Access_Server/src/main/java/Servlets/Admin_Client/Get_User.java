package Servlets.Admin_Client;

import JDBC.Admin;
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
 * Created by guardeec on 21.05.15.
 */
public class Get_User extends HttpServlet {

    ApplicationContext appContext = new ClassPathXmlApplicationContext("JDBC_config.xml");
    Admin search = (Admin) appContext.getBean("Admin_DAO");


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map reqMap = request.getParameterMap();
        String[] user_id = (String[]) reqMap.get("user_id");
        String[] user_name = (String[]) reqMap.get("user_name");
        String[] user_role_id = (String[]) reqMap.get("user_role_id");
        String[] user_role_title = (String[]) reqMap.get("user_role_title");
        String[] user_card = (String[]) reqMap.get("user_card");
        String[] card_state = (String[]) reqMap.get("card_state");

        ArrayList<Map> message;

        System.out.print(
        Parsing.IntegerIsNull(user_id[0])+" " +
                user_name[0]+" " +
                Parsing.IntegerIsNull(user_role_id[0])+" " +
                user_role_title[0]+" " +
                user_card[0]+" " +
                Parsing.BooleanIsNull(card_state[0]));

        message = search.Search(    Parsing.IntegerIsNull(user_id[0]),
                                    Parsing.StringIsNull(user_name[0]),
                                    Parsing.IntegerIsNull(user_role_id[0]),
                                    Parsing.StringIsNull(user_role_title[0]),
                                    Parsing.StringIsNull(user_card[0]),
                                    Parsing.BooleanIsNull(card_state[0])
        );

        PrintWriter out = response.getWriter();
        for(Map i:message){
            out.println(i.entrySet().toString()+"\n");
        }
    }
}
