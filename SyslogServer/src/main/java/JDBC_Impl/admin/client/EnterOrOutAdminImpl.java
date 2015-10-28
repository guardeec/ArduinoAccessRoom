package JDBC_Impl.admin.client;

import JDBC.SyslogData;
import JDBC_Impl.Spring_DAO;
import Methods.POJO.AdminClient.AdminClientSessionDATA;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 08.10.15.
 */
public interface EnterOrOutAdminImpl {
    public String log(AdminClientSessionDATA session);
}
