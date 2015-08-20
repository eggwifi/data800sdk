
package com.eggwifi.data800.sdk.entity;

/**
 * 接口订单请求实体类。
 * 
 * @author HuangYingNing
 */
public class OrderRequest
{
    /**
     * 签名数据。
     */
    public String sign;

    /**
     * 应用接入ID。
     */
    public String appId;

    /**
     * 产品ID。
     */
    public String productId;

    /**
     * 手机号。
     */
    public String mobile;

    /**
     * 生效模式。
     */
    public int effect;

    /**
     * 自定义订单ID。
     */
    public String orderId;
}
