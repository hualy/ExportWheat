package com.gdut.ds.servlet;

import com.gdut.ds.service.MSMService;
import com.gdut.ds.serviceImpl.MSMServiceImpl;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cjf on 2016/9/25.
 * 这是一个用于站点分析的httpServlet
 */
public class GetMoreMSM extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sites = req.getParameter("sites");
        if(null!=sites){
//            site = new String(site.getBytes("iso-8859-1"),"utf-8");
            req.getSession().setAttribute("sites",sites);
        }
        else{
            sites =  (String)req.getSession().getAttribute("sites");
            if(sites==null){
                sites = "[\"毫州\",\"砀山\"]";
                req.getSession().setAttribute("sites",sites);
//                String province = "河北";
            }
        }
        int startYear2;
        String syStr = req.getParameter("startYear2");
        if(syStr!=null){
            startYear2 = Integer.parseInt(syStr);
            req.getSession().setAttribute("startYear2",startYear2);
        }else{
            if(null!=req.getSession().getAttribute("startYear2"))
                startYear2 = Integer.parseInt(String.valueOf(req.getSession().getAttribute("startYear2")));
            else startYear2 = 1995;
        }

        int endYear2;
        String endStr = req.getParameter("endYear2");
        if(endStr!=null){
            endYear2 = Integer.parseInt(endStr);
            req.getSession().setAttribute("endYear2",endYear2);
        }else{
            if(null!=req.getSession().getAttribute("endYear2"))
                endYear2 = Integer.parseInt(String.valueOf(req.getSession().getAttribute("endYear2")));

            else endYear2 = 2010;
        }


        MSMService msmService = new MSMServiceImpl();

        System.out.println("sites"+sites);

        JSONArray jsonArray = JSONArray.fromObject(sites);
        Object[] objects = jsonArray.toArray();
        String[] strings = new String[objects.length];
       for(int i =0;i<objects.length;i++){
           strings[i] = objects[i].toString();
       }

       System.out.println("sites"+strings+" startYear"+startYear2+" endYears"+endYear2);

        String result = msmService.getDetail(strings,startYear2,endYear2);

        resp.setContentType("text/json;charset=utf-8");
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control","no-cache");


        PrintWriter out = resp.getWriter();

        out.println(result);

        out.flush();
        out.close();

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
