package Controllers.Security.Accounts;

import Controllers.Security.accountsController;
import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 25.09.15.
 */
public class AddAccountController {

    @FXML
    TextField login;
    @FXML
    TextField password;
    @FXML
    TextField id;

    @FXML
    public void add(){
        if (login.getText().isEmpty() || password.getText().isEmpty() || id.getText().isEmpty()){
            if (login.getText().isEmpty()){
                login.setText("Заполните все поля");
            }
            if (password.getText().isEmpty()){
                password.setText("Заполните все поля");
            }
            if (id.getText().isEmpty()){
                id.setText("Заполните все поля");
            }
        }else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&login="+login.getText()
                    +"&password="+password.getText()
                    +"&userId="+id.getText()
                    ;
            List<Map> result = httpRequest.makeInList(message, URL.addAccount);
            Map<String, String> answer = result.get(result.size() - 1);
            if (answer.get("message").contains("Success")){
                accountsController.addStage.close();
            }else {
                login.setText("Ошибка сервера");
            }
        }
    }
}
