package DB_Object;

import java.util.Date;

/**
 * Created by Kolomeec on 27.04.2015.
 */
public class Syslog {
    int id;
    String date;
    String type;
    String hostname;
    String message;

    public Syslog(){
    }

    public Syslog(String date, String type, String hostname, String message){
        this.date=date;
        this.type=type;
        this.hostname=hostname;
        this.message=message;
    }

    public void SetDate(String date){
        this.date=date;
    }
    public void SeType(String type){
        this.type=type;
    }
    public void SetHostname(String hostname){
        this.hostname=hostname;
    }
    public void SetMessage(String message){
        this.message=message;
    }

    public int GetId(){
        return this.id;
    }
    public String GetDate() {
        return this.date;
    }
    public String GetType(){
        return this.type;
    }
    public String GetHostname(){
        return  this.hostname;
    }
    public String GetMessage(){
        return this.message;
    }

}
