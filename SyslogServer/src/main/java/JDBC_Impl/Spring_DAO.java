package JDBC_Impl;

import JDBC.arduino.client.ConnectionError;
import JDBC.arduino.client.LocalError;
import JDBC_Impl.arduino.client.ConnectionErrorImpl;
import JDBC_Impl.arduino.client.LocalErrorImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by root on 23.10.15.
 */
public interface Spring_DAO {
    ApplicationContext appContext = new ClassPathXmlApplicationContext("JDBC_config.xml");
    JDBC_Impl.GeneralImpl GENERAL_DAO                                           = (JDBC.General) appContext.getBean("general_addLog");

    JDBC_Impl.admin.client.hr.EmplyeeImpl EMPLOYEE_DAO                          = (JDBC.admin.client.hr.Employee) appContext.getBean("employee_addLog");
    JDBC_Impl.admin.client.reception.GuestImpl GUEST_DAO                        = (JDBC.admin.client.reception.Guest) appContext.getBean("guest_addLog");
    JDBC_Impl.admin.client.security.AccountImpl ACCOUNT_DAO                     = (JDBC.admin.client.security.Account) appContext.getBean("account_addLog");
    JDBC_Impl.admin.client.security.CardImpl CARD_DAO                           = (JDBC.admin.client.security.Card) appContext.getBean("card_addLog");
    JDBC_Impl.admin.client.security.RightImpl RIGHT_DAO                         = (JDBC.admin.client.security.Right) appContext.getBean("right_addLog");
    JDBC_Impl.admin.client.security.RoleImpl ROLE_DAO                           = (JDBC.admin.client.security.Role) appContext.getBean("role_addLog");
    JDBC_Impl.admin.client.technical.DeviceImpl DEVICE_DAO                      = (JDBC.admin.client.technical.Device) appContext.getBean("device_addLog");
    JDBC_Impl.admin.client.EnterOrOutAdminImpl ENTER_OR_OUT_ADMIN_DAO           = (JDBC.admin.client.EnterOrOutAdmin) appContext.getBean("enterOrOutAdmin_addLog");

    JDBC_Impl.arduino.client.EnterOtOutTheRoomImpl ENTER_OT_OUT_THE_ROOM_DAO    = (JDBC.arduino.client.EnterOrOutTheRoom) appContext.getBean("enterOrOutTheRoom_addLog");
    //LocalErrorImpl LOCAL_ERROR_DAO                                              = (LocalError) appContext.getBean("localError_addLog");
    //JDBC_Impl.arduino.client.UnauthorizedAccessImpl UNAUTHORIZED_ACCESS_DAO     = (JDBC.arduino.client.UnauthorizedAccess) appContext.getBean("unauthorizedAccess_addLog");
    //ConnectionErrorImpl CONNECTION_ERROR_DAO                                    = (ConnectionError) appContext.getBean("connectionError_addLog");

    JDBC_Impl.user.client.EnterOtOutTheSystemImpl ENTER_OR_OUT_THE_SYSTEM_DAO       = (JDBC.user.client.EnterOrOutTheSystem) appContext.getBean("enterOrOutTheSystem_addLog");

}
