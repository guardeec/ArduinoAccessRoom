package Stages.WorkStructure.cashAdminDB;

import Stages.InitializationStructure.initialisation;
import Stages.WorkStructure.CheckConnection.checkConnectionRequest;
import Stages.WorkStructure.CheckConnection.connectionStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by root on 12.08.15.
 */
public class cashAdminDB extends Thread {
    File file = new File(initialisation.adminDBFile);
    String httpAddress = initialisation.httpAddressCashAdminDB;
    public void run() {
        try {
            for (;;){

                //делаем запрос
                List<Map> output = cashAdminDBRequest.make(httpAddress);
                //в ответе есть сообщение, которое говорит получили ли мы БД админов
                //посмотрим его, перед тем как удалять старую БД
                boolean requestStatus = false;
                for (Map<String, String> a : output){
                    if (a.containsKey("message")){
                        if (a.get("message").toLowerCase().contains("success")){
                            requestStatus = true;
                        }
                    }
                }

                //если сообщение пришло успешно, производим замену БД
                if (requestStatus){
                    try {
                        FileOutputStream FOStream = new FileOutputStream(file);
                        ObjectOutputStream OOStream = new ObjectOutputStream(FOStream);
                        OOStream.writeObject(output);
                        OOStream.close();
                        FOStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    connectionStatus.setNoConnection();
                }
                Thread.sleep(3600000);
            }
        } catch (InterruptedException e) {
        }
    }
}
