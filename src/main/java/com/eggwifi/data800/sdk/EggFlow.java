
package com.eggwifi.data800.sdk;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.eggwifi.data800.sdk.entity.OrderRequest;
import com.eggwifi.data800.sdk.entity.OrderResponse;
import com.eggwifi.data800.sdk.entity.ProductResponse;
import com.eggwifi.data800.sdk.type.EggAdcEffectRule;
import com.hynnet.util.HttpClientUtils;

/**
 * 易哥流量接口处理类。
 * 
 * @author HuangYingNing
 * @author ZhaoXiaoHai
 */
public class EggFlow
{
    /**
     * 日志对象。
     */
    private static final Logger logger = LoggerFactory.getLogger(EggFlow.class);

    /**
     * 获取指定流量产品的API路径。
     */
    public static final String GET_PRODUCT_API = "/data800/api/adcproduct/";

    /**
     * 查找产品的API路径。
     */
    public static final String FIND_PRODUCT_API = "/data800/api/adcproducts/";

    /**
     * 购买产品的API路径。
     */
    public static final String BUY_API = "/data800/api/adcbuy";

    /**
     * 获取指定流量产品的API路径。
     */
    public static final String GET_ORDER_API = "/data800/api/adcorder/";

    /**
     * 查找订单的API路径。
     */
    public static final String FIND_ORDER_API = "/data800/api/adcorders/";

    /**
     * 接口地址。
     */
    private String m_url;

    /**
     * 应用接入ID。
     */
    private String m_appId;

    /**
     * 应用API密钥。
     */
    private String m_appSecret;

    /**
     * http请求对象。
     */
    private HttpClientUtils m_client;

    /**
     * 构造函数。
     * 
     * @param url 接口地址。
     * @param appId 应用接入ID。
     * @param appSecret 应用API密钥。
     */
    public EggFlow(String url, String appId, String appSecret)
    {
        if (url == null || (url = url.trim()).length() == 0)
        {
            throw new RuntimeException("没有提供接口地址");
        }
        // 去掉结尾的斜杠
        m_url = url.charAt(url.length() - 1) == '/'
                ? url.substring(0, url.length()) : url;
        m_client = new HttpClientUtils();
    }

    /**
     * 获取指定的产品信息。
     * 
     * @param productId 产品ID。
     * @return
     */
    public ProductResponse getProduct(String productId)
    {
        if (productId != null && productId.length() > 0)
        {
            StringBuffer sigBuf = new StringBuffer();
            StringBuffer url = new StringBuffer();
            String resp;

            sigBuf.append(productId).append(m_appSecret);
            url.append(m_url)
               .append(FIND_PRODUCT_API)
               .append(md5(sigBuf.toString()))
               .append('/')
               .append(m_appId)
               .append('/')
               .append(productId)
               .append("/-/-"); // 没有mobile和amount，用-代替空值
            resp = m_client.getData(url.toString());
            return JSON.parseObject(resp, ProductResponse.class);
        }
        else
        {
            logger.error("参数不正确，产品ID：{}", productId);
        }
        return null;
    }

    /**
     * 通过手机号和流量包大小获取指定的产品信息。
     * 
     * @param mobile 产品适用的手机号。
     * @param amount 流量产品的流量大小，单位为MB。
     * @return
     */
    public ProductResponse getProduct(String mobile, int amount)
    {
        if (mobile != null && mobile.length() > 0 && amount > 0)
        {
            StringBuffer sigBuf = new StringBuffer();
            StringBuffer url = new StringBuffer();
            String resp;

            sigBuf.append(mobile).append(amount).append(m_appSecret);
            url.append(m_url)
               .append(FIND_PRODUCT_API)
               .append(md5(sigBuf.toString()))
               .append('/')
               .append(m_appId)
               .append('/')
               .append('-') // 没有productId，用-代替空值
               .append('/')
               .append(mobile)
               .append('/')
               .append(amount);
            resp = m_client.getData(url.toString());
            return JSON.parseObject(resp, ProductResponse.class);
        }
        else
        {
            logger.error("参数不正确，手机号：{} 流量大小：{}", mobile, amount);
        }
        return null;
    }

    /**
     * 订购流量包。
     * 
     * @param productId 订购的产品ID（必须）。
     * @param mobile 充值的手机号（必须）。
     * @param effect 流量生效模式（必须）。
     * @param orderId 自定义订单ID（可选，不指定时输入null）。
     * @param callbackUrl 自定义回调地址（可选，不指定时输入null）。
     * @return
     */
    public OrderResponse buy(String productId, String mobile,
            EggAdcEffectRule effect, String orderId, String callbackUrl)
    {
        OrderRequest req = new OrderRequest();
        String json, resp;
        StringBuffer sigBuf = new StringBuffer();
        List<String[]> datas = new ArrayList<String[]>();

        req.appId = m_appId;
        req.effect = effect.getCode();
        req.mobile = mobile;
        req.orderId = orderId != null && orderId.length() > 0 ? orderId : null;
        sigBuf.append(req.productId)
              .append(req.mobile)
              .append(req.effect)
              .append(req.orderId != null ? req.orderId : "")
              .append(m_appSecret);
        req.sign = md5(sigBuf.toString());
        json = JSON.toJSONString(req);
        datas.add(new String[]
            { null, json }); // 直接POST数据
        resp = m_client.postData(m_url + BUY_API, datas);
        return JSON.parseObject(resp, OrderResponse.class);
    }

    /**
     * 计算MD5。
     * 
     * @param in
     * @return
     */
    private static String md5(String in)
    {
        try
        {
            MessageDigest digest;

            digest = MessageDigest.getInstance("MD5");
            digest.update(in.getBytes(Charset.forName("UTF-8")));
            return toHex(digest.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            logger.error("计算文件MD5异常：" + in, e);
            throw new RuntimeException(e);
        }
        catch (Exception e)
        {
            logger.error("计算文件MD5异常：" + in, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 字节数据转16进制字符串。
     * 
     * @param hash
     * @return
     */
    private static String toHex(byte hash[])
    {
        int len = hash.length;
        StringBuffer buf = new StringBuffer(len * 2);

        for (int n = 0; n < len; n++)
        {
            if (((int)hash[n] & 0xff) < 0x10)
            {
                buf.append("0");
            }
            buf.append(Long.toString((int)hash[n] & 0xff, 16));
        }
        return buf.toString();
    }

}
