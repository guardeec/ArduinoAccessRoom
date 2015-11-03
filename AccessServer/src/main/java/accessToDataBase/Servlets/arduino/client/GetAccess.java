package accessToDataBase.Servlets.arduino.client;

import Methods.POJO.AdminClient.Technical.DeviceDATA;
import Methods.POJO.ArduinoClient.AdminDBCashDATA;
import Methods.POJO.ArduinoClient.RoomDATA_withAccess;
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
public class GetAccess extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        Gson gson = new Gson();

        RoomDATA_withAccess roomDATA_withAccess = gson.fromJson(json, RoomDATA_withAccess.class);
        roomDATA_withAccess = Spring_DAO.GET_ACCESS_DAO.read(roomDATA_withAccess);

        if (roomDATA_withAccess!=null){
            json = gson.toJson(roomDATA_withAccess);
            resp.addHeader("Status", "OK");
        } else {
            json = "";
            resp.setHeader("Status", "FAIL");
        }

        PrintWriter out = resp.getWriter();
        out.write(json);
    }
}
