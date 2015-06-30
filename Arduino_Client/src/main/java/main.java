import Methods.Initialization;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by guardeec on 18.05.15.
 */
public class main {



    public static void main(String args[]) {

        //Setting the configuration
        File config = new File("config.txt");
        String[] configuration = Initialization.setCofiguration(config);

        if(configuration != null){
            String httpAddress = configuration[0];
            String inputFile = configuration[1];
            String outputFile = configuration[2];

            //setting the files, if they are not exist - make them
            File readFile = new File(inputFile);
            File writeFile = new File(outputFile);
            Initialization.makeFiles(readFile, writeFile);

            //Starting LOOP
            loop.start(httpAddress, readFile, writeFile);
        }
    }

}

