package tests.methods;

/**
 * Created by root on 13.08.15.
 */
public class URL {
    private static final String url          = "192.168.1.96:8080/AccessServer/";

    public static final String checkRunnable= url + "checkRunnable";

    public static final String getAdminType = url + "getAdminType";

    public static final String addDevice    = url + "deviceAdmin/addDevice";
    public static final String changeDevice = url + "deviceAdmin/changeDevice";
    public static final String deleteDevice = url + "deviceAdmin/deleteDevice";
    public static final String getDevice    = url + "deviceAdmin/getDevice";

    public static final String getGuest     = url + "guestAdmin/getGuest";
    public static final String getFreeGuestCards = url + "guestAdmin/checkFreeCards";
    public static final String addGuest     = url + "guestAdmin/addGuest";
    public static final String changeGuest  = url + "guestAdmin/changeGuest";
    public static final String deleteGuest  = url + "guestAdmin/deleteGuest";
    public static final String getHistory   = url + "guestAdmin/getHistory";
    public static final String deleteExpiredCards= url+"guestAdmin/deleteExpiredCards";

    public static final String getFreeUserCards=url+"userAdmin/checkFreeCards";
    public static final String getUserRoles = url + "userAdmin/getRolesUser";
    public static final String getUser      = url + "userAdmin/getUser";
    public static final String addUser      = url + "userAdmin/addUser";
    public static final String changeUser   = url + "userAdmin/changeUser";
    public static final String deleteUser   = url + "userAdmin/deleteUser";

    public static final String addCard      = url + "securityAdmin/addCard";
    public static final String deleteCard   = url + "securityAdmin/deleteCard";
    public static final String getFreeSecurityCards=url+"securityAdmin/checkFreeCards";
    public static final String addRole      = url + "securityAdmin/addRole";
    public static final String changeRole   = url + "securityAdmin/changeRole";
    public static final String deleteRole   = url + "securityAdmin/deleteRole";
    public static final String getSecurityRoles= url + "securityAdmin/getRole";
    public static final String getSecurityDevices= url + "securityAdmin/getDevice";
    public static final String getPolicy= url + "securityAdmin/getPolicy";
    public static final String getPolicyOnDevice= url + "securityAdmin/getPolicyOnDevice";
    public static final String changeSecurityPermission=url + "securityAdmin/changePolicyOnDevice";

    public static final String getAccounts  = url + "securityAdmin/getAccount";
    public static final String addAccount   = url + "securityAdmin/addAccount";
    public static final String changeAccount= url + "securityAdmin/changeAccount";
    public static final String deleteAccount= url + "securityAdmin/deleteAccount";

    public static final String cashAdminDB  = url + "cashAdminDB";
    public static final String getAccess    = url + "getAccess";

    public static final String adminName = "Max";
    public static final String password  = "12345";



}
