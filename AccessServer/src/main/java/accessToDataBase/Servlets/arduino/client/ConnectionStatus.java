package accessToDataBase.Servlets.arduino.client;

import Methods.POJO.AdminClient.Technical.DeviceDATA;
import Methods.POJO.ArduinoClient.AdminDBCashDATA;
import Methods.POJO.ArduinoClient.ConnectionStatusDATA;
import accessToDataBase.JDBC_Impl.Spring_DAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by root on 03.11.15.
 */
public class ConnectionStatus extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        Gson gson = new Gson();

        ConnectionStatusDATA connectionStatusDATA = gson.fromJson(json, ConnectionStatusDATA.class);
        Spring_DAO.CONNECTION_STATUS_DAO.create(connectionStatusDATA);

        resp.addHeader("Status", "OK");
    }
}
