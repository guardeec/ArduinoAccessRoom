package Stages.InitializationStructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by root on 17.07.15.
 * read the congig.txt and set the configuration params
 */
public class setConfiguration {

    public static String[] start(File config){
        String[] configuration = null;
        if(config.exists()){
            try {
                Scanner in = new Scanner(config);
                configuration = new String[8];
                try{
                    configuration[0] = in.nextLine(); //http
                    configuration[1] = in.nextLine(); //httpCheck
                    configuration[2] = in.nextLine(); //httpCash
                    configuration[3] = in.nextLine(); //input
                    configuration[4] = in.nextLine(); //output
                    configuration[5] = in.nextLine(); //check
                    configuration[6] = in.nextLine(); //cash
                    configuration[7] = in.nextLine(); //deviceId
                }catch (java.util.NoSuchElementException ex){
                    configuration = null;
                    System.out.print(   "\nЗаполнены не все поля конфигурационного файла.\n" +
                                    "Создайте файл config.txt и занесите в него первой адрес сервера Access\n"+
                                    "Например localhost:8080/Get\n"+
                                    "не указывайте тип соединения (http/https) и параметры после адреса целевого сервиса такие как ? % | & и т.д.\n"+
                                    "второй строкой занесите адрес проверки работоспособности сервера\n"+
                                    "третей строкой занесите адрес копирования БД администраторов\n"+
                                    "четвёртой строкой занесите название входного файла, например read.txt\n"+
                                    "пятой строкой занесите название выходного файла, например write.txt\n"+
                                    "шестой строкой занесите название файла для проверки соединения, например connection.txt\n"+
                                    "седьмой строкой занесите название файла БД администраторов, например adminDB.txt\n"+
                                    "восьмой строчкой укажите id устройства"
                    );
                }
                in.close();
            } catch (FileNotFoundException e) {
                configuration = null;
            }
        }else {
            System.out.print(   "\nНет конфигурационного файла.\n" +
                            "Создайте файл config.txt и занесите в него первой адрес сервера Access\n"+
                            "Например localhost:8080/Get\n"+
                            "не указывайте тип соединения (http/https) и параметры после адреса целевого сервиса такие как ? % | & и т.д.\n"+
                            "второй строкой занесите адрес проверки работоспособности сервера\n"+
                            "третей строкой занесите адрес копирования БД администраторов\n"+
                            "четвёртой строкой занесите название входного файла, например read.txt\n"+
                            "пятой строкой занесите название выходного файла, например write.txt\n"+
                            "шестой строкой занесите название файла для проверки соединения, например connection.txt\n"+
                            "седьмой строкой занесите название файла БД администраторов, например adminDB.txt\n"+
                            "восьмой строчкой укажите id устройства"
            );
        }
        return configuration;
    }

}
