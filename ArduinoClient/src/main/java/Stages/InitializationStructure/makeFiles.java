package Stages.InitializationStructure;

import java.io.File;
import java.io.IOException;

/**
 * Created by root on 17.07.15.
 * making the read and write file
 */
public class makeFiles {

    public static void start(File[] files){
        for (int i=0; i<4; i++){
            if(!files[i].exists()){
                try {
                    files[i].createNewFile();
                    switch (i){
                        case 0 : System.out.println("Нет файла для чтения. Создаём...");
                        case 1 : System.out.println("Нет файла для записи. Создаём...");
                        case 2 : System.out.println("Нет файла для проверки соединения. Создаём...");
                        case 3 : System.out.println("Нет файла локальной БД администраторов. Создаём...");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
