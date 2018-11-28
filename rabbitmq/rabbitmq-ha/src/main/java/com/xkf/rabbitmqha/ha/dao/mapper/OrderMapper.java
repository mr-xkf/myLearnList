/**
 * FileName: OrderMapper
 * Author:   13235
 * Date:     2018/11/28 11:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.rabbitmqha.ha.dao.mapper;

import com.xkf.rabbitmqha.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2018/11/28
 * @since 1.0.0
 */
public interface OrderMapper {

    /**
     * 新增订单
     *
     * @param order
     */
    void insert(Order order);

}
