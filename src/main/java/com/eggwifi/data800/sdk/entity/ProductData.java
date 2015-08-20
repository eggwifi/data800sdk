
package com.eggwifi.data800.sdk.entity;

/**
 * 接口数据中的订单数据实体类。
 * 
 * @author HuangYingNing
 */
public class ProductData
{
    /**
     * 产品ID。
     */
    public String productId;

    /**
     * 产品名称。
     */
    public String name;

    /**
     * 充流量的MB数量。
     */
    public int amount;

    /**
     * 运营商。<br/>
     * 中国移动：CM 中国联通：CU 中国电信：CT
     */
    public String operator;

    /**
     * 标准价格（单位为分）。
     */
    public int price;

    /**
     * 可销售地区（适用该地区的手机号）。
     */
    public String[] area;

}
