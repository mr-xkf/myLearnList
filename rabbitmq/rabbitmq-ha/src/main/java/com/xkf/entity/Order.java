/**
 * FileName: Order
 * Author:   13235
 * Date:     2018/11/26 22:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2018/11/26
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class Order implements Serializable {

    private String id;
    private String name;
    /**
     * 存储消息的唯一标识符
     */
    private String messageId;

}
