package com.xkf.rabbitmqha;

import com.xkf.entity.Order;
import com.xkf.ha.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqHaApplicationTests {
    @Autowired
    private OrderService os;

    @Test
    public void send() {
        Order order = new Order();
        order.setId("201809062009010008");
        order.setName("测试订单1");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString().replaceAll("-",""));
        try {
            os.create(order);
        } catch (Exception e) {
            System.out.println("发送失败！"+e.getMessage());
        }

    }
}
