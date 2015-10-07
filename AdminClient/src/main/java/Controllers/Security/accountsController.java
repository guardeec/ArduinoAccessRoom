package Controllers.Security;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import POJO.Accounts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.table.TableColumn;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 23.09.15.
 */
public class accountsController {
    @FXML
    TextField userId;
    @FXML
    TextField accountId;
    @FXML
    TextField login;

    @FXML
    TableView<Accounts> accountsTable;

    @FXML
    TableColumn deviceColumn;
    @FXML
    TableColumn receptionColumn;
    @FXML
    TableColumn hrColumn;
    @FXML
    TableColumn securityColumn;

    public static Stage addStage;
    public static Stage changeStage;
    public static Stage deleteStage;


    @FXML
    public void accountsFindBtnAction(){
        AdminType adminType = AdminType.getInstance();
        String adminName = adminType.getLogin();
        String adminPassword = adminType.getPassword();
        String message =    "adminName="+adminName
                            +"&adminPassword="+adminPassword
                            +"&employee_id="+userId.getText()
                            +"&id="+accountId.getText()
                            +"&login="+login.getText()

                ;
        List<Map> devices = httpRequest.makeInList(message, URL.getAccounts);
        Map<String, String> resultDevices = devices.get(devices.size() - 1);
        if (resultDevices.get("message").contains("Success")){
            ObservableList<Accounts> accounts = FXCollections.observableArrayList();
            for (int i=0; i<devices.size()-1; i++){
                Accounts account = new Accounts(
                        (String) devices.get(i).get("employee_id"),
                        (String) devices.get(i).get("id"),
                        (String) devices.get(i).get("login"),
                        devices.get(i).get("technical").toString().contains("true") ? "+" : "-",
                        devices.get(i).get("reception").toString().contains("true") ? "+" : "-",
                        devices.get(i).get("hr").toString().contains("true") ? "+" : "-",
                        devices.get(i).get("security").toString().contains("true") ? "+" : "-"
                );
                accounts.add(account);
            }
            accountsTable.setItems(accounts);
        }else {

        }

    }
    @FXML
    public void accountsChangeRoleBtnAction(){

    }

    @FXML
    public void addAccountBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/Security/accounts/addAccount.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        addStage = new Stage();
        addStage.setTitle("Добавление аккаунта");
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    public void changeAccountBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/Security/accounts/changeAccount.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        changeStage = new Stage();
        changeStage.setTitle("Изменение аккаунта");
        changeStage.setScene(scene);
        changeStage.show();
    }

    @FXML
    public void deleteAccountBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/Security/accounts/deleteAccount.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        deleteStage = new Stage();
        deleteStage.setTitle("Удаление аккаунта");
        deleteStage.setScene(scene);
        deleteStage.show();
    }


}
