package Stages.InitializationStructure;

import java.io.File;

/**
 * Created by guardeec on 22.06.15.
 */
public class initialisation {

    public static String httpAddress;
    public static String httpAddressCheckRunnable;
    public static String httpAddressCashAdminDB;
    public static String inputFile;
    public static String outputFile;
    public static String checkRunnableFile;
    public static String adminDBFile;

    public static void start() {

        //setting the configuration
        String[] configuration = setConfiguration.start(new File("config.txt"));
        if(configuration != null){
            httpAddress = configuration[0];
            httpAddressCheckRunnable = configuration[1];
            httpAddressCashAdminDB = configuration [2];
            inputFile = configuration[3];
            outputFile = configuration[4];
            checkRunnableFile = configuration[5];
            adminDBFile = configuration[6];

            File files[] = {    new File(inputFile),
                                new File(outputFile),
                                new File(checkRunnableFile),
                                new File(adminDBFile)
            };

            //make files if they are not exist
            makeFiles.start(files);
        }else {
            System.exit(0);
        }
    }






}
