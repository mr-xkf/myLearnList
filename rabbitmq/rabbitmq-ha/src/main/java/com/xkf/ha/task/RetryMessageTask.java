/**
 * FileName: RetryMessageTask
 * Author:   13235
 * Date:     2018/11/28 13:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.ha.task;

import com.xkf.entity.Order;
import com.xkf.ha.constant.Constants;
import com.xkf.ha.dao.mapper.BrokerMessageLogMapper;
import com.xkf.ha.dao.po.BrokerMessageLogPO;
import com.xkf.ha.producer.OrderSender;
import com.xkf.ha.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2018/11/28
 * @since 1.0.0
 */
@Component
@Slf4j
public class RetryMessageTask {

    @Autowired
    private OrderSender orderSender;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    /**
     * 启动完成3秒后开始执行,
     * 每隔10秒执行一次
     */
    @Scheduled(initialDelay = 3000, fixedDelay = 10000)
    public void retrySend() {
        log.debug("重发消息定时任务开始。。");
        List<BrokerMessageLogPO> list = this.brokerMessageLogMapper.listSendFailureAndTimeoutMessage();
        for (BrokerMessageLogPO messageLogPO : list) {
            log.debug("处理消息日志:{}", messageLogPO);
            if (messageLogPO.getTryCount() >= Constants.MAX_RETRY_COUNT) {
                //更新状态为失败
                BrokerMessageLogPO logPO = new BrokerMessageLogPO();
                logPO.setMessageId(messageLogPO.getMessageId());
                logPO.setStatus(Constants.OrderSendStatus.SEND_FAILURE);
                this.brokerMessageLogMapper.changeBrokerMessageLogStatus(logPO);
            } else {
                //进行重试，重试次数+1
                this.brokerMessageLogMapper.updateRetryCount(messageLogPO);
                Order order = JSONUtil.toObject(messageLogPO.getMessage(), Order.class);
                this.orderSender.send(order);

            }
            log.debug("重发消息定时任务结束。。。");
        }
    }


}
