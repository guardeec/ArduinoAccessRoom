package Controllers;

import POJO.Device;
import POJO.GuestCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Created by root on 13.07.15.
 */
public class ReceptionController extends mainController {
    @FXML
    private TextField guestCardNumber;
    @FXML
    private MenuButton guestCardStatus;
    @FXML
    private TextField guestCardGuest;

    @FXML
    private TableView<GuestCard> guestTable;

    public void findGuestBtnAction(){
        ObservableList<GuestCard> guestCards = FXCollections.observableArrayList(
                new GuestCard("1343423", "Активна", "Tom"),
                new GuestCard("2234233", "Активна", "Lena"),
                new GuestCard("3234444", "Неактивна", null),
                new GuestCard("4324422", "Неактивна", "Eva")
        );
        guestTable.setItems(guestCards);
    }

    @FXML
    public void guestStatusActiveSelected(){
        System.out.println("Hi");
    }
    @FXML
    public void guestStatusDisactiveSelected(){
        System.out.println("Bye");
    }
}
