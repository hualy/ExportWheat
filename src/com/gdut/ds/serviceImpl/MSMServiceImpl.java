package com.gdut.ds.serviceImpl;

import com.gdut.ds.service.MSMService;
import com.gdut.ds.util.ConnectDatabase;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cjf on 2016/9/24.
 *
 * 实现MSMService接口的类
 *
 */
public class MSMServiceImpl implements MSMService {

    public String getMSMInfor() {
        String sql = "select * from msm ";

        Connection conn = ConnectDatabase.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        JSONObject root = new JSONObject();
        JSONArray altitudes = new JSONArray();
        JSONObject lals = new JSONObject();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();



//            JSONArray latitudes = new JSONArray();  //存放的是海拔高度
//
//            JSONArray lal = new JSONArray();    //这个存放的是经度 与 纬度

            while(rs.next()){
//                rs.getInt(1);

                String name = rs.getString(3);

                double longititude = rs.getInt(5)/100.0;
                double latitude = rs.getInt(6)/100.0;
                int altitude = rs.getInt(7);

                JSONObject alt = new JSONObject();
                alt.put("name",name);
                alt.put("value",altitude);
                altitudes.add(alt);
//                JSONObject lal = new JSONObject();
                JSONArray lalTemp = new JSONArray();
                lalTemp.add(longititude);
                lalTemp.add(latitude);
                lals.put(name,lalTemp);



//                msm.put("DSNo",rs.getString(2));
//                msm.put("site",rs.getString(3));
//                msm.put("province",rs.getString(4));
//                msm.put("longititude",rs.getInt(5)/100.0);
//                msm.put("latitude",rs.getInt(6)/100.0);
//                msm.put("altitude",rs.getInt(7));
//
//                msmArray.add(msm);

//                rs.getString(2);
//                rs.getString(3);
//                rs.getString(4);
//                rs.getInt(5);
//                rs.getInt(6);
//                System.out.println("id:"+ rs.getInt(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        ConnectDatabase.close(rs,ps,conn);

        root.put("lals",lals);
        root.put("altitudes",altitudes);

        return root.toString();
    }



    public String getDetail(String site, int option) {
        String sql = "select DSNo from msm where site=?";
        Connection conn = ConnectDatabase.getConn();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,site);

            ResultSet rs = ps.executeQuery();
            int DSNo = 0;
            if(rs.next()){
                DSNo = rs.getInt(1);
            }
            String sqlStr = "";
            switch(option){
                case 2:sqlStr = "select year_,measureOfCPP from cpp where DSNo=? order by year_ asc";break;
                case 3:sqlStr = "select year_,measureOfPanicles from panicles where DSNo=? order by year_ asc";break;
                case 4:sqlStr = "select year_,measureOfRainfall from rainfall where DSNo=? order by year_ asc";break;
                default: sqlStr = "select year_,measureOfLTPP from ltpp where DSNo=? order by year_ asc";break;
            }
            System.out.println(sqlStr);
            ps = conn.prepareStatement(sqlStr);
            ps.setInt(1,DSNo);
            rs = ps.executeQuery();

            JSONObject root = new  JSONObject();

            JSONArray years = new JSONArray();
            JSONArray values = new JSONArray();
            double max  =0,min =0;
            boolean flag = true;
            while(rs.next()){
                years.add(rs.getObject(1));

                double value = Double.parseDouble(String.valueOf(rs.getObject(2)));
                if(flag){
                    max = min = value;
                    flag = false;
                }else{
                    if(max<value) max = value;
                    if(min > value) min = value;
                }
                values.add(value);

            }
            String name;
            String unit;
            switch(option){
                case 2: name = "气候生产潜力";unit = "t/hm^2*year";break;
                case 3: name = "生育期需水量";unit = "ml";break;
                case 4:name = "降水量盈亏";unit="ml";break;
                default:name = "光温生产潜力";unit="kg*hm^-1/hr";break;
            }
            root.put("site",site);
            root.put("name",name);
            root.put("years",years);
            root.put("values",values);
            root.put("unit",unit);

            double distance = (max - min)/5;

            if(max<0&&max+distance>0) max = 0;
            else max = max+distance;

            if(min>0&&min-distance<0) min = 0;
            else min = min-distance;

            root.put("max",(int)max);
            root.put("min",(int)min);

            ConnectDatabase.close(rs,ps,conn);

            return root.toString();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public JSONObject getDetail(String site, int startYear, int endYear) {

        try{
            Connection conn = ConnectDatabase.getConn();
            String sql = "select DSNo from msm where site=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,site);
            ResultSet rs = ps.executeQuery();
            int DSNo = 0;
            if(rs.next()){
                DSNo = rs.getInt(1);
            }
            JSONObject root = new JSONObject();
            JSONArray years = new JSONArray();
            for(int i=startYear;i<=endYear;i++){
                years.add(i);
            }
            root.put("years",years);
            root.put("title",site+":"+startYear+"-"+endYear);

            Temp ltppsTemp = findDetailForYear(DSNo,startYear,endYear,"select distinct year_,measureOfLTPP from ltpp where DSNo= ? and year_ >= ? and year_<= ? order by year_ asc");
            Temp cppsTemp = findDetailForYear(DSNo,startYear,endYear,"select distinct year_,measureOfCPP from cpp where DSNo= ? and year_ >= ? and year_<= ? order by year_ asc");
            Temp paniclesesTemp = findDetailForYear(DSNo,startYear,endYear,"select distinct year_,measureOfPanicles from panicles where DSNo= ? and year_ >= ? and year_<= ? order by year_ asc");
            Temp rainfallsTemp = findDetailForYear(DSNo,startYear,endYear,"select distinct year_,measureOfRainfall from rainfall where DSNo= ? and year_ >= ? and year_<= ? order by year_ asc");


            if(null!=ltppsTemp){
                root.put("ltpps",ltppsTemp.getResult());
                double distance = (ltppsTemp.getMax()-ltppsTemp.getMin())/5;
                double max = ltppsTemp.getMax()+distance;
                if(ltppsTemp.getMax()<0&&max>0) max =0 ;
                double min = ltppsTemp.getMin()-distance;
                if(min<0&&ltppsTemp.getMin()>0) min = 0;
                root.put("ltppsMax",(int)max);
                root.put("ltppsMin",(int)min);
            }

            if(null!=cppsTemp){
                root.put("cpps",cppsTemp.getResult());
                double distance = (cppsTemp.getMax()-cppsTemp.getMin())/5;

                double max = cppsTemp.getMax()+distance;
                if(cppsTemp.getMax()<0&&max>0) max =0 ;
                double min = cppsTemp.getMin()-distance;
                if(min<0&&cppsTemp.getMin()>0) min = 0;

                root.put("cppsMax",(int)max);
                root.put("cppsMin",(int)min);
            }

            if(null!=paniclesesTemp){
                root.put("panicleses",paniclesesTemp.getResult());
                double distance = (paniclesesTemp.getMax()-paniclesesTemp.getMin())/5;

                double max = paniclesesTemp.getMax()+distance;
                if(paniclesesTemp.getMax()<0&&max>0) max =0 ;
                double min = paniclesesTemp.getMin()-distance;
                if(min<0&&paniclesesTemp.getMin()>0) min = 0;

                root.put("paniclesesMax",(int)max);
                root.put("paniclesesMin",(int)min);
            }

            if(null!=rainfallsTemp){
                root.put("rainfalls",rainfallsTemp.getResult());
                double distance = (rainfallsTemp.getMax()-rainfallsTemp.getMin())/5;

                double max = rainfallsTemp.getMax()+distance;
                if(rainfallsTemp.getMax()<0&&max>0) max =0 ;
                double min = rainfallsTemp.getMin()-distance;
                if(min<0&&rainfallsTemp.getMin()>0) min = 0;

                root.put("rainfallsMax",(int)max);
                root.put("rainfallsMin",(int)min);
            }

            return root;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    private Temp findDetailForYear(int DSNo,int startYear,int endYear,String sql){
        try{
            Connection conn = ConnectDatabase.getConn();
//            String sql = "select year_,measureOfLTPP from ltpp where DNSo= ? and year_ >= ? and year_<= ? order by year_ asc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,DSNo);
            ps.setInt(2,startYear);
            ps.setInt(3,endYear);
            ResultSet rs = ps.executeQuery();

            double min = 0.0;
            double max = 0.0;
            boolean flag = false;
            int i = startYear;

            JSONArray jsonArray = new JSONArray();
            while(rs.next()){
                int year = rs.getInt(1);
//                if(year>startYear)
                int j;
                for(j = i;j<year;j++){
                    jsonArray.add(0);
                }
                double value = Double.parseDouble(String.valueOf(rs.getObject(2)));
               if(flag){
                   if(value>max) max = value;
                   if(value<min) min = value;
               }else{
                   max = min = value;
                   flag = true;
               }
                jsonArray.add(value);
                i = j+1;
            }
            ConnectDatabase.close(rs,ps,conn);
            Temp temp = new Temp();
            temp.setMax(max);
            temp.setMin(min);
//            System.out.println("max:"+max+" min:"+min+" string:"+jsonArray.toString());
            temp.setResult(jsonArray.toString());
            return temp;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    protected class Temp{
        private double min;
        private double max;
        private String result;

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    public String getDetail(String[] sites, int startYear2, int endYear2) {
        JSONObject root = new JSONObject();
        JSONArray names = new JSONArray();
        JSONArray datas = new JSONArray();

        int ltppmax =0;
        int ltppmin = 0;
        int cppmax =0;
        int cppmin = 0;
        int paniclesmax = 0;
        int paniclesmin = 0;
        int rainfallmax = 0;
        int rainfallmin = 0;
        boolean flag = true;
        for(String site:sites){
            JSONObject jo = getDetail(site,startYear2,endYear2);
            names.add(site);
            datas.add(jo);
            int max ;
            int min;
            if(flag) {
                ltppmax = jo.getInt("ltppsMax");
                ltppmin = jo.getInt("ltppsMin");
                cppmax = jo.getInt("cppsMax");
                cppmin = jo.getInt("cppsMin");
                paniclesmax = jo.getInt("paniclesesMax");
                paniclesmin = jo.getInt("paniclesesMin");
                rainfallmax = jo.getInt("rainfallsMax");
                rainfallmin = jo.getInt("rainfallsMin");
                flag = false;
            }else{
                max = jo.getInt("cppsMax");
                if(max>cppmax) cppmax = max;
                min = jo.getInt("cppsMin");
                if(min<cppmin) cppmin = min;

                max = jo.getInt("ltppsMax");
                if(max>ltppmax) ltppmax = max;
                min = jo.getInt("ltppsMin");
                if(min<ltppmin) ltppmin = min;

                max = jo.getInt("paniclesesMax");
                if(max>paniclesmax) paniclesmax = max;
                min = jo.getInt("paniclesesMin");
                if(min<paniclesmin) paniclesmin = min;

                max = jo.getInt("rainfallsMax");
                if(max>rainfallmax) rainfallmax = max;
                min = jo.getInt("rainfallsMin");
                if(min<rainfallmin) rainfallmin = min;

            }

        }
        root.put("names",names);
        root.put("datas",datas);

        root.put("ltppMax",ltppmax);
        root.put("ltppMin",ltppmin);
        root.put("cppMax",cppmax);
        root.put("cppMin",cppmin);
        root.put("paniclesesMax",paniclesmax);
        root.put("paniclesesMin",paniclesmin);
        root.put("rainfallMax",rainfallmax);
        root.put("rainfallMin",rainfallmin);
        return root.toString();

    }
}
