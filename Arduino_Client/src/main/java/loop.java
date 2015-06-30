import Methods.ServerRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by guardeec on 22.06.15.
 */
public abstract class loop {

    /*
        ** Loop that will check requests from Arduino
        ** Loop is checking file once in 100mls (look at the end of method to configure it)
        ** if you dont need external exit from Loop, you can remove the "FUCK"
        ** if you will do this, dont forget to remove "break FUCK" that is down the code
         */

    public static void start(String httpAddress, File readFile, File writeFile){

        Map<String, String> input= new HashMap<String, String>();

        input.put("deviceId", "");
        System.out.print("Arduino client started");
        for(;;){
            try {

                //Reading readFile
                Scanner in = new Scanner(readFile);
                if (in.hasNext() == true){

                    //get the message from file, clear it and close it
                    String message = in.nextLine();
                    for(int i=0; i<message.split("&").length+1; i++){
                        if (message.startsWith("card=")){
                            input.put("card", message.substring(message.lastIndexOf("card=")));
                        }
                        if (message.startsWith("device_id=")){
                            input.put("device_id", message.substring(message.lastIndexOf("device_id=")));
                        }
                    }
                    /*
                    while (in.hasNext()){
                        String message = in.nextLine();
                        if (message.startsWith("card=")){
                            input.put("card", message.substring(message.lastIndexOf("card=")));
                        }
                        if (message.startsWith("device_id=")){
                            input.put("device_id", message.substring(message.lastIndexOf("device_id=")));
                        }
                    }
                    */
                    in.close();
                    PrintWriter out = new PrintWriter(readFile.getAbsoluteFile());
                    out.write("");
                    out.close();

                    /*
                    HTTP request to server
                     */
                    Map<String, String> output = ServerRequest.make(input, httpAddress);

                    //Writing to WriteFile
                    System.out.println(output.get("name")+"\n");
                    System.out.println(output.get("id")+"\n");
                    System.out.println(output.get("right_id")+"\n");
                    out = new PrintWriter(writeFile.getAbsoluteFile());
                    out.write(output.get("name")+"\n");
                    out.write(output.get("id")+"\n");
                    out.write(output.get("right_id")+"\n");
                    out.close();

                }
                else {
                    //if there is nothing in ReadFile, we will close it (look at "if" params)
                    in.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            /*
            ** delay between the checking the ReadFile
             * configure it, if you are not afraid =)
             * just remember that little delay can make app slowly too
             */
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
