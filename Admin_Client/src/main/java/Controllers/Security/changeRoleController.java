package Controllers.Security;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Created by root on 15.07.15.
 */
public class changeRoleController {
    @FXML
    TextField changeSecurityId;

    @FXML
    TextField changeSecurityRole;

    @FXML
    Text changeSecurityStatus;

    @FXML
    public void changeSecurityChangeBtnAction(){
        //http
        SecurityController.changeStage.close();
    }
    @FXML
    public void changeSecurityFindBtnAction(){
        String id = changeSecurityId.getText();
        String role = changeSecurityRole.getText();

        if (id.isEmpty() || role.isEmpty()){
        }else {
            //http
        }
    }
}
