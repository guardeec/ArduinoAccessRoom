package methods;

/**
 * Created by root on 07.10.15.
 */
public class URL {

    private final static String addr = "192.168.1.96:8080/SyslogServer/";

    /*
    user.client
     */
    public final static String userClient_enterOrOutTheSystemLog = addr + "userClient/enterOrOutTheSystemLog";

    /*
    arduino.client
     */
    public final static String arduinoClient_enterOrOutTheRoomLog = addr + "arduinoClient/enterOrOutTheRoomLog";
    public final static String arduinoClient_initialisationErrorLog = addr + "arduinoClient/initialisationErrorLog";
    public final static String arduinoClient_alarmStatusLog =addr + "arduinoClient/alarmStatusLog";
    public final static String arduinoClient_workErrorsLog = addr +"arduinoClient/workErrorsLog";
    public final static String arduinoClient_unauthorizedAccessLog = addr +"arduinoClient/unauthorizedAccessLog";

    /*
    admin.client
     */
    public final static String adminClient_enterOrOutTheSessionLog =addr + "adminClient/enterOrOutTheSessionLog";
    public final static String adminClient_technical_deviceLog = addr +"adminClient/technical/deviceLog";
    public final static String adminClient_reception_guestLog =addr + "adminClient/reception/guestLog";
    public final static String adminClient_hr_employeeLog =addr + "adminClient/hr/employeeLog";
    public final static String adminClient_security_roleLog = addr +"adminClient/security/roleLog";
    public final static String adminClient_security_changeRightsLog = addr +"adminClient/security/changeRightsLog";
    public final static String adminClient_security_cardLog =addr + "adminClient/security/cardLog";
    public final static String adminClient_security_accountLog = addr +"adminClient/security/accountLog";
}
