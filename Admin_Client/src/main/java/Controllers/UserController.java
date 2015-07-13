package Controllers;

import POJO.Device;
import POJO.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Created by root on 13.07.15.
 */
public class UserController  {

    @FXML
    private TextField userId;
    @FXML
    private TextField userName;
    @FXML
    private TextField userRole;

    @FXML
    private TableView<User> userTable;

    @FXML
    public void findUserBtnAction(){
        ObservableList<User> users = FXCollections.observableArrayList(
                new User("134", "Tom", "admin"),
                new User("223", "Eva", "admin"),
                new User("356", "Carl", "junior"),
                new User("423", "Sam", "senior")
        );
        userTable.setItems(users);
    }
}
