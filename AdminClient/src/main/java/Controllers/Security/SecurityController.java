package Controllers.Security;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import Controllers.User.UserController;
import POJO.Guest;
import POJO.Security;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 13.07.15.
 */
public class SecurityController extends UserController {

    public static Stage addRoleStage;
    public static Stage changeRoleStage;
    public static Stage deleteRoleStage;
    public static Stage addCardStage;
    public static Stage deleteCardStage;
    public static Stage changePermissionStage;

    @FXML
    private TextField securityDeviceId;
    @FXML
    private TextField securityRoleId;
    @FXML
    private TableView<Security> securityTable;

    @FXML
    public void findSecurityBtnAction(){
        AdminType adminType = AdminType.getInstance();
        String adminName = adminType.getLogin();
        String adminPassword = adminType.getPassword();
        String deviceId = securityDeviceId.getText();
        String roleId = securityRoleId.getText();
        String message =    "adminName="+adminName
                            +"&adminPassword="+adminPassword
                            +"&deviceId="+deviceId
                            +"&roleId"+roleId
                ;
        List<Map> answer = httpRequest.makeInList(message, URL.getSecurityPermission);
        Map<String, String> result = answer.get(answer.size() - 1);
        List<Map> devices = httpRequest.makeInList(message, URL.getSecurityDevices);
        Map<String, String> resultDevices = devices.get(answer.size() - 1);
        List<Map> roles = httpRequest.makeInList(message, URL.getSecurityRoles);
        Map<String, String> resultRoles = roles.get(answer.size() - 1);
        if (result.get("message").contains("Success") && resultDevices.get("message").contains("Success") && resultRoles.get("message").contains("Success")){
            ObservableList<Security> polices = FXCollections.observableArrayList();
            for (int i=0; i<answer.size()-1; i++){
                String access = (String) answer.get(i).get("getAccess");
                if (access.contains("true")){
                    String deviceSpecification = null;
                    for (int q=0; q<devices.size()-1; q++){
                        deviceId = (String) devices.get(q).get("id");
                        if (deviceId.compareTo((String) answer.get(i).get("device_id"))==0){
                            deviceSpecification=(String)devices.get(q).get("specification");
                        }
                    }
                    String roleTitle = null;
                    for (int q=0; q<roles.size()-1; q++){
                        roleId = (String) roles.get(q).get("id");
                        if (roleId.compareTo((String) answer.get(i).get("role_id"))==0){
                            roleTitle=(String)roles.get(q).get("title");
                        }
                    }
                    Security security = new Security(
                            (String) answer.get(i).get("role_id"),
                            roleTitle,
                            (String) answer.get(i).get("device_id"),
                            deviceSpecification
                    );
                    polices.add(security);
                }
            }
            securityTable.setItems(polices);
        }else {
            ObservableList<Security> polices = FXCollections.observableArrayList();
            Security security = new Security(
                    "Ошибка сервера",
                    "Ошибка сервера",
                    "Ошибка сервера",
                    "Ошибка сервера"
            );
            polices.add(security);
            securityTable.setItems(polices);
        }
    }

    @FXML
    public void findSecurityAllDevicesBtnAction(){
        AdminType adminType = AdminType.getInstance();
        String adminName = adminType.getLogin();
        String adminPassword = adminType.getPassword();
        String message =    "adminName="+adminName
                            +"&adminPassword="+adminPassword
                ;
        List<Map> devices = httpRequest.makeInList(message, URL.getSecurityDevices);
        Map<String, String> resultDevices = devices.get(devices.size() - 1);

        if (resultDevices.get("message").contains("Success")){
            ObservableList<Security> polices = FXCollections.observableArrayList();
            for (int i=0; i<devices.size()-1; i++){
                Security security = new Security(
                        null,
                        null,
                        (String) devices.get(i).get("id"),
                        (String) devices.get(i).get("specification")
                );
                polices.add(security);
            }
            securityTable.setItems(polices);
        }else {
            ObservableList<Security> polices = FXCollections.observableArrayList();
            Security security = new Security(
                    "Ошибка сервера",
                    "Ошибка сервера",
                    "Ошибка сервера",
                    "Ошибка сервера"
            );
            polices.add(security);
            securityTable.setItems(polices);
        }
    }

    @FXML
    public void findSecurityAllRolesBtnAction(){
        AdminType adminType = AdminType.getInstance();
        String adminName = adminType.getLogin();
        String adminPassword = adminType.getPassword();
        String message =    "adminName="+adminName
                            +"&adminPassword="+adminPassword
                ;
        List<Map> roles = httpRequest.makeInList(message, URL.getSecurityRoles);
        Map<String, String> resultDevices = roles.get(roles.size() - 1);

        if (resultDevices.get("message").contains("Success")){
            ObservableList<Security> polices = FXCollections.observableArrayList();
            for (int i=0; i<roles.size()-1; i++){
                Security security = new Security(
                        (String) roles.get(i).get("id"),
                        (String) roles.get(i).get("title"),
                        null,
                        null
                );
                polices.add(security);
            }
            securityTable.setItems(polices);
        }else {
            ObservableList<Security> polices = FXCollections.observableArrayList();
            Security security = new Security(
                    "Ошибка сервера",
                    "Ошибка сервера",
                    "Ошибка сервера",
                    "Ошибка сервера"
            );
            polices.add(security);
            securityTable.setItems(polices);
        }
    }

    @FXML
    public void addSecurityPermissionBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/Security/changePermission.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        changePermissionStage= new Stage();
        changePermissionStage.setTitle("Изменение прав");
        changePermissionStage.setScene(scene);
        changePermissionStage.show();
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
        addRoleStage = new Stage();
        addRoleStage.setTitle("Добавление Роли");
        addRoleStage.setScene(scene);
        addRoleStage.show();
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
        changeRoleStage = new Stage();
        changeRoleStage.setTitle("Изменение Роли");
        changeRoleStage.setScene(scene);
        changeRoleStage.show();
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
        deleteRoleStage = new Stage();
        deleteRoleStage.setTitle("Удаление Роли");
        deleteRoleStage.setScene(scene);
        deleteRoleStage.show();
    }

    @FXML
    public void addSecurityCardBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/Security/addCard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        addCardStage = new Stage();
        addCardStage.setTitle("Добавление карты");
        addCardStage.setScene(scene);
        addCardStage.show();
    }

    public void deleteSecurityCardBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/Security/deleteCard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        deleteCardStage = new Stage();
        deleteCardStage.setTitle("Удаление карты");
        deleteCardStage.setScene(scene);
        deleteCardStage.show();
    }
}
