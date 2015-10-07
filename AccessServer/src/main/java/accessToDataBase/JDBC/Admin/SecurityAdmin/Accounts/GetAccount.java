package accessToDataBase.JDBC.Admin.SecurityAdmin.Accounts;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by root on 25.09.15.
 */
public class GetAccount extends JdbcDaoSupport implements GetAccountImpl {
    @Override
    public List<Map> get(String login, Integer id, Integer employee_id) {
        ArrayList<Map> message;
        try{
            message = (ArrayList<Map>) getJdbcTemplate().queryForObject(
                    "SELECT accounts.id, accounts.employee_id, accounts.login,db_roles.title FROM accounts JOIN accounts_and_roles ON accounts.id = accounts_and_roles.account_id JOIN db_roles ON accounts_and_roles.db_role_id = db_roles.id WHERE accounts.id =coalesce(?, accounts.id) AND   login = coalesce(?, login) AND  employee_id = coalesce(?, employee_id);",
                    new Object[]{id, login, employee_id},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException ex){
            message = new ArrayList<>();
            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Success getting empty accounts list");
            message.add(messageComponent);
        }
        catch (org.springframework.jdbc.CannotGetJdbcConnectionException ex){
            message = new ArrayList<>();
            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Error when getting account list");
            message.add(messageComponent);



        }
        return message;
    }

    private class SearchRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            ArrayList<Map> message = new ArrayList<>();
            do {
                Map<String, String> messageComponent = new HashMap<>();
                messageComponent.put("hr", "false");
                messageComponent.put("reception", "false");
                messageComponent.put("technical", "false");
                messageComponent.put("security", "false");
                messageComponent.put("id", Integer.toString(resultSet.getInt("id")));
                messageComponent.put("login", resultSet.getString("login"));
                messageComponent.put("employee_id", resultSet.getString("employee_id"));
                String title = resultSet.getString("title");
                if (title.contains("hr")){
                    messageComponent.put("hr", "true");
                }
                if (title.contains("reception")){
                    messageComponent.put("reception", "true");
                }
                if (title.contains("technical")){
                    messageComponent.put("technical", "true");
                }
                if (title.contains("security")){
                    messageComponent.put("security", "true");
                }
                boolean flag = false;
                for (Map<String, String> naitiveComponent : message){
                    if (naitiveComponent.get("id").compareTo(messageComponent.get("id"))==0){
                        if (messageComponent.get("hr").contains("true")){
                            naitiveComponent.put("hr", "true");
                        }
                        if (messageComponent.get("reception").contains("true")){
                            naitiveComponent.put("reception", "true");
                        }
                        if (messageComponent.get("technical").contains("true")){
                            naitiveComponent.put("technical", "true");
                        }
                        if (messageComponent.get("security").contains("true")){
                            naitiveComponent.put("security", "true");
                        }

                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    message.add(messageComponent);
                }
            }while (resultSet.next());

            Map<String, String> messageComponent = new HashMap<>();
            messageComponent.put("message", "Success getting accounts list");
            message.add(messageComponent);

            return message;
        }
    }
}
