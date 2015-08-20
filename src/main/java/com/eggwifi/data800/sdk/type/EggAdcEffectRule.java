
package com.eggwifi.data800.sdk.type;

/**
 * 流量充值生效模式枚举类型。
 * 
 * @author HuangYingNing
 */
public enum EggAdcEffectRule
{
    /**
     * 立即生效模式。
     */
    IMMEDIATELY(0),
    /**
     * 下账期生效模式。
     */
    NEXT(1),
    UNKNOWN(-1);

    private int m_code;

    /**
     * 构造函数。
     * 
     * @param code
     */
    EggAdcEffectRule(int code)
    {
        m_code = code;
    }

    /**
     * 通过数值获取枚举对象。
     * 
     * @param code
     * @return
     */
    public static EggAdcEffectRule get(int code)
    {
        switch (code)
        {
        case 0:
            return IMMEDIATELY;
        case 1:
            return NEXT;
        default:
            break;
        }
        return UNKNOWN;
    }

    /**
     * 获取生效模式说明文字。
     * 
     * @return
     */
    public String getDesc()
    {
        switch (this)
        {
        case IMMEDIATELY:
            return "立即生效";
        case NEXT:
            return "下账期生效";
        case UNKNOWN:
            return "未知";
        default:
            break;
        }
        return null;
    }

    /**
     * 获取代码值。
     * 
     * @return
     */
    public int getCode()
    {
        return m_code;
    }

    /**
     * 转字符串。
     * 
     * @return
     */
    public String toString()
    {
        return String.valueOf(m_code);
    }
}
