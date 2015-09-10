package Stages.WorkStructure;

import Stages.WorkStructure.Access.access;
import Stages.WorkStructure.CheckConnection.checkConnection;
import Stages.WorkStructure.cashAdminDB.cashAdminDB;

/**
 * Created by root on 17.07.15.
 */
public class work {
    public static void start() {
        new access().start();
        new checkConnection().start();
        new cashAdminDB().start();
    }
}
