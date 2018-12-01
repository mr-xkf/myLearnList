package com.xkf.rabbitproducer;

import com.xkf.entity.Order;
import com.xkf.producer.SendOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitProducerApplicationTests {
	@Autowired
	private SendOrder sendOrder;

	@Test
	public void contextLoads() {
		Order order = new Order();
		order.setId("201809062009010007");
		order.setName("测试订单1");
		order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString().replaceAll("-",""));
		try {
			sendOrder.send(order);
		} catch (Exception e) {
			System.out.println("发送失败！"+e.getMessage());
		}
	}

}
