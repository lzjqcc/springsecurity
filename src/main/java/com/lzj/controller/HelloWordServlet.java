package com.lzj.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by win7 on 2017-07-10.
 */
@WebServlet("/hello")
public class HelloWordServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.print("doGet");
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }
}

