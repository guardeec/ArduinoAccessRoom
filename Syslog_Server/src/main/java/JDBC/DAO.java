package JDBC;


import DB_Object.Syslog;

import java.util.ArrayList;

/**
 * Created by Kolomeec on 23.04.2015.
 */
public interface DAO {

    public void Add(Syslog syslog);

    public ArrayList Get(String SQLWhere);

}
