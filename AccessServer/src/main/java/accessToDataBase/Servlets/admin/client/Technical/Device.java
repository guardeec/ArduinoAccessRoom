package accessToDataBase.Servlets.admin.client.Technical;

import Methods.POJO.AdminClient.Technical.DeviceDATA;
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
public class Device extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        String header = req.getHeader("Type");
        Gson gson = new Gson();

        DeviceDATA deviceDATA = null;
        List<DeviceDATA> deviceDATAList = null;

        if (header.compareTo("create") == 0){
            deviceDATA = gson.fromJson(json, DeviceDATA.class);
            deviceDATA = Spring_DAO.DEVICE_DAO.create(deviceDATA);
        }
        if (header.compareTo("update") == 0){
            deviceDATA = gson.fromJson(json, DeviceDATA.class);
            deviceDATA = Spring_DAO.DEVICE_DAO.update(deviceDATA);
        }
        if (header.compareTo("delete") == 0){
            deviceDATA = gson.fromJson(json, DeviceDATA.class);
            deviceDATA = Spring_DAO.DEVICE_DAO.delete(deviceDATA);
        }
        if (header.compareTo("read") == 0){
            deviceDATA = gson.fromJson(json, DeviceDATA.class);
            deviceDATAList = Spring_DAO.DEVICE_DAO.read(deviceDATA);
        }

        if (deviceDATA!=null && header.compareTo("read") != 0){
            json = gson.toJson(deviceDATA);
            resp.addHeader("Status", "OK");
        }
        if (deviceDATAList!=null && header.compareTo("read") == 0){
            json = gson.toJson(deviceDATAList);
            resp.addHeader("Status", "OK");
        }
        if (deviceDATA == null && deviceDATAList == null){
            json = "";
            resp.addHeader("Status", "FAIL");
        }

        PrintWriter out = resp.getWriter();
        out.write(json);
    }
}
