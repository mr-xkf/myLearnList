package com.xkf.rabbitproducer;

import com.xkf.rabbitproducer.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitProducerApplicationTests {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void contextLoads() {
		Order order = new Order();
		order.setId("201809062009010001");
		order.setName("测试订单1");
		order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString().replaceAll("-",""));
		CorrelationData correlationData = new CorrelationData();
		correlationData.setId(order.getMessageId());

		// exchange：交换机
		// routingKey：路由键
		// message：消息体内容
		// correlationData：消息唯一ID
		this.rabbitTemplate.convertAndSend("order-exchange", "order.a", order, correlationData);
	}

}
