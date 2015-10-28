package Concept;



import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by root on 27.10.15.
 */
public class getObject extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Pojo pojo = new Pojo();
        String a = req.getReader().readLine();
        if (a.compareTo("1")==0){
            pojo.setA(1);
            pojo.setB("Hello");
        }else {
            pojo.setA(2);
            pojo.setB("Bye");
        }

        Gson gson = new Gson();
        String object = gson.toJson(pojo);

        PrintWriter out = resp.getWriter();
        out.println(object);
        resp.addHeader("Type", "POJO1");

    }
}
