/**
 * FileName: Constants
 * Author:   13235
 * Date:     2018/11/26 23:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.rabbitmqha.ha.constant;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2018/11/26
 * @since 1.0.0
 */
public class Constants {
    /**
     * 订单投递状态
     */
    public class OrderSendStatus {

        /**
         * 投递中
         */
        public static final String SENDING = "0";
        /**
         * 投递成功
         */
        public static final String SEND_SUCCESS = "1";
        /**
         * 投递失败
         */
        public static final String SEND_FAILURE = "2";


    }

    /**
     * 消息超时时间(单位：分钟)
     */
    public static final int ORDER_TIMEOUT = 1;

    /**
     * 最大重试次数
     */
    public static final int MAX_RETRY_COUNT = 3;
}
