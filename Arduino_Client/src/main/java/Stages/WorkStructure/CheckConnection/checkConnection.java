package Stages.WorkStructure.CheckConnection;

import Stages.InitializationStructure.initialisation;

import java.util.Map;

/**
 * Created by root on 12.08.15.
 */
public class checkConnection extends Thread {
    String httpAddress = initialisation.httpAddressCheckRunnable;
    public void run() {
        try {
            for (;;){
                Map<String, String> output = checkConnectionRequest.make(httpAddress);
                if (Boolean.parseBoolean(output.get("DB")) && Boolean.parseBoolean(output.get("Server"))){
                    connectionStatus.setYesConnetion();
                }else {
                    connectionStatus.setNoConnection();
                }
                System.out.println("DB: "+Boolean.parseBoolean(output.get("DB")));
                System.out.println("Server: "+Boolean.parseBoolean(output.get("Server")));
                Thread.sleep(20000);
            }
        } catch (InterruptedException e) {
        }
    }
}
