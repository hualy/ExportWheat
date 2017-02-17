package com.gdut.ds.servlet;

import com.gdut.ds.service.MSMService;
import com.gdut.ds.serviceImpl.MSMServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cjf on 2016/9/23.
 */
public class GetMSMServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MSMService msmService = new MSMServiceImpl();

        String result = msmService.getMSMInfor();

        resp.setContentType("text/json;charset=utf-8");
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control","no-cache");


        PrintWriter out = resp.getWriter();

        out.println(result);

        out.flush();
        out.close();
//        System.out.println(result);
//        resp.sendRedirect("views/index.jsp");


//        System.out.println(result);

//        req.getRequestDispatcher("views/index.jsp").forward(req,resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
