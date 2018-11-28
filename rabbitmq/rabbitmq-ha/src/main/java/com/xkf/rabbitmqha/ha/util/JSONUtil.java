/**
 * FileName: JSONUtil
 * Author:   13235
 * Date:     2018/11/26 23:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.rabbitmqha.ha.util;


import com.alibaba.fastjson.JSON;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2018/11/26
 * @since 1.0.0
 */
public class JSONUtil {

    public static String toJSON(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T toObject(String values, Class<T> tClass) {
        return JSON.parseObject(values, tClass);
    }
}
