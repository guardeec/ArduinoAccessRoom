package JDBC;

import DB_Object.Syslog;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kolomeec on 23.04.2015.
 */
public class Syslog_DAO extends JdbcDaoSupport implements DAO {

    private class UserRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int i) throws SQLException, SQLException {
            ArrayList<Syslog> syslog = new ArrayList<Syslog>();

            do{
                Syslog log = new Syslog();
                log.SetDate(rs.getString("Date"));
                log.SeType(rs.getString("Type"));
                log.SetHostname(rs.getString("HostName"));
                log.SetMessage(rs.getString("Message"));
                syslog.add(log);
            }while(rs.next());

            return syslog;
        }
    }

    public void Add(Syslog syslog) {
        this.getJdbcTemplate().update(
                "INSERT INTO log VALUES (?,?,?,?)",

                new Object[] {
                        syslog.GetDate(), syslog.GetType(), syslog.GetHostname(), syslog.GetMessage()
                }

        );

    }

    public ArrayList Get(String SQLWhere) {
        ArrayList<Syslog> syslog = new ArrayList<Syslog>();
        try {
            syslog = (ArrayList<Syslog>) getJdbcTemplate().queryForObject(
                    "SELECT * FROM log",
                    new UserRowMapper()
            );
        }
        catch(org.springframework.dao.EmptyResultDataAccessException ex){
            syslog.add(new Syslog());
        }
        return syslog;
    }


}
