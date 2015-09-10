package Controllers.Security;

import Controllers.User.UserController;
import POJO.Security;
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
public class SecurityController extends UserController {

    ObservableList<Security> security = FXCollections.observableArrayList(
            new Security("1", "admin", "1", "Door 1"),
            new Security("1", "admin", "2", "Door 2"),
            new Security("1", "admin", "3", "Door 3"),
            new Security("2", "guest", "1", "Door 1"),
            new Security("2", "guest", "3", "Door 3")
    );
    ObservableList<Security> allDevices = FXCollections.observableArrayList(
            new Security(null, null, "1", "Door 1"),
            new Security(null, null, "2", "Door 2"),
            new Security(null, null, "3", "Door 3")
    );
    ObservableList<Security> allRoles = FXCollections.observableArrayList(
            new Security("1", "admin", null, null),
            new Security("2", "guest", null, null)
    );

    public static Stage addStage;
    public static Stage changeStage;
    public static Stage deleteStage;

    @FXML
    private TextField securityDeviceId;
    @FXML
    private TextField securityRoleId;
    @FXML
    private TableView<Security> securityTable;




    @FXML
    public void findSecurityBtnAction(){
        securityTable.setItems(security);
    }

    @FXML
    public void findSecurityAllDevicesBtnAction(){
        securityTable.setItems(allDevices);
    }

    @FXML
    public void findSecurityAllRolesBtnAction(){
        securityTable.setItems(allRoles);
    }

    @FXML
    public void addSecurityBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/Security/addRole.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        addStage = new Stage();
        addStage.setTitle("Добавление Роли");
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    public void changeSecurityBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/Security/changeRole.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        changeStage = new Stage();
        changeStage.setTitle("Изменение Роли");
        changeStage.setScene(scene);
        changeStage.show();
    }

    @FXML
    public void deleteSecurityBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/Security/deleteRole.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        deleteStage = new Stage();
        deleteStage.setTitle("Удаление Роли");
        deleteStage.setScene(scene);
        deleteStage.show();
    }
}
