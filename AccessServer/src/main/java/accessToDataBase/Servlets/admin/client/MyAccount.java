package accessToDataBase.Servlets.admin.client;

import Methods.POJO.AdminClient.Security.AccountDATA_withPassword;
import accessToDataBase.JDBC_Impl.Spring_DAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by root on 03.11.15.
 */
public class MyAccount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        String header = req.getHeader("Type");
        Gson gson = new Gson();

        AccountDATA_withPassword accountDATA_withPassword = gson.fromJson(json, AccountDATA_withPassword.class);
        if (header.compareTo("read")==0){
            accountDATA_withPassword = Spring_DAO.MY_ACCOUNT_DAO.read(accountDATA_withPassword);
        }
        if (header.compareTo("update")==0){
            accountDATA_withPassword = Spring_DAO.MY_ACCOUNT_DAO.update(accountDATA_withPassword);
        }

        if (accountDATA_withPassword!=null){
            json = gson.toJson(accountDATA_withPassword);
            resp.addHeader("Status", "OK");
        } else {
            json = "";
            resp.setHeader("Status", "FAIL");
        }

        PrintWriter out = resp.getWriter();
        out.write(json);
    }
}
