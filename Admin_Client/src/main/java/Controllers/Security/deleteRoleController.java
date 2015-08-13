package Controllers.Security;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by root on 15.07.15.
 */
public class deleteRoleController {
    @FXML
    TextField deleteSecurityRoleId;

    @FXML
    public void deleteSecurityDeleteBtnAction(){
        String id = deleteSecurityRoleId.getText();
        if (id.isEmpty()){
        }
        else {
            //http
            SecurityController.deleteStage.close();
        }
    }
}
