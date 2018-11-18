package org.lpw.tephra.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author lpw
 */
public interface Json {
    /**
     * 添加数据。如果key已存在则自动添加为数组。
     *
     * @param json   JSON对象。
     * @param key    key值。
     * @param object 数据。
     */
    void add(JSONObject json, String key, Object object);

    /**
     * 添加为数组。
     *
     * @param json   JSON对象。
     * @param key    属性key。
     * @param object 要添加的对象。
     */
    void addAsArray(JSONObject json, String key, Object object);

    /**
     * 获取字符串数组。
     *
     * @param json JSON对象。
     * @param key  属性key。
     * @return 字符串数组。
     */
    String[] getAsStringArray(JSONObject json, String key);

    /**
     * 将XML字符串转化为JSON对象。
     *
     * @param xml XML字符串。
     * @return JSON对象；如果转化失败则返回null。
     */
    JSONObject fromXml(String xml);

    /**
     * 转化为JSON对象。
     *
     * @param object 要转化的对象。
     * @return JSON对象；转化失败则返回null。
     */
    JSONObject toObject(Object object);

    /**
     * 转化为JSON对象。
     * 如果nullable=true，则当转化失败时将返回null；
     * 如果nullable=false，则当转化失败时将新建一个JSON对象返回。
     *
     * @param object   要转化的对象。
     * @param nullable 是否允许为null。
     * @return JSON对象。
     */
    JSONObject toObject(Object object, boolean nullable);

    /**
     * 转化为JSON数组。
     *
     * @param object 要转化的对象。
     * @return JSON数组；转化失败则返回null。
     */
    JSONArray toArray(Object object);

    /**
     * 转化为JSON数组。
     * 如果nullable=true，则当转化失败时将返回null；
     * 如果nullable=false，则当转化失败时将新建一个JSON对象返回。
     *
     * @param object   要转化的对象。
     * @param nullable 是否允许为null。
     * @return JSON数组；转化失败则返回null。
     */
    JSONArray toArray(Object object, boolean nullable);

    /**
     * 将JSON数据转化为JSON字符串。
     *
     * @param object JSON数据。
     * @return JSON字符串。
     */
    String toString(Object object);

    /**
     * 将JSON数据转化为JSON字节流。
     *
     * @param object JSON数据。
     * @return JSON字节流。
     */
    byte[] toBytes(Object object);

    /**
     * 转化为Map对象。
     *
     * @param object JSON数据。
     * @return Map对象。
     */
    Map<String, String> toMap(JSONObject object);

    /**
     * 递归查找对象。
     *
     * @param object 根对象。
     * @param keys   递归key集。
     * @return 对象；未找到则返回null。
     */
    JSONObject findObject(JSONObject object, String... keys);

    /**
     * 是否包含key。
     *
     * @param object JSON对象。
     * @param key    目标key。
     * @return 如果包含则返回true；否则返回false。
     */
    boolean containsKey(JSONObject object, String key);

    /**
     * 验证是否包含值为true的元素。
     *
     * @param object JSON数据。
     * @param key    属性KEY。
     * @return 如果包含则返回true；否则返回false。
     */
    boolean hasTrue(JSONObject object, String key);

    /**
     * 验证是否包含指定值的元素。
     *
     * @param object JSON数据。
     * @param key    属性KEY。
     * @param value  值。
     * @return 如果包含且值相等则返回true；否则返回false。
     */
    boolean has(JSONObject object, String key, String value);

    /**
     * 复制。
     *
     * @param object 源JSON对象。
     * @return 新JSON对象。
     */
    JSONObject copy(JSONObject object);
}
