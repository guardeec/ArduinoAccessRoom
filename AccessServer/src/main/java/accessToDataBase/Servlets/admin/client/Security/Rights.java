package accessToDataBase.Servlets.admin.client.Security;

import Methods.POJO.AdminClient.Security.RightDATA;
import Methods.POJO.AdminClient.Technical.DeviceDATA;
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
public class Rights extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        String header = req.getHeader("Type");
        Gson gson = new Gson();

        RightDATA rightDATA = null;
        List<RightDATA> rightDATAList = null;

        if (header.compareTo("read") == 0){
            rightDATA = gson.fromJson(json, RightDATA.class);
            rightDATA = Spring_DAO.RIGHTS_DAO.read(rightDATA);
        }
        if (header.compareTo("update") == 0){
            rightDATA = gson.fromJson(json, RightDATA.class);
            rightDATA = Spring_DAO.RIGHTS_DAO.update(rightDATA);
        }
        if (header.compareTo("readAll") == 0){
            rightDATA = gson.fromJson(json, RightDATA.class);
            rightDATAList = Spring_DAO.RIGHTS_DAO.readAllRights(rightDATA);
            rightDATA = null;
        }

        if (rightDATA!=null && header.compareTo("readAll") != 0){
            json = gson.toJson(rightDATA);
            resp.addHeader("Status", "OK");
        }
        if (rightDATAList!=null && header.compareTo("readAll") == 0){
            json = gson.toJson(rightDATAList);
            resp.addHeader("Status", "OK");
        }
        if (rightDATAList == null && rightDATA == null){
            json = "";
            resp.addHeader("Status", "FAIL");
        }

        PrintWriter out = resp.getWriter();
        out.write(json);
    }
}
