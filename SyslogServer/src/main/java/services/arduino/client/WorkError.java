package services.arduino.client;

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
public class WorkError extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map reqMap = request.getParameterMap();
        String[] device_id = (String[]) reqMap.get("device_id");
        String[] device_ip = (String[]) reqMap.get("device_ip");
        String[] client_error_type = (String[]) reqMap.get("client_error_type");
        String[] arduino_error_type = (String[]) reqMap.get("arduino_error_type");
        String[] datetime = (String[]) reqMap.get("datetime");

        Map<String, String> message = null;

        PrintWriter out = response.getWriter();
        out.println(message.entrySet());
    }
}