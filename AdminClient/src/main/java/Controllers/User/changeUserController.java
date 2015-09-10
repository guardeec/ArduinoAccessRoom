package Controllers.User;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by root on 15.07.15.
 */
public class changeUserController {
    @FXML
    private TextField changeUserId;
    @FXML
    private TextField changeUserName;
    @FXML
    private TextField changeUserRole;

    @FXML
    public void changeUserChangeBtnAction(){
        String id = changeUserId.getText();
        String name;
        String role;
        if (id.isEmpty()){
        }else {
            //http
            UserController.changeStage.close();
        }
    }

    @FXML
    public void changeUserFindBtnAction(){
        String id = changeUserId.getText();
        if (id.isEmpty()){
        }else {
            //http
        }
    }
}
