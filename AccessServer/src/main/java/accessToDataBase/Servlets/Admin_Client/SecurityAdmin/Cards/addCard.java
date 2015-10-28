package accessToDataBase.Servlets.Admin_Client.SecurityAdmin.Cards;

import accessToDataBase.JDBC.Admin.SecurityAdmin.Cards.addCardImpl;
import accessToDataBase.JDBC.Authorisation.getAdminTypeImpl;
import log.adminClientMethods;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

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
 * Created by root on 11.08.15.
 */
public class addCard extends HttpServlet {
    private final ApplicationContext appContext = new ClassPathXmlApplicationContext("accessToDataBase/JDBC_config.xml");
    private final addCardImpl cardDB = (addCardImpl) appContext.getBean("addCardSecurityAdmin");
    private final getAdminTypeImpl authorisationDB = (getAdminTypeImpl) appContext.getBean("getAdminType");

//     private static ApplicationContext appSecurityContext = new ClassPathXmlApplicationContext("securityco.xml");
//     private static Md5PasswordEncoder passwordEncoder = (Md5PasswordEncoder) appSecurityContext.getBean("passwordEncoder");


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Map reqMap = request.getParameterMap();
        String[] adminName = (String[]) reqMap.get("adminName");
        String[] adminPassword = (String[]) reqMap.get("adminPassword");
//        adminPassword[0] = passwordEncoder.encodePassword(adminPassword[0], salt.salt);

        Map<String, Boolean> adminTable = authorisationDB.get(adminName[0], adminPassword[0]);
        Map<String, String> message = null;
        String[] card = (String[]) reqMap.get("cardNumber");
        if(adminTable.get("securityAdmin")){

            message = cardDB.add(card[0]);
        }else {
            message = new HashMap<String, String>();
            message.put("message", "wrong pass");
        }

        //log
        Map<String, String> a = adminClientMethods.cardLog(
            /*
            String host_ip,
            String host_mac,

            String login_name,
            String login_id,

            String result_type,

            String card_id,

            String datetime,

            String event_type,
            String description
         */
            "",
            "",

            adminName[0],
            "",

            message.get("message").contains("Success") ? "true" : "false",

            card[0],

            java.sql.Date.valueOf(LocalDate.now()).toString(),

            "create",
            ""
        );
        System.out.println(a.entrySet());

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
