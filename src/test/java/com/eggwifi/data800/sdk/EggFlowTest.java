
package com.eggwifi.data800.sdk;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eggwifi.data800.sdk.entity.ProductResponse;

/**
 * 流量包接口单元测试类。
 * 
 * @author HuangYingNing
 */
public class EggFlowTest
{
    /**
     * 日志对象。
     */
    private static final Logger logger = LoggerFactory.getLogger(EggFlowTest.class);

    /**
     * 接口对象。
     */
    private static EggFlow g_client;

    /**
     * 初始化方法。
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        g_client = new EggFlow("http://api.test.data800.com", "test", "123456");
    }

    /**
     * 测试获取产品。
     */
    @Test
    public void testGetProductById()
    {
        ProductResponse res;

        res = g_client.getProduct("test");
        logger.debug("返回结果：{}", res);
    }

}
