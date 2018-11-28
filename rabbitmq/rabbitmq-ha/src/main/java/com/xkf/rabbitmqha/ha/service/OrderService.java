/**
 * FileName: OrderService
 * Author:   13235
 * Date:     2018/11/28 12:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.rabbitmqha.ha.service;

import com.xkf.rabbitmqha.entity.Order;
import com.xkf.rabbitmqha.ha.constant.Constants;
import com.xkf.rabbitmqha.ha.dao.mapper.BrokerMessageLogMapper;
import com.xkf.rabbitmqha.ha.dao.mapper.OrderMapper;
import com.xkf.rabbitmqha.ha.dao.po.BrokerMessageLogPO;
import com.xkf.rabbitmqha.ha.producer.OrderSender;
import com.xkf.rabbitmqha.ha.util.JSONUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2018/11/28
 * @since 1.0.0
 */
@Service
public class OrderService {

  @Autowired
  private OrderMapper orderMapper;

  @Autowired
  private BrokerMessageLogMapper brokerMessageLogMapper;

  @Autowired
  private  OrderSender orderSender;

  public void create(Order order) {
     //当前时间
    Date date = new Date();
    //业务数据入库
    this.orderMapper.insert(order);
    //消息日志入库
    BrokerMessageLogPO messageLogPO = new BrokerMessageLogPO();
    messageLogPO.setMessageId(order.getMessageId());
    messageLogPO.setMessage(JSONUtil.toJSON(order));
    messageLogPO.setTryCount(0);
    messageLogPO.setStatus(Constants.OrderSendStatus.SENDING);
    messageLogPO.setNextRetry(DateUtils.addMinutes(date,Constants.ORDER_TIMEOUT));
    this.brokerMessageLogMapper.insert(messageLogPO);
    //发送消息
    this.orderSender.send(order);
  }
}
