package Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guardeec on 19.05.15.
 */
public class Parsing {
    public static String[] parse(String a){
        String[] b = a.split("[|]+");
        return b;
    }

    public static String parseArrayHashMap(String request){
        String response = "";

        int StrokeNumbers = 0;
        for(int i=0; i<request.length(); i++){
            if(request.charAt(i)==']'){
                StrokeNumbers++;
            }
        }

        if(StrokeNumbers>1){

            ArrayList<String> ArrayMap = new ArrayList<String>();
            for(int i=0 ; i<request.length(); i++){

                if (request.charAt(i)=='['){
                    String Map = "";
                    do{
                        Map += request.charAt(i);
                        i++;
                    }while(request.charAt(i)!='\n');
                    ArrayMap.add(Map);
                }
            }

            for(String i:ArrayMap){
                response +=  parseHashMap(i)[0] + " "
                        + parseHashMap(i)[1] + " "
                        + parseHashMap(i)[2] + " "
                        + parseHashMap(i)[3] + " "
                        + parseHashMap(i)[4] + " "
                        + parseHashMap(i)[5] + "\n"
                ;
            }

        }else{
            response =  parseHashMap(request)[0] + " "
                        + parseHashMap(request)[1] + " "
                        + parseHashMap(request)[2] + " "
                        + parseHashMap(request)[3] + " "
                        + parseHashMap(request)[4] + " "
                        + parseHashMap(request)[5]
            ;
        }

        return response;
    }

    public static String[] parseHashMap(String request){
        String[] response = {"", "", "", "", "", ""};

        if (request.contains("user_id=")){
            int startIndex = request.lastIndexOf("user_id=") + "user_id=".length();
            while (request.charAt(startIndex)!=',' && request.charAt(startIndex)!=']'){
                response[0] +=request.charAt(startIndex);
                startIndex++;
            }
        }

        if (request.contains("name=")){
            int startIndex = request.lastIndexOf("name=") + "name=".length();
            while (request.charAt(startIndex)!=',' && request.charAt(startIndex)!=']'){
                response[1] +=request.charAt(startIndex);
                startIndex++;
            }
        }

        if (request.contains("role_id=")) {
            int startIndex = request.lastIndexOf("role_id=") + "role_id=".length();
            while (request.charAt(startIndex)!=',' && request.charAt(startIndex)!=']'){
                response[2] +=request.charAt(startIndex);
                startIndex++;
            }
        }

        if (request.contains("Roles=")) {
            int startIndex = request.lastIndexOf("Roles=") + "Roles=".length();
            while (request.charAt(startIndex)!=',' && request.charAt(startIndex)!=']'){
                response[3] +=request.charAt(startIndex);
                startIndex++;
            }
        }

        if (request.contains("card=")) {
            int startIndex = request.lastIndexOf("card") + "card=".length();
            while (request.charAt(startIndex)!=',' && request.charAt(startIndex)!=']'){
                response[4] +=request.charAt(startIndex);
                startIndex++;
            }
        }

        if (request.contains("state=")){
            int startIndex = request.lastIndexOf("state") + "state=".length();
            while (request.charAt(startIndex)!=',' && request.charAt(startIndex)!=']'){
                response[5] +=request.charAt(startIndex);
                startIndex++;
            }
        }

        return response;
    }


    public static String[] parseWithNull(String a){
        String[] b = {"", "", "", "", ""};

        for(int i=0, n=0, flag=0; i<a.length(); i++){
            if(!Character.toString(a.charAt(i)).contains("|")){
                b[n] += a.charAt(i);
                flag=0;
            }
            if(Character.toString(a.charAt(i)).contains("|") && (flag==1 || i==0)){
                b[n]=null;
                flag=1;
            }
            if(Character.toString(a.charAt(i)).contains("|") && flag==0){
                flag=1;
            }

            if(flag==1){
                n++;
            }

        }
        return b;
    }
}
