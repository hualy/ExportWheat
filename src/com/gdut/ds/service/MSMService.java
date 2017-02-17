package com.gdut.ds.service;

import net.sf.json.JSONObject;

/**
 * Created by cjf on 2016/9/24.
 */
public interface MSMService {
    /*
    * 这是一个得到MSM（站点信息的方法)
     */
   public String getMSMInfor();

    /*
    * 这是一个得到详情具体的信息，
    * site 为站点名
    * option 1 代表的是光温生产潜力
    * option 2 代表的是气候生产潜力
    * option 3 代表的是生育期间的需水量
    * option 4 代表的是降水盈亏量
     */
    String getDetail(String site, int option);

    /*
    *这是一个得到具体的信息
    * 其中 site为站点名
    * startYear 为开始的年份
    * endYear 为结束的年份
     */

    JSONObject getDetail(String site, int startYear, int endYear);

    /*
    * 这是一个得到多个站点的分析信息的类
    * 其中sites 为要分析的类名，
    * startYear2 为要分析的开始年份
    * endYear2 为分析的结束年份
    *
     */

    String getDetail(String[] sites, int startYear2, int endYear2);

}
