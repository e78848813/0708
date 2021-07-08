package com.hazards.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String methodName = req.getParameter("method");
        if(methodName!=null&&!methodName.trim().equals("")) {
            try {
                Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                method.invoke(this, req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
