package accessToDataBase.Servlets.admin.client.Security;

import Methods.POJO.AdminClient.Security.CardDATA;
import Methods.POJO.AdminClient.Security.CardDATA_withCardNumber;
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
public class Cards extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        String header = req.getHeader("Type");
        Gson gson = new Gson();

        CardDATA cardDATA = null;
        CardDATA_withCardNumber cardDATA_withCardNumber = null;
        List<CardDATA> cardDATAList = null;

        if (header.compareTo("create") == 0){
            cardDATA_withCardNumber = gson.fromJson(json, CardDATA_withCardNumber.class);
            cardDATA_withCardNumber = Spring_DAO.CARDS_DAO.create(cardDATA_withCardNumber);
        }
        if (header.compareTo("delete") == 0){
            cardDATA = gson.fromJson(json, CardDATA.class);
            cardDATA = Spring_DAO.CARDS_DAO.delete(cardDATA);
        }
        if (header.compareTo("read") == 0){
            cardDATA = gson.fromJson(json, CardDATA.class);
            cardDATAList = Spring_DAO.CARDS_DAO.read(cardDATA);
            cardDATA = null;
        }

        if (cardDATA !=null && header.compareTo("delete")==0){
            json = gson.toJson(cardDATA);
            resp.addHeader("Status", "OK");
        }
        if (cardDATA_withCardNumber != null && header.compareTo("create") == 0){
            json = gson.toJson(cardDATA_withCardNumber);
            resp.addHeader("Status", "OK");
        }
        if (cardDATAList != null && header.compareTo("read") == 0){
            json = gson.toJson(cardDATAList);
            resp.addHeader("Status", "OK");
        }
        if (cardDATAList == null && cardDATA_withCardNumber == null && cardDATA == null){
            json = "";
            resp.addHeader("Status", "FAIL");
        }

        PrintWriter out = resp.getWriter();
        out.write(json);
    }
}
