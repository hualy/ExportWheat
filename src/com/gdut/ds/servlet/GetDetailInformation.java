package com.gdut.ds.servlet;

import com.gdut.ds.service.MSMService;
import com.gdut.ds.serviceImpl.MSMServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cjf on 2016/9/24.
 * 这是一个根据年份和站点来得到详细的站点信息
 *
 */
public class GetDetailInformation extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MSMService msmService = new MSMServiceImpl();

        String site = req.getParameter("site");
        if(null!=site&&!site.equals("")){
//            site = new String(site.getBytes("iso-8859-1"),"utf-8");
            req.getSession().setAttribute("site",site);
        }
        else{
            site =  (String)req.getSession().getAttribute("site");
            if(site==null||site.equals("")){
                site = "乐亭";
                req.getSession().setAttribute("site",site);
//                String province = "河北";
            }
        }
        int startYear;
        String syStr = req.getParameter("startYear");
        if(syStr!=null){
            startYear = Integer.parseInt(syStr);
            req.getSession().setAttribute("startYear",startYear);
        }else{
            if(null!=req.getSession().getAttribute("startYear"))
            	
                startYear = Integer.parseInt(String.valueOf(req.getSession().getAttribute("startYear")));
            else startYear = 1995;
        }

        int endYear;
        String endStr = req.getParameter("endYear");
        if(endStr!=null){
            endYear = Integer.parseInt(endStr);
            req.getSession().setAttribute("endYear",endYear);
        }else{
            if(null!=req.getSession().getAttribute("endYear"))
                endYear = Integer.parseInt(String.valueOf(req.getSession().getAttribute("endYear")));

            else endYear = 2010;
        }



        System.out.print("getDetailInfor----- site:"+site+" startYear:"+startYear+" endYear:"+endYear);

        JSONObject result = msmService.getDetail(site,startYear,endYear);

        resp.setContentType("text/json;charset=utf-8");
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control","no-cache");


        PrintWriter out = resp.getWriter();

        out.println(result.toString());

        out.flush();
        out.close();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
