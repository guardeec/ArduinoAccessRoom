package Data;

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
import Methods.POJO.PojoObject;
import Methods.POJO.UserClient.UserClientSessionDATA;

import java.net.Inet4Address;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * Created by root on 29.10.15.
 */
public class SyslogData {
    public static final String URL_SYSLOG = "http://192.168.1.96:8080/SyslogServer/addLog";

    private static EmployeeDATA employeeDATA = new EmployeeDATA();
    private static GuestDATA guestDATA = new GuestDATA();
    private static AccountDATA accountDATA = new AccountDATA();
    private static CardDATA cardDATA = new CardDATA();
    private static RightDATA rightDATA = new RightDATA();
    private static RoleDATA roleDATA = new RoleDATA();
    private static DeviceDATA deviceDATA = new DeviceDATA();
    private static AdminClientSessionDATA adminClientSessionDATA = new AdminClientSessionDATA();

    private static ConnectionErrorDATA connectionErrorDATA = new ConnectionErrorDATA();
    private static LocalErrorDATA localErrorDATA = new LocalErrorDATA();
    private static RoomDATA roomDATA = new RoomDATA();
    private static UnuathorizedAccessDATA unuathorizedAccessDATA = new UnuathorizedAccessDATA();

    private static UserClientSessionDATA userClientSessionDATA = new UserClientSessionDATA();

    private static PojoObject setGeneralAndSourceParams(PojoObject object){
        object.setGeneralParams(null,null,true,"THIS IS TEST");
        object.setSourceParams(null, "ip","mac",null,null,null);
        return object;
    }

    /*
    ClientAdmin
     */

    public static EmployeeDATA getEmployeeDATA() {
        employeeDATA = (EmployeeDATA) setGeneralAndSourceParams(employeeDATA);
        employeeDATA.setSpecificParams(0,"ghost",0,"ghost",0,"ghost_title",0);
        employeeDATA.setGeneral_event_type_id(JDBC.SyslogData.Event_Types.hr_dep.create);
        employeeDATA.setSource_type_id(JDBC.SyslogData.Source_Types.adminClient);
        return employeeDATA;
    }

    public static GuestDATA getGuestDATA() {
        guestDATA = (GuestDATA) setGeneralAndSourceParams(guestDATA);
        guestDATA.setSpecificParams(0, "ghost", 0, Date.valueOf(LocalDate.now().toString()), Date.valueOf(LocalDate.now().toString()));
        guestDATA.setGeneral_event_type_id(JDBC.SyslogData.Event_Types.rec_dep.create);
        guestDATA.setSource_type_id(JDBC.SyslogData.Source_Types.adminClient);
        return guestDATA;
    }

    public static AccountDATA getAccountDATA() {
        accountDATA = (AccountDATA) setGeneralAndSourceParams(accountDATA);
        accountDATA.setSpecificParams(0,"ghost",0,"ghost","S");
        accountDATA.setGeneral_event_type_id(JDBC.SyslogData.Event_Types.sec_dep_acc.create);
        accountDATA.setSource_type_id(JDBC.SyslogData.Source_Types.adminClient);
        return accountDATA;
    }

    public static CardDATA getCardDATA() {
        cardDATA = (CardDATA) setGeneralAndSourceParams(cardDATA);
        cardDATA.setSpecificParams(0);
        cardDATA.setGeneral_event_type_id(JDBC.SyslogData.Event_Types.sec_dep_cards.create);
        cardDATA.setSource_type_id(JDBC.SyslogData.Source_Types.adminClient);
        return cardDATA;
    }

    public static RightDATA getRightDATA() {
        rightDATA = (RightDATA) setGeneralAndSourceParams(rightDATA);
        rightDATA.setSpecificParams(0,"ghost",0,"ghost",true);
        rightDATA.setGeneral_event_type_id(JDBC.SyslogData.Event_Types.sec_dep_acs_r);
        rightDATA.setSource_type_id(JDBC.SyslogData.Source_Types.adminClient);
        return rightDATA;
    }

    public static RoleDATA getRoleDATA() {
        roleDATA = (RoleDATA) setGeneralAndSourceParams(roleDATA);
        roleDATA.setSpecificParams(0,"ghost");
        roleDATA.setGeneral_event_type_id(JDBC.SyslogData.Event_Types.sec_dep_sys_r.create);
        roleDATA.setSource_type_id(JDBC.SyslogData.Source_Types.adminClient);
        return roleDATA;
    }

    public static DeviceDATA getDeviceDATA() {
        deviceDATA = (DeviceDATA) setGeneralAndSourceParams(deviceDATA);
        deviceDATA.setSpecificParams(0,"0.0.0.0","00:00:00:00:00","ghost",true,"ghost");
        deviceDATA.setGeneral_event_type_id(JDBC.SyslogData.Event_Types.tech_dep.create);
        deviceDATA.setSource_type_id(JDBC.SyslogData.Source_Types.adminClient);
        return deviceDATA;
    }

    public static AdminClientSessionDATA getAdminClientSessionDATA() {
        adminClientSessionDATA = (AdminClientSessionDATA) setGeneralAndSourceParams(adminClientSessionDATA);
        adminClientSessionDATA.setSpecificParams(0,"ghost","S");
        adminClientSessionDATA.setGeneral_event_type_id(JDBC.SyslogData.Event_Types.adm_cl_auth.login);
        adminClientSessionDATA.setSource_type_id(JDBC.SyslogData.Source_Types.adminClient);
        return adminClientSessionDATA;
    }

    /*
    Arduino
     */
    public static ConnectionErrorDATA getConnectionErrorDATA() {
        connectionErrorDATA = (ConnectionErrorDATA) setGeneralAndSourceParams(connectionErrorDATA);
        connectionErrorDATA.setGeneral_event_type_id(JDBC.SyslogData.Event_Types.ard_conn_err);
        connectionErrorDATA.setSource_type_id(JDBC.SyslogData.Source_Types.arduinoClient);
        return connectionErrorDATA;
    }

    public static LocalErrorDATA getLocalErrorDATA() {
        localErrorDATA = (LocalErrorDATA) setGeneralAndSourceParams(localErrorDATA);
        localErrorDATA.setGeneral_event_type_id(JDBC.SyslogData.Event_Types.ard_local_err.in_work);
        localErrorDATA.setSource_type_id(JDBC.SyslogData.Source_Types.arduinoClient);
        return localErrorDATA;
    }

    public static RoomDATA getRoomDATA() {
        roomDATA = (RoomDATA) setGeneralAndSourceParams(roomDATA);
        roomDATA.setSpecificParams(0,"ghost",0,"ghost",0);
        roomDATA.setGeneral_event_type_id(JDBC.SyslogData.Event_Types.room_auth.login);
        roomDATA.setSource_type_id(JDBC.SyslogData.Source_Types.arduinoClient);
        return roomDATA;
    }

    public static UnuathorizedAccessDATA getUnuathorizedAccessDATA() {
        unuathorizedAccessDATA = (UnuathorizedAccessDATA) setGeneralAndSourceParams(unuathorizedAccessDATA);
        unuathorizedAccessDATA.setGeneral_event_type_id(JDBC.SyslogData.Event_Types.ard_unauth);
        unuathorizedAccessDATA.setSource_type_id(JDBC.SyslogData.Source_Types.arduinoClient);
        return unuathorizedAccessDATA;
    }

    public static UserClientSessionDATA getUserClientSessionDATA() {
        userClientSessionDATA = (UserClientSessionDATA) setGeneralAndSourceParams(userClientSessionDATA);
        userClientSessionDATA.setSpecificParams(0,"ghost",0,"ghostOS");
        userClientSessionDATA.setGeneral_event_type_id(JDBC.SyslogData.Event_Types.os_auth.login);
        userClientSessionDATA.setSource_type_id(JDBC.SyslogData.Source_Types.hostAgent);
        return userClientSessionDATA;
    }
}
