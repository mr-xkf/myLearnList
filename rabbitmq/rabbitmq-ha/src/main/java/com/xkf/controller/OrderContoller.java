/**
 * FileName: OrderContoller
 * Author:   13235
 * Date:     2018/12/1 9:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.controller;

import com.xkf.entity.Order;
import com.xkf.ha.producer.OrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2018/12/1
 * @since 1.0.0
 */
@RestController
@RequestMapping("/order/ha")
public class OrderContoller {

    @Autowired
    private OrderSender orderSender;


    @GetMapping("/send")
    public String send() {
        Order order = new Order();
        order.setId("201809062009010002");
        order.setName("测试订单1");
        order.setMessageId(System.currentTimeMillis() + "$" +
                UUID.randomUUID().toString().replaceAll("-", ""));
        orderSender.send(order);
        return "发送成功！";
    }

}
