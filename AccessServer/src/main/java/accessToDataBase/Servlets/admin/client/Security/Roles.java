package accessToDataBase.Servlets.admin.client.Security;

import Methods.POJO.AdminClient.Security.RoleDATA;
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
public class Roles extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        String header = req.getHeader("Type");
        Gson gson = new Gson();

        RoleDATA roleDATA = null;
        List<RoleDATA> roleDATAList = null;

        if (header.compareTo("create") == 0){
            roleDATA = gson.fromJson(json, RoleDATA.class);
            roleDATA = Spring_DAO.ROLES_DAO.create(roleDATA);
        }
        if (header.compareTo("update") == 0){
            roleDATA = gson.fromJson(json, RoleDATA.class);
            roleDATA = Spring_DAO.ROLES_DAO.update(roleDATA);
        }
        if (header.compareTo("delete") == 0){
            roleDATA = gson.fromJson(json, RoleDATA.class);
            roleDATA = Spring_DAO.ROLES_DAO.delete(roleDATA);
        }
        if (header.compareTo("read") == 0){
            roleDATA = gson.fromJson(json, RoleDATA.class);
            roleDATAList = Spring_DAO.ROLES_DAO.read(roleDATA);
            roleDATA = null;
        }

        if (roleDATA!=null && header.compareTo("read") != 0){
            json = gson.toJson(roleDATA);
            resp.addHeader("Status", "OK");
        }
        if (roleDATAList!=null && header.compareTo("read") == 0){
            json = gson.toJson(roleDATAList);
            resp.addHeader("Status", "OK");
        }
        if (roleDATA == null && roleDATAList == null){
            json = "";
            resp.addHeader("Status", "FAIL");
        }

        PrintWriter out = resp.getWriter();
        out.write(json);
    }
}
