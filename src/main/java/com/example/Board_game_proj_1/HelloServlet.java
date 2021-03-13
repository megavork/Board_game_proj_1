package com.example.Board_game_proj_1;

import lombok.Data;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        //getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(request,response);
        getServletContext().getRequestDispatcher("/jsp/reg_page.jsp").forward(request,response);

        String str1 = new String("Hello");
        changeStr1(str1);
        System.out.println(str1);

        String str2 = new String("Hello");
        changeStr2(str1);
        System.out.println(str2);

        Integer intVal = Integer.valueOf(8);
        changeInt(intVal);
        System.out.println(intVal);

        Person person = new Person("John");
        changePerson(person);
        System.out.println(person.getName());

        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        System.out.println(s1==s2);
        System.out.println(s1==s3); //что сделать, чтобы был другой результат?

        Integer i = 132;
        Integer i2 = 132;
        System.out.println(i==i2);          // ??????
        System.out.println(i.equals(i2));   //????


    }

    public static void changeStr1(String str) {
        str.concat(" Karina");
    }
    public static void changeStr2(String str) {
        str = str.concat("  Karina");
    }
    public static void changeInt(Integer intVal) {
        intVal++;
    }
    public static void changePerson(Person person) {
        person = new Person("Valerka");
    }

    public void destroy() {
    }
}


@Data
class Person {
    String name = "";

    public Person(String name) {
        this.name = name;
    }
}