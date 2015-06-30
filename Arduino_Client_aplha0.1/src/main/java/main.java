import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by guardeec on 28.06.15.
 */
public class main {

    private static String readStreamToString(InputStream in, String encoding) throws IOException {
        StringBuffer b = new StringBuffer();
        InputStreamReader r = new InputStreamReader(in, encoding);
        int c;
        while ((c = r.read()) != -1) {
            b.append((char)c);
        }
        return b.toString();
    }

    public static void main(String args[]) {

        String serverHttp = null;
    /*
    ** seting the files, if they are not exist - make them
     */
        File readFile = new File("read.txt");
        File writeFile = new File("write.txt");
        File configFile = new File("config.txt");
        if(!configFile.exists()){
            System.out.print("не найден config.txt");
            System.exit(0);
        }else {
            try {
                Scanner in = new Scanner(configFile);
                serverHttp = in.next();
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        if(!readFile.exists()){
            try {
                readFile.createNewFile();
                System.out.println("Нет файла для чтения. Создаём...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!writeFile.exists()){
            try {
                writeFile.createNewFile();
                System.out.println("Нет файла для записи. Создаём...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    /*
    ** Our message that we will get from Read_File
    ** after we sending this message to server
    ** after we setting server response like a message
    ** after we setting message to WriteFile
     */
        String message = "";
    /*
    ** Loop that will check requests from Arduino
    ** Loop is checking file once in 100mls (look at the end of method to configure it)
     */
    /*
    ** if you dont need external exit from Loop, you can remove the "FUCK"
    ** if you will do this, dont forget to remove "break FUCK" that is down the code
     */
        FUCK:  for(;;){
            try {
            /*
            ** Reading readFile
             */
                Scanner in = new Scanner(readFile);
                if (in.hasNext() == true){
                    //get the message from file and close it
                    message += in.nextLine();
                    in.close();
                    //clear the file
                    PrintWriter out = new PrintWriter(readFile.getAbsoluteFile());
                    out.write("");
                    out.close();
                    //external exit
                    if (message.equals("13")){
                        break FUCK;
                    }
                    //URLConnection (thank you КЭП)
                    URLConnection conn = null;
                    try {
                        conn = new URL("http://"+ serverHttp + "?" + message).openConnection();
                        message = readStreamToString(conn.getInputStream(), "UTF-8");
                    } catch (IOException e) {
                        message = "NO_SERVER_CONNECTION";
                        e.printStackTrace();
                    }
                    //Writing to WriteFile
                    out = new PrintWriter(writeFile.getAbsoluteFile());

                    message = message.replaceAll(",", "&");
                    message = message.replaceAll(" ", "");
                    message = message.replace("]", "");
                    message = message.replace("[", "");
                    System.out.print(message);

                    out.write(message);
                    out.close();
                    //after writing to file, we dont need the message
                    message = "";
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
