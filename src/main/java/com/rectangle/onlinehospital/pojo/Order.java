package com.rectangle.onlinehospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("orders")
public class Order {
    @TableId(value = "orderId", type = IdType.AUTO)
    private Integer orderID;

    @TableField("orderDate")
    private Date orderDate;

    @TableField("userId")
    private String userID;

    @TableField("hpId")
    private Integer hpID;

    @TableField("smId")
    private Integer smID;

    @TableField("state")
    private Integer state;
}
