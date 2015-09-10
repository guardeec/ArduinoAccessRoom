package accessToDataBase.JDBC.Admin.old;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guardeec on 20.05.15.
 */
public class Admin extends JdbcDaoSupport implements DAO_Admin{


    private class SearchRowMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int i) throws SQLException, SQLException {

            /*
            **List that will contains result set
            **index -> stroke number
            **value -> stroke map (contains rows)
             */

            ArrayList<Map> message = new ArrayList<Map>();
            do{
                Map searchStrokeResult = new HashMap<String, String>();

                searchStrokeResult.put("user_id", Integer.toString(rs.getInt("user_id")));


                /*
                if we will search user we will get 6 columns
                if we will add user we will get 1 column
                this is check for what type of operation we using
                 */
                if(rs.getMetaData().getColumnCount()>1){
                    searchStrokeResult.put("name", rs.getString("name"));
                    searchStrokeResult.put("role_id", Integer.toString(rs.getInt("role_id")));
                    searchStrokeResult.put("Roles", rs.getString("Roles"));
                    searchStrokeResult.put("card", rs.getString("card"));
                    searchStrokeResult.put("state", Boolean.toString(rs.getBoolean("state")));
                }

                message.add(searchStrokeResult);
            }while(rs.next());

            return message;
        }
    }

    public ArrayList<Map> Search(Integer id, String name, Integer role_id, String title, String card, Boolean state) {

        ArrayList<Map> message;
        try{
            message = (ArrayList<Map>) getJdbcTemplate().queryForObject(
                    "SELECT users.id as \"user_id\", users.name as \"name\", roles.id as \"role_id\", roles.title as \"role\", cards.card as \"card\", cards.state as \"state\" FROM (users JOIN cards ON users.id = cards.user_id) JOIN roles ON users.role_id = roles.id WHERE users.id = coalesce(?,users.id) AND users.name = coalesce(?,users.name) AND roles.id = coalesce(?,roles.id) AND roles.title = coalesce(?,roles.title) AND cards.card = coalesce(?,cards.card) AND cards.state = coalesce(?,cards.state);",
                    new Object[]{id, name, role_id, title, card, state},
                    new SearchRowMapper()
            );
        }catch (org.springframework.dao.EmptyResultDataAccessException ex){
            message = new ArrayList<Map>();
            Map <String, String> seachStrokeResult= new HashMap<String, String>();
            seachStrokeResult.put("user_id", "NO USER");
            seachStrokeResult.put("name", "NO USER");
            seachStrokeResult.put("role_id", "NO USER");
            seachStrokeResult.put("Roles", "NO USER");
            seachStrokeResult.put("card", "NO USER");
            seachStrokeResult.put("state", "NO USER");
            message.add(seachStrokeResult);
        }

        return message;
    }

    public ArrayList<Map> Add(String name, String card) {

        ArrayList<Map> message;
        message = (ArrayList<Map>) getJdbcTemplate().queryForObject(
                "INSERT INTO users(name) VALUES(?) RETURNING users.id AS \"user_id\";",
                new Object[]{name},
                new SearchRowMapper()
        );

        this.getJdbcTemplate().update(
                "INSERT INTO cards(user_id, card) VALUES (?,?);",
                new Object[] {
                        Integer.parseInt(message.get(0).get("user_id").toString()), card
                }
        );

        return message;
    }


    public void Delete(Integer id) {

        this.getJdbcTemplate().update(
                "DELETE FROM users WHERE users.id = ?;",
                new Object[] {
                        id
                }
        );

    }


    public void Change(Integer id, String name, Integer role_id, String card, Boolean state) {

        if(name != null){
            this.getJdbcTemplate().update(
                    "UPDATE users SET name = ? WHERE id = ?;",
                    new Object[]{
                            name, id
                    }
            );
        }

        if(role_id != null){
            this.getJdbcTemplate().update(
                    "UPDATE users SET role_id = ? WHERE id = ?;",
                    new Object[]{
                            role_id, id
                    }
            );
        }

        if(card != null){
            this.getJdbcTemplate().update(
                    "UPDATE cards SET card = ? WHERE user_id = ?;",
                    new Object[]{
                            card, id
                    }
            );
        }

        if(state!=null){
            this.getJdbcTemplate().update(
                    "UPDATE cards SET state = ? WHERE user_id = ?;",
                    new Object[]{
                            state, id
                    }
            );
        }

    }
}
