package Stages.WorkStructure.Access;

import Stages.InitializationStructure.initialisation;
import Stages.Methods.HttpParse;
import Stages.WorkStructure.CheckConnection.connectionStatus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by guardeec on 22.06.15.
 */
public class access extends Thread{

    /*
    Loop that will check requests from Arduino
    Loop is checking file once in 100mls (look at the end of method to configure it)
    */

    public void run() {
        File readFile = new File(initialisation.inputFile);
        File writeFile = new File(initialisation.outputFile);
        String httpAddress = initialisation.httpAddress;

        System.out.println("Arduino client started");
        try{
            for (; ; ) {

                boolean connection = connectionStatus.get();
                if (connection){
                    try {
                        Scanner in = new Scanner(readFile);
                        if (in.hasNext() == true) {


                            //Reading readFile
                            Map<String, String> input = HttpParse.parseHttp2Map(in.nextLine());
                            in.close();
                            PrintWriter out = new PrintWriter(readFile.getAbsoluteFile());
                            out.write("");
                            out.close();

                            //HTTP request to server
                            Map<String, String> output = accessRequest.make(input, httpAddress);


                            //Writing to WriteFile
                            out = new PrintWriter(writeFile.getAbsoluteFile());
                            String outputMessage = "name=" + output.get("name") + "&";
                            outputMessage += "id=" + output.get("id") + "&";
                            outputMessage += "access=" + output.get("access");
                            out.write(outputMessage);
                            out.close();
                        } else {
                            in.close();
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        Scanner in = new Scanner(readFile);
                        if (in.hasNext() == true) {


                            //Reading readFile
                            Map<String, String> input = HttpParse.parseHttp2Map(in.nextLine());
                            in.close();
                            PrintWriter out = new PrintWriter(readFile.getAbsoluteFile());
                            out.write("");
                            out.close();

                            //HTTP request to localDB
                            Map<String, String> output = accessRequestToLocalDB.make(input);
                            System.out.println(output.entrySet());
                            //Writing to WriteFile
                            out = new PrintWriter(writeFile.getAbsoluteFile());
                            String outputMessage = "name=" + output.get("name") + "&";
                            outputMessage += "id=" + output.get("id") + "&";
                            outputMessage += "access=" + output.get("access");
                            out.write(outputMessage);
                            out.close();
                        } else {
                            in.close();
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }


                Thread.sleep(100);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
