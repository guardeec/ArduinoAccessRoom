package Controllers.Security;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import POJO.Accounts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.swing.table.TableColumn;
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


    @FXML
    public void accountsFindBtnAction(){
        AdminType adminType = AdminType.getInstance();
        String adminName = adminType.getLogin();
        String adminPassword = adminType.getPassword();
        String message =    "adminName="+adminName
                            +"&adminPassword="+adminPassword
                            +"&id="
                            +"&account="
                            +"&login="
                ;
        List<Map> devices = httpRequest.makeInList(message, URL.getAccounts);
        Map<String, String> resultDevices = devices.get(devices.size() - 1);
        if (resultDevices.get("message").contains("Succes")){
            ObservableList<Accounts> accounts = FXCollections.observableArrayList();
            for (int i=0; i<devices.size()-1; i++){
                Accounts account = new Accounts(
                        (String) devices.get(i).get("id"),
                        (String) devices.get(i).get("account"),
                        (String) devices.get(i).get("login"),
                        (String) devices.get(i).get("device"),
                        (String) devices.get(i).get("reception"),
                        (String) devices.get(i).get("hr"),
                        (String) devices.get(i).get("security")
                );
                accounts.add(account);
            }
            accountsTable.setItems(accounts);


        }else {

        }

    }
    @FXML
    public void accountsChangeBtnAction(){

    }
    @FXML
    public void addAccountBtnAction(){

    }
    @FXML
    public void changeAccountBtnAction(){

    }
    @FXML
    public void deleteAccountBtnAction(){

    }


}
