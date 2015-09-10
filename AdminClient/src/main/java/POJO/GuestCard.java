package POJO;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by root on 13.07.15.
 */
public class GuestCard {
    private final SimpleStringProperty cardNumber;
    private final SimpleStringProperty cardStatus;
    private final SimpleStringProperty cardGuest;

    public GuestCard(String cardNumber, String cardStatus, String cardGuest) {
        this.cardNumber = new SimpleStringProperty(cardNumber);
        this.cardStatus = new SimpleStringProperty(cardStatus);
        this.cardGuest = new SimpleStringProperty(cardGuest);
    }

    public String getCardNumber() {
        return cardNumber.get();
    }

    public SimpleStringProperty cardNumberProperty() {
        return cardNumber;
    }

    public String getCardStatus() {
        return cardStatus.get();
    }

    public SimpleStringProperty cardStatusProperty() {
        return cardStatus;
    }

    public String getCardGuest() {
        return cardGuest.get();
    }

    public SimpleStringProperty cardGuestProperty() {
        return cardGuest;
    }
}
