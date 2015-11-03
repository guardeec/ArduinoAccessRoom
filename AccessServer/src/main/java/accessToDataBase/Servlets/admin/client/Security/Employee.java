package accessToDataBase.Servlets.admin.client.Security;

import Methods.POJO.AdminClient.HR.EmployeeDATA;
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
public class Employee extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().readLine();
        Gson gson = new Gson();

        EmployeeDATA employeeDATA = gson.fromJson(json, EmployeeDATA.class);
        List<EmployeeDATA> employeeDATAList = Spring_DAO.SECURITY_HELPER_DAO.readEmployers(employeeDATA);

        if (employeeDATAList!=null){
            json = gson.toJson(employeeDATAList);
            resp.addHeader("Status", "OK");
        }else {
            json = "";
            resp.addHeader("Status", "FAIL");
        }

        PrintWriter out = resp.getWriter();
        out.write(json);
    }
}
