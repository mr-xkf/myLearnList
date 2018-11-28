/**
 * FileName: BrokerMessageLogMapper
 * Author:   13235
 * Date:     2018/11/28 12:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.rabbitmqha.ha.dao.mapper;

import com.xkf.rabbitmqha.ha.dao.po.BrokerMessageLogPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 13235
 * @create 2018/11/28
 * @since 1.0.0
 */
@Repository
public interface BrokerMessageLogMapper {
    /**
     *
     * 创建消息日志
     *
     * @param brokerMessageLogPO
     */
    void insert(BrokerMessageLogPO brokerMessageLogPO);

    /**
     * 更新消息状态
     * @param brokerMessageLogPO
     */
    void changeBrokerMessageLogStatus(BrokerMessageLogPO brokerMessageLogPO);


    /**
     * 查询消息状态为0 且 已经超时的消息
     * @return 消息日志集合
     */
    List<BrokerMessageLogPO> listSendFailureAndTimeoutMessage();

    /**
     * 更新重试次数+1
     *
     * @param brokerMessageLogPO
     *
     */
    void updateRetryCount(BrokerMessageLogPO brokerMessageLogPO);
}
