package services;

import Methods.POJO.AdminClient.AdminClientSessionDATA;
import Methods.POJO.AdminClient.HR.EmployeeDATA;
import Methods.POJO.AdminClient.Reception.GuestDATA;
import Methods.POJO.AdminClient.Security.AccountDATA;
import Methods.POJO.AdminClient.Security.CardDATA;
import Methods.POJO.AdminClient.Security.RightDATA;
import Methods.POJO.AdminClient.Security.RoleDATA;
import Methods.POJO.AdminClient.Technical.DeviceDATA;
import Methods.POJO.ArduinoClient.ConnectionErrorDATA;
import Methods.POJO.ArduinoClient.LocalErrorDATA;
import Methods.POJO.ArduinoClient.RoomDATA;
import Methods.POJO.ArduinoClient.UnuathorizedAccessDATA;
import Methods.POJO.UserClient.UserClientSessionDATA;
import JDBC_Impl.Spring_DAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by root on 28.10.15.
 */
public class addLog extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String object = req.getReader().readLine();
        String objectType = req.getHeader("ObjectType");
        Gson gson = new Gson();

        System.out.println("------------------------------------------");
        System.out.println(object);
        System.out.println(objectType);
        /*
        Admin Client
         */
        if (objectType.compareTo(EmployeeDATA.class.getName())==0){
            EmployeeDATA employee = gson.fromJson(object, EmployeeDATA.class);
            object = Spring_DAO.GENERAL_DAO.log(employee);
            if (!object.contains("Error")){
                object = Spring_DAO.EMPLOYEE_DAO.log(employee);
            }
        }
        if (objectType.compareTo(GuestDATA.class.getName())==0){
            GuestDATA guest = gson.fromJson(object, GuestDATA.class);
            object = Spring_DAO.GENERAL_DAO.log(guest);
            if (!object.contains("Error")){
                object = Spring_DAO.GUEST_DAO.log(guest);
            }
        }
        if (objectType.compareTo(AccountDATA.class.getName())==0){
            AccountDATA account = gson.fromJson(object, AccountDATA.class);
            object = Spring_DAO.GENERAL_DAO.log(account);
            if (!object.contains("Error")){
                object = Spring_DAO.ACCOUNT_DAO.log(account);
            }
        }
        if (objectType.compareTo(CardDATA.class.getName())==0){
            CardDATA card = gson.fromJson(object, CardDATA.class);
            object = Spring_DAO.GENERAL_DAO.log(card);
            if (!object.contains("Error")){
                object = Spring_DAO.CARD_DAO.log(card);
            }
        }
        if (objectType.compareTo(RightDATA.class.getName())==0){
            RightDATA right = gson.fromJson(object, RightDATA.class);
            object = Spring_DAO.GENERAL_DAO.log(right);
            if (!object.contains("Error")){
                object = Spring_DAO.RIGHT_DAO.log(right);
            }
        }
        if (objectType.compareTo(RoleDATA.class.getName())==0){
            RoleDATA role = gson.fromJson(object, RoleDATA.class);
            object = Spring_DAO.GENERAL_DAO.log(role);
            if (!object.contains("Error")){
                object = Spring_DAO.ROLE_DAO.log(role);
            }
        }
        if (objectType.compareTo(DeviceDATA.class.getName())==0){
            DeviceDATA device = gson.fromJson(object, DeviceDATA.class);
            object = Spring_DAO.GENERAL_DAO.log(device);
            if (!object.contains("Error")){
                object = Spring_DAO.DEVICE_DAO.log(device);
            }
        }
        if (objectType.compareTo(AdminClientSessionDATA.class.getName())==0){
            AdminClientSessionDATA session = gson.fromJson(object, AdminClientSessionDATA.class);
            object = Spring_DAO.GENERAL_DAO.log(session);
            if (!object.contains("Error")){
                object = Spring_DAO.ENTER_OR_OUT_ADMIN_DAO.log(session);
            }
        }

        /*
        Arduino Client
         */
        if (objectType.compareTo(LocalErrorDATA.class.getName())==0){
            LocalErrorDATA localError = gson.fromJson(object, LocalErrorDATA.class);
            object = Spring_DAO.GENERAL_DAO.log(localError);
        }
        if (objectType.compareTo(ConnectionErrorDATA.class.getName())==0){
            ConnectionErrorDATA connectionError = gson.fromJson(object, ConnectionErrorDATA.class);
            object = Spring_DAO.GENERAL_DAO.log(connectionError);
            System.out.println("RRRRRRRRR:   "+object);
        }
        if (objectType.compareTo(RoomDATA.class.getName())==0){
            RoomDATA room = gson.fromJson(object, RoomDATA.class);
            object = Spring_DAO.GENERAL_DAO.log(room);
            if (!object.contains("Error")){
                object = Spring_DAO.ENTER_OT_OUT_THE_ROOM_DAO.log(room);
            }
        }
        if (objectType.compareTo(UnuathorizedAccessDATA.class.getName())==0){
            UnuathorizedAccessDATA fail = gson.fromJson(object, UnuathorizedAccessDATA.class);
            object = Spring_DAO.GENERAL_DAO.log(fail);
        }

        /*
        User client
         */
        if (objectType.compareTo(UserClientSessionDATA.class.getName())==0){
            UserClientSessionDATA session = gson.fromJson(object, UserClientSessionDATA.class);
            object = Spring_DAO.GENERAL_DAO.log(session);
            if (!object.contains("Error")){
                object = Spring_DAO.ENTER_OR_OUT_THE_SYSTEM_DAO.log(session);
            }
        }

        /*
        Ответ
         */
        if (object.contains("Success")){
            resp.addHeader("Status", "OK");
        }else {
            resp.addHeader("Status", "FAIL");
        }
    }
}
