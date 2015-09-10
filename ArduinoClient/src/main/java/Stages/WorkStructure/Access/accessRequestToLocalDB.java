package Stages.WorkStructure.Access;

import Stages.InitializationStructure.initialisation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 13.08.15.
 */
public class accessRequestToLocalDB {
    public static Map<String, String> make (Map<String, String> input){
        Map<String, String> output = null;

        ArrayList<Map> adminDB = new ArrayList<Map>();
        FileInputStream fis = null;
        if(new File(initialisation.adminDBFile).length()!=0){
            try {
                fis = new FileInputStream(new File(initialisation.adminDBFile));
                ObjectInputStream ois = new ObjectInputStream(fis);
                adminDB = (ArrayList) ois.readObject();
                ois.close();
                fis.close();

                for (Map<String, String> admin : adminDB){
                    if (admin.get("card").compareTo(input.get("card"))==0){
                        output = new HashMap<String, String>();
                        output.put("name", admin.get("name"));
                        output.put("id", admin.get("id"));
                        output.put("access", "true");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        if (output == null){
            output = new HashMap<String, String>();
            output.put("id", "UNKNOWN");
            output.put("name", "UNKNOWN");
            output.put("access", "false");
        }

        return output;

    }
}
