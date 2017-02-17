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
public class GetDetail extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MSMService msmService = new MSMServiceImpl();

        String site = req.getParameter("site");
        if(null!=site&&!site.equals("")){
            site = new String(site.getBytes("iso-8859-1"),"utf-8");
            req.getSession().setAttribute("site",site);
        }
        else{
           site =  (String)req.getSession().getAttribute("site");
            if(site==null||site.equals("")){ site = "乐亭";req.getSession().setAttribute("site",site);}
        }

        String optionStr = req.getParameter("option");

        System.out.println("site:"+site+"  option:"+optionStr);
        int option =0;

        try{
            option = Integer.parseInt(optionStr);
        }catch(RuntimeException e){
            option = 1;
        }

        String result = msmService.getDetail(site,option);

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
