package accessToDataBase.Servlets.admin.client.Security;

import Methods.POJO.AdminClient.Security.AccountDATA;
import Methods.POJO.AdminClient.Security.AccountDATA_withPassword;
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
public class Account extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        String header = req.getHeader("Type");
        Gson gson = new Gson();

        AccountDATA accountDATA = null;
        AccountDATA_withPassword accountDATA_withPassword = null;
        List<AccountDATA> accountDATAList = null;

        if (header.compareTo("create") == 0){
            accountDATA_withPassword = gson.fromJson(json, AccountDATA_withPassword.class);
            accountDATA_withPassword = Spring_DAO.ACCOUNT_DAO.create(accountDATA_withPassword);
        }
        if (header.compareTo("update") == 0){
            accountDATA = gson.fromJson(json, AccountDATA.class);
            accountDATA = Spring_DAO.ACCOUNT_DAO.update(accountDATA);
        }
        if (header.compareTo("delete") == 0){
            accountDATA = gson.fromJson(json, AccountDATA.class);
            accountDATA = Spring_DAO.ACCOUNT_DAO.delete(accountDATA);
        }
        if (header.compareTo("read") == 0){
            accountDATA = gson.fromJson(json, AccountDATA.class);
            accountDATAList = Spring_DAO.ACCOUNT_DAO.read(accountDATA);
            accountDATA = null;
        }

        if (accountDATA !=null && (header.compareTo("update") == 0 || header.compareTo("delete") == 0)){
            json = gson.toJson(accountDATA);
            resp.addHeader("Status", "OK");
        }
        if (accountDATA_withPassword != null && header.compareTo("create") == 0){
            json = gson.toJson(accountDATA_withPassword);
            resp.addHeader("Status", "OK");
        }
        if (accountDATAList != null && header.compareTo("read") == 0){
            json = gson.toJson(accountDATAList);
            resp.addHeader("Status", "OK");
        }
        if (accountDATA == null && accountDATA_withPassword == null && accountDATAList == null){
            json = "";
            resp.addHeader("Status", "FAIL");
        }

        PrintWriter out = resp.getWriter();
        out.write(json);
    }
}
