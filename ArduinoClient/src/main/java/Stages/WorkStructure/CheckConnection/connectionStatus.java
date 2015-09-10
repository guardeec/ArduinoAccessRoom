package Stages.WorkStructure.CheckConnection;

import Stages.InitializationStructure.initialisation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by root on 13.08.15.
 */
public class connectionStatus {

    static File file = new File(initialisation.checkRunnableFile);

    public static void setNoConnection(){
        try {
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            out.write("1");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setYesConnetion(){
        try {
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            out.write("");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean get(){
        boolean status=true;
        if (file.length() > 0){
            status=false;
        }
        return status;
    }
}
