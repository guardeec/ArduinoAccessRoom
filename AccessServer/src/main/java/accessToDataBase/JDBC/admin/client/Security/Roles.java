package accessToDataBase.JDBC.admin.client.Security;

import Methods.POJO.AdminClient.Security.RoleDATA;
import accessToDataBase.JDBC_Impl.admin.client.security.RolesImpl;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by root on 02.11.15.
 */
public class Roles extends JdbcDaoSupport implements RolesImpl {
    @Override
    public RoleDATA create(RoleDATA roleDATA) {
        try{
            Integer id =  getJdbcTemplate().queryForInt(
                    "INSERT INTO system_roles(title) VALUES (?) RETURNING id;",
                    new Object[]{
                            roleDATA.getSys_r_title()
                    }
            );
            roleDATA.setSys_r_id(id);
            return roleDATA;
        }  catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }

    @Override
    public List<RoleDATA> read(RoleDATA roleDATA) {
        List<RoleDATA> roleDATAList;
        try{
            roleDATAList = (List<RoleDATA>) getJdbcTemplate().queryForObject(
                    "SELECT * FROM system_roles WHERE id = coalesce(?, id) AND title = coalesce(?, title);",
                    new Object[]{
                            roleDATA.getSys_r_id(),
                            roleDATA.getSys_r_title()
                    },
                    new SearchRowMapper()
            );
            return roleDATAList;
        }catch (org.springframework.dao.EmptyResultDataAccessException | org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            return null;
        }
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            List<RoleDATA> list = new LinkedList<>();
            do {
                RoleDATA roleDATA = new RoleDATA();
                roleDATA.setSys_r_id(resultSet.getInt("id"));
                roleDATA.setSys_r_title(resultSet.getString("title"));
                list.add(roleDATA);
            }while (resultSet.next());
            return list;
        }
    }

    @Override
    public RoleDATA update(RoleDATA roleDATA) {
        try {
            getJdbcTemplate().update(
                    "UPDATE system_roles SET title = ? WHERE id = ?;",
                    new Object[]{
                            roleDATA.getSys_r_id(),
                            roleDATA.getSys_r_title()
                    }
            );
            return roleDATA;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }

    @Override
    public RoleDATA delete(RoleDATA roleDATA) {
        try {
            getJdbcTemplate().update(
                    "DELETE FROM system_roles WHERE id = ?;",
                    new Object[]{
                            roleDATA.getSys_r_id()
                    }
            );
            return roleDATA;
        } catch (CannotGetJdbcConnectionException ex){
            return null;
        }
    }
}
