package com.rectangle.onlinehospital.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cireport")
public class CiReport {
    @TableId(value = "cirId", type = IdType.AUTO)
    private Integer cirID;

    @TableField("ciId")
    private Integer ciID;

    @TableField("ciName")
    private String ciName;

    @TableField("orderId")
    private Integer orderID;
}
