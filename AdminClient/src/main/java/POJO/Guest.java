package POJO;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by root on 17.09.15.
 */
public class Guest {
    private final SimpleStringProperty guestId;
    private final SimpleStringProperty guestName;
    private final SimpleStringProperty guestCardId;
    private final SimpleStringProperty guestStart;
    private final SimpleStringProperty guestEnd;
    private final SimpleStringProperty guestDate;

    public Guest(String guestId, String guestName, String guestStart, String guestEnd, String guestDate, String guestCardId) {
        this.guestId = new SimpleStringProperty(guestId);
        this.guestName = new SimpleStringProperty(guestName);
        this.guestStart = new SimpleStringProperty(guestStart);
        this.guestEnd = new SimpleStringProperty(guestEnd);
        this.guestDate = new SimpleStringProperty(guestDate);
        this.guestCardId = new SimpleStringProperty(guestCardId);
    }

    public String getGuestCardId(){
        return  guestCardId.get();
    }

    public SimpleStringProperty guestCardProperty(){
        return guestCardId;
    }

    public String getGuestId() {
        return guestId.get();
    }

    public SimpleStringProperty guestIdProperty() {
        return guestId;
    }

    public String getGuestName() {
        return guestName.get();
    }

    public SimpleStringProperty guestNameProperty() {
        return guestName;
    }

    public String getGuestStart() {
        return guestStart.get();
    }

    public SimpleStringProperty guestStartProperty() {
        return guestStart;
    }

    public String getGuestEnd() {
        return guestEnd.get();
    }

    public SimpleStringProperty guestEndProperty() {
        return guestEnd;
    }

    public String getGuestDate() {
        return guestDate.get();
    }

    public SimpleStringProperty guestDateProperty() {
        return guestDate;
    }
}
