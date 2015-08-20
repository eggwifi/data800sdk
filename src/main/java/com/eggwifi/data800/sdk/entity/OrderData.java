
package com.eggwifi.data800.sdk.entity;

/**
 * 接口数据中的订单数据实体类。
 * 
 * @author HuangYingNing
 */
public class OrderData
{
    /**
     * 订单ID。
     */
    public String orderId;

    /**
     * 订单状态。0：不符合处理条件；1：等待处理；2：处理中；3：处理成功；4：处理失败。
     */
    public int status;

    /**
     * 手机号码。
     */
    public String mobile;

    /**
     * 流量生效模式。<br/>
     * 0：立即生效；1：下账期生效。
     */
    public int effect;

    /**
     * 产品ID。
     */
    public String productId;

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
     * 下订单调用接口的客户端IP。
     */
    public String ip;

    /**
     * 下订单时间（格式：yyyy-MM-dd HH:mm:ss）。
     */
    public String orderTime;

    /**
     * 处理时间（格式：yyyy-MM-dd HH:mm:ss）。
     */
    public String procTime;

}
