package Stages;

import Stages.loop_Methods.Http2Map;
import Stages.loop_Methods.ServerRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by guardeec on 22.06.15.
 */
public abstract class loop {

    /*
    Loop that will check requests from Arduino
    Loop is checking file once in 100mls (look at the end of method to configure it)
    */

    public static void start(String httpAddress, File readFile, File writeFile){

        System.out.print("Arduino client started");
        for(;;){
            try {

                Scanner in = new Scanner(readFile);
                if (in.hasNext() == true){

                    //Reading readFile
                    Map<String, String> input = Http2Map.parseHttp2Map(in.nextLine());
                    in.close();
                    PrintWriter out = new PrintWriter(readFile.getAbsoluteFile());
                    out.write("");
                    out.close();

                    //HTTP request to server
                    Map<String, String> output = ServerRequest.make(input, httpAddress);

                    //Writing to WriteFile
                    out = new PrintWriter(writeFile.getAbsoluteFile());
                    out.write("user_name="+output.get("user_name")+"&");
                    out.write("user_id="+output.get("user_id")+"&");
                    out.write("user_access="+output.get("user_access"));
                    out.close();

                }
                else {
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
