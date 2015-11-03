package accessToDataBase.JDBC_Impl;

import accessToDataBase.JDBC.admin.client.AdminClientSession;
import accessToDataBase.JDBC.admin.client.HR.Employee;
import accessToDataBase.JDBC.admin.client.HR.HRHelper;
import accessToDataBase.JDBC.admin.client.MyAccount;
import accessToDataBase.JDBC.admin.client.Reception.ExpiredCards;
import accessToDataBase.JDBC.admin.client.Reception.Guest;
import accessToDataBase.JDBC.admin.client.Reception.ReceptionHelper;
import accessToDataBase.JDBC.admin.client.Security.*;
import accessToDataBase.JDBC.admin.client.Technical.Device;
import accessToDataBase.JDBC.admin.client.Technical.TechnicalHepler;
import accessToDataBase.JDBC.arduino.client.CashAdminDB;
import accessToDataBase.JDBC.arduino.client.ConnectionStatus;
import accessToDataBase.JDBC.arduino.client.GetAccess;
import accessToDataBase.JDBC_Impl.Arduino.CashAdminDBImpl;
import accessToDataBase.JDBC_Impl.Arduino.ConnectionStatusImpl;
import accessToDataBase.JDBC_Impl.Arduino.GetAccessImpl;
import accessToDataBase.JDBC_Impl.admin.client.AdminClientSessionImpl;
import accessToDataBase.JDBC_Impl.admin.client.MyAccountImpl;
import accessToDataBase.JDBC_Impl.admin.client.hr.EmployeeImpl;
import accessToDataBase.JDBC_Impl.admin.client.hr.HRHelperImpl;
import accessToDataBase.JDBC_Impl.admin.client.reception.ExpiredCardsImpl;
import accessToDataBase.JDBC_Impl.admin.client.reception.GuestImpl;
import accessToDataBase.JDBC_Impl.admin.client.reception.ReceptionHelperImpl;
import accessToDataBase.JDBC_Impl.admin.client.security.*;
import accessToDataBase.JDBC_Impl.admin.client.technical.DeviceImpl;
import accessToDataBase.JDBC_Impl.admin.client.technical.TechnicalHelperImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by root on 03.11.15.
 */
public interface Spring_DAO {
    ApplicationContext appContext = new ClassPathXmlApplicationContext("JDBC_config.xml");

    EmployeeImpl EMPLOYEE_DAO = (Employee) appContext.getBean("employee");
    HRHelperImpl HR_HELPER_DAO = (HRHelper) appContext.getBean("hrHelper");

    GuestImpl GUEST_DAO = (Guest) appContext.getBean("guest");
    ReceptionHelperImpl RECEPTION_HELPER_DAO = (ReceptionHelper) appContext.getBean("receptionHelper");
    ExpiredCardsImpl EXPIRED_CARDS_DAO = (ExpiredCards) appContext.getBean("expiredCards");

    AccountImpl ACCOUNT_DAO = (Account) appContext.getBean("account");
    CardsImpl CARDS_DAO = (Cards) appContext.getBean("cards");
    RightsImpl RIGHTS_DAO = (Rights) appContext.getBean("rights");
    RolesImpl ROLES_DAO = (Roles) appContext.getBean("roles");
    SecurityHelperImpl SECURITY_HELPER_DAO = (SecurityHelper) appContext.getBean("securityHelper");

    DeviceImpl DEVICE_DAO = (Device) appContext.getBean("device");
    TechnicalHelperImpl TECHNICAL_HELPER_DAO = (TechnicalHepler) appContext.getBean("technicalHelper");

    AdminClientSessionImpl ADMIN_CLIENT_SESSION_DAO = (AdminClientSession) appContext.getBean("adminClientSession");
    MyAccountImpl MY_ACCOUNT_DAO = (MyAccount) appContext.getBean("myAccount");

    CashAdminDBImpl CASH_ADMIN_DB_DAO = (CashAdminDB) appContext.getBean("cashAdminDB");
    ConnectionStatusImpl CONNECTION_STATUS_DAO = (ConnectionStatus) appContext.getBean("connectionStatus");
    GetAccessImpl GET_ACCESS_DAO = (GetAccess) appContext.getBean("getAccess");
}
