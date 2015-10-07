package Controllers.Security.Accounts;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import Controllers.Security.accountsController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 25.09.15.
 */
public class ChangeAccountController {
    @FXML
    TextField id;
    @FXML
    TextField login;
    @FXML
    TextField password;
    @FXML
    CheckBox technicalCheckBox;
    @FXML
    CheckBox receptionCheckBox;
    @FXML
    CheckBox hrCheckBox;
    @FXML
    CheckBox securityCheckBox;

    @FXML
    public void changeBtnAction(){
        System.out.println(technicalCheckBox.isSelected());
        if (id.getText().isEmpty()){
            id.setText("Введите id аккаунта");
        }else {
            if (technicalCheckBox.isSelected() || receptionCheckBox.isSelected() || hrCheckBox.isSelected() || securityCheckBox.isSelected()) {
                AdminType adminType = AdminType.getInstance();
                String adminName = adminType.getLogin();
                String adminPassword = adminType.getPassword();
                String message = "adminName=" + adminName
                        + "&adminPassword=" + adminPassword
                        + "&id=" + id.getText()
                        + "&login=" + login.getText()
                        + "&password=" + password.getText()
                        + "&technical=" + technicalCheckBox.isSelected()
                        + "&reception=" + receptionCheckBox.isSelected()
                        + "&hr=" + hrCheckBox.isSelected()
                        + "&security=" + securityCheckBox.isSelected();
                System.out.println(message);
                List<Map> result = httpRequest.makeInList(message, URL.changeAccount);
                Map<String, String> answer = result.get(result.size() - 1);
                if (answer.get("message").contains("Success")) {
                    accountsController.changeStage.close();
                } else {
                    id.setText("Ошибка сервера");
                }
            }else {
                id.setText("Оставьте одну роль");
            }
        }
    }

    @FXML
    public void findBtnAction(){
        if (id.getText().isEmpty()){
            id.setText("Введите id аккаунта");
        }else {

            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&employee_id="
                    +"&id="+id.getText()
                    +"&login="
                    ;
            List<Map> result = httpRequest.makeInList(message, URL.getAccounts);
            Map<String, String> answer = result.get(result.size() - 1);
            if (answer.get("message").contains("empty")){
                id.setText("Пользователь не найден");
            }else {
                if (answer.get("message").contains("Success")){
                    login.setText((String)result.get(0).get("login"));
                    if(result.get(0).get("hr").toString().compareTo("true")==0){
                        hrCheckBox.setSelected(true);
                    }else {
                        hrCheckBox.setSelected(false);
                    }
                    if (result.get(0).get("technical").toString().compareTo("true")==0){
                        technicalCheckBox.setSelected(true);
                    }else {
                        technicalCheckBox.setSelected(false);
                    }
                    if (result.get(0).get("reception").toString().compareTo("true")==0){
                        receptionCheckBox.setSelected(true);
                    }else {
                        receptionCheckBox.setSelected(false);
                    }
                    if (result.get(0).get("security").toString().compareTo("true")==0){
                        securityCheckBox.setSelected(true);
                    }else {
                        securityCheckBox.setSelected(false);
                    }
                }else {
                    id.setText("Ошибка сервера");
                }
            }
        }
    }
}
