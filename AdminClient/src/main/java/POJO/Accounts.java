package POJO;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by root on 23.09.15.
 */
public class Accounts {
    private final SimpleStringProperty id;
    private final SimpleStringProperty account;
    private final SimpleStringProperty login;
    private final SimpleStringProperty device;
    private final SimpleStringProperty reception;
    private final SimpleStringProperty hr;
    private final SimpleStringProperty security;

    public Accounts(String id, String account, String login, String device, String reception, String hr, String security) {
        this.id = new SimpleStringProperty(id);
        this.account = new SimpleStringProperty(account);
        this.login = new SimpleStringProperty(login);
        this.device = new SimpleStringProperty(device);
        this.reception = new SimpleStringProperty(reception);
        this.hr = new SimpleStringProperty(hr);
        this.security = new SimpleStringProperty(security);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getAccount() {
        return account.get();
    }

    public SimpleStringProperty accountProperty() {
        return account;
    }

    public String getLogin() {
        return login.get();
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public String getDevice() {
        return device.get();
    }

    public SimpleStringProperty deviceProperty() {
        return device;
    }

    public String getReception() {
        return reception.get();
    }

    public SimpleStringProperty receptionProperty() {
        return reception;
    }

    public String getHr() {
        return hr.get();
    }

    public SimpleStringProperty hrProperty() {
        return hr;
    }

    public String getSecurity() {
        return security.get();
    }

    public SimpleStringProperty securityProperty() {
        return security;
    }
}
