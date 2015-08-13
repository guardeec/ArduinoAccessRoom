package Controllers.User;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Created by root on 15.07.15.
 */
public class addUserController {
    @FXML
    private TextField addUserName;
    @FXML
    private TextField addUserRole;
    @FXML
    Text addUserMessage;

    @FXML
    public void addUserBtnAction(){
        String name = addUserName.getText();
        String role = addUserRole.getText();
        if (name.isEmpty() || role.isEmpty()){
            addUserMessage.setText("Заполните все поля");
        }else {
            //http
            UserController.addStage.close();
        }
    }
}
