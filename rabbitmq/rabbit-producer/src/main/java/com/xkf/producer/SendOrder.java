/**
 * FileName: SendOrder
 * Author:   13235
 * Date:     2018/11/27 0:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.producer;

import com.xkf.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2018/11/27
 * @since 1.0.0
 */
@Component
public class SendOrder {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {
            System.out.println("correlationData:" + correlationData.toString());
            if (b) {
                System.out.println("确认成功！");
            }else{
                System.out.println("确认失败！");
            }
        }
    };


    /**
     * 发送订单
     *
     * @param order 订单
     * @throws Exception 异常
     */
    public void send(Order order) throws Exception {
        this.rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        // exchange：交换机
        // routingKey：路由键
        // message：消息体内容
        // correlationData：消息唯一ID
        this.rabbitTemplate.convertAndSend("order-exchange", "order.v", order, correlationData);
    }


}
