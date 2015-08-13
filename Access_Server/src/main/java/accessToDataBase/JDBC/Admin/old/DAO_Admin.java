package accessToDataBase.JDBC.Admin.old;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by guardeec on 20.05.15.
 */
public interface DAO_Admin {

    /*
    Set users.id || users.name || users.role_id || Roles.title || Cards.card || Cards.state
    Get users.id, users.name, users.role_id, Roles.title, Cards.card, Cards.state
     */
    public ArrayList<Map> Search(Integer id, String name, Integer role_id, String title, String card, Boolean state);


    /*
    Set UserName, UserRole
    Get UserId
     */
    public ArrayList<Map> Add(String name, String card);


    /*
    Set the UserId
    Get NULL
     */
    public void Delete(Integer id);


    /*
    Set UserId, UserName, UserRole, UserCard, CardStatement
    Get NULL
     */
    public void Change(Integer id, String name, Integer role_id, String card, Boolean state);
}