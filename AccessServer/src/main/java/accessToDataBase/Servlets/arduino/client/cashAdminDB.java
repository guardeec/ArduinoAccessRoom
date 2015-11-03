package accessToDataBase.Servlets.arduino.client;

import Methods.POJO.AdminClient.Technical.DeviceDATA;
import Methods.POJO.ArduinoClient.AdminDBCashDATA;
import accessToDataBase.JDBC.arduino.client.CashAdminDB;
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
public class cashAdminDB extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        Gson gson = new Gson();

        DeviceDATA deviceDATA = gson.fromJson(json, DeviceDATA.class);
        List<AdminDBCashDATA> listOfAdmins = Spring_DAO.CASH_ADMIN_DB_DAO.read(deviceDATA);

        if (listOfAdmins!=null){
            json = gson.toJson(listOfAdmins);
            resp.addHeader("Status", "OK");
        } else {
            json = "";
            resp.setHeader("Status", "FAIL");
        }

        PrintWriter out = resp.getWriter();
        out.write(json);
    }
}
