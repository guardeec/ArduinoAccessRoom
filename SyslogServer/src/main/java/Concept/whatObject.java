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
public class whatObject extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String object = req.getReader().readLine();

        Gson gson = new Gson();
        Pojo pojo = gson.fromJson(object, Pojo.class);

        PrintWriter out = resp.getWriter();
        out.println(pojo.getA() + " " + pojo.getB());
        resp.addHeader("Type", "POJO4");
    }

}
