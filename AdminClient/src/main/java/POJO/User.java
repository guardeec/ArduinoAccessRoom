package POJO;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by root on 13.07.15.
 */
public class User {

    private final SimpleStringProperty userId;
    private final SimpleStringProperty userName;
    private final SimpleStringProperty userRole;

    public User(String userId, String userName, String userRole) {
        this.userId = new SimpleStringProperty(userId);
        this.userName = new SimpleStringProperty(userName);
        this.userRole = new SimpleStringProperty(userRole);
    }

    public String getUserId() {
        return userId.get();
    }

    public SimpleStringProperty userIdProperty() {
        return userId;
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public String getUserRole() {
        return userRole.get();
    }

    public SimpleStringProperty userRoleProperty() {
        return userRole;
    }
}
