/**
 * FileName: OrderReceiver
 * Author:   13235
 * Date:     2018/11/26 22:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.rabbitconsumer.consumer;

import com.rabbitmq.client.Channel;
import com.xkf.rabbitconsumer.entity.Order;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2018/11/26
 * @since 1.0.0
 */
@Component
public class OrderReceiver {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "order-queue",
            durable = "true"), exchange = @Exchange(name = "order-exchange", type = "topic"),
            key = "order.*"))
    @RabbitHandler
    public void onOrderMessage(@Payload Order order, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        //消费者操作
        System.out.println("收到消息：");
        System.out.println("订单信息：" + order.toString());
        //手动签收消息
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

}
