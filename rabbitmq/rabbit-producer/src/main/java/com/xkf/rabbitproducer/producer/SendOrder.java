/**
 * FileName: SendOrder
 * Author:   13235
 * Date:     2018/11/27 0:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.rabbitproducer.producer;

import com.xkf.rabbitproducer.entity.Order;
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
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public SendOrder(
            RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送订单
     *
     * @param order 订单
     * @throws Exception 异常
     */
    public void send(Order order) throws Exception {

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());

        // exchange：交换机
        // routingKey：路由键
        // message：消息体内容
        // correlationData：消息唯一ID
        this.rabbitTemplate.convertAndSend("order-exchange", "order.a", order, correlationData);
    }



}
