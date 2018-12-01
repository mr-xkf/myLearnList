/**
 * FileName: OrderSender
 * Author:   13235
 * Date:     2018/11/26 23:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.ha.producer;

import com.xkf.entity.Order;
import com.xkf.ha.constant.Constants;
import com.xkf.ha.dao.mapper.BrokerMessageLogMapper;
import com.xkf.ha.dao.po.BrokerMessageLogPO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2018/11/26
 * @since 1.0.0
 */
@Component
public class OrderSender {

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {

        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {
            System.out.println("correlationData:" + correlationData);
            String messageId = correlationData.getId();
            if (b) {
                System.out.println("消息确认成功！");
                BrokerMessageLogPO messageLogPO = new BrokerMessageLogPO();
                messageLogPO.setMessageId(messageId);
                messageLogPO.setStatus(Constants.OrderSendStatus.SEND_SUCCESS);
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageLogPO);
            } else {
                System.out.println("消息确认失败！");
            }
        }
    };

    /**
     * 发送订单
     */
    public void send(Order order) {
        //设置回调方法
        this.rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        this.rabbitTemplate.convertAndSend("order-exchange", "order.b", order, correlationData);
    }
}
