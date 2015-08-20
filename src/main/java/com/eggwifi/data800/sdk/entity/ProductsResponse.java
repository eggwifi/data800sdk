
package com.eggwifi.data800.sdk.entity;

/**
 * 接口流量包产品结果实体类。
 * 
 * @author HuangYingNing
 */
public class ProductsResponse
{
    /**
     * 接口返回状态。
     */
    public Status status;

    /**
     * 产品数组。
     */
    public Data data;

    /**
     * 产品数组类。
     */
    public class Data
    {

        /**
         * 产品数据。
         */
        public ProductData[] products;

    }
}
