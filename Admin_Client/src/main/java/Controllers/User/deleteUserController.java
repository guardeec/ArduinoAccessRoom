package Controllers.User;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by root on 15.07.15.
 */
public class deleteUserController {
    @FXML
    TextField deleteUserId;

    @FXML
    TextField deleteUserName;

    @FXML
    TextField deleteUserRole;

    @FXML
    public void deleteUserDeleteBtnAction(){
        String id = deleteUserId.getText();
        //http
        UserController.deleteStage.close();
    }

    @FXML
    public void deleteUserFindBtnAction(){
        String id = deleteUserId.getText();
        //http
    }
}
