package com.rectangle.onlinehospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cidetailedreport")
public class CiDetailedReport {
    @TableId(value = "cidrId", type = IdType.AUTO)
    private String cidrID;

    @TableField("name")
    private String name;

    @TableField("unit")
    private String unit;

    @TableField("minrange")
    private String minRange;

    @TableField("maxrange")
    private String maxRange;

    @TableField("normalValue")
    private String normalValue;

    @TableField("normalValueString")
    private String normalValueString;

    @TableField("type")
    private String type;

    @TableField("value")
    private String value;

    @TableField("siError")
    private String siError;

    @TableField("ciId")
    private String ciID;

    @TableField("orderId")
    private String orderID;
}
