package accessToDataBase.methods;

/**
 * Created by Kolomeec on 26.04.2015.
 */
public class Parsing {

    public static String[] parse(String a){
        String[] b = a.split("[|]+");
        return b;
    }

    //Parsing is STUPID
    //Rewrite, please
    public static String[] parseWithNull(String a){
        String[] b = {"", "", "", "", "", ""};

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

    public static Integer IntegerIsNull(String integer){

        if (integer == null || integer.isEmpty()){
            return null;
        }
        else{
            return Integer.parseInt(integer);
        }

    }

    public static String StringIsNull(String string){

        if (string.isEmpty()){
            return null;
        }
        else{
            return string;
        }

    }

    public static Boolean BooleanIsNull(String bool){
        if (bool == null || bool.isEmpty()){
            return null;
        }
        else{
            return Boolean.parseBoolean(bool);
        }
    }
}
