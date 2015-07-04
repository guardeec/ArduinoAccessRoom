package Stages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by guardeec on 22.06.15.
 */
public abstract class Initialization {

    //making the read and write file
    public static void makeFiles(File readFile, File writeFile){
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
    }

    //read the congig.txt and set the confoguration params
    public static String[] setCofiguration(File config){
        String[] configuration = null;
        if(config.exists()){
            try {
                Scanner in = new Scanner(config);
                configuration = new String[3];
                try{
                    configuration[0] = in.nextLine();
                    configuration[1] = in.nextLine();
                    configuration[2] = in.nextLine();
                }catch (java.util.NoSuchElementException ex){
                    configuration = null;
                    System.out.print(   "\nЗаполнены не все поля конфигурационного файла.\n" +
                                    "Создайте файл config.txt и занесите в него первой адрес сервера\n"+
                                    "Например localhost:8080/Get\n"+
                                    "не указывайте тип соединения (http/https) и параметры после адреса целевого сервиса такие как ? % | & и т.д.\n"+
                                    "второй строкой занесите название входного файла, например read.txt\n"+
                                    "третей строкой занесите название выходного файла, например write.txt\n"
                    );
                }
                in.close();
            } catch (FileNotFoundException e) {
                configuration = null;
            }
        }else {
            System.out.print(   "\nНет конфигурационного файла.\n" +
                                "Создайте файл config.txt и занесите в него первой адрес сервера\n"+
                                "Например localhost:8080/Get\n"+
                                "не указывайте тип соединения (http/https) и параметры после адреса целевого сервиса такие как ? % | & и т.д.\n"+
                                "второй строкой занесите название входного файла, например read.txt\n"+
                                "третей строкой занесите название выходного файла, например write.txt\n"
            );
        }
        return configuration;
    }

}
