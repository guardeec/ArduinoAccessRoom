package Controllers.User;

import Controllers.GuestCard.GuestCardController;
import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
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
import java.util.List;
import java.util.Map;

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
        AdminType adminType = AdminType.getInstance();
        String adminName = adminType.getLogin();
        String adminPassword = adminType.getPassword();
        String id = userId.getText();
        String name = userName.getText();
        String roleId = userRole.getText();
        String message =    "adminName="+adminName
                +"&adminPassword="+adminPassword
                +"&userId="+id
                +"&name="+name
                +"&roleId="+roleId
                ;

        List<Map> answer = httpRequest.makeInList(message, URL.getUser);
        Map<String, String> result = answer.get(answer.size()-1);
        if (result.get("message").contains("Success")){
            ObservableList<User> users = FXCollections.observableArrayList();
            for (int i=0; i<answer.size()-1; i++){
                User user = new User(
                        (String) answer.get(i).get("id"),
                        (String) answer.get(i).get("name"),
                        (String) answer.get(i).get("role")
                );
                users.add(user);
            }
            userTable.setItems(users);
        }else {
            ObservableList<User> users = FXCollections.observableArrayList();
            for (int i=0; i<answer.size()-1; i++){
                User user = new User(
                        "Ошибка сервера",
                        "Ошибка сервера",
                        "Ошибка сервера"
                );
                users.add(user);
            }
            userTable.setItems(users);
        }

    }

    @FXML
    public void findUserRolesBtnAction(){
        AdminType adminType = AdminType.getInstance();
        String adminName = adminType.getLogin();
        String adminPassword = adminType.getPassword();
        String message =    "adminName="+adminName
                +"&adminPassword="+adminPassword
                ;
        List<Map> answer = httpRequest.makeInList(message, URL.getUserRoles);
        Map<String, String> result = answer.get(answer.size() - 1);
        if (result.get("message").contains("Success")){
            ObservableList<User> roles = FXCollections.observableArrayList();
            for (int i=0; i<answer.size(); i++){
                User user = new User(
                        null,
                        (String) answer.get(i).get("title"),
                        (String) answer.get(i).get("id")
                );
                roles.add(user);
            }
            userTable.setItems(roles);
        }else {
            ObservableList<User> users = FXCollections.observableArrayList();
            for (int i=0; i<answer.size()-1; i++){
                User user = new User(
                        "Ошибка сервера",
                        "Ошибка сервера",
                        "Ошибка сервера"
                );
                users.add(user);
            }
            userTable.setItems(users);
        }

    }

    @FXML
    public void findFreeUserCardsBtnAction(){
        AdminType adminType = AdminType.getInstance();
        String adminName = adminType.getLogin();
        String adminPassword = adminType.getPassword();
        String message =    "adminName="+adminName
                            +"&adminPassword="+adminPassword
                ;
        List<Map> answer = httpRequest.makeInList(message, URL.getFreeUserCards);
        Map<String, String> result = answer.get(answer.size() - 1);
        if (result.get("message").contains("Success")){
            ObservableList<User> freeCards = FXCollections.observableArrayList();
            for (int i=0; i<answer.size(); i++){
                User user = new User(
                        (String) answer.get(i).get("id"),
                        null,
                        null
                );
                freeCards.add(user);
            }
            userTable.setItems(freeCards);
        }else {
            ObservableList<User> users = FXCollections.observableArrayList();
            for (int i=0; i<answer.size()-1; i++){
                User user = new User(
                        "Ошибка сервера",
                        "Ошибка сервера",
                        "Ошибка сервера"
                );
                users.add(user);
            }
            userTable.setItems(users);
        }

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
