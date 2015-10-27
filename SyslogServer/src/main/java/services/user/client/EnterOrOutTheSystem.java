package services.user.client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public class EnterOrOutTheSystem extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map reqMap = request.getParameterMap();
        String[] host_ip = (String[]) reqMap.get("host_ip");
        String[] host_mac = (String[]) reqMap.get("host_mac");
        String[] user_id = (String[]) reqMap.get("user_id");
        String[] event_type = (String[]) reqMap.get("event_type");
        String[] datetime = (String[]) reqMap.get("datetime");

        Map<String, String> message = null;

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}
