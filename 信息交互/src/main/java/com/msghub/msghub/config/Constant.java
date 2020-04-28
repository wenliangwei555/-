package com.msghub.msghub.config;

/**
 * @Author 温莨
 * @Date 2020/2/20 18:58
 * @Version 1.0
 */
public class Constant {

    private Constant() {
    }

    /**
     * 获取全国总数据
     */
    public static final String GET_ALL_URL = "http://www.dzyong.top:3005/yiqing/total";
    /**
     * 获取历史数去
     */
    public static final String GET_HISTORY_URL = "http://www.dzyong.top:3005/yiqing/history";
    /**
     * 获取各省/市最新总数据
     * 请求参数：
     * <p>
     * 参数名是否必填说明
     * <p>
     * province否当为空或不填时，返回所有省/市最新总数据，当传参时要注意，不要带'省'或'市'字，如：'重庆市'应填'重庆'
     */
    public static final String GET_PROVINCE_URL = "http://www.dzyong.top:3005/yiqing/province";
    /**
     * 获取各省/市/地区数据
     * 请求参数：
     * <p>
     * 参数名是否必填说明
     * <p>
     * area否当为空或不填时，返回所有省市地区数据，当传参时要注意，不要带'省'或'市'字，如：'重庆市'应填'重庆'
     */
    public static final String GET_REGION_URL = "http://www.dzyong.top:3005/yiqing/area";
    /**
     * 获取最新动态新闻
     * 请求参数：
     * <p>
     * 参数名是否必填说明
     * <p>
     * pageNum否页码未填时，替代查询所有新闻）
     * <p>
     * pageSize否每页新闻条数（未填时，交替查询所有新闻）
     */
    public static final String GET_JOURNALISM_URL = "http://www.dzyong.top:3005/yiqing/news";


}
