package accessToDataBase.JDBC.Arduino;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guardeec on 20.05.15.
 */
public class getAccess extends JdbcDaoSupport implements getAccessImpl {

    private class RowMapperCheckUser implements RowMapper {
        public Object mapRow(ResultSet rs, int i) throws SQLException, SQLException {
            Map<String, String> message = new HashMap<>();
            message.put("user_id", Integer.toString(rs.getInt("user_id")));
            return message;
        }
    }

    private class RowMapperCheckGuest implements RowMapper {
        public Object mapRow(ResultSet rs, int i) throws SQLException, SQLException {
            Map<String, String> message = new HashMap<>();
            message.put("guest_id", Integer.toString(rs.getInt("guest_id")));
            return message;
        }
    }

    private class RowMapperUserGetAccess implements RowMapper {
        public Object mapRow(ResultSet rs, int i) throws SQLException, SQLException {
            Map<String, String> message = new HashMap<>();
            message.put("id", Integer.toString(rs.getInt("id")));
            message.put("name", rs.getString("name"));
            message.put("getAccess", Boolean.toString(rs.getBoolean("getAccess")));
            message.put("type", "user");
            return message;
        }
    }

    private class RowMapperGuestGetAccess implements RowMapper {
        public Object mapRow(ResultSet rs, int i) throws SQLException, SQLException {
            Map<String, String> message = new HashMap<>();
            message.put("id", Integer.toString(rs.getInt("id")));
            message.put("name", rs.getString("name"));
            message.put("getAccess", Boolean.toString(rs.getBoolean("getAccess")));
            message.put("type", "guest");
            return message;
        }
    }


    public Map<String, String> Get(String card, String deviceId) {

        Map<String, String> message = new HashMap<>();
        Boolean indefiniteUser = false;
        Boolean indefiniteGuest = false;

        //Проверка пользователь ли это
        try{
            message = (Map<String, String>) getJdbcTemplate().queryForObject(
                "SELECT user_id FROM users_and_cards WHERE card_id = (SELECT cards.id FROM cards WHERE cards.number = ?);",
                new Object[]{card},
                new RowMapperCheckUser()
            );
            indefiniteUser = true;
        }catch (org.springframework.dao.EmptyResultDataAccessException ex){
            indefiniteUser = false;
        }catch (org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            System.out.println("Попытка проверки на статус Пользователя. БД не доступна.");
            indefiniteUser = false;
        }

        //Если не пользователь то проверка на гостя
        if (!indefiniteUser){
            try {
                message = (Map<String, String>) getJdbcTemplate().queryForObject(
                        "SELECT guest_id FROM guests_and_cards WHERE card_id = (SELECT cards.id FROM cards WHERE cards.number = ?);",
                        new Object[]{card},
                        new RowMapperCheckGuest()
                );
                indefiniteGuest = true;
            }
            catch (org.springframework.dao.EmptyResultDataAccessException ex){
                indefiniteGuest = false;
            }
            catch (org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                System.out.println("Попытка проверки на статус Гостя. БД не доступна.");
                indefiniteGuest = false;
            }
        }

        //если проверка на пользователя успешна
        if (indefiniteUser){
            try {
                message = (Map<String, String>) getJdbcTemplate().queryForObject(
                        "SELECT users.id, users.name, access_rights.access FROM users JOIN users_and_cards ON users.id = users_and_cards.user_id JOIN cards ON cards.id = users_and_cards.card_id JOIN users_and_roles ON users.id = users_and_roles.user_id JOIN roles ON users_and_roles.role_id = roles.id JOIN access_rights ON roles.id = access_rights.role_id WHERE device_id = ? AND cards.number = ?;",
                        new Object[]{Integer.parseInt(deviceId), card},
                        new RowMapperUserGetAccess()
                );
            }
            catch (org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                System.out.println("Попытка запроса на доступ пользователя. БД недоступна");
            }
        }

        //если проверка на гостя успешна
        if (indefiniteGuest){
            try {
                message = (Map<String, String>) getJdbcTemplate().queryForObject(
                        "SELECT guests.id, guests.name, access_rights.access FROM guests JOIN guests_and_cards ON guests.id = guests_and_cards.guest_id JOIN cards ON cards.id = guests_and_cards.card_id JOIN access_rights ON access_rights.role_id = (SELECT id FROM roles WHERE title = 'guest')WHERE device_id = ? AND cards.number = ?;",
                        new Object[]{Integer.parseInt(deviceId), card},
                        new RowMapperGuestGetAccess()
                );
            }
            catch (org.springframework.jdbc.CannotGetJdbcConnectionException ex){
                System.out.println("Попытка запроса на доступ пользователя. БД недоступна");
            }
        }

        if (!indefiniteUser && !indefiniteGuest){
            message.put("id", "UNKNOWN");
            message.put("name", "UNKNOWN");
            message.put("getAccess", "false");
            message.put("Roles", "UNKNOWN");
        }



        return message;
    }
}
