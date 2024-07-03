package com.rectangle.onlinehospital.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("orders")
public class Order {
    @TableId(value = "orderId", type = IdType.AUTO)
    @TableField(insertStrategy = FieldStrategy.NEVER)
    private Integer orderID;

    @TableField("orderDate")
    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    private LocalDate orderDate;

    @TableField("userId")
    private String userID;

    @TableField("hpId")
    private Integer hpID;

    @TableField("smId")
    private Integer smID;

    @TableField("state")
    private Integer state;
}
