package com.rectangle.onlinehospital.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("overallresult")
public class OverallResult {
    @TableId(value = "orId", type = IdType.AUTO)
    private Integer orID;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("orderId")
    private Integer orderID;
}
