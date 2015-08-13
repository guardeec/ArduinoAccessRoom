package Controllers.User;

import Controllers.GuestCard.GuestCardController;
import POJO.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by root on 13.07.15.
 */
public class UserController  extends GuestCardController {

    public static Stage addStage;
    public static Stage changeStage;
    public static Stage deleteStage;

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

    @FXML
    public void addUserBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/User/addUser.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        addStage = new Stage();
        addStage.setTitle("Добавление Пользователя");
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    public void changeUserBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/User/changeUser.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        changeStage = new Stage();
        changeStage.setTitle("Изменение Пользователя");
        changeStage.setScene(scene);
        changeStage.show();
    }

    @FXML
    public void deleteUserBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/User/deleteUser.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        deleteStage = new Stage();
        deleteStage.setTitle("Удаление Пользователя");
        deleteStage.setScene(scene);
        deleteStage.show();
    }
}
