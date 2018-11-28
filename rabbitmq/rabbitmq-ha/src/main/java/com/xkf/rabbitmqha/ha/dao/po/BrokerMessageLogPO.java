/**
 * FileName: BrokerMessageLogPO
 * Author:   13235
 * Date:     2018/11/28 11:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.rabbitmqha.ha.dao.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2018/11/28
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrokerMessageLogPO implements Serializable {

    /**
     * 消息ID
     */
    private String messageId;

    /***
     *
     * 消息内容
     *
     */
    private String message;

    /**
     * 重试次数
     */
    private Integer tryCount;

    /**
     * 投递状态
     */
    private String status;

    /**
     * 下次重试时间
     */
    private Date nextRetry;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
