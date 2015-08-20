
package com.eggwifi.data800.sdk.entity;

public class OrdersResponse
{
    /**
     * 接口返回状态。
     */
    public Status status;

    /**
     * 返回的数据。
     */
    public Data data;

    /**
     *  数据类。
     */
    public class Data
    {
        /**
         * 订单总数。
         */
        public int total;

        /**
         * 当前页号。
         */
        public int pageNo;

        /**
         * 每页记录数。
         */
        public int pageSize;

        /**
         * 订单数组。
         */
        public OrderData[] orders;
    }
}
